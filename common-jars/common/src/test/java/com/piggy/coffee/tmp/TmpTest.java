package com.piggy.coffee.tmp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TmpTest {
	@Test
	public void test() {
		LocalDateTime startTime = LocalDateTime.parse("2019-07-29T17:56:00");
		LocalDateTime endTime = LocalDateTime.parse("2019-07-29T17:55:01");

		long secends = ChronoUnit.SECONDS.between(startTime, endTime);
		System.out.println(secends);
		
		System.out.println(Integer.MAX_VALUE);

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

	@Test
	public void test2() {
		PodMgr podMgr = new PodMgr();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add("pod" + i);
		}

		for (int i = 0; i < 101; i++) {
			final int k = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						String pod = list.get(j);
						if (podMgr.put(pod)) {
							System.out.println("thread" + k + " 获取到" + pod);
							return;
						}
					}
					System.out.println("thread" + k + " 获取不到！！！！！！！！！！！");
				}
			}).start();

		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("podMgr size " + podMgr.size());
	}

}
