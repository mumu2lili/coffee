package com.piggy.java.lang;

import org.junit.Assert;
import org.junit.Test;

public class IntegerTest {

	@Test
	public void test() {
		Integer i = null;
		boolean r = (i == 1);
		Assert.assertEquals(false, r);

	}

}
