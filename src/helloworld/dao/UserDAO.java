package helloworld.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import helloworld.model.User;

public class UserDAO {

	private String url = "jdbc:mysql://localhost:3306/BBS?verifyServerCertificate=false&useSSL=false&serverTimezone=Asia/Seoul";
	private String user = "root";
	private String password = "root";
	
	public UserDAO() {
		// Driver Class Loading
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public void close(Statement stmt, Connection con) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		
		if (con != null) {
			con.close();
		}
	}
	
	public User findById(String id) {
		String SQL = "SELECT	userID"
				+ ",	userPassword"
				+ ",	userName"
				+ ",	userGender"
				+ ",	userPhoneNumber "
				+ "FROM user WHERE userID = ?";
		
		User user = null;
		
		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL)) {
			pstmt.setNString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(
							rs.getNString(1),
							rs.getNString(2),
							rs.getNString(3),
							rs.getNString(4),
							rs.getNString(5)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
