package com.piggy.java.lang;

import org.junit.Test;

public class ThreadLocalTest {

	@Test
	public void test() {
		System.out.println(0x61c88647);
		int i = 0x61c88647;
		System.out.println(Integer.toBinaryString(i));
		
		
		System.out.println(Integer.toBinaryString(16 - 1));
	}

}
