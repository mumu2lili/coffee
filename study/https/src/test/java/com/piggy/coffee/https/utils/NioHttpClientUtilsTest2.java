package com.piggy.coffee.https.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.junit.Test;

public class NioHttpClientUtilsTest2 {

	@Test
	public void testNioHttp() throws InterruptedException, ExecutionException, IOException {

		String url = "http://127.0.0.1:8080/test";
		// url = "http://localhost/test";
		Map<String, Object> params = new HashMap<>();
		params.put("jsonTestDetails",
				"{\"buildID\":\"438445\",\"compileSuccess\":\"1\",\"msg\":[{\"caseId\":\"1\",\"output\":\"5ZCD6bG8DQrmipPogIHpvKANCuWQg-mqqOWktA0K55yL5a62DQo\",\"passed\":\"1\"}],\"outPut\":\"Y29tcGlsZSBzdWNjZXNzZnVsbHk\",\"resubmit\":\"MJVBAH6K_138429\",\"status\":\"0\"}");
		params.put("timeCost",
				"{\"evaluateEnd\":\"2019-04-10T15:45:31.610\",\"pull\":\"0.150\",\"createPod\":\"0.163\",\"evaluateAllTime\":\"1.910\",\"execute\":\"1.634\",\"evaluateStartTime\":\"2019-04-10T15:45:29.700\"}");

		String result = NioHttpClientUtils.sendPost(url, params);
		System.out.println("result:" + result);

	}
	
	
	@Test
	public void testNioHttp2() throws InterruptedException, ExecutionException, IOException {

		String url = "http://127.0.0.1:8080/test";
		// url = "http://localhost/test";
		Map<String, Object> params = new HashMap<>();
		params.put("jsonTestDetails",
				"{\"buildID\":\"438445\",\"compileSuccess\":\"1\",\"msg\":[{\"caseId\":\"1\",\"output\":\"5ZCD6bG8DQrmipPogIHpvKANCuWQg-mqqOWktA0K55yL5a62DQo\",\"passed\":\"1\"}],\"outPut\":\"Y29tcGlsZSBzdWNjZXNzZnVsbHk\",\"resubmit\":\"MJVBAH6K_138429\",\"status\":\"0\"}");
		params.put("timeCost",
				"{\"evaluateEnd\":\"2019-04-10T15:45:31.610\",\"pull\":\"0.150\",\"createPod\":\"0.163\",\"evaluateAllTime\":\"1.910\",\"execute\":\"1.634\",\"evaluateStartTime\":\"2019-04-10T15:45:29.700\"}");

		CloseableHttpAsyncClient client = NioHttpClientUtils2.getClient();
		String result = NioHttpClientUtils2.sendPost(client, url, params);
		NioHttpClientUtils2.close(client);
		System.out.println("result:" + result);

	}

	@Test
	public void testNioHttpMultipleThreads() throws InterruptedException, ExecutionException, IOException {

		String url = "http://127.0.0.1:8080/test";
		// url = "http://localhost/test";
		Map<String, Object> params = new HashMap<>();
		params.put("jsonTestDetails",
				"{\"buildID\":\"438445\",\"compileSuccess\":\"1\",\"msg\":[{\"caseId\":\"1\",\"output\":\"5ZCD6bG8DQrmipPogIHpvKANCuWQg-mqqOWktA0K55yL5a62DQo\",\"passed\":\"1\"}],\"outPut\":\"Y29tcGlsZSBzdWNjZXNzZnVsbHk\",\"resubmit\":\"MJVBAH6K_138429\",\"status\":\"0\"}");
		params.put("timeCost",
				"{\"evaluateEnd\":\"2019-04-10T15:45:31.610\",\"pull\":\"0.150\",\"createPod\":\"0.163\",\"evaluateAllTime\":\"1.910\",\"execute\":\"1.634\",\"evaluateStartTime\":\"2019-04-10T15:45:29.700\"}");

		CloseableHttpAsyncClient client = NioHttpClientUtils2.getClient();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					String result = NioHttpClientUtils2.sendPost(client, url, params);
					System.out.println("result:" + result);
				}
			}).start();

		}

		Thread.currentThread().sleep(5 * 1000);

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					String result = NioHttpClientUtils2.sendPost(client, url, params);
					System.out.println("result:" + result);
				}
			}).start();

		}

		Thread.currentThread().sleep(5 * 1000);
		NioHttpClientUtils2.close(client);
	}

}
