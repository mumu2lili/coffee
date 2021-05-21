package com.piggy.coffee.k8s.client.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piggy.coffee.common.util.json.JsonUtils;
import com.piggy.coffee.k8s.client.json.metrics.NodeMetricsList;

public class MetricsTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test() throws Exception {
		String s = "{\n"
				+ "  \"kind\": \"NodeMetricsList\",\n"
				+ "  \"apiVersion\": \"metrics.k8s.io/v1beta1\",\n"
				+ "  \"metadata\": {\n"
				+ "    \"selfLink\": \"/apis/metrics.k8s.io/v1beta1/nodes\"\n"
				+ "  },\n"
				+ "  \"items\": [\n"
				+ "    {\n"
				+ "      \"metadata\": {\n"
				+ "        \"name\": \"192.168.56.102\",\n"
				+ "        \"selfLink\": \"/apis/metrics.k8s.io/v1beta1/nodes/192.168.56.102\",\n"
				+ "        \"creationTimestamp\": \"2021-05-21T02:26:21Z\"\n"
				+ "      },\n"
				+ "      \"timestamp\": \"2021-05-21T02:25:26Z\",\n"
				+ "      \"window\": \"30s\",\n"
				+ "      \"usage\": {\n"
				+ "        \"cpu\": \"197654934n\",\n"
				+ "        \"memory\": \"2242124Ki\"\n"
				+ "      }\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"metadata\": {\n"
				+ "        \"name\": \"192.168.56.103\",\n"
				+ "        \"selfLink\": \"/apis/metrics.k8s.io/v1beta1/nodes/192.168.56.103\",\n"
				+ "        \"creationTimestamp\": \"2021-05-21T02:26:21Z\"\n"
				+ "      },\n"
				+ "      \"timestamp\": \"2021-05-21T02:25:25Z\",\n"
				+ "      \"window\": \"30s\",\n"
				+ "      \"usage\": {\n"
				+ "        \"cpu\": \"60437330n\",\n"
				+ "        \"memory\": \"2176472Ki\"\n"
				+ "      }\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";
		
		NodeMetricsList list = JsonUtils.toBean(s, NodeMetricsList.class);
		String json = JsonUtils.toJson(list);
		log.info(json);
	}



}
