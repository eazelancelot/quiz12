package com.example.quiz12.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quiz12.entity.Feedback;
import com.example.quiz12.entity.FeedbackId;
import com.example.quiz12.vo.FeedbackDto;
import com.example.quiz12.vo.StatisticsDto;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, FeedbackId>{
	
	@Query(value = "select count(quiz_id) from feedback where quiz_id = ?1 and email = ?2", nativeQuery = true)
	public int selectCount(int quizId, String email);
	
	/**
	 * 1. 因為使用 join 撈取多張表的資料，撈回來的資料用 FeedbackDto 裝載</br>
	 * 2. FeedbackDto 不是資料表，所以 nativeQuery = false</br>
	 * 3. nativeQuery = false 時，語法中原本表的名稱會變成 Entity class 名稱，欄位的名稱會變成屬性變數名稱</br>
	 * 4. FeedbackDto 不像 Entity 有被 Spring boot 託管(class 上有加上 @Entity 的註釋)，所以必須要透過
	 *    new FeedbackDto() 的方式來建構(建構方法)，且前面要加上完整路徑(com.example.quiz12.vo)</br>
	 * 5. 語法中的建構方法寫法在 FeedbackDto 中也要有對應的建構方法 
	 */
	@Query(value = "select new com.example.quiz12.vo.FeedbackDto(Qz.name, Qz.description, F.userName, " //
			+ " F.email, F.age, F.answer, F.fillinDate, Qu.quesId, Qu.quesName) " //
			+ " from Quiz as Qz join Question as Qu on Qz.id = Qu.quizId " //
			+ " join Feedback as F on Qz.id = F.quizId and Qu.quesId = F.quesId " //
			+ " where Qz.id = ?1", //
			nativeQuery = false)
	public List<FeedbackDto> selectFeedbackByQuizId(int quizId);
	
	@Query(value = "select new com.example.quiz12.vo.StatisticsDto(" //
			+ " Qz.name, Qu.quesId, Qu.quesName, Qu.required, Qu.options, Qu.type, F.answer)" //
			+ " from Quiz as Qz" //
			+ " join Question as Qu on Qz.id = Qu.quizId " //
			+ " join Feedback as F on Qz.id = F.quizId and Qu.quesId = F.quesId " //
			+ " where Qz.id = ?1", //
			nativeQuery = false)
	public List<StatisticsDto> statistics(int quizId);

}
