package com.example.quiz12.vo;

import java.time.LocalDate;
import java.util.List;

import com.example.quiz12.entity.Question;

public class CreateReq {
	
	private String name;

	private String description;

	private LocalDate startDate;

	private LocalDate endDate;

	private boolean published;

	private List<Question> questionList;

	public CreateReq() {
		super();
	}

	public CreateReq(String name, String description, LocalDate startDate, LocalDate endDate, boolean published,
			List<Question> questionList) {
		super();
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.published = published;
		this.questionList = questionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

}
