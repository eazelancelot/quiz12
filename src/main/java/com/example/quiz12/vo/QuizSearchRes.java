package com.example.quiz12.vo;

import java.util.List;

import com.example.quiz12.entity.Quiz;

public class QuizSearchRes extends BasicRes {

	private List<Quiz> quizList;

	public QuizSearchRes() {
		super();
	}

	public QuizSearchRes(int code, String message) {
		super(code, message);
	}

	public QuizSearchRes(int code, String message, List<Quiz> quizList) {
		super(code, message);
		this.quizList = quizList;
	}

	public List<Quiz> getQuizList() {
		return quizList;
	}
	
	
}
