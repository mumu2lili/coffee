package com.piggy.coffee.k8s.client.json.metrics;

public class Usage {
	private String cpu;
	private String memory;

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

}
