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
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
    integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
  <link rel="stylesheet" href="/views/css/style.css">
  <script defer src="/views/js/navbar.js"></script>
  <title>mamma store</title>
</head>
<body>
 <!-- Navbar -->
  <nav id="navbar">
    <div class="navbar__logo">
      <a href="/" class="navbar__logo__img"><img src="/views/img/logo.png" alt="logo"></a>
    </div>
 <!-- main menu -->   
    <ul class="navbar__menu">
      <li class="navbar__menu__item"><a href="/">ABOUT</a></li>
      <li class="navbar__menu__item"><a href="/">CONTACT</a></li>
      <li class="navbar__menu__item"><a href="/item/list">SHOP</a></li>
    </ul>
<!-- side menu -->
		<ul class="navbar_sideMenu">
			<%
				if (member_sq == null) {
			%>
			<li><a href="/member/login">LOGIN</a></li>
			<li><a href="/member/register">JOIN</a></li>
			<li><a href="/cart/list">CART</a></li>
			<%
				} else {
			%>
			<li><a href='/mymenu/myPage'>MY MENU</a></li>
			<li><a href="/member/logout">LOGOUT</a></li>
			<li><a href="/cart/list">CART</a></li>
			<%
				}
			%>
		</ul>
		
    <!-- toggle button -->
    <button class="navbar__toggle-btn">
      <i class="fas fa-bars"></i>
    </button>
  </nav>
</body>
</html>