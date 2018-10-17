package net.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

@Component
public class DBOpen {	

	public DBOpen() {
		System.out.println("----DBOpen() ��ü ������...");
	}

	public Connection getConnection() { // �����ͺ��̽� ����
		// 1)����Ŭ DB
		/*
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "java0927";
		String password = "1234";
		String driver = "oracle.jdbc.driver.OracleDriver";
		*/
		
		// 2)My-SQL DB
		
		String url ="jdbc:mysql://localhost:3306/radesk"; 
		String user ="root"; 
		String	password="Soldesk1234~"; 
		String driver ="org.gjt.mm.mysql.Driver";
		
		/*
		//3)���� DB
		String url = "jdbc:mysql://localhost:3306/choul3416";
	    String user = "choul3416";
	    String password = "rlacjfdn135^";
	    String driver = "com.mysql.jdbc.Driver";
		*/
		
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("DB ���� ���� : " + e);
		}

		return con;

	}// end

}// class end
