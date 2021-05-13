package com.piggy.coffee.common.util.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import exception.CoffeeException;

public final class FileUtils {

	public static void diff(String path, String cmpPath, Set<String> surplus, Set<String> lacks) {
		Set<String> files = new TreeSet<>();
		subFiles(path, files);
		Set<String> tmpSet = new TreeSet<String>();
		for (Iterator<String> iter = files.iterator(); iter.hasNext();) {
			tmpSet.add(iter.next().replace(path, ""));
		}
		files = tmpSet;

		Set<String> cmpFiles = new TreeSet<>();
		subFiles(cmpPath, cmpFiles);
		tmpSet = new TreeSet<String>();
		for (Iterator<String> iter = cmpFiles.iterator(); iter.hasNext();) {
			tmpSet.add(iter.next().replace(cmpPath, ""));
		}
		cmpFiles = tmpSet;

		tmpSet = new TreeSet<String>(files);
		tmpSet.removeAll(cmpFiles);
		surplus.addAll(tmpSet);

		tmpSet = new TreeSet<String>(cmpFiles);
		tmpSet.removeAll(files);
		lacks.addAll(tmpSet);
	}

	private static void subFiles(String path, Set<String> set) {
		set.add(path);

		File file = new File(path);
		if (file.isFile()) {
			return;
		}
		for (File subFile : file.listFiles()) {
			if (subFile.isFile()) {
				set.add(subFile.getAbsolutePath());
			} else {
				subFiles(subFile.getAbsolutePath(), set);
			}
		}

	}

	public static void writeFile(File file, String data) {
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(file, data, "UTF8", false);
		} catch (IOException e) {
			throw new CoffeeException(e);
		}
	}

	public static void writeFile(File file, byte[] data) {
		try {
			org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
		} catch (IOException e) {
			throw new CoffeeException(e);
		}
	}
}