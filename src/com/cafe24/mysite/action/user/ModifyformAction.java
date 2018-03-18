package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.vo.UserVo;

public class ModifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		if (user == null) {
			// 인증 실패
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		request.setAttribute("no", user.getNo());
		request.setAttribute("name", user.getName());
		request.setAttribute("gender", user.getGender());
		/*이전 패스워드가 다른 경우*/
		if ("fail".equals(request.getParameter("result"))) {
			request.setAttribute("result", "fail");
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			return;
		}
		
		WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
	}

}
