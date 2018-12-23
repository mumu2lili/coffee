package com.piggy.coffee.syntax.innerclass;

/**
 * 内部类拥有外部类的引用。 <br />
 * 内部类拥有外部类局部变量的引用，或者原始类型的副本。
 * 
 * jdk8, 默认变量为 final
 * 
 * @author mumu
 *
 */
public class Outer {
	private String name = "outer";

	public static Object exe() {
		Outer out = new Outer();
		// 放开下面的再次赋值语句， out不是 final，将报错
		// out = new Outer();

		Object obj = new Object() {
			@Override
			public String toString() {
				return out.name;
			}
		};

		return obj;
	}

	public static Object exe2() {
		int i = 2;
		// 放开下面的再次赋值语句，i不是 final，将报错
		// i = 3;

		Object obj = new Object() {
			@Override
			public String toString() {
				return i + "";
			}
		};

		return obj;
	}
}
