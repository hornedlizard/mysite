package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		UserVo user = (UserVo) session.getAttribute("authUser");
		String checkPw = request.getParameter("checkPw");
		UserDao dao = new UserDao();
		
		if (session == null || dao.get(user.getEmail(), checkPw) == null) {
			// 인증 실패
			request.setAttribute("result", "fail");
			WebUtil.redirect(request, response, "/mysite/user?a=modifyform&result=fail");
			return;
		}
		
		/*if () {
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			return;			
		}*/
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		user.setNo(user.getNo());
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		
//		UserDao dao = new UserDao();
		dao.update(user);
		
		// 인증 처리
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
