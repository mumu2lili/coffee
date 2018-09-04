package com.piggy.coffee.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.piggy.coffee.mxgraph.web.filter.PathFilter;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean registerEncodingFilter() {
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new CharacterEncodingFilter());
		filterRegBean.setName("characterEncodingFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(1);

		return filterRegBean;
	}

	@Bean
	public FilterRegistrationBean registerPathFilter() {
		FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
		filterRegBean.setFilter(new PathFilter());
		filterRegBean.setName("pathFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(2);

		return filterRegBean;
	}

}