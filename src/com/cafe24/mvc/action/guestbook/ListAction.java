package com.cafe24.mvc.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = dao.getGuestbookList();
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
