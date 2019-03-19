package com.piggy.coffee.tmp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LamdaTest {

	@Test
	public void test() {

		List<String> proNames = Arrays.asList(new String[] { "Ni", "Hao", "Lambda" });
		List<String> lowercaseNames1 = proNames.stream().map(name -> {
			return name.toLowerCase();
		}).collect(Collectors.toList());

		System.out.println(lowercaseNames1);
	}

	@Test
	public void testMethodRef() {
		List<String> proNames = Arrays.asList(new String[] { "Ni", "Hao", "Lambda" });

		List<String> lowercaseNames3 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());

		System.out.println(lowercaseNames3);
	}

	@Test
	public void testVar() {
		String waibu = "lambda :";
		List<String> proStrs = Arrays.asList(new String[] { "Ni", "Hao", "Lambda" });
		List<String> execStrs = proStrs.stream().map(chuandi -> {
			Long zidingyi = System.currentTimeMillis();
			return waibu + chuandi.toLowerCase() + " -----:" + zidingyi;
		}).collect(Collectors.toList());
		execStrs.forEach(System.out::println);
	}

}
