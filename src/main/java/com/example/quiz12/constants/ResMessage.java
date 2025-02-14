package com.example.quiz12.constants;

public enum ResMessage {
	// 列舉的項目
	SUCCESS(200, "Success!!"), //
	PARAM_QUIZ_NAME_ERROR(400, "Param quiz name error!!"), //
	PARAM_DESCRIPTION_ERROR(400, "Param description error!!"), //
	PARAM_START_DATE_ERROR(400, "Param start date error!!"), //
	PARAM_END_DATE_ERROR(400, "Param end date error!!"), //
	PARAM_DATE_ERROR(400, "Param date error!!"), //
	PARAM_QUES_ID_ERROR(400, "Param question id error!!"), //
	PARAM_QUES_NAME_ERROR(400, "Param question name error!!"), //
	PARAM_TYPE_ERROR(400, "Param type error!!"), //
	PARAM_OPTIONS_ERROR(400, "Param options error!!"), //
	PARAM_QUES_LIST_ERROR(400, "Param question list error!!"),
	DATA_SAVE_ERROR(400, "Data save error!!"),//
	DATA_UPDATE_ERROR(400, "Data update error!!"),//
	QUES_TYPE_MISMATCH(400, "Ques type mismatch!!"), //
	PARAM_QUIZ_ID_ERROR(400, "Param quiz id error!!"),//
	QUIZ_NOT_FOUND(404, "Quiz not found!!"),//
	QUIZ_ID_MISMATCH(400, "Quiz id mismatch!!"),//
	PARAM_USER_NAME_ERROR(400, "Param user name error!!"),//
	PARAM_EMAIL_ERROR(400, "Param email error!!"),//
	PARAM_AGE_ERROR(400, "Param age error!!"),//
	EMAIL_DUPLICATED(400, "Email duplicated!!"),//
	OUT_OF_FILLIN_DATE_RANGE(400, "Out of fillin date range!!"),//
	ANSWER_IS_REQUIRED(400, "Answer is required!!"), //
	ONE_OPTION_IS_ALLOWED(400, "One option is allowed!!"),//
	OPTIONS_PARSER_ERROR(400, "Options parser error!!"),//
	OPTION_ANSWER_MISMATCH(400, "Option answer mismatch!!"), //
	ANSWER_PARSER_ERROR(400, "Answer parser error!!"),//
	OPTIONS_COUNT_ERROR(400, "Options count error!!");

	private int code;

	private String message;

	private ResMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
