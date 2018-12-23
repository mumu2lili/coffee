package com.piggy.coffee.tmp;

import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {

		String test = "/aa/callback";
		System.out.println(test.matches("/[(a-zA-Z0-9_)]+/callback"));

		test = "aa/callback";
		System.out.println(test.matches("/[(a-zA-Z0-9_)]+/callback"));

		test = "/a/aa/callback";
		System.out.println(test.matches("/[(a-zA-Z0-9_)]+/callback"));

		test = "/aa/callback2";
		System.out.println(test.matches("/[(a-zA-Z0-9_)]+/callback"));
	}

	@Test
	public void testBinary() {

		Integer i = 17;
		System.out.println(Integer.toBinaryString(i));

		i = 16;
		System.out.println(Integer.toBinaryString(i));

		i = 37;
		System.out.println(Integer.toBinaryString(i));

		i = 36;
		System.out.println(Integer.toBinaryString(i));

	}

}
