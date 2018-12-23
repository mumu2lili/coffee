package com.piggy.coffee.designpattern.singleton;

/**
 * 枚举
 * 
 * @author mumu
 *
 */
public class EnumSingleton {

	private EnumSingleton() {

	}

	private static enum SingletonHolder {
		HOLDER;

		private EnumSingleton singleton;

		private SingletonHolder() {// 枚举类的构造方法在类加载是被实例化
			singleton = new EnumSingleton();
		}

	}

	public static EnumSingleton getInstance() {
		return SingletonHolder.HOLDER.singleton;
	}

}
