package com.helloworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.helloworld.model.User;


public class UserDAO extends DAO {
	
	public User findById(String id) {
		String query = "SELECT	userID"
				+ ",	userPassword"
				+ ",	userName"
				+ ",	userGender"
				+ ",	userPhoneNumber "
				+ "FROM user WHERE userID = ?";
		
		User user = null;
		
		try (Connection con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setNString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// Fix. 추후 빌더 패턴으로 수정할것.
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
	
	public int login(String id, String password) {
		
		User user = findById(id);
		
		if (user == null) return -1; // 아이디가 존재하지 않습니다.
		
		String query = "SELECT userID, "
				+ "userPassword, "
				+ "userName, "
				+ "userGender, "
				+ "userPhoneNumber "
				+ "FROM user "
				+ "WHERE userID = ? "
				+ "AND userPassword = ?";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			user = null;
			
			if (rs.next()) {
				user = new User(rs.getNString(1)
						, rs.getNString(2)
						, rs.getNString(3)
						, rs.getNString(4)
						, rs.getNString(5));
			}
			
			if (user == null) return 0; // 비밀번호가 올지 않습니다.
			
			return 1; // 로그인 성공.
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -2;		
	}
	
	public int join(User newUser) {
		
		User alreadyExistUser = findById(newUser.getId());
		
		if (alreadyExistUser != null) {
			return -1;
		}
		
		String query = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			// start transaction block
			con.setAutoCommit(false); // default true;
			
			pstmt.setNString(1, newUser.getId());
			pstmt.setNString(2, newUser.getPassword());
			pstmt.setNString(3, newUser.getName());
			pstmt.setNString(4, newUser.getGender());
			pstmt.setNString(5, newUser.getPhoneNumber());
			
			int result = pstmt.executeUpdate();
			
			// end transaction block, commit changes
			con.commit();
			
			// good practice to set it back to default true
			con.setAutoCommit(true);
			
			return result;
		
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
