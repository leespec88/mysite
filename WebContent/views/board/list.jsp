<%@page import="com.lee2015mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">

<script>
	
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			
			
			<c:set var="currentPageNo" value="${currentPageNo }"/>
			<c:set var="totCnt" value="${totCnt }"/>
			<c:set var="pageSize" value="${pageSize }"/>
			<c:set var="groupSize" value="${groupSize }"/>
			<c:set var="currentGroupNo" value="${currentGroupNo }"/>
			<c:set var="currentGroupStartPage" value="${currentGroupStartPage }"/>
			<c:set var="currentGroupEndPage" value="${currentGroupEndPage }"/>
			<c:set var="pageJumpRight" value="${pageJumpRight }"/>
			<c:set var="pageJumpLeft" value="${pageJumpLeft }"/>
			<c:set var="startRow" value="${startRow }"/>
			<c:set var="endRow" value="${endRow }"/>
			<c:set var="endGroupNo" value="${endGroupNo }"/>
			<c:set var="count" value="${fn:length(pageList) }"/>
			<c:set var="jumperPagingEndNumb" value="${currentGroupNo }"/>
			
			<div id="board">
				<form id="search_form" action="/board?a=search" method="post">
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
					
					<c:forEach var="pageList" items="${pageList }" varStatus="status">
					<tr>
						<td>${totCnt-((currentPageNo-1)*pageSize)-status.index }</td>
						<td><a href="/board?a=viewform&no=${pageList.no }">${pageList.title }</a></td>
						
						<td>${pageList.memberName }</td>
						<td>${pageList.viewCnt }</td>
						<td>${pageList.regDate }</td>
						<td>	
							<c:if test="${pageList.memberNo==authUser.no }">
							<a href="/board?a=delete&no=${pageList.no }" class="del">삭제</a>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				<div id="paging">
					<c:if test="${currentPageNo>groupSize }">
						<a href="/board?currentPageNo=${pageJumpLeft }">◀</a>
					</c:if>
					<c:forEach var="i" begin="${currentGroupStartPage }" step="1" end="${currentGroupEndPage }">
						<a href="/board?currentPageNo=${i }">[${i }]</a>
					</c:forEach>
					
					<c:if test="${currentGroupEndPage<endGroupNo }">
						<a href="/board?currentPageNo=${pageJumpRight }">▶</a>
					</c:if>
					</div>
					<c:if test="${not empty authUser }">
						<div class="bottom">
							<a href="board?a=writeform" id="new-book">글쓰기</a>
						</div>	
					</c:if>
				
					
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pageName" value="user"/>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp">
			</c:import>
		</div>
	</div>
</body>
</html>