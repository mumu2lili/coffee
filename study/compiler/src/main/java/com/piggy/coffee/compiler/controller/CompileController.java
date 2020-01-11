package com.piggy.coffee.compiler.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompileController {
	private Logger log = LoggerFactory.getLogger(getClass());

	private AtomicInteger i = new AtomicInteger(1);

	@GetMapping("/hello")
	public String helloWorld3() throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		String s = "public class HelloWorld {\r\n" + "	public static void main(String[] args) {\r\n"
				+ "		System.out.println(";
		int k = i.getAndIncrement();
		s = s + k + ");\r\n" + "	}\r\n" + "}";

		String path = "D:\\mumu\\company\\zhi_log\\tmp\\" + k + "\\HelloWorld.java";
		FileUtils.writeStringToFile(new File(path), s, "UTF-8");
		int r = compiler.run(null, null, null, path);
		log.info("第{}次编译，结果{}", k, r);
		return "hello";
	}

}