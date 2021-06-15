package com.piggy.coffee.harbor;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CoffeeHarborApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeHarborApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}