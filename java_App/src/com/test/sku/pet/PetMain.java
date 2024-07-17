package com.test.sku.pet;

public class PetMain {

	public static void main(String[] args) {
		/* Pet Store 관리 프로그램
		 * Pet(no, species(품종), weight, price)
		 * pets.data
		 * 이용자 입출력, 파일 입출력, PetVO, PetMain
		 * 추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x) 
		 */
		boolean go = true;
		while(go) {
			String m = UserIO.ShowMenu();
			switch (m) {
			case "x": go = false; break; 
			case "a": UserIO.add(); break; 
			case "s": UserIO.list(); break; 
			case "f": UserIO.find(); break; 
			case "u": UserIO.update(); break; 			
			case "d": UserIO.delete(); break; 			
			}
		
		}
		System.out.println("프로그램 종료");
	}
}
