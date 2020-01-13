package com.piggy.coffee.compiler.tmp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hellos")
@RestController
public class HelloController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/hello")
	public String helloWorld() throws IOException {

		log.info("[end]!!!!!!!!!!!!!!!");
		return "hello";
	}

}