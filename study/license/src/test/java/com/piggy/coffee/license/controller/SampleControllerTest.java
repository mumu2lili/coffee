package com.piggy.coffee.license.controller;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.piggy.coffee.common.model.ApiResult;


public class SampleControllerTest {


	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testPost() {

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);

		HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
		ResponseEntity<ApiResult> res = restTemplate.postForEntity("http://127.0.0.1:8080/hello", entity,
				ApiResult.class);
		System.out.println(res.getBody().getMsg());
	}

}
