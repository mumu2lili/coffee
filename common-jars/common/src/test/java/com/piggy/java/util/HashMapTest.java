package com.piggy.java.util;

import java.util.HashMap;

import org.junit.Test;

public class HashMapTest {

	@Test
	public void testCapacity() {

		HashMap<String, String> map = new HashMap<>(3);
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");

	}

	@Test
	public void testCapacity2() {

		HashMap<String, String> map = new HashMap<>(2);
		map.put("1", "1");
		map.put("2", "2"); // 将被 resize

	}

}
