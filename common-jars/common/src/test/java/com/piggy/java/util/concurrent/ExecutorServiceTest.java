package com.piggy.java.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecutorServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// git pull
		Future<Boolean> pull = executor.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				try {
					throw new RuntimeException("pull ex");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return false;
				}
			}
		});

		// 创建pod
		final StringBuilder podRef = new StringBuilder();

		Future<String> createPod = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				throw new RuntimeException("create pod ex");

			}
		});

		try {
			Boolean r = pull.get();
			if (!r) {
				logger.info("下载代码失败");
				// return;
			}
			createPod.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			logger.error(e.getMessage(), e);
		} finally {
			executor.shutdown();
		}
		/*
		 * try { pull.get(); createPod.get(); } catch (InterruptedException e) {
		 * e.printStackTrace(); } catch (ExecutionException e) {
		 * logger.error(e.getMessage(), e); } finally { executor.shutdown(); }
		 */
		// 通过标签获取pod名
		String podName = podRef.toString();
		logger.info("podName [{}]", podName);
		while (!executor.isTerminated()) {

		}

	}

}
