<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
	<form action="/member/loginProc" method="post"
		onsubmit="return login()">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디 입력">
		</div>
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력">
		</div>
		<div>
			<a href='/member/findId'>아이디찾기</a> <a href='/member/findPwd'>비밀번호찾기</a>
		</div>
		<button type="submit">로그인</button>
		<button onclick="location.href='/'">취소</button>
	</form>


</body>
</html>