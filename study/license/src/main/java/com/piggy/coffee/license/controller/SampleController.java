package com.piggy.coffee.license.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggy.coffee.common.model.ApiResult;

@RestController
public class SampleController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/hello")
	public ApiResult<?> helloWorld() {
		ApiResult<?> r = new ApiResult<>();
		log.info("hello");
		r.setMsg("hello world");
		return r;
	}

}