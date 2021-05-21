package com.piggy.coffee.k8s.client.json.metrics;

import java.util.List;

public class NodeMetricsList {
	private String kind;
	private String apiVersion;
	private Metadata metadata;
	private List<NodeMetrics> items;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<NodeMetrics> getItems() {
		return items;
	}

	public void setItems(List<NodeMetrics> items) {
		this.items = items;
	}

}
