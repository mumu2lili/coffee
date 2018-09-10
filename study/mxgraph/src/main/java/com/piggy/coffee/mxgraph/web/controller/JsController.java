package com.piggy.coffee.mxgraph.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用于测试 javascript
 *
 * @author mm
 *
 */
@Controller
@RequestMapping("js")
public class JsController {

	@RequestMapping(value = "global", method = RequestMethod.GET)
	public String editor(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "js/global";
	}

}
