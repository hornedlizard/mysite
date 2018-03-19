<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set value="${fn:length(list) }" var="count" />
					<c:forEach items="${list }" var="list" varStatus="status">
						<c:choose>
							<c:when test="${list.isDelete == false }">
								<tr>
									<td>${list.no }</td>
									<td style="text-align:left; padding-left:${list.depth*10 }px">
										<c:choose>
											<c:when test="${list.depth != 0 }">
												<img alt="...." src="assets/images/reply.png">
											</c:when>
										</c:choose>
									<a href="/mysite/board?a=view&no=${list.no }">${list.title }</a>
									</td>
									<td>${list.userVo.name }</td>
									<td>${list.hits }</td>
									<td>${list.regdate }</td>
									<td>
										<a href="/mysite/board?a=delete&no=${list.no }" class="del">삭제</a>
										<%-- <c:choose>
											<c:when test="${list.userVo.no == sessionScope.authUser.no }">
											</c:when>
										</c:choose> --%>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${list.no }</td>
									<td>
										삭제된 댓글입니다.
									</td>
									<td>${list.userVo.name }</td>
									<td>${list.hits }</td>
									<td>${list.regdate }</td>
									<td><a class="del">삭제</a></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${vo.prev}">
							<li><a href="">◀</a></li>
						</c:if>
						<c:forEach begin="${vo.startPage }" end="${vo.endPage }" var="idx">
				            <li
				                <c:out value="${vo.page == idx ? 'class = selected' : ''}"/>>
				                <a href="list?page=${idx}">${idx}</a>
				            </li>
				        </c:forEach>
						<li><a href="">1</a></li>
						<li><a href="">2</a></li>
						<li class="selected">3</li>
						<li><a href="">4</a></li>
						<li>5</li>
						<c:if test="${vo.next}">
							<li><a href="">▶</a></li>
						</c:if>
					</ul>
				</div>				
				<div class="bottom">
					<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="main"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>