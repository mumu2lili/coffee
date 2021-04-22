package com.piggy.coffee.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piggy.coffee.model.society.Person;
import com.piggy.coffee.web.result.ApiResult;

@Controller
@RequestMapping("/persons")
public class PersonController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiResult<String> json(@RequestBody(required = false) Person[] persons) {
		return new ApiResult<String>();
	}
	

	@RequestMapping(value = "test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiResult<String> test(@RequestBody(required = false) Person[] persons) {
        log.info("test");
		return new ApiResult<String>();
	}


}
