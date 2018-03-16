package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
//		long groupNo = Long.parseLong(request.getParameter("groupNo"));
//		System.out.println(request.getParameter("groupNo"));
		/*long orderNo = Long.parseLong(request.getParameter("orderNo"));
		long userNo = Long.parseLong(request.getParameter("userNo"));*/
		BoardVo vo = new BoardVo();
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		
		vo.setUserVo(userVo);
		vo.setTitle(title);
		vo.setContent(content);
//		vo.setGroupNo(groupNo);
		
		/*userVo.setNo(userNo);
		vo.setOrderNo(orderNo);*/
		
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "/mysite/board?a=list");
	}

}
