package com.piggy.coffee.k8s;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling 
@Configuration
@SpringBootApplication
public class CoffeeK8sApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeK8sApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

	
}