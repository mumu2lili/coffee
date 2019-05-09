package com.piggy.coffee.web.controller;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class TmpTest {

	@Test
	public void test() throws IOException {

		String locationPattern = "classpath*:*.properties";
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(locationPattern);

		System.out.println("************");
		for (Resource res : resources) {
			System.out.println(res.getURI());
		}

	}

}
