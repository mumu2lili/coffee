package com.piggy.coffee.compiler.controller;

import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggy.coffee.common.model.ApiResult;
import com.piggy.coffee.compiler.model.ClassInfo;

@RequestMapping("/compilers")
@RestController
public class CompilerController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/hello")
	public String helloWorld() throws IOException {

		log.info("[end]**********");
		return "hello";
	}

	@PostMapping("/compile")
	public ApiResult<?> compile(@RequestBody ClassInfo classInfo) throws IOException {
		ApiResult<?> result = new ApiResult<>();
		String srcFile = classInfo.getSrcFile();
		log.info("[start]编译oj {}文件{}", classInfo.getTpiId(), srcFile);

		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int r = compiler.run(null, null, null, srcFile);

		log.info("[end]编译oj {}文件{},result:{}", classInfo.getTpiId(), srcFile, r);
		return result;
	}

}