package com.example.quiz12.vo;

public class StatisticsDto {

	private String quizName;

	private int quesId;

	private String quesName;

	private boolean required;

	private String options;

	private String type;

	private String answer;

	public StatisticsDto() {
		super();
	}

	public StatisticsDto(String quizName, int quesId, String quesName, boolean required, String options, String type,
			String answer) {
		super();
		this.quizName = quizName;
		this.quesId = quesId;
		this.quesName = quesName;
		this.required = required;
		this.options = options;
		this.type = type;
		this.answer = answer;
	}

	public String getQuizName() {
		return quizName;
	}

	public int getQuesId() {
		return quesId;
	}

	public String getQuesName() {
		return quesName;
	}

	public boolean isRequired() {
		return required;
	}

	public String getAnswer() {
		return answer;
	}

	public String getOptions() {
		return options;
	}

	public String getType() {
		return type;
	}

}
