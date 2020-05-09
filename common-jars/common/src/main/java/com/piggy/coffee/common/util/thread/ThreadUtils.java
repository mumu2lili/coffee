package com.piggy.coffee.common.util.thread;

public class ThreadUtils {

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {
			// ingore quitely
		}
	}
}
