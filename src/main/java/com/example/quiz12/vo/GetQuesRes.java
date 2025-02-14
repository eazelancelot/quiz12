package com.example.quiz12.vo;

import java.util.List;

import com.example.quiz12.entity.Question;

public class GetQuesRes extends BasicRes {

	private List<Question> quesList;

	public GetQuesRes() {
		super();
	}

	public GetQuesRes(int code, String message) {
		super(code, message);
	}

	public GetQuesRes(int code, String message, List<Question> quesList) {
		super(code, message);
		this.quesList = quesList;
	}

	public List<Question> getQuesList() {
		return quesList;
	}

}
