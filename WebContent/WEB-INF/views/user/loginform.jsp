<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%
	/* String result = request.getParameter("result"); */
	String result = (String) request.getAttribute("result");
	String email = (String) request.getAttribute("email");
	System.out.println(email);
%> --%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="/mysite/user">
					<input type ="hidden" name="a" value="login"/>
					<label class="block-label" for="email">이메일</label>
					<%-- <input id="email" name="email" type="text" value="<%=(email==null ? "" : email)%>"> --%>
					<input id="email" name="email" type="text" value='${empty email ? "" : email }'>
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					<%-- <%
						if ("fail".equals(result)) {
					%> --%>
					<c:if test='${param.result == "fail" }'>
						<p>
							로그인이 실패 했습니다.
						</p>
					</c:if>
					<%-- <%
						}
					%> --%>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>