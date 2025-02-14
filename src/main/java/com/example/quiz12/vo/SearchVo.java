package com.example.quiz12.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz12.entity.Question;

public class SearchVo extends CreateReq {

	private int quizId;

	public SearchVo() {
		super();
	}

	public SearchVo(int quizId, String name, String description, LocalDate startDate, LocalDate endDate,
			boolean published, List<Question> questionList) {
		super(name, description, startDate, endDate, published, questionList);
		this.quizId = quizId;
	}

	public int getQuizId() {
		return quizId;
	}

}
