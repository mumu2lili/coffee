package com.piggy.coffee;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.piggy.coffee.web.filter.InjectionAttackFilter;
import com.piggy.coffee.web.listener.ContextPathListener;

@EnableScheduling 
@Configuration
@SpringBootApplication
public class CoffeeWebApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(CoffeeWebApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

	@Bean
	public ServletListenerRegistrationBean<ContextPathListener> registerServletListener() {
		ServletListenerRegistrationBean<ContextPathListener> listenerRegBean = new ServletListenerRegistrationBean<ContextPathListener>();
		listenerRegBean.setListener(new ContextPathListener());
		listenerRegBean.setOrder(1);

		return listenerRegBean;
	}
	
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> registerFilter1() {
		FilterRegistrationBean<CharacterEncodingFilter> filterRegBean = new FilterRegistrationBean<CharacterEncodingFilter>();
		filterRegBean.setFilter(new CharacterEncodingFilter());
		filterRegBean.setName("characterEncodingFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(1);

		return filterRegBean;
	}
	
	@Bean
	public FilterRegistrationBean<InjectionAttackFilter> registerFilter2() {
		FilterRegistrationBean<InjectionAttackFilter> filterRegBean = new FilterRegistrationBean<InjectionAttackFilter>();
		filterRegBean.setFilter(new InjectionAttackFilter());
		filterRegBean.setName("injectionAttackFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(2);

		return filterRegBean;
	}


	//@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> registerFilter3() {
		FilterRegistrationBean<DelegatingFilterProxy> filterRegBean = new FilterRegistrationBean<DelegatingFilterProxy>();
		filterRegBean.setFilter(new DelegatingFilterProxy());
		filterRegBean.setName("shiroFilter");
		filterRegBean.addUrlPatterns("/*");
		filterRegBean.setOrder(3);

		return filterRegBean;
	}
}