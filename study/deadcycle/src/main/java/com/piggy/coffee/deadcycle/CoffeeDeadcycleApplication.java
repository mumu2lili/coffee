package com.piggy.coffee.deadcycle;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CoffeeDeadcycleApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeDeadcycleApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}