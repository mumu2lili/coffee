package com.piggy.java.util.jar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class JarFileTest {

	@Test
	public void test() throws IOException {
		String jarPath = "C:\\Users\\mm\\Desktop\\tmp\\WEB-INF\\lib\\activation-1.1.jar";
		List<String> list = getClassPath(jarPath, "*");
		System.out.println(list);

	}

	@Test
	public void test2() throws IOException {
		String dir = "C:\\Users\\mm\\Desktop\\tmp\\WEB-INF\\lib";
		File file = new File(dir);
		File[] jarFiles = file.listFiles();
		for (File jarFile : jarFiles) {
			try {
				List<String> list = getClassPath(jarFile.getAbsolutePath(), ".*");
				if (!list.isEmpty()) {
					System.out.println(jarFile.getName() + ": " + list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private List<String> getClassPath(String jarPath, String reg) {
		String classPath = getClassPath(jarPath);
		if (StringUtils.isBlank(classPath)) {
			return Collections.emptyList();
		}

		List<String> list = new ArrayList<>();
		String[] ClassPathEntrys = classPath.split(" ");
		for (String str : ClassPathEntrys) {
			if (str.matches(reg)) {
				list.add(str);
			}
		}

		return list;

	}

	private String getClassPath(String jarPath) {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(jarPath);
			Manifest manifest = jarFile.getManifest();
			if (null == manifest) {
				return null;
			}
			Attributes attrs = manifest.getMainAttributes();
			return attrs.getValue("Class-Path");
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (null != jarFile) {
				try {
					jarFile.close();
				} catch (IOException e) {
					// Ignore quitely;
				}
			}
		}
	}

}
