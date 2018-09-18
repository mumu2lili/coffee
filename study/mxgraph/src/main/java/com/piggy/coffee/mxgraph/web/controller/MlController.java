package com.piggy.coffee.mxgraph.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.piggy.coffee.mxgraph.model.XmlFile;

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

	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Map<String, Object> save(@RequestBody XmlFile model, HttpServletRequest req, HttpServletResponse res) {

		Map<String, Object> result = new HashMap<>();
		result.put("message", "ok");
		return result;
	}

}
