package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestbookDao dao = new GuestbookDao();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setContent(content);
		
		dao.insertGuestbook(vo);
		
		WebUtil.redirect(request, response, "/mysite/guestbook?a=list");
	}

}
