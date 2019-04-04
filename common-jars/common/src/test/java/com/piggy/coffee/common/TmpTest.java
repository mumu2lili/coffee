package com.piggy.coffee.common;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
		boolean r = Boolean.TRUE.equals(null) || Boolean.FALSE.equals(null);
		Assert.assertEquals(false, r);
	}

	@Test
	public void test3() {
		String s = "https://www.educoder.net/tasks/kseut8phyxfl";
		boolean r = Boolean.TRUE.equals(null) || Boolean.FALSE.equals(null);
		Assert.assertEquals(false, r);
	}

	@Test
	public void test2() throws InterruptedException {
		System.out.println(Integer.MAX_VALUE);
		AtomicInteger i = new AtomicInteger();
		while (true) {
			System.out.println(i.incrementAndGet());
			Thread.sleep(1000);
		}
	}

}
