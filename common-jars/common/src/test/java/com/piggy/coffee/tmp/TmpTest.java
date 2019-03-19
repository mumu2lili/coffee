package com.piggy.coffee.tmp;

import org.junit.Assert;
import org.junit.Test;

public class TmpTest {
	@Test
	public void test() {
		double executeCost = Double.parseDouble("");
		System.out.println(executeCost);
	}

	@Test
	public void testReg() {

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

	@Test
	public void testFlag() {
		Integer i = 0b00000000;
		System.out.println(i + "->" + Integer.toBinaryString(i));

		i |= 0b00000001;
		System.out.println(i + "->" + Integer.toBinaryString(i));

		i |= 0b00000010;
		System.out.println(i + "->" + Integer.toBinaryString(i));

		i |= 0b00000100;
		System.out.println(i + "->" + Integer.toBinaryString(i));

		i |= 0b00001000;
		System.out.println(i + "->" + Integer.toBinaryString(i));

		System.out.println(0b10);

	}

	@Test
	public void testImage() {
		String base64 = "data:image/jpeg;base64,aaa";
		String reg = "data:image/.*;base64,";
		String r = base64.replaceFirst(reg, "");

		String expected = "aaa";
		Assert.assertEquals(expected, r);

		base64 = "data:image/png;base64,aaa";
		r = base64.replaceFirst(reg, "");
		Assert.assertEquals(expected, r);

	}

}
