package com.piggy.coffee.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.piggy.coffee.common.util.json.JsonUtils;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import net.minidev.json.JSONUtil;

@RunWith(JMockit.class)
public class OutLoadStatementServiceRestfulTest extends ExtractStatementServiceRestfulTest {

	@InjectMocks
	private PersonController personController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);
		mockMvc =
			MockMvcBuilders.standaloneSetup(personController).setMessageConverters(new MappingJackson2HttpMessageConverter())
						   .build(); // 构造MockMvc
	}

	@Test
	public void test_json()  {



	}




	private void mockJsonRequest(final String dataPath)  {

		new MockUp<RestTemplate>() {

			@Mock
			public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
												  Class<T> responseType, Object... uriVariables) {

				try {

					String uri = "/persons";
					if(StringUtils.isNotBlank(dataPath)) {
						uri += "/path";
					}
					String s = JsonUtils.toJson(requestEntity.getBody());
					mockMvc.perform(post(uri).accept(MediaType.APPLICATION_JSON)
														 .contentType(MediaType.APPLICATION_JSON).content(s.getBytes()))
						   .andDo(MockMvcResultHandlers.print())

						   // System.out.println(s.equals(s2));
						   // .andExpect(status().isOk()).andExpect(jsonPath("$.data").exists())
						   // .andExpect(jsonPath("$[0].name").value("name1"));

						   // .andExpect(status().isOk()).andExpect(jsonPath("$.data").exists());
						  .andExpect(jsonPath("data").exists())
						   .andExpect(status().isOk());

					return new ResponseEntity((T) JsonUtils.toJson(""), HttpStatus.OK);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}
		};
	}
}
