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
<link rel="stylesheet" href="/views/css/style.css" type="text/css">
<link rel="stylesheet" href="/views/css/login.css" type="text/css">
<link rel="stylesheet" href="/views/css/ress.css" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap"
	rel="stylesheet">
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
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<!-- 메인 -->
	<section>
		<main>
			<form action="/member/loginProc" method="post"
				onsubmit="return login()">

				<span class="login_title"><img class="login_title" alt="X"
					src="/views/img/login.png"></span>
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