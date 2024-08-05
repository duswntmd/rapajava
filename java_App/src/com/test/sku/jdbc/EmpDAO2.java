package com.test.sku.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Namespace;

public class EmpDAO2 
{
   //오라클 접속기능, 접속해제 기능
   //목록보기, 부서정보, 상세정보 : Read
   //사원정보 추가, 사원정보 수정, 검색, 삭제
   //사원정보 추가(add()), 사번으로 검색(findByEmpno()),
   //부서번호로 검색(findByDeptno()), 직무명칭으로 검색(findbyJob()),
   //사번으로 삭제(deleteByEmpno())
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Connection getConn() 
   {
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         return conn;
      }catch(Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   
   public Map<Integer, String[]> getEmpsByDept()
   {
      String sql = "SELECT deptno, COUNT(empno) \"사원수\", "
            + "LISTAGG(ename,',') WITHIN GROUP(ORDER BY deptno) names "
            + "FROM emp2 "
            + "GROUP BY deptno";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         Map<Integer, String[]> map = new HashMap<>();
         while(rs.next()) {
            int deptno = rs.getInt("DEPTNO");
            int cnt = rs.getInt("사원수");
            String names = rs.getString("NAMES");
            map.put(deptno, names.split("\\,"));
         }
         return map;
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }
      return null;
   }
   
   public  PageItem getPage1(int page, int ipp) {
//	   String sql = "SELECT * FROM "
//               + "("
//               + "    SELECT t2.*, TRUNC((RN-1)/3+1) AS page FROM "
//               + "    ( "
//               + "        SELECT t.*, ROWNUM RN FROM "
//               + "        ( "
//               + "            SELECT * FROM emp2, "
//               + "            ( "
//               + "               SELECT CEIL(COUNT(*)/3) total FROM emp2 "
//               + "            ) "
//               + "        )t "
//               + "    )t2 "
//               + ") "
//               + "WHERE page=?";			
	   String sql = "SELECT * FROM "
               + "("
               + "  SELECT t2.*, TRUNC((RN-1)/?+1) AS page FROM "
               + "  ("
               + "    SELECT t.*, ROWNUM RN FROM "
               + "    ("
               + "      SELECT empno, "
               + "             LPAD(' ', (LEVEL-1)*3, ' ') || ename ename, "
               + "             sal, deptno, job, hiredate, total "
               + "      FROM emp2, "
               + "           (SELECT CEIL(COUNT(*)/?) total FROM emp2) "
               + "      START WITH mgr IS NULL "
               + "      CONNECT BY PRIOR empno = mgr "
               + "      ORDER SIBLINGS BY empno "
               + "    ) t"
               + "  ) t2 "
               + ") WHERE page = ?"; 

	   conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, ipp);
	         pstmt.setInt(2, ipp);
	         pstmt.setInt(3, page);
	         rs = pstmt.executeQuery();
	         
	         List<EmpVO> list = new ArrayList<>();
	         PageItem pageItems = new PageItem();
	         
