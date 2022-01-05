package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//기본생성자
	public JDBConnect() {
		try {
			//오라클 드라이버 로드
			Class.forName("org.mariadb.jdbc.Driver");
			//커넥션URL생성
			String url = "jdbc:mariadb://127.0.0.1:3306/secondpj";
			String id = "secondpj";
			String pwd = "Kosmo99!!";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본생성자)");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//두번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			// DB에 연결
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println("DB 연결 성공(인자 생성자1)");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//세번째 생성자 
	public JDBConnect(ServletContext application) {
		
		//web.xml에 저장된 오라클 접속정보를 얻어온다. 
		String driver = application.getInitParameter("MariaDBDriver");
		String url = application.getInitParameter("MariaDBURL");
		String id = application.getInitParameter("MariaDBId");
		String pwd = application.getInitParameter("MariaDBPwd");
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			// DB에 연결
			con = DriverManager.getConnection(url,id,pwd);
			System.out.println("DB 연결 성공(인자 생성자2)");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
