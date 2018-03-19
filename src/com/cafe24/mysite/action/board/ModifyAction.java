package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.CheckAuth;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo user = (UserVo) request.getSession().getAttribute("authUser");
		
		/*if (user == null) {
			// 인증 실패
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}*/
		/*if (user.getNo() != userNo) {
			request.setAttribute("result", "fail");			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
			return;
		}*/
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
		BoardVo vo = dao.getBoard(no);
		/*String content = request.getParameter("content");
		request.setAttribute("no", no);
		request.setAttribute("title", title);*/
		request.setAttribute("vo", vo);
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}

}
