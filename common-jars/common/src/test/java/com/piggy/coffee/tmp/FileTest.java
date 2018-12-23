package com.piggy.coffee.tmp;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FileTest {

	@Test
	public void testSaasApiFile() {

		Set<String> set = new HashSet<>();
		String dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api\\src";
		File file = new File(dir);
		getFile(file, set);

		System.out.println("saas-api file 总数:" + set.size());

		Set<String> set2 = getSaasApi2File();

		Set<String> tmpSet = new HashSet<String>(set);
		tmpSet.removeAll(set2);
		System.out.println("saas-api2 file 缺失数量:" + tmpSet.size());
		System.out.println("saas-api2 file 缺失:" + tmpSet);

		Set<String> tmpSet2 = new HashSet<String>(set2);
		tmpSet2.removeAll(set);
		System.out.println("saas-api2 file 缺失数量:" + tmpSet2.size());
		System.out.println("saas-api2 file 缺失:" + tmpSet2);
	}

	private Set<String> getSaasApi2File() {

		Set<String> set = new HashSet<>();
		String dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\common\\src";
		File file = new File(dir);
		getFile(file, set);

		dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\sys\\src";
		file = new File(dir);
		getFile(file, set);

		dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\store\\src";
		file = new File(dir);
		getFile(file, set);

		dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\doctor\\src";
		file = new File(dir);
		getFile(file, set);

		dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\consumer\\src";
		file = new File(dir);
		getFile(file, set);

		dir = "D:\\work\\eclipse-workspace\\hd\\api\\saas-api2\\frame\\src";
		file = new File(dir);
		getFile(file, set);

		System.out.println("saas-api2 file 总数:" + set.size());
		return set;
	}

	private void getFile(File file, Set<String> set) {
		if (file.isFile()) {
			if (set.contains(file.getName())) {
				throw new RuntimeException("File exists :" + file.getName());
			}
			set.add(file.getName());
			return;
		}

		for (File sub : file.listFiles()) {
			getFile(sub, set);
		}
	}
}
