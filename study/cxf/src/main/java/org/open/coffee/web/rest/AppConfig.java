package org.open.coffee.web.rest;

import java.util.List;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Import
//@ImportResource
@Configuration
@Component
public class AppConfig {

	@Autowired
	private List<Rest> list;
	
	@CustomQualifier
	@Autowired
	private List<Rest> list2;
	
	@Bean(name = "rests")
	public List<Rest> myService() {
		return list;
	}
	
	
    @Bean  
    public ServletRegistrationBean servletRegistrationBean() {  
        return new ServletRegistrationBean(new CXFServlet(), "/api/*");// ServletName默认值为首字母小写，即myServlet1  
    }  
}