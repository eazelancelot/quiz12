package com.example.quiz12.vo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class FillinReq {

	private int quizId;

	private String userName;

	private String email;

	private int age;

	// 問題編號(quesId), 答案(answer)
	private Map<Integer, List<String>> quesIdAnswerMap;

	// 給定預設值(當前日期)；前端送過來的 req 中，此欄位若是沒有值(null)，這個欄位就會使用預設值
	private LocalDate fillinDate = LocalDate.now();

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
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

	public Map<Integer, List<String>> getQuesIdAnswerMap() {
		return quesIdAnswerMap;
	}

	public void setQuesIdAnswerMap(Map<Integer, List<String>> quesIdAnswerMap) {
		this.quesIdAnswerMap = quesIdAnswerMap;
	}

	public LocalDate getFillinDate() {
		return fillinDate;
	}

	public void setFillinDate(LocalDate fillinDate) {
		this.fillinDate = fillinDate;
	}

}
