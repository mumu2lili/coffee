package com.piggy.coffee.common.util.io;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.piggy.coffee.common.util.io.FileUtils;

public class FileUtilsTest {

	@Test
	public void test() {
		Set<String> surplus = new TreeSet<>();
		Set<String> lacks = new TreeSet<>();

		String path = "";
		String cmpPath = "";
		if (StringUtils.isBlank(path) || StringUtils.isBlank(cmpPath)) {
			return;
		}
		FileUtils.diff(path, cmpPath, surplus, lacks);

		System.out.println("多余...");
		Iterator<String> iter = surplus.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		System.out.println("缺少...");
		iter = lacks.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
