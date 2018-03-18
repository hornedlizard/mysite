package com.cafe24.mvc.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.UserVo;

public class CheckAuth {

	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		if (user == null) {
			// 인증 실패
			request.setAttribute("result", "fail");
			return false;
		}
		return true;
	}
	
	public static boolean boardAuth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		long no = Long.parseLong(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		long userNo = dao.getBoard(no).getUserVo().getNo();
		
		if (user.getNo() != userNo) {
			return false;
		}
		return true;
	}
	
	
}
