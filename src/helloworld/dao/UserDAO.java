package helloworld.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public int join(User newUser) {
		
		User alreadyExistUser = findById(newUser.getId());
		
		if (alreadyExistUser != null) {
			return -1;
		}
		
		String query = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			pstmt.setNString(1, newUser.getId());
			pstmt.setNString(2, newUser.getPassword());
			pstmt.setNString(3, newUser.getName());
			pstmt.setNString(4, newUser.getGender());
			pstmt.setNString(5, newUser.getPhoneNumber());
			
			return pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -2;
	}

	public List<User> findAll() {

		List<User> userList = new ArrayList<>();
		
		String query = "SELECT	userID"
				+ ",	userPassword"
				+ ",	userName"
				+ ",	userGender"
				+ ",	userPhoneNumber "
				+ "FROM user";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userList.add(new User(
							rs.getNString(1),
							rs.getNString(2),
							rs.getNString(3),
							rs.getNString(4),
							rs.getNString(5)
						));
			}
			
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
