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

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String gno = request.getParameter("groupNo");
		String ono = request.getParameter("orderNo");
		String dep = request.getParameter("depth");
		
		Long groupNo = (request.getParameter("groupNo") == "" ? 0 : Long.parseLong(request.getParameter("groupNo")));
		Long orderNo = (request.getParameter("orderNo") == "" ? 0 : Long.parseLong(request.getParameter("orderNo")));
		Long depth = (request.getParameter("depth") == "" ? 0 : Long.parseLong(request.getParameter("depth"))+1);
		
		if (gno != "" && ono != "" && dep != "") {
			dao.updateOrderNo(groupNo, orderNo);
		}
		
		BoardVo vo = new BoardVo();
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		vo.setUserVo(userVo);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setDepth(depth);
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "/mysite/board?a=list");
	}

}
