package com.piggy.coffee.https.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.junit.Test;

import com.piggy.coffee.common.model.ApiResult;
import com.piggy.coffee.common.util.json.JsonUtils;

public class NioHttpClientUtilsTest3 {

	@Test
	public void testNioHttp3() throws InterruptedException, ExecutionException, IOException {

		String url = "http://127.0.0.1:8080/test2";
		// url = "http://localhost/test";
		Map<String, Object> params = new HashMap<>();
		params.put("jsonTestDetails",
				"{\"buildID\":\"438445\",\"compileSuccess\":\"1\",\"msg\":[{\"caseId\":\"1\",\"output\":\"5ZCD6bG8DQrmipPogIHpvKANCuWQg-mqqOWktA0K55yL5a62DQo\",\"passed\":\"1\"}],\"outPut\":\"Y29tcGlsZSBzdWNjZXNzZnVsbHk\",\"resubmit\":\"MJVBAH6K_138429\",\"status\":\"0\"}");
		params.put("timeCost",
				"{\"evaluateEnd\":\"2019-04-10T15:45:31.610\",\"pull\":\"0.150\",\"createPod\":\"0.163\",\"evaluateAllTime\":\"1.910\",\"execute\":\"1.634\",\"evaluateStartTime\":\"2019-04-10T15:45:29.700\"}");

		CloseableHttpAsyncClient client = NioHttpClientUtils3.getClient();
		ApiResult<String> r = NioHttpClientUtils3.sendPost(client, url, JsonUtils.toJson(params));
		NioHttpClientUtils3.close(client);
		System.out.println("result:" + r);

	}

}
