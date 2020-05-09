package com.piggy.coffee.https.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class ConfigControllerTest {

	@Test
	public void testNioHttp() throws InterruptedException, ExecutionException, IOException {

		String url = "http://127.0.0.1:8080/configs/servers";
		Map<String, Object> params = new HashMap<>();
		params.put("k1", "v1");

		String result = NioHttpClientUtils.sendPost(url, params);
		System.out.println("result:" + result);

	}
}
