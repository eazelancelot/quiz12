package com.example.quiz12.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FeedbackId implements Serializable{

	private int quizId;

	private String email;

	private int quesId;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

}
