<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<ul>
	<c:choose>
		<c:when test="${param.pageName=='user' }">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="/">방문자</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/">${authUser.name }</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/guestbook">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pageName=='main' }">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="/">방문자</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/">${authUser.name }</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/guestbook">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pageName=='guestbook' }">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="/">방문자</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/">${authUser.name }</a></li>
				</c:otherwise>
			</c:choose>
			<li class="selected"><a href="#">방명록</a></li>
			<li><a href="/board">게시판</a></li>
		</c:when>
		<c:when test="${param.pageName=='board' }">
			<c:choose>
				<c:when test="${empty authUser }">
					<li><a href="/">방문자</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/">${authUser.name }</a></li>
				</c:otherwise>
			</c:choose>
			<li><a href="/guestbook">방명록</a></li>
			<li class="selected"><a href="#">게시판</a></li>
		</c:when>
	
	</c:choose>
</ul>
	