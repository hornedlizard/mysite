package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PageVo;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		PageVo vo = new PageVo();
		String keyword = request.getParameter("kwd");
		int totalData = dao.searchCount(keyword);
		vo.setTotalData(totalData);
		int page = Integer.parseInt(request.getParameter("page") == null ? "1": request.getParameter("page"));
		vo.setPage(page);
		vo.paging();
		List<BoardVo> list = dao.getList(vo, keyword);
		request.setAttribute("list", list);
		request.setAttribute("page", vo);
		request.setAttribute("kwd", keyword);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
