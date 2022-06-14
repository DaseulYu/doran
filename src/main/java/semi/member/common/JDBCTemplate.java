package semi.member.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	   // 필드
	   
	   // static 메서드에서 
	   // 해당 필드를 사용하려면
	   // 필드도 static 이여야 한다.
	   private static Connection conn; // 초기값 null
	   //private static Connection conn = null; // 초기값 null
	   
	   
	   // 메서드
	   
	   // DB 연결 정보를 담고있는 Connection 객체 반환 메서드
	   public static Connection getConnection() {
	      try {
	    	  Context initContext = new InitialContext();

	    	  Context envContext = (Context)initContext.lookup("java:/comp/env");
	    	  
	    	  DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
	    	  
	    	  conn = ds.getConnection();
		      conn.setAutoCommit(false);
	         
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      return conn;
	   }
	   
	   
	   // close() 메서드 작성
	   
	   // Connection 반환 메서드
	   public static void close(Connection conn) { 
	      try {
	         // 참조하는 Connection이 있으면서 닫혀있지 않은 경우
	         if(conn != null && !conn.isClosed()) {
	            // conn.isClosed() : 닫혀있으면 true
	            conn.close();
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	   
	   // Statement(부모), PreparedStatement(자식) 반환 메서드(다형성 적용)
	   public static void close(Statement stmt) { 
	      try {
	         // 참조하는 Statement가 있으면서 닫혀있지 않은 경우
	         if(stmt != null && !stmt.isClosed()) {
	            stmt.close();
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	   
	   
	   // ResultSet 반환 메서드
	   public static void close(ResultSet rs) { 
	      try {
	         // 참조하는 ResultSet이 있으면서 닫혀있지 않은 경우
	         if(rs != null && !rs.isClosed()) {
	            rs.close();
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	      
	   
	   // 트랜잭션 제어
	   
	   // commit 메서드
	   public static void commit(Connection conn) { 
	      try {
	         // 참조하는 Connection이 있으면서 닫혀있지 않은 경우
	         if(conn != null && !conn.isClosed()) {
	            conn.commit();
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	      
	   
	   // rollback 메서드
	   public static void rollback(Connection conn) { 
	      try {
	         // 참조하는 Connection이 있으면서 닫혀있지 않은 경우
	         if(conn != null && !conn.isClosed()) {
	            conn.rollback();
	         }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   

}
