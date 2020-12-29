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
	<nav class="navbar">
		<div class="navbar_logo">
			<a href="/"><img class="logo" alt="X" src="/views/img/logo.png"></a>
		</div>
		<ul class="navbar_menu">
			<li><a href="/item/ctgry">SHOP</a></li>
			<li><a href="/">ABOUT MAMMA</a></li>
			<li><a href="/">CONTACT</a></li>
		</ul>
		<ul class="navbar_sideMenu">
			<%
				if (member_sq == null) {
			%>
			<li><a href="/member/login">LOGIN</a></li>
			<li><a href="/member/register">JOIN</a></li>
			<li><a href="/item/cart">CART</a></li>
			<%
				} else {
			%>
			<li><a href='/mymenu/myPage'>MY MENU</a></li>
			<li><a href="/member/logout">LOGOUT</a></li>
			<li><a href="/item/cart">CART</a></li>
			<%
				}
			%>
		</ul>
	</nav>
</body>
</html>