<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp">
			</c:import>
		</div>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
				<c:set var="bdvo" value="${bdvo }"/>
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${bdvo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${bdvo.content }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="board?a=board">글목록</a>
					<c:set var="bdvo" value="${bdvo }"/>
					<c:if test="${bdvo.memberNo == authUser.no}">
					<a href="board?a=modifyform&no=${bdvo.no }">글수정</a>
					</c:if>
				</div>
				<div>
					<a href="https://www.google.co.kr"><img id="google" src="/assets/css/images/google.png"></a>
				</div>
			</div>
		
				
		<div id="content">
			<div id="board">
				<form class="board-form" action="/board?a=commentAdd&commentNo=${param.no }" method="post" >
					<table class="tbl-ex">
						<tr>
							<th colspan="3">COMMENT</th>
						</tr>
						<c:forEach var="list" items="${list }" varStatus="status">
							<c:if test="${bdvo.no==list.contentNo }">
							<tr>
								<td class="com-id" >${list.authUserName }</td>
								<td id="comment">${list.content }</td>
								<c:if test="${list.authUserNo == authUser.no}">
								<td><a href="/board?a=commentDelete&no=${list.no }&contentNo=${param.no }">삭제</a></td>
								</c:if>
							</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td class="com-id">내용</td>
							<td>
								<textarea id="comment" name="content" value=""></textarea>
							</td>
							<td></td>
						</tr>
					</table>
					<c:if test="${not empty authUser }">
					<div class="bottom">
						<input type="submit" value="댓글달기">
					</div>
					</c:if>
				</form>				
			</div>
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