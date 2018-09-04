package com.piggy.java.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class RandomTest {

	@Test
	public void test() {

		long seed = System.currentTimeMillis() + 79043824992L;
		while (true) {
			seed += System.currentTimeMillis();
			Random rand = new Random();
			rand.setSeed(seed);
			long a = rand.nextLong();
			long b = a % 1000000;

			seed += b;
			if (b < 100000) {
				continue;
			}

			Set<String> set = new HashSet<>();
			String str = String.valueOf(b);
			for (int i = 0; i < str.length(); i++) {
				set.add("" + str.charAt(i));
			}
			if (set.size() < 6 || set.contains("4")) {
				seed += 6;
				continue;
			}
			List<String> list1 = Arrays.asList("0", "1", "2");
			List<String> list2 = Arrays.asList("3", "5", "6");

			set.removeAll(list1);
			if (set.size() != 4) {
				seed += 4;
				continue;
			}
			set.removeAll(list2);
			if (set.size() != 2) {
				seed += 2;
				continue;
			}

			System.out.println(b);
		}
	}

}
