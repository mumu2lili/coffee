package com.piggy.java.lang;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void test() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				throw new RuntimeException();

			}
		}).start();

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("I'm running...");
		}
	}

}
