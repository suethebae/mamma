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
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
	integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/views/css/style.css">

<title>LOG IN</title>

<script src="https://kit.fontawesome.com/1920467f5c.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
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
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<!-- 로그인 -->
	<section id="login">
		<div class="login">
			<div class="login__header">
				<img src="/views/img/login.png" alt="login">
			</div>
			<form action="/member/loginProc" method="post"
				onsubmit="return login()">
				<div class="login__main">
					<div class="login__box">
						<input type="text" id="id" name="id" placeholder="ID">
					</div>
					<div class="login__box">
						<input type="password" id="pwd" name="pwd" placeholder="PASSWORD">
					</div>
				</div>
				<button class="login__btn" type="submit">로그인</button>

			</form>

			<div class="login__bottom">
				<div class="login__join">
					<a href="/member/register">회원가입</a>
				</div>
				<div class="login__find">
					<a href='/member/findId'>아이디 찾기</a> <span>|</span> <a
						href='/member/findPwd'>비밀번호 찾기</a>
				</div>
			</div>

		</div>
	</section>
</body>
</html>