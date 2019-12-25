package com.piggy.coffee.https.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.piggy.coffee.https.model.ClusterConfig;

//@Component
@ConfigurationProperties("cluster")
public class MultiClusterConfig {

	private Map<String, ClusterConfig> map = new HashMap<>();

	public Map<String, ClusterConfig> getMap() {
		return map;
	}

	public void setMap(Map<String, ClusterConfig> map) {
		this.map = map;
	};


}
