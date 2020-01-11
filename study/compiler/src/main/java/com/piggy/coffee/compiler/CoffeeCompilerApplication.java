package com.piggy.coffee.compiler;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CoffeeCompilerApplication {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		new SpringApplicationBuilder(CoffeeCompilerApplication.class).web(WebApplicationType.SERVLET).run(args);

	}
}