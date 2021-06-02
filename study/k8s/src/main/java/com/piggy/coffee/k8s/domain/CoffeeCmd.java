package com.piggy.coffee.k8s.domain;

public class CoffeeCmd {

	private String cmd;

	/**
	 * 命令执行的时间限制，单位秒
	 */
	private int timeLimit = 5;

	public String getCmd() {
		return cmd;
	}

	public CoffeeCmd setCmd(String cmd) {
		this.cmd = cmd;
		return this;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public CoffeeCmd setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
		return this;
	}
}
