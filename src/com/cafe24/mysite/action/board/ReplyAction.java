package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserVo user = (UserVo) session.getAttribute("authUser");
		if (user == null) {
			// 인증 실패
			request.setAttribute("result", "fail");
			WebUtil.redirect(request, response, "/mysite/user?a=loginform");
			return;
		}
		
		BoardDao dao = new BoardDao();
		long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = dao.getBoard(no);
		Long page = (request.getParameter("page") == "" ? 1 : Long.parseLong(request.getParameter("page")));
		request.setAttribute("groupNo", vo.getGroupNo());
		request.setAttribute("orderNo", vo.getOrderNo());
		request.setAttribute("depth", vo.getDepth());
		request.setAttribute("page", page);
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
	}

}
