package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

public class BoardDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void insert(BoardVo vo) {
		try {
			conn = MyConnection.getConnection();
			String sql = "insert into board "
							+ "select "
							+ "null, ?, ?, now(), 0, "
//							+ "ifnull(ifnull(?, max(group_no)+1), 1), ifnull(?, 1), ifnull(?, 0), 1 " 
							+ "if(?=0, max(group_no)+1, ?), "
							+ "if(?=0, 1, ?+1), "
							+ "if(?=0, 0, ?), 1 " 
							+ "from board";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getGroupNo());
			pstmt.setLong(4, vo.getGroupNo());
			pstmt.setLong(5, vo.getOrderNo());
			pstmt.setLong(6, vo.getOrderNo());
			pstmt.setLong(7, vo.getDepth());
			pstmt.setLong(8, vo.getDepth());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("게시판 등록 실패");
			} else {
				System.out.println("게시판 등록 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<BoardVo> getList() {
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<>();
		try {
			conn = MyConnection.getConnection();
			String sql = "select a.no, a.title, a.user_no, b.name, a.hits, a.regdate "
						+ "from board a, users b "
						+ "where a.user_no = b.no "
						+ "order by a.regdate desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				UserVo userVo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				userVo.setNo(rs.getLong(3));
				userVo.setName(rs.getString(4));
				vo.setUserVo(userVo);
				vo.setHits(rs.getLong(5));
				vo.setRegdate(rs.getString(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public BoardVo getBoard(long no) {
		ResultSet rs = null;
		BoardVo vo = new BoardVo();
		try {
			conn = MyConnection.getConnection();
			String sql = "select a.no, a.title, a.content, a.user_no, b.name, a.hits, a.regdate "
						+ "from board a, users b "
						+ "where a.no = ? "
						+ "and a.user_no = b.no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVo userVo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				userVo.setNo(rs.getLong(4));
				userVo.setName(rs.getString(5));
				vo.setUserVo(userVo);
				vo.setHits(rs.getLong(6));
				vo.setRegdate(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
	
	public boolean update(BoardVo vo) {
		boolean result = false;
		
		try {
			conn = MyConnection.getConnection();
			String sql = "update board " + 
						"set title = ?, content = ? " + 
						"where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			int count = pstmt.executeUpdate();
			if (count == 0) {
				System.out.println("게시물 수정 실패");
			} else {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
