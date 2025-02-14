package com.example.quiz12.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quiz12.constants.QuesType;
import com.example.quiz12.constants.ResMessage;
import com.example.quiz12.dao.QuestionDao;
import com.example.quiz12.dao.QuizDao;
import com.example.quiz12.entity.Question;
import com.example.quiz12.entity.Quiz;
import com.example.quiz12.service.ifs.QuizService;
import com.example.quiz12.vo.BasicRes;
import com.example.quiz12.vo.CreateReq;
import com.example.quiz12.vo.DeleteReq;
import com.example.quiz12.vo.GetQuesRes;
import com.example.quiz12.vo.QuizSearchRes;
import com.example.quiz12.vo.SearchReq;
import com.example.quiz12.vo.UpdateReq;





@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;

	// @Transactional: 因為同時新增問卷和問題，新增多筆資料都算是同一次的行為，所以要嘛全部成功，要嘛全部失敗
	// rollbackOn = Exception.class: 指定@Transactional 資料回朔有效的例外層級
	// 發生例外(Exception)是 RuntimeException 或其子類別時，@Transactional 才會讓資料回朔，
	// 藉由 rollbackOn 可以指定發生哪個例外時，就可以讓資料回朔
	@Transactional(rollbackFor = Exception.class)
	@Override
	public BasicRes create(CreateReq req) {
		// 檢查參數
		BasicRes checkRes = checkParam(req);
		if(checkRes != null) {
			return checkRes;
		}
		// 因為是新增問卷，所以問卷的 id 一定是0，但 req 沒有 quiz_id，所以不用檢查
		
		// 因為 quiz 的 PK 是流水號，不會重複寫入，所以不用檢查資料料庫是否已存在相同的 PK
		// 新增問卷
		// 因為 Quiz 中的 id 是 AI 自動生成的流水號，要讓 quizDao 執行 save 後可以把該 id 的值回傳，
		// 必須要在 Quiz 此 Entity 中將資料型態為 int 的屬性 id
		// 加上 @GeneratedValue(strategy = GenerationType.IDENTITY)
		// JPA 的 save，PK 已存在於 DB，會執行 update，若PK不存在，則會執行 insert
		
		// 增加新增資料失敗: 使用 try-catch，包含 @Transactional 的資料回朔層級
		try {
			Quiz quiz = quizDao.save(new Quiz(req.getName(), req.getDescription(), req.getStartDate(), //
					req.getEndDate(), req.isPublished()));
			// 把 quiz_id 塞到 question 中的 quizId
			for(Question item : req.getQuestionList()) {
				item.setQuizId(quiz.getId());
			}
			// 新增問題: 把 question list 寫進 DB
			questionDao.saveAll(req.getQuestionList());
			return new BasicRes(ResMessage.SUCCESS.getCode(), //
					ResMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			return new BasicRes(ResMessage.DATA_SAVE_ERROR.getCode(), //
					ResMessage.DATA_SAVE_ERROR.getMessage());
		}
	}
	
	private BasicRes checkParam(CreateReq req) {
		// 使用排除法一一檢查
		if(!StringUtils.hasText(req.getName())) {
			return new BasicRes(ResMessage.PARAM_QUIZ_NAME_ERROR.getCode(), //
					ResMessage.PARAM_QUIZ_NAME_ERROR.getMessage());
		}
		if(!StringUtils.hasText(req.getDescription())) {
			return new BasicRes(ResMessage.PARAM_DESCRIPTION_ERROR.getCode(), //
					ResMessage.PARAM_DESCRIPTION_ERROR.getMessage());
		}
		if(req.getStartDate() == null) {
			return new BasicRes(ResMessage.PARAM_START_DATE_ERROR.getCode(), //
					ResMessage.PARAM_START_DATE_ERROR.getMessage());
		}
		if(req.getEndDate() == null) {
			return new BasicRes(ResMessage.PARAM_END_DATE_ERROR.getCode(), //
					ResMessage.PARAM_END_DATE_ERROR.getMessage());
		}
		// 檢查 開始時間不能比結束時間晚
		// 開始時間.isAfter(結束時間): 判斷 開始時間是否在結束時間之後
		if(req.getStartDate().isAfter(req.getEndDate())) {
			return new BasicRes(ResMessage.PARAM_DATE_ERROR.getCode(), //
					ResMessage.PARAM_DATE_ERROR.getMessage());
		}
		//====== 檢查問題內容
		List<Question> quesList = req.getQuestionList();
		if(quesList.size() <= 0) {
			return new BasicRes(ResMessage.PARAM_QUES_LIST_ERROR.getCode(), //
					ResMessage.PARAM_QUES_LIST_ERROR.getMessage());
		}
		for(Question item : quesList) {
			// 問題編號一定是從1開始，但無法檢查是否有按照順序以及中間是否有空缺的編號
			if(item.getQuesId() <= 0) {
				return new BasicRes(ResMessage.PARAM_QUES_ID_ERROR.getCode(), //
						ResMessage.PARAM_QUES_ID_ERROR.getMessage());
			}
			if(!StringUtils.hasText(item.getQuesName())) {
				return new BasicRes(ResMessage.PARAM_QUES_NAME_ERROR.getCode(), //
						ResMessage.PARAM_QUES_NAME_ERROR.getMessage());
			}
			if(!StringUtils.hasText(item.getType())) {
				return new BasicRes(ResMessage.PARAM_TYPE_ERROR.getCode(), //
						ResMessage.PARAM_TYPE_ERROR.getMessage());
			}
			// 檢查 1. type 是否是 單選、多選、文字(簡答)
			if(!QuesType.checkType(item.getType())) {
				return new BasicRes(ResMessage.QUES_TYPE_MISMATCH.getCode(), //
						ResMessage.QUES_TYPE_MISMATCH.getMessage());
			}
			// 檢查 2. 文字(簡答)類型時，options 不能有值
			if(item.getType().equalsIgnoreCase(QuesType.TEXT.getType())//
					&& StringUtils.hasText(item.getOptions())) {
				return new BasicRes(ResMessage.PARAM_OPTIONS_ERROR.getCode(), //
						ResMessage.PARAM_OPTIONS_ERROR.getMessage());
			}
		}		
		return null;
	}

	@Override
	public QuizSearchRes getAllQuiz() {
		List<Quiz> res = quizDao.getAllQuiz();
		return new QuizSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), res);
		
	}

	@Override
	public QuizSearchRes getQuiz(SearchReq req) {
		// 若是 name 沒有條件，前端帶過的的資料可能會是 null 或是 空字串
		// 改變條件值: 如果 name 是 null 或是 空字串 或是 全空白字串，一律替換成空字串
		String name = req.getName();
		if(!StringUtils.hasText(name)) {
			// SQL 語法裡， 欄位 like %% (兩個%中間是空字串)表示 忽略該欄位的條件值
			name = "";
		}
		LocalDate startDate = req.getStartDate();
		if(startDate == null) { // startDate == null 表示開始時間此欄位前端沒有帶值
			// 沒有帶值，可以把開始時間指定到一個很早的時間點
			startDate = LocalDate.of(1970, 1, 1);
		}
		LocalDate endDate = req.getEndDate();
		if(endDate == null) {
			// 沒有帶值，可以把結束時間指定到一個很久之後的時間點
			endDate = LocalDate.of(2999, 12, 31);
		}
		List<Quiz> res = quizDao.getQuiz(name, startDate, endDate);
		return new QuizSearchRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), res);
	}

	@Override
	public GetQuesRes getQuesByQuizId(int quizId) {
		if(quizId <= 0) {
			return new GetQuesRes(ResMessage.PARAM_QUIZ_ID_ERROR.getCode(), //
					ResMessage.PARAM_QUIZ_ID_ERROR.getMessage());
		}
		List<Question> res = questionDao.getByQuizId(quizId);
		return new GetQuesRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage(), res);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public BasicRes delete(DeleteReq req) throws Exception {
		try {
			quizDao.deleteByQuizIdIn(req.getQuizIdList());
			if(req.getQuizIdList().size() == 1) {
				throw new IOException("發生錯誤了~~~~");
			}
			questionDao.deleteByQuizIdIn(req.getQuizIdList());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
//			return new BasicRes(ResMessage.DATA_SAVE_ERROR.getCode(), //
//					ResMessage.DATA_SAVE_ERROR.getMessage());
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public BasicRes update(UpdateReq req) {
		// 檢查參數
		BasicRes checkRes = checkParam(req);
		if(checkRes != null) {
			return checkRes;
		}
		// 檢查 quizId 是否有資料存在
		int count = quizDao.selectCount(req.getQuizId());
		if(count != 1) {
			return new BasicRes(ResMessage.QUIZ_NOT_FOUND.getCode(), //
					ResMessage.QUIZ_NOT_FOUND.getMessage());
		}
		// 檢查問題 List 中的 quizId 是否與 req 中的 quizId 相符
		for(Question item : req.getQuestionList()) {
			if(req.getQuizId() != item.getQuizId()) {
				return new BasicRes(ResMessage.QUIZ_ID_MISMATCH.getCode(), //
						ResMessage.QUIZ_ID_MISMATCH.getMessage());
			}
		}
		try {
			// 1. 更新 quiz by quizId
			quizDao.updateById(req.getName(), req.getDescription(), req.getStartDate(), //
					req.getEndDate(), req.isPublished(), req.getQuizId());
			
			// 2. 刪除 questions by quizId
			questionDao.deleteByQuizIdIn(List.of(req.getQuizId()));
			
			// 3. 新增 questions
			questionDao.saveAll(req.getQuestionList());
		} catch (Exception e) {
			return new BasicRes(ResMessage.DATA_UPDATE_ERROR.getCode(), //
					ResMessage.DATA_UPDATE_ERROR.getMessage());
		}
		
		return new BasicRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage());
	}

	@Override
	public BasicRes createList(List<CreateReq> reqList) {
		for(CreateReq item : reqList) {
			create(item);
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), //
				ResMessage.SUCCESS.getMessage());
	}

}
