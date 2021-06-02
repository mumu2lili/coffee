package com.piggy.coffee.k8s.util;

import org.junit.Test;

public class K8sUtilsTest {

	@Test
	public void test() {
		String imageName = "1133";
		String cName = K8sUtils.buildContainerName(imageName);
		System.out.println(cName);
	}

	@Test
	public void test2() {
		String imageName = "1133.a.B";
		String cName = K8sUtils.buildContainerName(imageName);
		System.out.println(cName);
	}
}
