package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cafe24.mysite.vo.UserVo;

public class UserDao {
	
	public UserVo get(String email, String password) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select no, name, email, gender " + 
					"from users " + 
					"where email = ? " + 
					"and password=password(?);";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new UserVo();
				result.setNo(rs.getLong(1));
				result.setName(rs.getString(2));
				result.setEmail(rs.getString(3));
				result.setGender(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();					
				}
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = MyConnection.getConnection();
			String sql = "insert into users " + 
						"values (null, ?, ?, password(?), ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			int count = pstmt.executeUpdate();
			if (count != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();					
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "update users " + 
						"set name = ?, password=password(?), gender = ? " + 
						"where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("회원 정보 수정 실패");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
