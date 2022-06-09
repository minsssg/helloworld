package com.helloworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.helloworld.model.Bbs;

public class BbsDAO extends DAO {
	
	private final int numberOfBbs = 6;
	
	public Bbs findById(Long id) {
		String query = 
				"SELECT bbsID, "
				+ "bbsTitle, "
				+ "userID, "
				+ "userName, "
				+ "bbsDate, "
				+ "bbsContent, "
				+ "latitude, "
				+ "longitude "
				+ "FROM bbs WHERE bbsID = ?";
		
		Bbs bbs = null;
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// Fix. 추후 빌더 패턴으로 수정할것.
				bbs = new Bbs();
				bbs.setId(rs.getLong(1));
				bbs.setTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setUserName(rs.getString(4));
				bbs.setBbsDate(rs.getString(5));
				bbs.setContent(rs.getString(6));
				bbs.setLatitude(rs.getDouble(7));
				bbs.setLongitude(rs.getDouble(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	public List<Bbs> findAll() {
		String query = "SELECT bbsID, "
				+ "bbsTitle, "
				+ "userID, "
				+ "userName, "
				+ "bbsDate, "
				+ "bbsContent, "
				+ "latitude, "
				+ "longitude "
				+ "FROM bbs";
		
		List<Bbs> bbsList = new ArrayList<>();
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bbs bbs = new Bbs();
				
				bbs = new Bbs();
				bbs.setId(rs.getLong(1));
				bbs.setTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setUserName(rs.getString(4));
				bbs.setBbsDate(rs.getString(5));
				bbs.setContent(rs.getString(6));
				bbs.setLatitude(rs.getDouble(7));
				bbs.setLongitude(rs.getDouble(8));
				
				bbsList.add(bbs);
			}
			
			return bbsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Bbs> getBbsPage(int pageNumber) {
		String query = "SELECT bbsID, "
				+ "bbsTitle, "
				+ "userID, "
				+ "userName, "
				+ "bbsDate, "
				+ "bbsContent, "
				+ "latitude, "
				+ "longitude "
				+ "FROM bbs "
				+ "LIMIT ? "
				+ "OFFSET ? ";
		
		List<Bbs> bbsList = new ArrayList<>();
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			pstmt.setInt(1, numberOfBbs);
			pstmt.setInt(2, numberOfBbs * (pageNumber - 1));
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bbs bbs = new Bbs();
				
				bbs = new Bbs();
				bbs.setId(rs.getLong(1));
				bbs.setTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setUserName(rs.getString(4));
				bbs.setBbsDate(rs.getString(5));
				bbs.setContent(rs.getString(6));
				bbs.setLatitude(rs.getDouble(7));
				bbs.setLongitude(rs.getDouble(8));
				
				bbsList.add(bbs);
			}
			
			return bbsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bbsList;
	}
	
	/**
	 * UserId를 통해 게시물 검색.
	 * @param userId
	 * @return
	 */
	public List<Bbs> getBbsListByUserId(String userId) {
		String query = "SELECT bbsID, "
				+ "bbsTitle, "
				+ "userID, "
				+ "userName, "
				+ "bbsDate, "
				+ "bbsContent, "
				+ "latitude, "
				+ "longitude "
				+ "FROM bbs "
				+ "WHERE userId = ?";
		
		List<Bbs> bbsList = new ArrayList<>();
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bbs bbs = new Bbs();
				
				bbs = new Bbs();
				bbs.setId(rs.getLong(1));
				bbs.setTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setUserName(rs.getString(4));
				bbs.setBbsDate(rs.getString(5));
				bbs.setContent(rs.getString(6));
				bbs.setLatitude(rs.getDouble(7));
				bbs.setLongitude(rs.getDouble(8));
				
				bbsList.add(bbs);
			}
			
			return bbsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bbsList;
	}
	
	public List<Bbs> getBbsPage(String userName, int pageNumber) {
		String query = "SELECT bbsID, "
				+ "bbsTitle, "
				+ "userID, "
				+ "userName, "
				+ "bbsDate, "
				+ "bbsContent, "
				+ "latitude, "
				+ "longitude "
				+ "FROM bbs "
				+ "WHERE userName LIKE ? "
				+ "LIMIT ? "
				+ "OFFSET ? ";
		
		List<Bbs> bbsList = new ArrayList<>();
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			pstmt.setString(1, "%" + userName + "%");
			pstmt.setInt(2, numberOfBbs);
			pstmt.setInt(3, numberOfBbs * (pageNumber - 1));
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bbs bbs = new Bbs();
				
				bbs = new Bbs();
				bbs.setId(rs.getLong(1));
				bbs.setTitle(rs.getString(2));
				bbs.setUserId(rs.getString(3));
				bbs.setUserName(rs.getString(4));
				bbs.setBbsDate(rs.getString(5));
				bbs.setContent(rs.getString(6));
				bbs.setLatitude(rs.getDouble(7));
				bbs.setLongitude(rs.getDouble(8));
				
				bbsList.add(bbs);
			}
			
			return bbsList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bbsList;
	}
	
	public Long bbsCount() {
		String query = "SELECT COUNT(*) FROM bbs";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	public Long bbsCount(String userName) {
		String query = "SELECT COUNT(*) FROM bbs WHERE userName LIKE ?";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query);) {
			
			pstmt.setString(1, "%" + userName + "%");
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0L;
	}
	
	/**
	 *  BbsDAO write 메서드
	 */
	public int write(Bbs bbs) {
		String query = "INSERT INTO BBS(bbsTitle, userID, userName, bbsDate, bbsContent, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (Connection con = getConnection();
			 PreparedStatement pstmt = con.prepareStatement(query)) {
			
			con.setAutoCommit(false); // off the auto-commit mode
			
			pstmt.setString(1, bbs.getTitle());
			pstmt.setString(2, bbs.getUserId());
			pstmt.setString(3, bbs.getUserName());
			pstmt.setString(4, bbs.getBbsDate());
			pstmt.setString(5, bbs.getContent());
			pstmt.setDouble(6, bbs.getLatitude());
			pstmt.setDouble(7, bbs.getLongitude());
			
			int result = pstmt.executeUpdate();
			
			con.commit(); // commit을 하지 않으면 업데이트가 안됨.
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return -1;
	}
	
	public boolean getAutoCommitMode() {
		try (Connection con = getConnection()) {
			return con.getAutoCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean getOffAutoCommitMode() {
		try (Connection con = getConnection()) {
			con.setAutoCommit(false);
			return con.getAutoCommit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
