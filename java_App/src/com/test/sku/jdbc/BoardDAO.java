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

public class BoardDAO 
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
   
   public List<Map<String, String>> getBoards()
   {
      String sql = "SELECT bid,title, "
            + "( "
            + "    SELECT COUNT(*) FROM attach a WHERE a.bid=b.bid "
            + ")attcnt "
            + "FROM board b";
      conn = getConn();
      try {
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         List<Map<String, String>> list = new ArrayList<>();
         
         while(rs.next()) {
            int bid = rs.getInt("BID");
            String title = rs.getString("TITLE");
            int attcnt = rs.getInt("ATTCNT");
            Map<String,String> map = new HashMap<>();
            map.put("BID", bid+"");
            map.put("TITLE", title);
            map.put("ATTCNT", attcnt+"");
            list.add(map);
         }
         return list;
      }catch(SQLException sqle) {
         sqle.printStackTrace();
      }
      return null;
   }
   
   
}
