<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/include/header.jsp"></c:import>
		</div>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/user?a=docheck">
					<input type="hidden" name="no" value="${authUser.no }">
					<label class="block-label" for="name">이름</label>
					${authUser.name }
				<br><br>
					<label class="block-label" for="email">이메일</label>
					${authUser.email }
				<br><br>	
					<label>남</label> 
						<c:choose>
							<c:when test="${authUser.gender=='male' }">
								<input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:otherwise>
								<input type="radio" name="gender" value="male" checked="">
							</c:otherwise>
						</c:choose>
						
						<label>여</label> 
						<c:choose>
							<c:when test="${authUser.gender=='female' }">
								<input type="radio" name="gender" value="female" checked="checked">
							</c:when>
							<c:otherwise>
								<input type="radio" name="gender" value="female" checked="">
							</c:otherwise>
						</c:choose>
				<br>
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="">
					
					
					<input type="submit" value="입력">
					
				</form>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/views/include/navigation.jsp">
				<c:param name="pageName" value="guestbook"></c:param>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/views/include/footer.jsp"></c:import>
		</div>
	</div>
</body>
</html>