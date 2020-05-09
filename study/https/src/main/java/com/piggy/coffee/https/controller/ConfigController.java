package com.piggy.coffee.https.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggy.coffee.https.config.ListConfig;
import com.piggy.coffee.https.config.MultiClusterConfig;

@RestController
@RequestMapping("configs")
public class ConfigController {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MultiClusterConfig multiClusterConfig;

	@Autowired
	private ListConfig ListConfig;

	@GetMapping("/clusters")
	public MultiClusterConfig getClusters() {
		log.info("clusters ...");
		return multiClusterConfig;
	}

	@PostMapping("/servers")
	public ListConfig getServers() {

		log.info("servers ...");
		Map<String, Object> params = new HashMap<>();
		params.put("k1", "v1");
		log.info("{}", params);
		return ListConfig;
	}

}