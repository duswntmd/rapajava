package com.test.sku;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class ControlStatement {

	public static void main(String[] args) 
	{
//		showGugu();
//		getSumRange();
//		getr();
//		getEven5();
//		ifTest();
//		dateTest();
//		variableInit();
//		login();
//		switchTest();
//		forTest();
//		forluf();
//		arrayTest();
//		arrayCRUD();
//		arrayCRUD1();
//		hello();
//		int res = add2(3, 5); // 정수 표현식
//		String msg =  greet("Smith");
//		System.out.println(msg);
//		int[] arr = getNums(5);
//		int res = sum(arr);
//		System.out.println("합계=" + res);
//		arrSort();
//		int[] arr = sortIntArr(10);
//		selectionSort(arr);
//		System.out.println("정렬 후" + Arrays.toString(sortIntArr(10))); //정렬 후 상태
//		Scanner kbd = new Scanner(System.in);
//		System.out.print("아이디: ");
//		String input = kbd.nextLine().trim();
//		String[] arr = input.split("\\s+"); //정규표현식을 받는다
//		String result = login2(arr[0], arr[1]) ? "로그인 성공":"로그인 실패";
//		System.out.println(result);
		
	}
	
	private static void showGugu() 
	{
		int dan = new Random().nextInt(8)+2;
		boolean go = true;
		int i = 0;
		while (go) 
		{
			System.out.printf("%d * %d = %d %n", dan, ++i, dan*i );
			go = i==9 ? false:true;
		}
	}
	private static void getSumRange() 
	{
		int i = new Random().nextInt(10);
		int start = i;
		int sum = 0;
		
		while (i++<=10) 
		{
			sum += i-1;
//			i = i + 1;
		}
		System.out.printf("%d ~ 10까지 합산= %d %n", start, sum );
	}
	private static void getr() 
	{
		int i = 1;
		int delta = 1;
		int cnt = 0;
		
		while (i>0) 
		{
			System.out.printf("%d %n", i );
			cnt++;
			delta = cnt >=  5 ? -1 : 1;
			i += delta;
		}
		
	}
	//while 문장을 사용하여 무작위 수 중에서 짝수가 5개 출력하기
	private static void getEven5() 
	{
		Random rd = new Random();
		int cnt = 0;
		while (cnt<5) 
		{
			int rn = rd.nextInt(20)+1;
			String s = rn%2==0 ? rn+" ":"";
			System.out.print(s);
			cnt += !s.equals("")?1:0;

		}
		
	}
	private static void ifTest() 
	{
		int i = 0;
		if(i==1)
		{
			System.out.println("참인 경우");			
		}		
		else if(i==2)
		{
			System.out.println("거짓인 경우");						
		}
		else
		{
			System.out.println("거짓인 경우");									
		}
		System.out.println("메소드 종료");			
	}
	private static void dateTest()
	{
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int M = cal.get(Calendar.MONTH)+1;
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int wd = cal.get(Calendar.DAY_OF_WEEK);
		int h = cal.get(Calendar.HOUR);
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		String sWeekDay = null;

		if		(wd == 1) sWeekDay = "일"; 
		else if (wd == 2) sWeekDay = "월";
		else if (wd == 3) sWeekDay = "화";
		else if (wd == 4) sWeekDay = "수";
		else if (wd == 5) sWeekDay = "목";
		else if (wd == 6) sWeekDay = "금";
		else if (wd == 7) sWeekDay = "토";
		
		System.out.printf("%d년-%d월-%d일 %s요일 %d:%d:%d%n", y, M, d, sWeekDay, h, m, s);			
	}
	private static void variableInit()
	{
		int v = 0;
		String s = null;
		s = ""; //요걸로
		String str = "";
		boolean result = str.equals(""); //NPE
		System.out.println(result);
	}
	private static void login()
	{
		Scanner kbd = new Scanner(System.in);
		int cnt = 0;
		int count = 4;
		boolean go = true;
		while(go)
		{
				
			System.out.print("아이디: ");
			String id = kbd.next();
			
			System.out.print("암호: ");
			String pwd = kbd.next();
			
			if(id.equals("smith") && pwd.equals("1234")) {
				System.out.println("로그인 성공");
				go = false;
			} 
			
			if(++cnt==4) {
				System.out.println("10분후에 다시 시도해보세요");
				go = false;
			}
			
			if(cnt!=4 && !(id.equals("smith") && pwd.equals("1234"))) {
				count--;
				System.out.printf("%d회남았습니다 %n", count);
			}
		}
	}
	//break가 없으면 if문으로 바꾸기 힘듬
	private static void switchTest()
	{
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int M = cal.get(Calendar.MONTH)+1;
		int d = cal.get(Calendar.DAY_OF_MONTH);
		int wd = cal.get(Calendar.DAY_OF_WEEK);
		int h = cal.get(Calendar.HOUR);
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		
//		int wd = 3;
		String sWeekDay = null;
		switch(wd)
		{
		case 1: sWeekDay = "일"; break;
		case 2: sWeekDay = "월"; break; 
		case 3: sWeekDay = "화"; break;
		case 4: sWeekDay = "수"; break;
		case 5: sWeekDay = "목"; break;
		case 6: sWeekDay = "금"; break;
		case 7: sWeekDay = "토"; break;
		default:
			
		}
		System.out.printf("%d년-%d월-%d일 %s요일 %d:%d:%d%n", y, M, d, sWeekDay, h, m, s);	
		
	}
	private static void forTest()
	{
		for(int i=10, delta=-1; i<=10; i+=delta)
		{
			System.out.printf(i + " ");	
			if( i==1 ) delta = 1;
		}
	}
	//continue : while, for, do while 문장에서 흐름을 다시 루프의 시작으로 돌린다
	//break : while, for, switch 문장에서 반복을 종료한다
	// 무작위 정수 10개를 출력, 모두 홀수여야 한다	
	private static void forluf()
	{
		Random rd = new Random();
		int n = 0;
		
		for(int cnt=0 ; cnt<10;)
		{
			n = rd.nextInt(20)+1;
				
			if( n % 2 == 1 ) 
			{
				System.out.printf("%d. %d %n", ++cnt, n);
			}
		}
	}	
	private static void arrayTest()
	{
		int[] nums; //배열변수 선언
		//new는 동적인 메모리할당 연산자
		nums = new int[5]; // 정수 5개 공간 확보(할당)
		nums[0] = 3;
		nums[1] = 4;
		nums[2] = 5;
		nums[3] = 6;
		nums[4] = 7;
		for(int i = 0; i < nums.length; i++)
		{
			System.out.printf("%d", nums[i]);
		}
	}
	private static void arrayCRUD()
	{
		// 키보드에서 회원정보를 입력받아서 배열에 저장하고 회원의 목록을 표시하는 기능
		// 프로그램이 시작되면, 번호, 이름, 전화버호를 입력 받을 수 있는 화면이 표시되고
		// 다수의 회원정보를 입력할 수 있어야 하고 이용자가 아무런 값도 입력하지 않고 엔터를 누르면
		// 입력을 종료하고 지금까지 입력된 회원목록을 화면에 표시해보세요.
		// 배열의 원소수는 처음에 10개로 확보한다.
		Scanner kbd = new Scanner(System.in);
		int[] num = new int[10];
		String[] name = new String[10];
		String[] phone = new String[10];
		int cnt = 0;
		while(true) {
			System.out.print("번호: ");
			String sNo = kbd.nextLine(); 
			
			System.out.print("이름: ");
			String nm = kbd.nextLine();
			
			System.out.print("전화: ");
			String ph = kbd.nextLine();
			
			if(sNo.equals("")) {
				System.out.println("입력 종료");
				break;
			}
				//문자열로 표현된 숫자를 가감스제가 가능한 숫자로 변환하려면 어떻게 해야할까
			int no = Integer.parseInt(sNo);
			
			num[cnt] = no;
			name[cnt] = nm;
			phone[cnt] = ph;
			cnt++;
		}
		
		System.out.println("\t***회원목록***");
		for(int i =0; i < cnt; i++) {
			System.out.printf("%d\t%s\t%s%n", num[i], name[i], phone[i]);
		}
	}
	private static void arrayCRUD1()
	{
		// 키보드에서 회원정보를 입력받아서 배열에 저장하고 회원의 목록을 표시하는 기능
		// 프로그램이 시작되면, 번호, 이름, 전화버호를 입력 받을 수 있는 화면이 표시되고
		// 다수의 회원정보를 입력할 수 있어야 하고 이용자가 아무런 값도 입력하지 않고 엔터를 누르면
		// 입력을 종료하고 지금까지 입력된 회원목록을 화면에 표시해보세요.
		// 배열의 원소수는 처음에 10개로 확보한다.
		Scanner kbd = new Scanner(System.in);
		int[] num = new int[10];
		String[] name = new String[10];
		String[] phone = new String[10];
		int cnt = 0;
		while(true) {
			System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x): ");
			String menu = kbd.nextLine();
			if(menu.equals("s")) { //목록보기
				System.out.println("\t***회원목록***");
				for(int i =0; i < cnt; i++) 
				{
					System.out.printf("%d\t%s\t%s%n", num[i], name[i], phone[i]);
				}	
			}else if(menu.equals("a")) {
				System.out.print("회원번호: ");
				String sNo = kbd.nextLine();
				int no = Integer.parseInt(sNo);
				
				System.out.print("이름: ");
				String nm = kbd.nextLine();
				
				System.out.print("전화번호: ");
				String ph = kbd.nextLine();
				
				num[cnt] = no;
				name[cnt] = nm;
				phone[cnt] = ph;
				cnt++;
				
			}else if(menu.equals("u")) {
				//번호, 새 전화번호
				//번호로 검색해서 그 회원의 전화번호를 새로 갱신한다.
				System.out.print("회원번호: ");
				int no = kbd.nextInt();
				kbd.nextLine(); //이거 없으면 menu 2번나옴
				System.out.print("새 전화번호: ");
				String newphone = kbd.nextLine();
				boolean updated = false;
				for (int i = 0; i < cnt; i++) {
				if (num[i]==no) {
					phone[i] = newphone;
					System.out.println("전화번호 변경 성공.");
					updated = true;
					break;
					}
				}
				if(!updated)System.out.println("전화번호 변경 실패");
				
			}else if(menu.equals("d")) {
				// 삭제대상 회원번호를 입력받아서 우측 값으로 덮어쓰기, cnt--, 성공/실패 메시지 띄워주기
				System.out.print("삭제대상 회원번호: ");
                int no = kbd.nextInt();
                kbd.nextLine();
                boolean deleted = false;
                for (int i = 0; i < cnt; i++) {
                    if (num[i]==no) {
                        for (int j = i; j < cnt - 1; j++) {
                        	//덮어쓰기 기능
                            num[j] = num[j + 1];
                            name[j] = name[j + 1];
                            phone[j] = phone[j + 1];
                        }
                        cnt--;
                        deleted = true;
                        System.out.println("삭제가 완료되었습니다.");
                        break;
                    }
                }
                if (!deleted) System.out.println("일치하는 회원번호가 없습니다.");
				
			}else if(menu.equals("f")) {
				//번호, 전화번호를 검색
				System.out.print("회원번호로 검색(n), 전화번호로 검색(p):");
				String fc = kbd.nextLine();
				int idx = -1;
				if(fc.equals("n")) {
					System.out.print("회원번호로 찾기: ");
					int no = kbd.nextInt();
					kbd.nextLine();
					for (int i = 0; i < cnt; i++) {
					if (num[i]==no) {
						idx = i;
						break;
						}
					}
				}
				if(fc.equals("p")) {
					System.out.print("전화번호로 찾기: ");
					String ph = kbd.nextLine();
					for (int i = 0; i < cnt; i++) {
					if (phone[i].equals(ph)) {
						idx = i;
						break;
						}
					}
				}
				if(idx!=-1) {
					System.out.println("\t\t검색된 회원정보");
					System.out.printf("%d\t%s\t%s %n", num[idx], name[idx], phone[idx]);				
				}else {					
					System.out.println("\t\t검색실패");					
				}
				
			}else if(menu.equals("x")) {
				break;
			}
			
		} //end of main loop
		System.out.println("프로그램 종료");
	} //end of method()
	//메소드(Method : 코드의 집합에 이름을 붙인 것)
	private static void hello() { //function:기능,함수 static이 붙은건 클래스메서드
		System.out.println("Hello");
	}
	private static void add() { //입력 파라미터가 없고 리턴 데이터도 없다
		int a = 5;
		int b = 4;
		
		System.out.printf("%d + %d = %d %n", a, b, a+b);
	}
	private static void add(int a, int b) { //입력 파라미터가 있지만 리턴 데이터가 없다
		System.out.printf("%d + %d = %d %n", a, b, a+b);
	}
	private static int add2(int a, int b) { //입력 파라미터가 있지만 리턴 데이터가 없다
		return a+b;
	}
	//greet 메소드에 회원의 이름을 전달하면 그 이름에 "Hello" 가 추가되어 리턴되고 리턴된 문자열을 화면에 표시하는 기능을 작성해보세요
	private static String greet(String name) { //입력 파라미터가 있지만 리턴 데이터가 없다
		return "Hello" + name;
	}
	// sum이라는 이름의 메소드를 정의하고 배열을 받아서 그 배열의 합을 리턴하는 기능
	private static int sum(int[] arr) { //입력 파라미터가 있지만 리턴 데이터가 없다
		int total = 0;
		for (int i=0; i<arr.length; i++) {
			total += arr[i];
		}
		return  total ;
	}
	// getNums 메소드를 호출하면 무작위 정수 원소로 하는 배열이 리턴되도록 해보세요
	// 파아미터로 전달하는 숫자만큼 배열의 원소수를 지정하여배열이 생성되도록 해보세요
	private static int[] getNums(int cnt) { //입력 파라미터가 있지만 리턴 데이터가 없다
//		50까지
		Random rd = new Random();
		int[] arr = new int[cnt];
		for(int i =0; i<cnt; i++) {
			arr[i] = rd.nextInt(50)+1;
			System.out.printf("랜덤숫자%d = %d %n", i, arr[i]);
		}
		return  arr;
	}
	// 선택정렬 알고리즘을 사용하여 무작위로 추출된 배열의 원소를 오름차순으로 정렬해보세요
	private static int[] arrSort() { //입력 파라미터가 있지만 리턴 데이터가 없다
		//swap 알고리즘
//		int a = 5;
//		int b = 3;
//		int tmp = a;
//		a = b;
//		b = tmp; // ?
		// 무작위 정수배열 원소 10개를 준비
		int[] arr = new int[10];
		Random rd = new Random();
		for(int i=0; i<arr.length; i++) {
			arr[i] = rd.nextInt(20);
		}
		System.out.println("정렬 전:" + Arrays.toString(arr)); //정렬전 상태
		
		//선택 정렬 
		for(int i=0; i<arr.length-1; i++) { //배열의 방번호 선택
			for(int j=i+1; j<arr.length; j++) { // 현재 선택된 값과 비교할 우측값
				if(arr[i]>arr[j]) { // 정렬대상인지 확인
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		System.out.println("정렬 후" + Arrays.toString(arr)); //정렬 후 상태
		return null;
	}
	// sortIntArr 메소드를 작성하고 파라미터로 정수를 전달하면
	// 파라미터가 의미하는 객수만큼 정수배열을 생성하고 정렬하여 정령된 배열을 
	// 리턴하는 메소드를 선언라고 호출하여 그 리턴값을 화면에 표시해보세요.
	// 선택정렬 알고리즘을 구현한 메소드(selectionSort)를 선언하고 활용해보세요
	private static int[] sortIntArr(int cnt) { 
		int[] arr = new int[cnt];
		Random rd = new Random();
		for(int i=0; i<arr.length; i++) {
			arr[i] = rd.nextInt(20);
		}
		
		return selectionSort(arr);
	}
	private static int[] selectionSort(int[] arr) { //출력 입력
		for(int i=0; i<arr.length-1; i++) { //배열의 방번호 선택
			for(int j=i+1; j<arr.length; j++) { // 현재 선택된 값과 비교할 우측값
				if(arr[i]>arr[j]) { // 정렬대상인지 확인
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		return arr;
	}
	// 키보드에서 아이디, 암호를 입력 받아서 로그인하고 그 결과를
	//로그인 성공/실패로 표시하는 기능을 작성해 보세요
	//boolean login(String id, String pwd)
	private static Boolean login2(String id, String pwd) { //출력 입력
		return id.equals("smith") && pwd.equals("1234");
	}
}