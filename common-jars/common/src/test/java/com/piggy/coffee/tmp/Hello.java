package com.piggy.coffee.tmp;

public class Hello {
	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		int e = 5;
		int sum = (a + b + c + d + e);
		int avg = sum / 5;
		System.out.println(avg);
		
		String s = "hello";
		System.out.println(s);
		
		s = "\\hello";
		System.out.println(s);
		
		s = "\"hello";
		System.out.println(s);
		
		s = "\\h\"ello//";
		System.out.println(s);
		

		s = "\\\'hello";
		System.out.println(s);
	}
}
