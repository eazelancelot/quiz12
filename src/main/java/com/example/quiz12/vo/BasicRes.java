package com.example.quiz12.vo;

public class BasicRes {

	private int code;

	private String message;

	public BasicRes() {
		super();
	}

	public BasicRes(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
