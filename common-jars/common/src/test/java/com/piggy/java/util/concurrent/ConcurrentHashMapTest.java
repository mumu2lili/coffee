package com.piggy.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.junit.Test;

public class ConcurrentHashMapTest {
	private Logger logger = Logger.getLogger(getClass().getName());

	@Test
	public void test() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					for (int i = 0; i < 10; i++) {
						int a = new Random().nextInt(100);
						map.put("s" + a, Long.valueOf(a));
						logger.info("put " + "s" + a);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					map.forEach((podName, delTime) -> {
						logger.info("remove " + podName);
						map.remove(podName);

					});
				}

			}
		}).start();

		while (true) {

		}

	}

	@Test
	public void test2() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("s1", 1L);
		map.put("s2", 1L);
		map.put("s3", 1L);
		map.put("s4", 1L);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					for (int i = 0; i < 10; i++) {
						int a = new Random().nextInt(100);
						map.put("s" + a, Long.valueOf(a));
						logger.info("put " + "s" + a);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					map.forEach((podName, delTime) -> {
						logger.info("remove " + podName);
						map.remove(podName);

					});
				}

			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					for (int i = 0; i < 10; i++) {

						map.remove("s" + i);
						logger.info("remove " + "s" + i);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {

					}
				}

			}
		}).start();

		while (true) {

		}

	}

}
