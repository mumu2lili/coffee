package com.piggy.coffee.mxgraph.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("graph")
public class GraphController {

	@RequestMapping(value = "editor", method = RequestMethod.GET)
	public String editor(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "graph/editor";
	}

	@RequestMapping(value = "toolbar", method = RequestMethod.GET)
	public String toolbar(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res) {

		return "graph/toolbar";
	}

}
