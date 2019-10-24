package com.piggy.coffee.tmp;

import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TmpTest {
	@Test
	public void test() {
		String tmp = "10-15 00:00:38 [8gvslq9xojy6] [threadPoolTaskExecutor-75] INFO  -- educoderURL: https://www.educoder.net/api/myshixuns/training_task_status.json?t=1571068838329, result: {\"jsonTestDetails\":\"{\\\"buildID\\\":\\\"2509174\\\",\\\"compileSuccess\\\":\\\"1\\\",\\\"createPodStatus\\\":\\\"1\\\",\\\"downloadStatus\\\":\\\"1\\\",\\\"msg\\\":[{\\\"caseId\\\":\\\"1\\\",\\\"output\\\":\\\"5Luj56CB6K-E5rWL6LaF5pe277yB5pys5YWz6ZmQ5a6a5pe26Ze05Li677yaMjBz77yM6K-35qOA5p-l5Luj56CB5piv5ZCm5a2Y5Zyo5q275b6q546v5oiW5YW25LuW6ICX5pe25pON5L2c\\\",\\\"passed\\\":\\\"0\\\"}],\\\"outPut\\\":\\\"5YWx5pyJMee7hOa1i-ivlembhu-8jOWFtuS4reaciTHnu4TmtYvor5Xnu5PmnpzkuI3ljLnphY3jgILor6bmg4XlpoLkuIvvvJo\\\",\\\"resubmit\\\":\\\"\\\",\\\"sec_key\\\":\\\"8gvslq9xojy6\\\",\\\"status\\\":\\\"-1\\\",\\\"tpiID\\\":\\\"661634\\\"}\",\"timeCost\":\"{\\\"evaluateEnd\\\":\\\"2019-10-15T00:00:38.329\\\",\\\"pull\\\":\\\"0.219\\\",\\\"createPod\\\":\\\"2.035\\\",\\\"evaluateAllTime\\\":\\\"22.103\\\",\\\"execute\\\":\\\"20.007\\\",\\\"evaluateStartTime\\\":\\\"2019-10-15T00:00:16.226\\\"}\",\"tpiRepoPath\":\"workspace/myshixun_661634/gn6x2uj3rw20191012105555\"}";
		int begin = tmp.indexOf("evaluateEnd\\\":\\\"") + "evaluateEnd\\\":\\\"".length();

		System.out.println(begin);

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
	
	
	@Test
	public void testTime() {
		LocalDateTime time = LocalDateTime.now();
		String s = cvtTime(time);
		System.out.println(s);
	}
	
	private String cvtTime(LocalDateTime time) {
		time = time.minusHours(8);
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(time);
	}

}
