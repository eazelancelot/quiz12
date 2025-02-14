package com.example.quiz12.vo;

import java.util.List;

public class FeedbackRes extends BasicRes {

	private List<FeedbackVo> feedbackVoList;

	public FeedbackRes() {
		super();
	}

	public FeedbackRes(int code, String message) {
		super(code, message);
	}

	public FeedbackRes(int code, String message, List<FeedbackVo> feedbackVoList) {
		super(code, message);
		this.feedbackVoList = feedbackVoList;
	}

	public List<FeedbackVo> getFeedbackVoList() {
		return feedbackVoList;
	}

}
