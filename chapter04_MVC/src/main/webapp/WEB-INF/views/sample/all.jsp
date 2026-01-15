<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>all.jsp</h1>
	
	<!-- 권한이 있고 없음을 이용해 if문처럼 사용. 권한 여부에 따라 다른 내용 출력 가능 -->
	<!-- 익명 사용자 -->
	<sec:authorize access="isAnonymous()">
		<a href="/customLogin">Login</a>
	</sec:authorize>
	
	<!-- 인증된 사용자 -->
	<sec:authorize access="isAuthenticated()">
		<a href="/customLogout">Logout</a>
	</sec:authorize>
</body>
</html>