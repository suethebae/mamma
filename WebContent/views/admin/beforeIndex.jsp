<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="stylesheet" type="text/css" href="/views/css/admin.css">
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
	<section id="section">
		<div class="main l==">
		<h2>관리자 페이지 로그인</h2>
			<form action="/amanager/loginProc" method="post" onsubmit="return login()">
				<div>
					<input class="login__input" type="text" id="id" name="id" placeholder="아이디">
				</div>
				<div>
					<input class="login__input" type="password" id="pwd" name="pwd" placeholder="비밀번호">
				</div>
				<button class="login__button" type="submit">로그인</button>
			</form>
		</div>
	</section>
</body>
</html>