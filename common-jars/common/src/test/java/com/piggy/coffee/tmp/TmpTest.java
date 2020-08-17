package com.piggy.coffee.tmp;

import java.io.File;

import org.junit.Test;

public class TmpTest {


	@Test
	public void test() {
		File file = new File("C:\\Users\\mumu\\Desktop\\tmp\\a");
		File[] files = file.listFiles();
		System.out.println(files);
	}

	

}
