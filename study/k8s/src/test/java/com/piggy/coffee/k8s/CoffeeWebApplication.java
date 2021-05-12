package com.piggy.coffee.k8s;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling 
@Configuration
@SpringBootApplication
public class CoffeeWebApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeWebApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

	
}