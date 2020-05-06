package com.piggy.coffee.logtool.error;

import java.time.LocalDateTime;

class EvaluatingAssayParam {
	public String tpiID;
	public Integer podType;
	public String instanceChallenge;
	public Integer timeLimit;
	public String evaluateStartTime;
	public String evaluateEndTime;
	public String gitUrl;
	/**
	 * 原始req
	 */
	public String req;
	/**
	 * 原始res
	 */
	public String res;
	
	public LocalDateTime getEvaluateStartTime() {
		return LocalDateTime.parse(evaluateStartTime.replace(" ", "T"));
	}
	
	
	public LocalDateTime getEvaluateEndTime() {
		return LocalDateTime.parse(evaluateEndTime.replace(" ", "T"));
	}

}