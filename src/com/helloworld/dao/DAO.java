package com.helloworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	protected final String url = "jdbc:mysql://localhost:3306/BBS?serverTimezone=Asia/Seoul";
	protected final String user = "root";
	protected final String password = "root";
	protected final String driverClassName = "com.mysql.cj.jdbc.Driver";
	
	protected DAO() {
		// Driver Class Loading
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	protected void close(Statement stmt, Connection con) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		
		if (con != null) {
			con.close();
		}
	}
}
