package com.test.sku;

import java.util.Random;
import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {
		
//		System.out.println("Hello");
//		incDecTest();
//		printGugu();
//		printGugu7();
//		inputDanPrint();
//		notEquals();
//		logicalOpTest();
//		logicallogin();
		bitShifOp();
			
	}
	
	private static void incDecTest()
	{
		int v = 0;
		v++;
		System.out.println(v);
		++v;
		System.out.println(v);
		System.out.println(v++);
	}
	
	private static void printGugu()
	{
		int dan = 5;
		for(int i = 1; i < 10; i++) {
			int result = dan * i;
			System.out.printf("%d * %d = %d %n", dan, i, result);
		}
	}
	
	private static void printGugu7()
	{
		int dan = 7;
		int i =1;
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		System.out.printf("%d * %d = %d %n", dan, i, dan*i++);
		
	}
	
	private static void inputDanPrint()
	{
		Scanner kbd = new Scanner(System.in);
		System.out.print("구구단 수:");
		int dan = kbd.nextInt();
		int i =1;
		String guguFormString = "%d * %d = %d %n";
		
		
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		System.out.printf(guguFormString, dan, i, dan*i++);
		
	}
	
	private static void OddOrEven()
	{
		Scanner kbd = new Scanner(System.in);
		//내가한거
		System.out.print("수를 입력하세요: ");
        int su = kbd.nextInt();
        String a = su % 2 == 0 ? "짝수" : "홀수";
        System.out.println(su+ "는 " + a + "입니다.");
        
        //강사님이한거
		System.out.print("수를 입력하세요: ");
        int n = kbd.nextInt();
        String msg = String.format("%d는(은) %s입니다", n, n % 2 == 0 ? "짝수" : "홀수");
        System.out.println(msg);
	}
	
	private static void logicalAnd()
	{
		Boolean result = true && true;
		result = false && true; //단축연산자
		result = true || true;
		result = true || false; //단축논리 or 연산자
		result = false || true;
		result = false & true; //비단축연산자
		result = true | false;
		
	}
	
	private static void logicalOpTest()
	{
		Random rd = new Random();
		int a = rd.nextInt(10)+1; 
		int b = rd.nextInt(10)+1;
		String result = a % 2 == 1 && b % 2 == 1 ? "유효한 수" : "무효한 수";
		String msg = String.format("%d, %d는 %s입니다", a, b, result);
		System.out.printf(msg);
	}
	
	private static void logicallogin()
	{
		Scanner kbd = new Scanner(System.in);	
		System.out.print("아이디: ");
		String id = kbd.next();
		
		System.out.print("암호: ");
		String pwd = kbd.next();
		
		String msg = String.format("%s입니다", !(id.equals("smith") && pwd.equals("1234")) ? "로그인 실패" : "로그인 성공");
		System.out.println(msg);
	}
	
	private static void xor()
	{
		boolean result = true || true;
		result = true ^ true;
	}

	private static void bitShifOp()
	{
		int a = 1;
		int b = a << 1;
		System.out.println(b);
		
		b = a << 2;
		System.out.println(b);
	}
}
