package com.example.quiz12.vo;

import java.util.List;

// 一個 StatisticsVo 表示一個問題的答案次數
public class StatisticsVo {

	private String quizName;

	private int quesId;

	private String quesName;

	private boolean required;

	List<OptionCount> optionCountList;

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getQuesName() {
		return quesName;
	}

	public void setQuesName(String quesName) {
		this.quesName = quesName;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<OptionCount> getOptionCountList() {
		return optionCountList;
	}

	public void setOptionCountList(List<OptionCount> optionCountList) {
		this.optionCountList = optionCountList;
	}

}
