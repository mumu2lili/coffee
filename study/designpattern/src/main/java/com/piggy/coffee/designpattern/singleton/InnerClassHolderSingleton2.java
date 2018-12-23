package com.piggy.coffee.designpattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 实际中不要序列化单例！！！
 * 
 * @author mumu
 *
 */
public class InnerClassHolderSingleton2 implements Serializable {

	private static final long serialVersionUID = -1466477226294922501L;

	private InnerClassHolderSingleton2() {

	}

	private static class SingletonHolder {
		// 由虚拟机保证类初始化的线程安全性
		private static InnerClassHolderSingleton2 singleton = new InnerClassHolderSingleton2();
	}

	public static InnerClassHolderSingleton2 getInstance() {

		return SingletonHolder.singleton;
	}

	// 该方法在反序列化时会被调用，该方法不是接口定义的方法，有点儿约定俗成的感觉
	protected Object readResolve() throws ObjectStreamException {
		System.out.println("调用了readResolve方法！");
		return SingletonHolder.singleton;
	}
}
