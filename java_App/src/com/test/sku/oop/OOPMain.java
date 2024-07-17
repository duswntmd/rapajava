package com.test.sku.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.text.IconView;

public class OOPMain {
//	static Member[] mems = new Member[5];
	static Scanner kbd = new Scanner(System.in);
	static List<Member> mems = new ArrayList<Member>();
	

	public static void main(String[] args) {
		
//			inputMember(); // 3인 정보 입력
			// 리스트에서 검색하여 번호가 11번인 회원 정보를 화면에 표시해보세요.
			initMens();
			updatePhoneByNo();
//			listUpdate();
//			listRemove();
//			listSerch();
//			memList()();
//			listTest();
			System.out.println("\t\t프로그램 종료");
			
//		User u = getUser();
//		String msg = login(u) ? "로그인 성공":"로그인 실패";
//		System.out.println(msg);
		
//		Scanner kbd = new Scanner(System.in);
//		System.out.print("아이디: ");
//		String id = kbd.next();
//		
//		System.out.print("암호: ");
//		String pwd = kbd.next();
//		
//		
//		String result = User(u) ? "로그인 성공":"로그인 실패";
//		System.out.println(result);
		
//		Board b = new Board(-1, "게시판테스트", "smith"); // 인스턴스 생성(객체생성), heap에 로드
//		Board b2 = new Board(2, "축하해요", "james");
//		Board b3 = new Board(3, "사랑해요", "Romeo");
		
//		b.title = "다른 제목으로 수정";
		
//		Board[] barr;
//		barr = new Board[3];
//		barr[0] = b;
//		barr[1] = b2;
//		barr[2] = b3;
//		
//		for(int i=0; i<barr.length; i++) {
//			System.out.printf("%d\t%s\t%s %n", 
//					barr[i].getNum(), 
//					barr[i].getTitle(), 
//					barr[i].getAuthor());
//		}
		
		
	}
	private static void initMens() { //출력 입력
		mems.add(new Member(11, "smith","010-2134-6345","smith@gmail.com"));
		mems.add(new Member(12, "james","010-5678-1234","games@yahoo.com"));
	}
	private static User getUser() { //출력 입력
		System.out.print("아이디 암호:");
		String input = kbd.nextLine().trim();
		String[] data = input.split("\\s+");
		User u = new User(data[0], data[1]);
		return u;
	}
	
	private static boolean login(User u) { //출력 입력
		return u.getId().equals("smith") && u.getPwd().equals("1234");
	}
	
	private static void inputMember() { //출력 입력
		
		while (true) {
			System.out.print("회원번호, 이름, 전화번호, 이메일 (빈 입력 시 종료): ");
            String input = kbd.nextLine();
            if (input.equals("")) break;
            String[] token = input.split("\\s+");
            Member m = new Member(token); //(Constructor Overload)
//            mems[cnt++] = m;
            mems.add(m);
		}
		System.out.println("\t\t회원정보 추가 성공");
	}
	private static void memList() {
		System.out.println("\t***회원목록***");
		for(int i =0; i < mems.size(); i++) 
		{
			System.out.println(mems.get(i));
		}	
	}
	private static void updatePhoneByNo() {
		//전화번호 갱신 대상 회원의 번호, 새 전화번호
		inputMember();
		System.out.print("변경대상 회원번호");
		int no = kbd.nextInt();
		kbd.nextLine();
		
		System.out.print("새 회원번호");
		String phone = kbd.nextLine();
		
		Member key = new Member(no, phone);
		if(mems.contains(key)) { //true/false
			int idx = mems.indexOf(key);
			mems.get(idx).setPhone(key.phone);
			System.out.println("전화번호 변경 성공");
		}else {
			System.out.println("전화번호 변경 실패");
		}
		memList();
	}
	private static void listUpdate() {
		inputMember();
		Member key = new Member(11,"sdf","010","dfs");
		if(mems.contains(key)) { //true/false
			Member rem = mems.set(mems.indexOf(key), key);
			if(rem != null) System.out.println("수정성공");
			else System.err.println("수정실패");
		}
		memList();
	}
	private static void listRemove() {
		inputMember();
		// 특정 번호를 가진 회원정보를 리스트에서 삭제해 보세요.
		Member key = new Member(11);
		Member update = new Member(11);
		boolean removed = mems.remove(key);
		System.out.println("삭제결과" + removed);
		memList();
	}
	private static void listSerch() {
		inputMember();
		Member key = new Member(11);
		if(mems.contains(key)) { //true/false
			int idx = mems.indexOf(key);
			Member found = mems.get(idx);
			System.out.println(found);
		}
	}
	
