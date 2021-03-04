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
<!-- Link -->
<link rel="stylesheet" type="text/css" href="/views/css/navbar.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
	integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp"
	crossorigin="anonymous">
<!-- Script -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function openNav() {
		document.getElementById("myNav").style.width = "100%";
	}

	function closeNav() {
		document.getElementById("myNav").style.width = "0%";
	}
</script>

<title>mamma store</title>
</head>
<body>
	<!-- Navbar Desktop-->
	<div class="navbar__normal">
		<div class="navbar__logo">
			<a href="/" class="navbar__logo__img"><img
				src="/views/img/logo.png" alt="logo"></a>
		</div>
		<ul class="navbar__menu">
			<li class="navbar__menu__item"><a href="/item/list">SHOP</a></li>
			<li class="navbar__menu__item"><a href="/etc/about">ABOUT</a></li>
			<li class="navbar__menu__item"><a href="/notice/list">NOTICE</a></li>
			<li class="navbar__menu__item"><a href="/etc/contact">CONTACT</a></li>
		</ul>
		<ul class="navbar__sidemenu">
			<%
			if (member_sq == null) {
			%>
			<li class="navbar__sidemenu__item"><a href="/member/login">LOGIN</a></li>
			<li class="navbar__sidemenu__item"><a href="/member/register">JOIN</a></li>
			<li class="navbar__sidemenu__item"><a href="/cart/list">CART</a></li>
			<%
			} else {
			%>
			<li class="navbar__sidemenu__item"><a href='/mymenu/myPage'>MY
					MENU</a></li>
			<li class="navbar__sidemenu__item"><a href="/member/logout">LOGOUT</a></li>
			<li class="navbar__sidemenu__item"><a href="/cart/list">CART</a></li>
			<%
			}
			%>
		</ul>
	</div>

	<!-- Navbar Mobile -->
	<div class="navbar__mobile">
		<div>
			<span style="font-size: 20px; cursor: pointer" onclick="openNav()"><i
				class="fas fa-bars" id="navbar__toggle-btn"></i></span>
		</div>
		<div>
			<img src="/views/img/logo.png" alt="logo" />
		</div>
		<div>
			<i class="fas fa-shopping-cart" style="font-size: 20px;"></i>
		</div>
	</div>

	<div id="myNav" class="overlay">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
		<div class="overlay-content">
			<div class="mnavbar__sidemenu">
				<%
				if (member_sq == null) {
				%>
				<a class="navbar__sidemenu__item" href="/member/login">LOGIN</a> <a
					class="navbar__sidemenu__item" href="/member/register">JOIN</a> <a
					class="navbar__sidemenu__item" href="/cart/list">CART</a>
				<%
				} else {
				%>
				<a class="navbar__sidemenu__item" href='/mymenu/myPage'>MY MENU</a>
				<a class="navbar__sidemenu__item" href="/member/logout">LOGOUT</a> <a
					class="navbar__sidemenu__item" href="/cart/list">CART</a>
				<%
				}
				%>
			</div>
			<div class="mnavbar__menu">
				<a class="mnavber__menu__item" href="/item/list">SHOP</a> 
				<a class="mnavber__menu__item" href="/etc/about">ABOUT</a>
				<a class="mnavber__menu__item" href="/etc/contact">CONTACT</a>
			</div>
		</div>
	</div>
</body>
</html>