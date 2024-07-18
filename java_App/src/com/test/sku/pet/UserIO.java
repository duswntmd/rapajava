package com.test.sku.pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.test.sku.textio.DataIO;


public class UserIO {

	static Scanner kbd = new Scanner(System.in);
	static String menu = "추가(a) 목록(s) 검색(f) 수정(u) 삭제(d) 종료(x):";
	
	public static String ShowMenu() {
		System.out.println();
		System.out.print(menu);
		String m = kbd.nextLine().trim();
		return m;
	}

	public static void add() {
		
		System.out.print("펫번호:");
		int no = kbd.nextInt();    kbd.nextLine();
		
		System.out.print("품종:");
		String species = kbd.nextLine();
		
		System.out.print("무게:");
		float weight = kbd.nextFloat();
		
		System.out.print("가격:");
		int price = kbd.nextInt();    kbd.nextLine();
		
		PetVO pet = new PetVO();
		pet.setNo(no);
		pet.setSpecies(species);
		pet.setWeight(weight);
		pet.setPrice(price);
		
//		boolean added = FileIO.sevePet(pet);
		boolean added = FileIO.addObject(pet);
		
		if(added) System.out.println("\t\t애완동물 저장 성공");
		else System.err.println("\t\t애완동물 저장 실패");
		
	}

	public static void list() {

		List<PetVO> list = FileIO.getList();
		Collections.sort(list);
		System.out.println("\t\t** 펫 목록 **");
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
		
	}

	public static void find() {
		
		System.out.print("검색할 펫번호:");
		int no = kbd.nextInt();     kbd.nextLine();
		
		PetVO pet = FileIO.getfindByNo(no);
		System.out.println("\n\t\t** 검색결과 **");
		if(pet!=null) {
			System.out.println(pet);  // 본글 외의 5가지 속성 출력
		}else {
			System.err.println("검색된 글이 없습니다");
		}
		
	}

	public static void update() {
		System.out.print("수정할 번호:");
		int no = kbd.nextInt();     kbd.nextLine();
		
		PetVO found = FileIO.getfindByTitle(no);
		if(found==null) {
			System.err.println("번호로 검색된 내용이 없습니다");
			return;
		}
		
		System.out.print("새 무게:");
		float newWeight = kbd.nextFloat();
		
		System.out.print("새 가격:");
		int newPrice = kbd.nextInt();    kbd.nextLine();
		
		PetVO p = new PetVO();
		p.setNo(found.getNo());
		p.setWeight(newWeight);
		p.setPrice(newPrice);
		
		boolean updated = FileIO.getupdate(p);
		
		if(updated) { System.out.println("수정 성공");}
		else {System.err.println("수정 실패");}
		
	}

	public static void delete() {
		System.out.print("삭제할 번호:");
		int no = kbd.nextInt();     kbd.nextLine();
		boolean deleted = FileIO.getdelete(no);
		if(deleted) System.out.println("삭제 성공");
		else System.err.println("삭제 실패");
		
	}

}
