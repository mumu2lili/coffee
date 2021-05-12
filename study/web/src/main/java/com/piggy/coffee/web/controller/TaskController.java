package com.piggy.coffee.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piggy.coffee.web.model.Task;
import com.piggy.coffee.web.result.ApiResult;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ApiResult<String> createTask(@RequestBody(required = false) Task task) {
		ApiResult<String> r = new ApiResult<String>();
		log.info("[start]create task");

		log.info("[end]create task");
		return r;
	}

}
