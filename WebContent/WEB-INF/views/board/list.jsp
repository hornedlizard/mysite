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
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="hidden" name="a" value="list">
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
									<td>${page.totalData - status.index - (page.page - 1) * page.dataPerPage }</td>
									<td style="text-align:left; padding-left:${list.depth*10 }px">
										<c:choose>
											<c:when test="${list.depth != 0 }">
												<img alt="...." src="assets/images/reply.png">
											</c:when>
										</c:choose>
									<a href="/mysite/board?a=view&no=${list.no }&page=${page.page }">${list.title }</a>
									</td>
									<td>${list.userVo.name }</td>
									<td>${list.hits }</td>
									<td>${list.regdate }</td>
									<td>
										<a href="/mysite/board?a=delete&no=${list.no }" class="del">삭제</a>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td>${list.no }</td>
									<td>
										삭제된 글입니다.
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
						<c:if test="${page.prev}">
							<li><a href="/mysite/board?a=list&page=${page.startPage-page.displayPage }&kwd=${kwd}">◀</a></li>
						</c:if>
						<c:forEach begin="${page.startPage }" end="${page.endPage }" var="index">
						<c:choose>
							<c:when test="${index <= page.totalPage }">
					            <li ${page.page == index ? 'class = "selected"' : ''}>
					            	<a href="/mysite/board?a=list&page=${index}&kwd=${kwd}">${index}</a>
					            </li>
				            </c:when>
				            <c:when test="${index > page.totalPage }">
					            <li ${page.page == index ? 'class = "selected"' : ''}>
					            	${index}
					            </li>
				            </c:when>
						</c:choose>
				        </c:forEach>
						<c:if test="${page.next}">
							<li><a href="/mysite/board?a=list&page=${page.endPage+1 }&kwd=${kwd}">▶</a></li>
						</c:if>
					</ul>
				</div>				
				<div class="bottom">
					<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>