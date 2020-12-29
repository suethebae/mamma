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
<title>로그인</title>
<<<<<<< Updated upstream
<link rel="stylesheet" href="/views/css/style.css" type="text/css">
<link rel="stylesheet" href="/views/css/login.css" type="text/css">
<link rel="stylesheet" href="/views/css/ress.css" type="text/css">
<link rel="stylesheet" href="/views/css/navbar.css" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/1920467f5c.js"
=======

	<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
>>>>>>> Stashed changes
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function login() {
		var id = $('#id');
		var pwd = $('#pwd');

		if (!id.val() || id.val() == "") {
			alert("아이디를 입력하세요.");
			id.focus();
			return false;
		}
		if (!pwd.val() || pwd.val() == "") {
			alert("비밀번호를 입력하세요.");
			pwd.focus();
			return false;
		}
	}
</script>
</head>

<body>
	<!-- navbar -->
	<header>
		<nav class="navbar">
			<div class="navbar_logo">
				<a href="/"><img class="logo" alt="X" src="/views/img/logo.png"></a>
			</div>
			<ul class="navbar_menu">
				<li><a href="/item/list">SHOP</a></li>
				<li><a href="/">ABOUT MAMMA</a></li>
				<li><a href="/">CONTACT</a></li>
			</ul>
			<ul class="navbar_sideMenu">
				<%
					if (member_sq == null) {
				%>
				<li><a href="/member/login">LOGIN</a></li>
				<li><a href="/member/register">JOIN</a></li>
				<li><a href="/item/list">CART</a></li>
				<%
					} else {
				%>
				<li><a href='/mymenu/myPage'>MY MENU</a></li>
				<li><a href="/member/logout">LOGOUT</a></li>
				<li><a href="/item/list">CART</a></li>
				<%
					}
				%>
			</ul>
		</nav>
	</header>
	<!-- 메인 -->
	<section>
		<main>
			<form action="/member/loginProc" method="post"
				onsubmit="return login()">

				<span class="login_title"><img class="login_title" alt="X"
					src="/views/img/login_title.png"></span>
				<div class="login_box">
					<input type="text" id="id" name="id" placeholder="아이디 입력">
				</div>
				<div class="login_box">
					<input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력">
				</div>

				<div class="login_box">
					<button class="login_button" type="submit">로그인</button>
				</div>

				<div class="login_find">
					<a href='/member/findId'>아이디 찾기 |</a> <a href='/member/findPwd'>비밀번호
						찾기</a>
				</div>
			</form>
		</main>
	</section>
</body>
</html>