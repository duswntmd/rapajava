package com.test.sku.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ListCRUD {
	
	static List<Employee> emps = new ArrayList<Employee>();
	static Scanner kbd = new Scanner(System.in);
	// List Collection API를 사용한 CRUD 실습
	// 사원정보 관리 시스템
	public static void main(String[] args) {
		// 사원 : Employee(데이터 모델)
		// empno(사원번호), ename(사원이름)String, deptno(부서번호), sal(급여), hiredate(고용일)date
		// 추가(a), 목록(s), 검색(f), 수정(u), 삭제(d), 종료(x):
		// private, constructor, setter, getter, Override, Overload
		// 기능구현은 현재 클래스에 클래스 메소드 선언으로 해결
		
		//날짜 다루기(java.util.Date)
//		Date hiredate = new Date();
		
//		String sDate = "2020-10-21";
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date hireDate = sdf.parse(sDate); // 문자열로 표현된 날짜를  Date 오브젝트 형식으로
//			
//			sDate = sdf.format(hireDate); // Date 오브젝트를 문자열로 변환
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		initEmps();
		menu();
		System.out.println("프로그램 종료");
	}
	
	private static void menu() {
		
		while(true) {
			System.out.print("목록(s), 추가(a), 수정(u), 삭제(d), 검색(f), 종료(x): ");
			String menu = kbd.nextLine();
			
			if(menu.equals("s"))       list();
			else if(menu.equals("a"))  add();
		    else if(menu.equals("u"))  update();
			else if(menu.equals("d"))  delete();
			else if(menu.equals("f"))  find();
			else if(menu.equals("x"))  break;
			}
	}
	
	private static void initEmps() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
            emps.add(new Employee(11, "smith", 3, 300, sdf.parse("2022-03-02")));
            emps.add(new Employee(12, "james", 4, 400, sdf.parse("2023-05-07")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	private static void list() {
		
		System.out.println("\t\t***사원정보 목록***");
		for(int i =0; i < emps.size(); i++) {
			System.out.println(emps.get(i));
		}	
	}
	
	private static void add() { 
		
		while (true) {
			System.out.print("사원번호, 사원이름, 부서번호, 급여, 고용일,(빈 입력 시 종료): ");
            String input = kbd.nextLine();
            String[] token = input.split("\\s+");
            
            int empno = Integer.parseInt(token[0]);
            boolean isDuplicate = false;
            for (Employee emp : emps) {
                if (emp.getEmpno() == empno) {
                    System.out.println("이미 존재하는 사원번호입니다. 다시 입력해주세요.");
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                continue; // 중복되었으므로 다시 입력받음
            }
            
            if (!isDuplicate) {
                // 중복이 아니면 사원 추가
                Employee m = new Employee(token);
                emps.add(m);
                System.out.println("\t\t사원정보 추가 성공");
                break; 
            }
		}
	}
	
	private static void update() {
		
		System.out.print("변경 대상 사원번호: ");
		int empno = kbd.nextInt();
		kbd.nextLine(); 
		
		Employee key = new Employee(empno);
		if(emps.contains(key)) {
			int idx = emps.indexOf(key);
			Employee emp = emps.get(idx);

//			System.out.print("사원이름: ");
//			String ename = kbd.nextLine();
			System.out.print("부서번호: ");
			int deptno = kbd.nextInt();
			System.out.print("급여: ");
			int sal = kbd.nextInt();
			kbd.nextLine(); 
//			System.out.print("고용일 (yyyy-MM-dd): ");
//			String hiredateStr = kbd.nextLine();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date hiredate = null;
//			try {
//				hiredate = sdf.parse(hiredateStr);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			
//			emp.setEname(ename);
			emp.setDeptno(deptno);
			emp.setSal(sal);
//			emp.setHiredate(hiredate);

			System.out.println("\t\t사원정보 변경 성공");
		} else {
			System.out.println("사원정보 변경 실패: 해당 사원이 존재하지 않습니다.");
		}
	}
	
	private static void delete() {
		
		System.out.print("삭제할 사원의 번호를 입력하세요: ");
		int empno = kbd.nextInt();
		kbd.nextLine();
		
		Employee key = new Employee(empno);
		boolean removed = emps.remove(key);
		System.out.println("삭제결과: " + removed);
	}
	
	private static void find() {
		System.out.print("번호찾기(b), 이름찾기(n): ");
		String fc = kbd.nextLine();
		
		if(fc.equals("b"))       findNo();
		if(fc.equals("n"))       findName();
	}
	
	private static void findNo() {
		System.out.print("찾을 사원의 번호를 입력하세요: ");
		int empno = kbd.nextInt();
		kbd.nextLine(); 
		
		Employee key = new Employee(empno);
		if(emps.contains(key)) {
			int idx = emps.indexOf(key);
			Employee found = emps.get(idx);
			System.out.println(found);
		} else {
			System.out.println("사원번호를 찾을 수 없습니다.");
		}
	}
	
	 private static void findName() {
	        System.out.print("찾을 사원의 이름을 입력하세요: ");
	        String ename = kbd.nextLine();
	        
	        int idx = -1;
	        for (int i = 0; i < emps.size(); i++) {
	            if (emps.get(i).getEname().equals(ename)) {
	                idx = i;
	                break;
	            }
	        }
	        
	        if (idx != -1) {
	            Employee found = emps.get(idx);
	            System.out.println(found);
	        } else {
	            System.out.println("사원이름을 찾을 수 없습니다.");
	        }
	 }
	
}

class Employee { 
	
	private int empno;
	private String ename;
	private int deptno;
	private int sal;
	private Date hiredate;
	
	public Employee() {}
	
	public Employee(int empno) {
		setEmpno(empno);
	}
	
	public Employee(String ename) {
		setEname(ename);
	}
	
	public Employee(int empno, String ename, int deptno, int sal, Date hiredate) {
		setEmpno(empno);
		setEname(ename);
		setDeptno(deptno);
		setSal(sal);
		setHiredate(hiredate);
    }
	
	public Employee(String[] token) { 
    	int empno = Integer.parseInt(token[0]);
        String ename = token[1];
        int deptno = Integer.parseInt(token[2]);
        int sal = Integer.parseInt(token[3]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hiredate = null;
        try {
            hiredate = sdf.parse(token[4]);
        } catch (ParseException e) {
            e.printStackTrace();
        }    
        setEmpno(empno);
		setEname(ename);
		setDeptno(deptno);
		setSal(sal);
		setHiredate(hiredate);
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(this.getHiredate());
        
		String str = String.format("%d\t%s\t%d\t%d\t%s", 
				this.getEmpno(),
				this.getEname(),
				this.getDeptno(),
				this.getSal(),
				formattedDate);
		return str;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
    
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Employee other = (Employee) obj;
	    return empno == other.empno;
//	    Employee other = (Employee) obj;
//	    return getEmpno() == other.getEmpno();
	}
	
}