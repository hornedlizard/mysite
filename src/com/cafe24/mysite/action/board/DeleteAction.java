package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.CheckAuth;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(CheckAuth.loginCheck(request, response) == false) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		if(CheckAuth.boardAuth(request, response) == false) {
			WebUtil.redirect(request, response, "/mysite/board?a=list");
			return;
		}
		
		long no = Long.parseLong(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		dao.delete(no);
		
		WebUtil.redirect(request, response, "/mysite/board?a=list");
	}

}
