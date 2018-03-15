package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cafe24.mysite.vo.GuestbookVo;

public class GuestbookDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void insertGuestbook(GuestbookVo vo) {
		
		try {
			conn = getConnection();
			String sql = "insert into guestbook values(null, ?, password(?), ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("방명록 등록 실패");
			} else {
				System.out.println("방명록 등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<GuestbookVo> getGuestbookList() {
		
		ResultSet rs = null;
		List<GuestbookVo> list = new ArrayList<>();
		try {
			conn = getConnection();
			String sql = "select no, name, content, reg_date "
						+ "from guestbook "
						+ "order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setRegdate(rs.getString(4));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void deleteGuestbook(long no, String password) {
		try {
			conn = getConnection();
			System.out.println(no+password);
			String sql = "delete from guestbook "
						+ "where no = ? "
						+ "and password = password(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public HashMap<Boolean, GuestbookVo> checkPassword(long no, String password) {
		ResultSet rs = null;
		HashMap<Boolean, GuestbookVo> checkPassword = new HashMap<>();
		try {
			conn = getConnection();
			String sql ="select no, name, content, reg_date "
					+ "from guestbook "
					+ "where no = ? "
					+ "and password = password(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setRegdate(rs.getString(4));
				checkPassword.put(true, vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			checkPassword.put(false, null);
			return checkPassword;
		}
		return checkPassword;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
