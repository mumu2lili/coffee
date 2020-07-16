package com.piggy.coffee.common.util.json;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.piggy.coffee.common.model.ApiResult;

public class JsonUtilsTest {
	public static class Book  {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}

	@Test
	public void test() {
		Book book = new Book();
		book.setName("aa");
		
		ApiResult<Book> apiResult = new ApiResult<>();
		apiResult.setData(book);
		
		String s = JsonUtils.toJson(apiResult);
		System.out.println(s);
		
		ApiResult<Book> r = JsonUtils.toBean(s, new TypeReference<ApiResult<Book>>() {});
		
		System.out.println(r);
	}

}
