package com.piggy.coffee.tmp;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

public class TmpTest {

	@Test
	public void test() {
		Assert.assertEquals(false, false);

		Map<String, String> map = new TreeMap<>();
		map.put("3", null);
		map.put("9", null);
		map.put("2", null);
		map.put("5", null);
		map.put("1", null);

		for (String str : map.keySet()) {
			System.out.println(str);
		}
	}

}
