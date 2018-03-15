package com.cafe24.mvc.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestbookDao dao = new GuestbookDao();
		String strNo = request.getParameter("no");
		System.out.println(strNo);
		long no = Long.parseLong(strNo);
		String password = request.getParameter("password");
		
		dao.deleteGuestbook(no, password);
		
		WebUtil.redirect(request, response, "/mysite/guestbook/list");
	}

}
