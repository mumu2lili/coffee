package com.piggy.coffee.mxgraph.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 机器学习
 *
 * @author mm
 *
 */
@Controller
@RequestMapping("ml")
public class MlController {

	@RequestMapping(value = "editor", method = RequestMethod.GET)
	public String editor(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "ml/editor";
	}

}
