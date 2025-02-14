package com.example.quiz12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.quiz12.constants.QuesType;
import com.example.quiz12.dao.QuizDao;
import com.example.quiz12.service.ifs.QuizService;
import com.example.quiz12.vo.DeleteReq;

//@SpringBootTest
class Quiz12ApplicationTests {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuizDao quizDao;

	@Test
	void contextLoads() {
		List<Integer> quizIdList = List.of(1);
		DeleteReq req = new DeleteReq();
		req.setQuizIdList(quizIdList);
		try {
			quizService.delete(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		String str = "text";
//		if(!str.equalsIgnoreCase(QuesType.SINGLE.getType())) {
//			System.out.println(false);
//		}
//		if(!str.equalsIgnoreCase(QuesType.MULTI.getType())) {
//			System.out.println(false);
//		}
//		if(!str.equalsIgnoreCase(QuesType.TEXT.getType())) {
//			System.out.println(false);
//		}
		//=========================================
//		if(!str.equalsIgnoreCase(QuesType.SINGLE.getType())//
//				|| !str.equalsIgnoreCase(QuesType.MULTI.getType())//
//				|| !str.equalsIgnoreCase(QuesType.TEXT.getType())) {
//			System.out.println(false);
//		}
		boolean boo = QuesType.checkType(str);
		System.out.println(boo);
	}
	
	@Test
	public void listTest() {
		List<String> strList = List.of("A", "B", "C");
		System.out.println(strList.contains("D"));
	}
	
	@Test
	public void forLoopTest() {
		List<Integer> intList = List.of(1, 2, 3, 4, 5);
		for(int item : intList) {
			if(item == 3) {
				//跳過當次
				continue;
			}
			System.out.println(item);
		}
		System.out.println("======================");
		for(int item : intList) {
			if(item == 3) {
				// 跳出整個迴圈
				break;
			}
			System.out.println(item);
		}
	}
	
	@Test
	public void mapTest() {
		Map<Integer, List<String>> map = new HashMap<>();
		map.put(1, List.of("A", "三明治", "三明治", "C"));
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		String str = String.join("", map.get(1)); // ABBC
		System.out.println(str); 
		int a = str.length();
		String newStr = str.replace("三明治", "");
		int b = newStr.length();
		System.out.println((a-b)/"三明治".length());
		
	}

}
