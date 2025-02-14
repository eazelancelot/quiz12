package com.example.quiz12.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz12.service.ifs.FeedbackService;
import com.example.quiz12.service.ifs.QuizService;
import com.example.quiz12.vo.BasicRes;
import com.example.quiz12.vo.CreateReq;
import com.example.quiz12.vo.DeleteReq;
import com.example.quiz12.vo.GetQuesRes;
import com.example.quiz12.vo.QuizSearchRes;
import com.example.quiz12.vo.SearchReq;
import com.example.quiz12.vo.StatisticsRes;
import com.example.quiz12.vo.TestVo;
import com.example.quiz12.vo.UpdateReq;

@CrossOrigin
@RestController
public class QuizServiceController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping(value = "quiz/create_list")
	public BasicRes createList(@RequestBody List<CreateReq> reqList) {
		return quizService.createList(reqList);
	}

	@PostMapping(value = "quiz/create")
	public BasicRes create(@RequestBody CreateReq req) {
		return quizService.create(req);
	}

	@GetMapping(value = "quiz/get_all_quiz")
	public QuizSearchRes getAllQuiz() {
		return quizService.getAllQuiz();
	}

	@PostMapping(value = "quiz/get_quiz")
	public QuizSearchRes getQuiz(@RequestBody SearchReq req) {
		return quizService.getQuiz(req);
	}

	// ===================================================
	// 多個參數使用 @RequestParam
	// 呼叫API的路徑: http://localhost:8080/quiz/search?name=AAA&start_date=2024-12-01
	@GetMapping(value = "quiz/search")
	public QuizSearchRes search( //
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "start_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "end_date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		return null;
	}

	// ===================================================

	// 呼叫API的路徑: http://localhost:8080/quiz/get_ques?quizId=1(指定值)
	// quizId 名稱要和方法中的變數名稱一樣
	@PostMapping(value = "quiz/get_ques")
	public GetQuesRes getQuesByQuizId(//
			// 參數使用基本資料型態，@RequestParam 括號中的3個參數一定要寫不然會報錯
			@RequestParam(value = "quizId", required = false, defaultValue = "1") int quizId) {
		return quizService.getQuesByQuizId(quizId);
	}

	// 以下方法與上面同，只是透過 @RequestParam 中的 value 來指定變數名稱

	// 呼叫API的路徑: http://localhost:8080/quiz/get_ques_list?quiz_id=1(指定值)
	// @RequestParam 中的 value，用來指定並對應路經?後面的名稱，並將路徑等號後面的值塞到方法的變數名稱中
	@PostMapping(value = "quiz/get_ques_list")
	public GetQuesRes getQuesListByQuizId(@RequestParam(value = "quiz_id") int quizId) {
		return quizService.getQuesByQuizId(quizId);
	}

	@PostMapping(value = "quiz/update")
	public BasicRes update(@RequestBody UpdateReq req) {
		return quizService.update(req);
	}

	@PostMapping(value = "quiz/delete")
	public BasicRes delete(@RequestBody DeleteReq req) throws Exception {
		return quizService.delete(req);
	}
	
	// 呼叫API的路徑: http://localhost:8080/quiz/statistics?quiz_id=1(指定值)
	@PostMapping(value = "quiz/statistics")
	public StatisticsRes statistics(@RequestParam(value = "quiz_id") int quizId) throws Exception {
		return feedbackService.statistics(quizId);
	}
	
	@PostMapping(value = "quiz/test")
	public void testDt(@RequestBody TestVo vo) {
		System.out.println(vo.getDateTime());
	}

}
