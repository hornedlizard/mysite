package com.cafe24.mvc.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static long checkParameter(String value, long defaultValue) {
		return 0;
	}
	
	public static void requestCharSet(
			HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
	}
	
	public static void forward(
			HttpServletRequest request, 
			HttpServletResponse response, 
			String path) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public static void redirect(
			HttpServletRequest request,
			HttpServletResponse response,
			String url) throws IOException {
		response.sendRedirect(url);
	}
	
}