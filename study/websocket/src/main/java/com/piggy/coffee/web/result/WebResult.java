package com.piggy.coffee.web.result;

public class WebResult<T> {

	private String msg = "ok";

	private T body;

	public String getMsg() {

		return msg;
	}

	public void setMsg(String msg) {

		this.msg = msg;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
