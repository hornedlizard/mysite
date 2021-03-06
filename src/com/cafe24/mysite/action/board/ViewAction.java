package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		long no = Long.parseLong(request.getParameter("no"));
		int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));
		String keyword = request.getParameter("kwd");
		BoardVo vo = dao.getBoard(no);
		if (vo.getIsDelete() == true) {
			WebUtil.redirect(request, response, "/mysite/board?a=list");
			return;
		}
		dao.updateHits(no);
		request.setAttribute("vo", vo);
		request.setAttribute("page", page);
		request.setAttribute("kwd", keyword);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}