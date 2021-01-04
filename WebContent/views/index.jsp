<%@page import="shop.mammastore.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
String member_sq = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>+ mamma store +</title>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
메인페이지
</body>
</html>