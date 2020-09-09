package com.piggy.coffee.license.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.piggy.coffee.license.interceptor.LicenseInterceptor;

@Configuration
public class LicenseConfig implements WebMvcConfigurer {

	@Bean
	public LicenseInterceptor getAuthInterceptor() {
		return new LicenseInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LicenseInterceptor interceptor = getAuthInterceptor();
		registry.addInterceptor(interceptor).addPathPatterns("/webssh/**", "/ssh/**",
				// game包
				"/game/**", "/vnc/**", "/jupyter/**",
				// ojs
				"/ojs/**", "/hello");
	}

}
