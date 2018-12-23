package com.piggy.coffee.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piggy.coffee.model.society.Person;
import com.piggy.coffee.web.result.WebResult;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public WebResult<String> json(@RequestBody(required = false) Person[] persons) {

		return new WebResult<String>();
	}


}
