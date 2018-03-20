package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		
		if (name == "" || email == null || password == null) {
			WebUtil.redirect(request, response, "/mysite/user?a=joinform");
			return;
		}
		
		vo.setEmail(email);
		vo.setName(name);
		vo.setGender(gender);
		vo.setPassword(password);
		
		new UserDao().insert(vo);
		
		WebUtil.redirect(request, response, "/mysite/user?a=joinsuccess");
	}

}
