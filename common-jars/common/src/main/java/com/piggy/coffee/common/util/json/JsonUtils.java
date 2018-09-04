/*******************************************************************************
case_consumer * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.piggy.coffee.common.util.json;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author mumu
 *
 */
public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
