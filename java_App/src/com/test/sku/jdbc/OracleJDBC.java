package com.test.sku.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OracleJDBC 
{
   public static void main(String[] args) 
   {
	  EmpDAO dao = new EmpDAO();
//      List<EmpVO> list = dao.getList();
//      
//      for(EmpVO emp : list) {
//         System.out.println(emp);
//      }
	  EmpVO emp = new EmpVO(7369);
	  emp.setSal(880);
	  
	  boolean updated = dao.updateSal(emp);
	  System.out.println("수정결과:" + updated);
	  
//	  EmpVO emp = new EmpVO();
//	  emp.setEmpno(7777);
//	  emp.setEname("sss");
//	  emp.setJob("Clerk");
//	  emp.setMgr(7902);
//	  String hireDateString = "2022-07-31";
//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//      LocalDate hireLocalDate = LocalDate.parse(hireDateString, formatter);
//      Date hireDate = Date.valueOf(hireLocalDate);
//      emp.setHiredate(hireDate);
//	  emp.setSal(1000);
//
//	  boolean added = dao.add(emp);
//	  System.out.println("추가 결과: " + added);
   }
}
