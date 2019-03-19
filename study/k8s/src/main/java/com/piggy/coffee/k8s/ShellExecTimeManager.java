package com.piggy.coffee.k8s;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.client.dsl.ExecWatch;

/**
 * shell 执行时间管理
 */
public class ShellExecTimeManager {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final ConcurrentHashMap<Long, ExecTime> execTimeMap = new ConcurrentHashMap<>();

	private final AtomicLong key = new AtomicLong(1);

	private static class ExecTime {
		private ExecWatch execWatch;
		private Long overtime;

		public ExecTime(ExecWatch execWatch, Long overtime) {
			super();
			this.execWatch = execWatch;
			this.overtime = overtime;
		}

	}

	/**
	 * 加入执行时间
	 *
	 * @param execWatch
	 * @param delayTime
	 *            单位秒
	 */
	public Long putExecTime(ExecWatch execWatch, long delayTime) {
		long overtime = System.currentTimeMillis() + delayTime * 1000;
		ExecTime execTime = new ExecTime(execWatch, overtime);
		Long k = key.incrementAndGet();
		execTimeMap.put(k, execTime);
		return k;
	}

	@PostConstruct
	public void init() {
		new Thread(() -> {
			this.doWork();
		}).start();
	}

	private void doWork() {
		AtomicBoolean hasDel = new AtomicBoolean(false);

		while (true) {
			hasDel.set(false);

			execTimeMap.forEach((execKey, execTime) -> {
				if (execTime.overtime < System.currentTimeMillis()) {
					hasDel.set(true);

					try {
						overtime(execKey, execTime);
					} catch (Exception e) {
						logger.error("delete execTime error", e);
					}
				}
			});

			if (!hasDel.get()) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// ignore quietly
				}
			}

		}
	}

	/**
	 * 超时处理
	 *
	 * @param execKey
	 * @param execTime
	 * @throws Exception
	 */
	private void overtime(Long execKey, ExecTime execTime) throws Exception {

		execTime.execWatch.close();
		execTimeMap.remove(execKey);
	}

	/**
	 * 已经完成，删除执行时间
	 */
	public void removeExecTime(Long execKey) {
		execTimeMap.remove(execKey);

	}
}
