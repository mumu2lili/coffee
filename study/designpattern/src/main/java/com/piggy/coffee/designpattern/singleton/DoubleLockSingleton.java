package com.piggy.coffee.designpattern.singleton;

/**
 * 双重锁定
 * 
 * @author mumu
 *
 */
public class DoubleLockSingleton {

	/**
	 * volatile内存屏障，禁止指令重排序
	 */
	private static volatile DoubleLockSingleton singleton;

	private DoubleLockSingleton() {

	}

	public static DoubleLockSingleton getInstance() {
		if (null == singleton) {
			synchronized (DoubleLockSingleton.class) {
				if (null == singleton) {
					DoubleLockSingleton instance = new DoubleLockSingleton();
					singleton = instance;
				}
			}
		}

		return singleton;
	}
}
