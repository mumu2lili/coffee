package com.piggy.coffee.https.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/hello")
	public String helloWorld() {
		return "hello";
	}

	@PostMapping("/test")
	public String test(@RequestBody String content) {

		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		log.info("收到请求 {}", content);
		return "a";
	}
}