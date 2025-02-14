package com.example.quiz12.service.ifs;

import com.example.quiz12.vo.BasicRes;
import com.example.quiz12.vo.FeedbackRes;
import com.example.quiz12.vo.FillinReq;
import com.example.quiz12.vo.StatisticsRes;

public interface FeedbackService {
	
	public BasicRes fillin(FillinReq req);
	
	public FeedbackRes feedback(int quizId);
	
	public StatisticsRes statistics(int quizId) throws Exception;

}
