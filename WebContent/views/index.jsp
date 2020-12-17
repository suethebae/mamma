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
<title>+ mamma store +</title>

</head>
<body>
	<%-- 로그인 되어있는지 테스트 <p> <%=loginSq %></p> --%>
	<a href='/'>홈으로</a>
	<br>
	<!-- shop -->
	<hr>
	<a href='/item/list'>샵 페이지로 들어가게</a>
	<br>
	<a href='/item/cart'>카트로 들어가기</a>
	<br>
	<!-- 회원관련 -->
	<hr>
	<%
		if (member_sq == null) {
	%>
	<a href='/member/login'>로그인</a>
	<a href='/member/register'>회원가입</a>
	<br>
	<%
		} else {
	%>
	<a href='/member/logout'>로그아웃</a>
	<%
		}
	%>
	<%
		if (member_sq != null) {
	%>
	<a href='/member/modify'>회원정보수정</a>
	<br>
	<%
		}
	%>
	<!-- 나의메뉴 -->
	<hr>
	<a href='/mymenu/myPage'>나의메뉴 메인</a>
	<br>
	<a href='/views/board/review/writeForm.jsp'>리뷰쓰기</a>
</body>
</html>