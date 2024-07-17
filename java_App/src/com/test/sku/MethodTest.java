package com.test.sku;

import java.util.Scanner;

public class MethodTest {

	//Member Variables
	/* static variable(class variable): 프로그램 시작할 떄 자동으로 메모리에 로드됨
	 * instance variable: 클래스의 인스턴스가 생성될 때 로드됨
	 */
	//클래스 변수
	static int[] num = new int[10];
	static String[] name = new String[10];
	static String[] phone = new String[10];
	static Scanner kbd = new Scanner(System.in);
	static int cnt;
	public static void main(String[] args) {
		
			// 키보드에서 회원정보를 입력받아서 배열에 저장하고 회원의 목록을 표시하는 기능
			// 프로그램이 시작되면, 번호, 이름, 전화버호를 입력 받을 수 있는 화면이 표시되고
			// 다수의 회원정보를 입력할 수 있어야 하고 이용자가 아무런 값도 입력하지 않고 엔터를 누르면
			// 입력을 종료하고 지금까지 입력된 회원목록을 화면에 표시해보세요.
			// 배열의 원소수는 처음에 10개로 확보한다.
			
			while(true) {
				System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x): ");
				String menu = kbd.nextLine();
				if(menu.equals("s"))       list();
				else if(menu.equals("a"))  add();
			    else if(menu.equals("u"))  update();
				else if(menu.equals("d"))  delete();
				else if(menu.equals("f"))  find();
				else if(menu.equals("x"))  break;
					
			} //end of main loop
			System.out.println("프로그램 종료");
	} // end of main()
	private static void list() {
		System.out.println("\t***회원목록***");
		for(int i =0; i < cnt; i++) 
		{
			System.out.printf("%d\t%s\t%s%n", num[i], name[i], phone[i]);
		}	
	}
	private static void add() {
		// no변수에 있는 번호가 이미 등록된 상태인지 아닌지 확인하는 메소드
		// 이미 등록된 번호라면 해당 인덱스를 리턴하고, 아니면 -1을 리턴한다
//		int res = isFound(no);
		int no = -1;
		/*
		do 
		{
			System.out.print("회원번호: ");
			String sNo = kbd.nextLine();
			no = Integer.parseInt(sNo);
		} 
		while (isFound(no) != -1);*/
		while (true) {
			System.out.print("회원번호: ");
			String sNo = kbd.nextLine();
			no = Integer.parseInt(sNo);
			if(isFound(no) != -1) {
				System.out.println("\n이미 등록된 번호입니다.");
			}else break;
		}
		
		System.out.print("이름: ");
		String nm = kbd.nextLine();
		
		System.out.print("전화번호: ");
		String ph = kbd.nextLine();
		
		num[cnt] = no;
		name[cnt] = nm;
		phone[cnt] = ph;
		cnt++;
		System.out.println("\t\t회원정보 추가 성공");
	}
	/** 
	 * num 배열을 검사하여 no 파라미터가 이미 존재하면 그 인덱스를 리턴함
	 * @param no num 배열에서 검사할 회원번호
	 * @return no가 num 배열에서 발견되면 그 인덱스, 아니면 -1
	 */
	private static int isFound(int no) {
		for(int i=0; i<num.length; i++) {
			if(num[i]==no) {
				return i;
			}
		}
		return -1;
	}
	private static void update() {
		//번호, 새 전화번호
		//번호로 검색해서 그 회원의 전화번호를 새로 갱신한다.
		int idx = 0;
		while(true) {
		System.out.print("회원번호: ");
		int no = kbd.nextInt();
		kbd.nextLine(); //이거 없으면 menu 2번나옴
		idx = isFound(no);
		if(idx==-1) {
				System.out.println("\n"+no+"는(은) 없는 회원번호입니다");
			}else break;
		}
		System.out.print("새 전화번호: ");
		String newphone = kbd.nextLine();
		
		phone[idx] = newphone;
		System.out.println("\t\t전화번호 변경 성공.");
	}
	private static void delete() {
		// 삭제대상 회원번호를 입력받아서 우측 값으로 덮어쓰기, cnt--, 성공/실패 메시지 띄워주기
		int idx = -1;
		while(true) {
			System.out.print("삭제대상 회원번호: ");
			int no = kbd.nextInt();
			kbd.nextLine();
			idx = isFound(no);
			if(idx==-1) {
				System.out.println("\\n"+no+"는(은) 없는 회원번호입니다.");
			}else break;
		}
        for (int i = idx; i < cnt - 1; i++) {
        	//덮어쓰기 기능
            num[i] = num[i + 1];
            name[i] = name[i + 1];
            phone[i] = phone[i + 1];
        }
        cnt--;
        System.out.println("삭제가 완료되었습니다.");    
	}
	private static void find() {
		//번호, 전화번호를 검색
		// searchByNo(), searchByPhon()
		System.out.print("회원번호로 검색(n), 전화번호로 검색(p):");
		String m = kbd.nextLine();
		int idx = -1;
		if(m.equals("n")) {
			idx = searchByNo();
		}else if(m.equals("p")){
			idx = searchByPhon();
		}
		if(idx!=-1) {
			System.out.println("\t\t검색된 회원정보");
			System.out.printf("%d\t%s\t%s %n", num[idx], name[idx], phone[idx]);				
		}else {					
			System.out.println("\t\t검색실패");					
		}
	}
	private static int searchByNo() {
		int idx = -1;
		System.out.print("회원번호로 찾기: ");
		int no = kbd.nextInt();
		kbd.nextLine();
		idx = isFound(no);
		return idx;
	}
	 private static int searchByPhon() {
	    int idx = -1;
	    System.out.print("전화번호로 찾기: ");
	    String ph = kbd.nextLine();
	    for (int i = 0; i < cnt; i++) {
	        if (phone[i].equals(ph)) {
	        	idx = i;
	        	break;
	        }
	    }
	    return idx;
	}
}
