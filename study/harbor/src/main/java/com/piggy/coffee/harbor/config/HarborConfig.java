package com.piggy.coffee.harbor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HarborConfig {

	@Value("${harbor.url:http://192.168.56.104/api/v2.0}")
	private String url;

	@Value("${harbor.username:admin}")
	private String username;

	@Value("${harbor.password:Harbor12345}")
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
