package com.example.quiz12;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ExceptionTest {
	
	@Test
	public void test() {
		try {
			int x = 5;
			int y = 2;
			System.out.println(x/y);
		} catch (Exception e) {
			System.out.println(e);
			// 寫 log 紀錄
			return;
		} finally {
			System.out.println("!!!!!!!!!!!");
		}{
			
		}
		System.out.println("===================");
	}
	
	@Test
	public void test1() {
		Scanner scan = new Scanner(System.in);
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			scan.close();
		}
		int a = scan.nextInt();
	}
	
	@Test
	public void test2() throws Exception {
		Scanner scan = new Scanner(System.in);
		String str = scan.next();
		try(Scanner scan1 = new Scanner(System.in)) {
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
