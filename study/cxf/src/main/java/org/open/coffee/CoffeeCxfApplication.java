package org.open.coffee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.open.coffee.web.rest" })
public class CoffeeCxfApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeCxfApplication.class).web(true).run(args);
	}
}