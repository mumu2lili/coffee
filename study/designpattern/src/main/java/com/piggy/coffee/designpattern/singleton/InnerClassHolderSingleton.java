package com.piggy.coffee.designpattern.singleton;

/**
 * 不可序列化
 * 
 * @author mumu
 *
 */
public class InnerClassHolderSingleton {

	private InnerClassHolderSingleton() {

	}

	private static class SingletonHolder {
		// 由虚拟机保证类初始化的线程安全性
		private static InnerClassHolderSingleton singleton = new InnerClassHolderSingleton();
	}

	public static InnerClassHolderSingleton getInstance() {

		return SingletonHolder.singleton;
	}
}
