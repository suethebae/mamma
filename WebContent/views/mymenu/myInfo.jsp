<%@page import="shop.mammastore.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	LoginManager lm = LoginManager.getInstance();
String member_sq = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	내 정보 페이지
	<%
	if (member_sq != null) {
%>
	<a href='/member/modify'>회원정보수정</a>
	<br>
	<%
		}
	%>

	<a href='/'>홈으로</a>
</body>
</html>