	         int i = 0;
	         while(rs.next()) {
	            if(i==0) {
	               int currPage = rs.getInt("PAGE");
	               int totalPages = rs.getInt("TOTAL");
	               pageItems.setCurrPage(currPage);
	               pageItems.setTotalPages(totalPages);
	            }
	            int empno = rs.getInt("EMPNO");
	            String ename = rs.getString("ENAME");
	            java.sql.Date hiredate = rs.getDate("HIREDATE");
	            int deptno = rs.getInt("DEPTNO");
	            int salary = rs.getInt("SAL");
	            String job = rs.getString("JOB");
	            
	            EmpVO emp = new EmpVO();
	            emp.setEmpno(empno);
	            emp.setEname(ename);
	            emp.setDeptno(deptno);
	            emp.setHiredate(hiredate);
	            emp.setSal(salary);
	            emp.setJob(job);
	            
	            list.add(emp);
	            i++;
	         }
	         pageItems.setList(list);
	         return pageItems;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	   
	   return null;
   }
   
   public List<EmpVO> getPage(int page, int ipp) {
	   String sql = "SELECT * FROM\r\n"
			   	   + "(\r\n"
				   + "   SELECT t.*, ROWNUM RN FROM\r\n"
				   + "   (\r\n"
				   + "       SELECT * FROM emp2\r\n"
				   + "   )t\r\n"
				   + ") WHERE RN BETWEEN ? AND ?"; // WHERE page=2
	   conn = getConn();
	   try {
		pstmt = conn.prepareStatement(sql);
		int end = page * ipp;
		int start = end-(ipp-1);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		rs = pstmt.executeQuery();
		List<EmpVO> list = new ArrayList<>();
		while(rs.next()) {
			int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            int deptno = rs.getInt("DEPTNO");
            int salary = rs.getInt("SAL");
            String job = rs.getString("JOB");
            
            EmpVO emp = new EmpVO();
            emp.setEmpno(empno);
            emp.setEname(ename);
            emp.setDeptno(deptno);
            emp.setHiredate(hiredate);
            emp.setSal(salary);
            emp.setJob(job);
            
            list.add(emp);
		}
		return list;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	   
	   return null;
   }
   
   public List<EmpVO> getList() //empno,ename,sal,deptno,job,hiredate,mgr,comm
   {
      conn = getConn();
      try {
         pstmt = conn.prepareStatement("SELECT * FROM emp2");
         rs = pstmt.executeQuery();
         List<EmpVO> list = new ArrayList<>();
         while(rs.next())
         {
            int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            int salary = rs.getInt("SAL");
            
            EmpVO emp = new EmpVO();
            emp.setEmpno(empno);
            emp.setEname(ename);
            emp.setHiredate(hiredate);
            emp.setSal(salary);
            list.add(emp);
         }
         return list;
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }finally {
         closeAll();
      }
      return null;
   }
   
   public boolean updateSal(EmpVO emp) {
	   conn = getConn();
	   try {
		   String sql = "UPDATE emp2 SET sal=? WHERE empno=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, emp.getSal());
		   pstmt.setInt(2, emp.getEmpno());
		   int rows = pstmt.executeUpdate();
		   return rows>0;
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return false;
   }
   
   public boolean add(EmpVO emp) {
	      conn = getConn();
	      try {  	  
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	         String sDate = sdf.format(emp.getHiredate());
	         String sql = "INSERT INTO emp2(empno, ename, deptno, sal, hiredate, job) VALUES (?, ?, ?, ?, ?, ?)";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, emp.getEmpno());
			 pstmt.setString(2, emp.getEname());
			 pstmt.setInt(3, emp.getDeptno());
			 pstmt.setInt(4, emp.getSal());
			 pstmt.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
			 pstmt.setString(6, emp.getJob());
	         
	         int rows = pstmt.executeUpdate();
	         return rows>0;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }
	      return false;
   }
   
   public List<EmpVO> findByEmpno(int empno) {
	   conn = getConn();
	      try {
	    	 pstmt = conn.prepareStatement("SELECT * FROM emp2 WHERE empno=?");
	    	 pstmt.setInt(1, empno);
	         rs = pstmt.executeQuery();
	         List<EmpVO> list = new ArrayList<>();
	         while(rs.next())
	         {
	        	 
//	            int empno = rs.getInt("EMPNO");
	            String ename = rs.getString("ENAME");
	            int deptno = rs.getInt("DEPTNO");
	            java.sql.Date hiredate = rs.getDate("HIREDATE");
	            int salary = rs.getInt("SAL");
	            
	            EmpVO emp = new EmpVO();
	            emp.setEmpno(empno);
	            emp.setEname(ename);
	            emp.setDeptno(deptno);
	            emp.setHiredate(hiredate);
	            emp.setSal(salary);
	            list.add(emp);
	         }
	         return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      }
	      return null;
   }
   
   public List<EmpVO> findByJob(String job) {
	   conn = getConn();
	      try {
	    	 pstmt = conn.prepareStatement("SELECT * FROM emp2 WHERE job=?");
	    	 pstmt.setString(1, job);
	         rs = pstmt.executeQuery();
	         List<EmpVO> list = new ArrayList<>();
	         while(rs.next())
	         {
	        	 
	            int empno = rs.getInt("EMPNO");
	            String ename = rs.getString("ENAME");
	            int deptno = rs.getInt("DEPTNO");
	            java.sql.Date hiredate = rs.getDate("HIREDATE");
	            int salary = rs.getInt("SAL");
	            
	            EmpVO emp = new EmpVO();
	            emp.setEmpno(empno);
	            emp.setEname(ename);
	            emp.setDeptno(deptno);
	            emp.setHiredate(hiredate);
	            emp.setSal(salary);
	            emp.setJob(job);
	            list.add(emp);
	         }
	         return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }finally {
	         closeAll();
	      }
	      return null;
   }
   
   public boolean delete(int empno) {
	   conn = getConn();
	   try {
		   String sql = "DELETE FROM emp2 WHERE empno=?";
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setInt(1, empno);
		   int rows = pstmt.executeUpdate();
		   return rows>0;
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   return false;
   }
   
   public List<Map<String,String>> getJoinResult(int deptno){
	   String sql = "SELECT e.empno, e.ename, e.deptno, d.dname, s.grade \"호봉\"\r\n"
               + "FROM emp2 e JOIN dept d ON e.deptno = d.deptno\r\n"
               + "JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal\r\n"
               + "WHERE e.deptno = ?"
               + "ORDER BY 호봉 DESC";
	      conn = getConn();
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, deptno);
	         rs = pstmt.executeQuery();
	         List<Map<String,String>> list = new ArrayList<>();
	         Map<String, String> map = null;
	         while(rs.next()) {
	            map = new HashMap<>();
	            int empno = rs.getInt("EMPNO");
	            String ename = rs.getString("ENAME");
	            int dno = rs.getInt("DEPTNO");
	            String dname = rs.getString("DNAME");
	            int grade = rs.getInt("호봉");
	            
	            map.put("EMPNO", ""+empno);
	            map.put("ENAME", ename);
	            map.put("DEPTNO", ""+dno);
	            map.put("DNAME", dname);
	            map.put("GRADE", ""+grade);
	            list.add(map);
	         }
	         return list;
	      }catch(SQLException sqle) {
	         sqle.printStackTrace();
	      }
	      return null;
   }
   
   private void closeAll() {
      try {
         if(rs!=null) rs.close();
         if(pstmt!=null) pstmt.close();
         if(conn!=null) conn.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
}