	private static void listTest() {
		//List 사용하기(CRUD)
		List<String> sList = new ArrayList<>();
		sList.add("Hello"); //Create
		sList.add("World");
		sList.add("홍길동");
		sList.add("홍길동");
		
		for(int i=0; i<sList.size(); i++) {
				String v = sList.get(i); //Read
				System.out.println(v);
		}
		
		System.out.println(sList.contains("World")); //true/false
		int idx = sList.indexOf("World");
		String value = sList.get(idx);
		System.out.println("검색 결과:" + value);
//		
		sList.set(2, "임꺽정");
		System.out.println(Arrays.toString(sList.toArray()));
		
		sList.remove("홍길동");
		sList.remove(2);
		System.out.println(Arrays.toString(sList.toArray()));
	}
	
}

/* 키보드에서 회원의 번호, 이름, 전화, 이메일을 입력받아서
 * Member 인스턴스를 생성하고 배열에 저장한 후에 이용자가 아무것도 입력하지 않고
 * 그냥엔터를 누르면 지금까지 입력된 회원정보를 화면에 목록으로 표시하는 기능을 작성해보세요
 * 
 */
class Member extends Object{ //Inheritance(상속) = extends | Object안쓰면 안보여도 자동으로 해줌
    int no;
    String name;
    String phone;
    String email;
    
    public Member(int no, String phone) {
        setNo(no);
        setPhone(phone);
    }
    
    public Member(int no, String name, String phone, String email) {
        setNo(no);
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    public Member(String[] token) { //생성자 오버로드(Constructor Overload)
    	int no = Integer.parseInt(token[0]);
        String name = token[1];
        String phone = token[2];
        String email = token[3];
        
        setNo(no);
        setName(name);
        setPhone(phone);
        setEmail(email);
	}

    public Member(int no) {
		setNo(no);
	}

	@Override
	public boolean equals(Object obj) {
		Member other = (Member)obj; 
		return this.getNo()==other.getNo();
	}

	@Override
	public String toString() {
		String str = String.format("%d\t%s\t%s\t%s", 
				this.getNo(),
				this.getName(),
				this.getPhone(),
				this.getEmail());
		return str;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}

class Board { //Inheritance 상속성, Polymorphism 다형성, Encapsulation 은닉성
	//번호, 제목, 작성자, 본문, 작성일, 히트수
	private int num;         //Access Modifier(public, protected, private, default)
	private String title;
	private String author;
	private String contents;
	private Date rDate;
	private int hits;
	
	// 개발자가 생성자를 정의하지 않으면 컴파일러는 자동으로 기본 생성자를 선언한다.
	public Board() {}
	// 생성된 인스턴스 멤버 변수를 초기화하는 생성자
	// 인스턴스가 생성된 직후에 자동으로 호출됨
	public Board(int num, String title, String author) { //기본생성자(Constuctor)
		setNum(num);
		setTitle(title);
		setAuthor(author);
	}
	public int getNum() {
		return num;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getContents() {
		return contents;
	}
	public Date getrDate() {
		return rDate;
	}
	public int getHits() {
		return hits;
	}
	public void setNum(int num) {
		if(num<=0) {
			System.err.println("num 초기와 실패(글번호는 0보다 커야합니다.)");
			return;
		}
		this.num = num;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
		
}
	// 키보드에서 입력된 아이디, 암호를 사용하여 User 클래스의 인스턴스를 초기화하고
	// booleean login(User u) 메소드를 작성하여 로그인 성공여부를 활인하는 기능을 작성해보세요.
	// Encapsulation 적용
class User{
	private String id;
	private String pwd;
	
	public User() {}

	public User(String id, String pwd) { //기본생성자(Constuctor)
//		setId(id);
//		setPwd(pwd);
		this.id = id;
		this.pwd = pwd;
	}
		
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}

/* Collection API
 * 
 * 다수개의 객체를 저장 및 관리하는 자료구조
 * List, set, Map
 * List : 저장할 때의 순서가 유지됨, 동일 객체의 중복저장 가능
 * Set : 순서유지 안됨, 중복저장 안됨
 * Map : 값만 저장하지 않고 값에 꼬리표 (key)를 붙여서 함께 저장(key, value 쌍으로 저장)
 */

