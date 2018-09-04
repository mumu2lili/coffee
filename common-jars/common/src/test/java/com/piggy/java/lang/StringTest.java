package com.piggy.java.lang;

import org.junit.Test;

public class StringTest {

	@Test
	public void test() {
		String s = new StringBuilder().append("a").toString();
		System.out.println(s.intern() == s);

		s = new StringBuilder().append("a").append("b").toString();
		System.out.println(s.intern() == s);
		
		s = new StringBuilder().append("ja").append("va").toString();
		System.out.println(s.intern() == s);
	}

}
