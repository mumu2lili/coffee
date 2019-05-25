package com.piggy.coffee.https.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/test2")
	public String test2(@RequestParam String command) {

		log.info("收到请求 {}", command);
		return command;
	}
}