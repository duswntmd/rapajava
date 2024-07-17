package com.test.sku.textio;
/* 프로그램 코드 분리의 예

 * main loop, UserIO, DataIO, VO(Value Object)
 * 
 * main loop : 프로그램 흐름 전체 관리
 * UserIO : 이용자로부터 입력 및 이용자에게 출력(키보드, 모니터)
 * DataIO : 데이터 파일 입,출력,수정,삭제,검색
 * VO(Value Object) : 데이터모델
 * 
 * 게시판 프로그램
 * BoardVO(번호, 제목, 작성자, 작성일, 히트수, 본문)
 */
public class TextIOMain 
{
	public static void main(String[] args)
	{
		boolean go = true;
		while(go)
		{
			String m = UserIO.showMenu();
			switch(m)
			{
				case "x": go=false; 		break;
				case "a": UserIO.add();		break;
				case "s": UserIO.list();	break;
				case "f": UserIO.find();	break;
				case "u": UserIO.update();	break;
				case "d": UserIO.delete();	break;
			}
		}
		System.out.println("프로그램 종료");
	}
}
