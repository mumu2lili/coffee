package com.piggy.coffee.https.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piggy.coffee.common.model.ApiResult;

@RestController
public class SampleController {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static AtomicInteger testTimes = new AtomicInteger(0);
	

	@GetMapping("/hello")
	public String helloWorld() {
		return "hello";
	}

	@PostMapping("/test")
	public String test(@RequestBody String content) {

		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			// ignore quietly
		}

		int times = testTimes.incrementAndGet();
		log.info("第{}次收到请求 {}", times, content);
		return content;
	}
	
	@PostMapping("/test2")
	public ApiResult<String> test12(@RequestBody Map<String,Object> content) {

        ApiResult<String> r = new ApiResult<>();

		int times = testTimes.incrementAndGet();
		log.info("第{}次收到请求 {}", times, content);
		
		r.setData("aa");
		r.setMsg("ok");
		return r;
	}

	@GetMapping("/test2")
	public String test2(@RequestParam String command) {

		log.info("收到请求 {}", command);
		return command;
	}
	
	@GetMapping("/test3")
	public String test3(@RequestParam String command) {

		log.info("收到请求 {}", command);
		log.error("收到请求 {}", command);
		return command;
	}
}