<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	const var id = $('#id');
	const var name = $('#name');
	const var email = $('#email');

	if(!id.val()||id.val()==""){
		alert("아이디를 입력하여주십시오");
		id.focus();
		return false;
	}
	if(!name.val()||name.val()==""){
		alert("이름을 입력하여주십시오");
		id.focus();
		return false;
	}
	if(!email.val()||email.val()==""){
		alert("비밀번호를 입력하여주십시오");
		id.focus();
		return false;
	}
</script>
</head>
<body>
	비번 찾기 페이지
	<form action="/member/findPwdProc" method="post"
		onsubmit="return findPwd()">
		<div>
			<input type="text" id="id" name="id" placeholder="아이디" />
		</div>
		<div>
			<input type="text" id="name" name="name" placeholder="이름" />
		</div>
		<div>
			<input type="text" id="email" name="email" placeholder="이메일" />
		</div>
		<div>
			<button type="submit">비밀번호 찾기</button>
		</div>
	</form>

	<br>

	<a href='/'>홈으로</a>
</body>
</html>