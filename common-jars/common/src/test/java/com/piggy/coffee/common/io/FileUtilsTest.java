package com.piggy.coffee.common.io;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.piggy.coffee.common.util.io.FileUtils;

public class FileUtilsTest {

	@Test
	public void test() {
		Set<String> surplus = new TreeSet<>();
		Set<String> lacks = new TreeSet<>();

		FileUtils.diff("C:\\Users\\mumu\\Desktop\\git\\u5wf9r38e", "C:\\Users\\mumu\\Desktop\\git\\u5wf9r38", surplus,
				lacks);
		
		System.out.println("多余...");
		Iterator<String> iter = surplus.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		System.out.println("缺少...");
		iter = lacks.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
