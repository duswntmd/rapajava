package com.test.sku.textio;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserIO 
{
	static Scanner kbd = new Scanner(System.in);
	static String menu = "추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x):";
	
	static String showMenu() {
		System.out.println();
		System.out.print(menu);
		String m = kbd.nextLine().trim();
		return m;
	}

	public static void add() {  // 입력 > 파일에 저장 > 성공/실패 메시지
		System.out.print("글번호:");
		int no = kbd.nextInt();    kbd.nextLine();
		
		System.out.print("글제목:");
		String title = kbd.nextLine();
		
		System.out.print("작성자:");
		String author = kbd.nextLine();
		
		System.out.print("글내용:");
		String contents = kbd.nextLine();
		
		Date regDate = new Date();
		
		// VO에 담아서 파일에 저장하도록 한다(VO, DataIO)
		BoardVO board = new BoardVO();
		board.setNo(no);
		board.setTitle(title);
		board.setAuthor(author);
		board.setContents(contents);
		board.setRegDate(regDate);
		board.setHits(0);
		
		boolean saved = DataIO.saveBoard(board);
		
		if(saved) System.out.println("\t\t게시글 저장 성공");
		else System.err.println("\t\t글 저장 실패");
	}

	public static void list() 
	{
		List<BoardVO> list = DataIO.list();
		System.out.println();
		System.out.println("\t\t** 게시글 목록 **");
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}

	public static void find() {
		System.out.print("검색할 글번호:");
		int no = kbd.nextInt();     kbd.nextLine();
		
		BoardVO board = DataIO.findByNo(no);
		
		System.out.println("\n\t\t** 검색결과 **");
		if(board!=null) {
			System.out.println(board);  // 본글 외의 5가지 속성 출력
			System.out.println("글내용: " + board.getContents()); // 본글(내용)
		}else {
			System.err.println("검색된 글이 없습니다");
		}
	}

	public static void update() {
		System.out.print("검색할 글 제목:");
		String title = kbd.nextLine();
		
		BoardVO found = DataIO.findByTitle(title);
		if(found==null) {
			System.err.println("제목으로 검색된 내용이 없습니다");
			return;
		}
		
		System.out.print("새 제목:");
		String newTitle = kbd.nextLine();
		
		System.out.print("새 내용:");
		String newContents = kbd.nextLine();
		
		BoardVO b = new BoardVO();
		b.setNo(found.getNo());
		b.setTitle(newTitle);
		b.setContents(newContents);
		
		boolean updated = DataIO.update(b);
		
		if(updated) { System.out.println("수정 성공");}
		else {System.err.println("수정 실패");}
	}

	public static void delete() {
		System.out.print("삭제할 글 번호:");
		int no = kbd.nextInt();     kbd.nextLine();
		boolean deleted = DataIO.delete(no);
		if(deleted) System.out.println("삭제 성공");
		else System.err.println("삭제 실패");
	}

}
