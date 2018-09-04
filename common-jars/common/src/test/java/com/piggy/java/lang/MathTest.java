package com.piggy.java.lang;

import org.junit.Assert;
import org.junit.Test;

public class MathTest {
	

	@Test
	public void test() {
		long expected = 256;
		long r = (long) Math.pow(2, 8);
		Assert.assertEquals(expected, r);

		r = 1 << 8;
		Assert.assertEquals(expected, r);

	}

	@Test
	public void test2() {
		long expected = 256;
		long r = 1 << 8;
		Assert.assertEquals(expected, r);

		expected = 65536;
		r = 1 << 16;
		Assert.assertEquals(expected, r);

		expected = 4294967296L;
		r = 1L << 32;
		Assert.assertEquals(expected, r);

	}

	@Test
	public void test_1024() {
		long expected = 1024;
		long r = (long) Math.pow(2, 10);
		Assert.assertEquals(expected, r);

		r = 1 << 10;
		Assert.assertEquals(expected, r);

	}

	@Test
	public void test_0x() {
		long expected = 0x0021;
		long r = 0x0001 | 0x0020;
		Assert.assertEquals(expected, r);

		System.out.println(Long.toBinaryString(expected));

	}

}
