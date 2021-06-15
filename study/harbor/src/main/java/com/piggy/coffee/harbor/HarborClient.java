package com.piggy.coffee.harbor;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.piggy.coffee.harbor.config.HarborConfig;

@Component
public class HarborClient {
	@Autowired
	private HarborConfig config;
	private RestTemplate restTemplate = new RestTemplate();

	public void health() {

		String url = config.getUrl() + "/health";
		HttpHeaders headers = getHeader();
		HttpEntity<String> ans = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, headers),
				String.class);
		System.out.println(ans);
	}

	private HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		String pair = config.getUsername() + ":" + config.getPassword();
		headers.set("authorization", "Basic " + Base64.getEncoder().encodeToString(pair.getBytes()));
		return headers;

	}

}
