package com.piggy.coffee.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.piggy.coffee.common.util.json.JsonUtils;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ExtractStatementServiceRestfulTest {

	@Test
	public void test_extract_json() {

		Assert.assertEquals(9, 0);

	}

	private void mockJsonRequest(final String dataPath)  {

		new MockUp<RestTemplate>() {

			@Mock
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
												  Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

				List<Map> list = getData(uriVariables);
				if (StringUtils.isBlank(dataPath)) {
					return new ResponseEntity((T) JsonUtils.toJson(list), HttpStatus.OK);
				}

				Map<String, Object> root = new HashMap<String, Object>();
				Map<String, Object> map = root;

				String[] sections = dataPath.split("\\.");
				for (int i = 0; i < sections.length - 1; i++) {
					Map<String, Object> sub = new HashMap<String, Object>();
					map.put(sections[i], sub);
					map = sub;

				}
				map.put(sections[sections.length - 1], list);

				return new ResponseEntity((T) JsonUtils.toJson(root), HttpStatus.OK);

			}
		};

	}

	private void mockXmlRequest(final String dataPath) {

		new MockUp<RestTemplate>() {

			@Mock
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
					Class<T> responseType, Map<String, ?> uriVariables) throws RestClientException {

				String s = "<xml><persons><person><name>w1</name><sex>男</sex></person><person><name>w1</name><sex>女</sex></person></persons></xml>";

				return new ResponseEntity((T) s, HttpStatus.OK);

			}
		};

	}

	private List<Map> getData(Map<String, ?> urlVariables) {

		List<Map> list = new ArrayList<Map>();
		String[] usernames = { "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9" };
		String[] emails = { "e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9" };
		for (int i = 0; i < usernames.length; i++) {
			Map map = new HashMap();
			map.put("name", usernames[i]);
			map.put("email", emails[i]);
			list.add(map);
		}

		Integer pageSize = (Integer) urlVariables.get("pageSize");
		if (pageSize != null) {
			Integer pageNumber = (Integer) urlVariables.get("pageNumber");
			int begin = (pageNumber - 1) * pageSize;
			if (begin >= list.size()) {
				list.clear();
			} else {
				int end = begin + pageSize;
				if (end > list.size()) {
					end = list.size();
				}
				list = list.subList(begin, end);
			}
		}

		return list;
	}

}
