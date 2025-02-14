package com.example.quiz12.vo;

import java.util.List;

public class SearchRes extends BasicRes {

	private List<SearchVo> searchList;

	public SearchRes() {
		super();
	}

	public SearchRes(int code, String message) {
		super(code, message);
	}

	public SearchRes(int code, String message, List<SearchVo> searchList) {
		super(code, message);
		this.searchList = searchList;
	}

	public List<SearchVo> getSearchList() {
		return searchList;
	}

}
