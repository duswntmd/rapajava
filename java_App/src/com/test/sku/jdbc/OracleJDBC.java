package com.test.sku.jdbc;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OracleJDBC 
{
   static enum MENU{ADD,LIST,FIND,JOIN,UPDATE,DELETE,PAGE,EXIT};
   static Scanner kbd = new Scanner(System.in);
   static EmpDAO2 dao = new EmpDAO2();
   
   public static void main(String[] args) 
   {
      boolean go = true;
      while(go) {
         switch(showMenu()) {
         case ADD:       add();       break;
         case LIST:      list();      break;
         case FIND:      find();      break;
         case JOIN:      join();      break;
         case UPDATE:    update();    break;
         case DELETE:    delete();    break;
         case PAGE:      page();      break;
         case EXIT:      go=false;    break;
         }
      }
      System.out.println("프로그램 종료");
   }

   static MENU showMenu() {
      System.out.print("추가(a), 목록(s), 검색(f), 조인결과(j), 수정(u), 삭제(d), 페이지(p), 종료(x):");
      String m = kbd.nextLine().trim();
      MENU menu = null;
      switch(m) {
      case "a": menu = MENU.ADD;       break;
      case "s": menu = MENU.LIST;      break;
      case "f": menu = MENU.FIND;      break;
      case "j": menu = MENU.JOIN;      break;
      case "u": menu = MENU.UPDATE;    break;
      case "d": menu = MENU.DELETE;    break;
      case "p": menu = MENU.PAGE;      break;
      case "x": menu = MENU.EXIT;      break;
      }
      return menu;
   }
   
   static void add() {
      System.out.println("사번:");
      int empno = kbd.nextInt();      kbd.nextLine();
      System.out.println("이름:");
      String ename = kbd.nextLine().trim();
      System.out.println("부서번호:");
      int deptno = kbd.nextInt();     kbd.nextLine();
      System.out.println("급여:");
      int sal = kbd.nextInt();        kbd.nextLine();
      System.out.println("입사일:");
      String sDate = kbd.nextLine().trim();
      System.out.println("직무:");
      String job = kbd.nextLine().trim();
      
      EmpVO emp = new EmpVO();
      emp.setEmpno(empno);
      emp.setEname(ename);
      emp.setDeptno(deptno);
      emp.setSal(sal);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      java.sql.Date hiredate = null;
      try {
         hiredate = new java.sql.Date(sdf.parse(sDate).getTime());
      }catch(Exception e) {
         e.printStackTrace();
      }
      emp.setHiredate(hiredate);
      emp.setJob(job);
      
      boolean added = dao.add(emp);
      if(added) System.out.println("추가 성공");
      else System.err.println("추가 실패");
   }
   
   private static void list() {
      List<EmpVO> list = dao.getList();
      for(int i=0;i<list.size();i++) {
         System.out.println(list.get(i));
      }
   }
   
   private static void find() {
      System.out.print("검색대상 사번:");
      int empno = kbd.nextInt();     kbd.nextLine();
      List<EmpVO> emp = dao.findByEmpno(empno);
      System.out.println(emp==null ? "검색실패":emp.toString());
   }
   
   
   private static void update() {
	  System.out.print("삭제대상 사번:");
      int empno = kbd.nextInt();
      System.out.print("사번 새급여액:");
      int newSal = kbd.nextInt();       kbd.nextLine();
      
      EmpVO emp = new EmpVO();
      emp.setEmpno(empno);
      emp.setSal(newSal);
      
      boolean updated = dao.updateSal(emp);
      if(updated) System.out.println("수정 성공");
      else System.err.println("수정 실패");
   }
   
   private static void delete() {
      System.out.print("삭제대상 사번:");
      int empno = kbd.nextInt();       kbd.nextLine();

      boolean deleted = dao.delete(empno);
      if(deleted) System.out.println("삭제 성공");
      else System.err.println("삭제 실패");
   }
   
   private static void join() {
	      System.out.print("부서번호:");
	      int deptno = kbd.nextInt();       kbd.nextLine();

	      List<Map<String, String>> list = dao.getJoinResult(deptno);

//	      for (Map<String, String> result : list) {
//	          System.out.println(result);
//	      }
	      for(int i=0; i<list.size(); i++) {
	          Map<String, String> map = list.get(i);
	          String sEmpno = map.get("EMPNO");
	          String ename = map.get("ENAME");
	          String sDeptno = map.get("DEPTNO");
	          String dname = map.get("DNAME");
	          String grade = map.get("GRADE");
	          String line = String.format("%s\t%s\t%s\t%s\t%s", sEmpno,ename,sDeptno,dname,grade);
	          System.out.println(line);
	       }
   }
   
   private static void page() {
	   System.out.print("페이지 번호:");
	    int page = kbd.nextInt(); kbd.nextLine();
	    System.out.print("페이지당 항목 수:");
	    int ipp = kbd.nextInt(); kbd.nextLine();

	    List<EmpVO> list = dao.getPage(page, ipp);
	    for (int i = 0; i < list.size(); i++) {
	        System.out.println(list.get(i));
	    }
   }
}
