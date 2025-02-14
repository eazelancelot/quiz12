package com.example.quiz12.vo;

import java.time.LocalDate;
import java.util.List;

public class FeedbackVo {

	private int quizId;

	private String quizName;

	private String description;

	private String userName;

	private String email;

	private int age;

	private LocalDate fillinDate;

	private List<OptionAnswer> optionAnswerList;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getFillinDate() {
		return fillinDate;
	}

	public void setFillinDate(LocalDate fillinDate) {
		this.fillinDate = fillinDate;
	}

	public List<OptionAnswer> getOptionAnswerList() {
		return optionAnswerList;
	}

	public void setOptionAnswerList(List<OptionAnswer> optionAnswerList) {
		this.optionAnswerList = optionAnswerList;
	}

}
