package com.test.sku.list;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListCRUD 
{
	//List Collection API를 사용한 CRUD 실습
	// 사원정보 관리 시스템
	static List<Employee> list = new ArrayList<>();
	static Scanner kbd = new Scanner(System.in);
	static {
		list.add(new Employee(11,"Smith", 2500, 20, "2020-05-12"));
		list.add(new Employee(12,"James", 2800, 10, "2021-07-20"));
		list.add(new Employee(13,"Laura", 3100, 20, "2019-08-20"));
		list.add(new Employee(14,"Blake", 3300, 30, "2022-10-22"));
	}
	
	public static void main(String[] args) 
	{
		// 사원 : Employee(데이터 모델)
		// empno, ename, sal, deptno, hiredate
		// 추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):
		// private, constructor, setter, getter, Override, Overload
		// 기능구현은 현재 클래스에 클래스 메소드 선언으로 해결
		boolean go = true;

		do 
		{
			switch(showMenu()) 
			{
				case "x": go = false;	break;
				case "a": add();		break;
				case "s": showList();	break;
				case "f": find();		break;
				case "u": update();		break;
				case "d": delete();		break;
			}
		}
		while(go);
		System.out.println("프로그램 종료");
	}
	
	private static String showMenu()
	{
		String mline = "추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):";
		System.out.printf("%n%s",mline);
		String menu = kbd.nextLine().trim();
		return menu;
	}
	
	private static void add()
	{
		System.out.print("사번 이름 급여 부서번호 입사일:");
		String input = kbd.nextLine().trim();
		if(!input.equals(""))
		{
			String[] info = input.split("\\s+");
			if(info.length != 5) {
				System.err.println("입력 항목이 부족합니다");
				return;
			}
			Employee emp = new Employee(info);
			list.add(emp);
			System.out.println("사원정보 저장 성공");
		}
	}
	
	private static void showList()
	{
		System.out.println("\n\t\t* 사원정보 목록 *");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static Employee searchByEmpno(int empno)
	{
		Employee key = new Employee(empno);
		Employee found = null;
		
		if(list.contains(key)){
			int idx = list.indexOf(key);
			found = list.get(idx);
		}
		return found;
	}
	
	private static List<Employee> searchByName(String ename)
	{
		List<Employee> found = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			Employee e = list.get(i);
			if(e.getEname().equals(ename)) {
				found.add(e);
			}
		}
		return found.size()==0 ? null : found;
	}
	
	private static void find() 
	{
		System.out.print("번호로 검색(i), 이름으로 검색(n):");
		String sub = kbd.nextLine().trim();
		
		if(sub.equals("i")) {
			System.out.print("검색할 사번:");
			int empno = kbd.nextInt();    		kbd.nextLine();
			Employee found = searchByEmpno(empno);
			if(found != null){
				System.out.println("\t\t* 번호로 검색한 결과 *");
				System.out.println(found);
			}
		}else if(sub.equals("n")) {
			System.out.print("검색할 이름:");
			String ename = kbd.nextLine().trim();
			List<Employee> found = searchByName(ename);
			System.out.println("\n\t* 이름으로 검색된 사원정보 목록 *");
			for(int i=0;i<found.size();i++) {
				System.out.println(list.get(i));
			}
		}
	}
	
	private static void update()
	{
		System.out.print("수정할 사번, 급여, 부서번호:");
		int empno = kbd.nextInt();
		int sal = kbd.nextInt();
		int deptno = kbd.nextInt();
		kbd.nextLine();
		
		Employee emp = new Employee(empno);
		if(list.contains(emp)) {
			list.get(list.indexOf(emp)).setSal(sal);
			list.get(list.indexOf(emp)).setDeptno(deptno);
			System.out.println("\t사원정보 수정 성공");
		}else {
			System.err.println("\t사원정보 수정 실패");
		}
	}
	
	private static void delete()
	{
		System.out.print("삭제할 사번:");
		int empno = kbd.nextInt();			kbd.nextLine();
		Employee key = new Employee(empno);
		boolean deleted = false;
		if(list.contains(key)) {
			 deleted = list.remove(key);
		}
		if(deleted) System.out.println("삭제 성공");
		else System.err.println("삭제 실패");
	}
}

