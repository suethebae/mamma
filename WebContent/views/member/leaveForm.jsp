<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
function leave() {
	$('#lForm').submit();
}
</script>
</head>
<body>
<a href="/">mamma store</a>
	<h1>회원탈퇴</h1>
	<p>회원탈퇴하면 어쩌구저쩌구 어절시구 저쩌구 어저구저쩌구 합니다.</p>
	<hr>
	<form action="/member/leaveProc" method="post" id="lForm">
		비밀번호 <input type="password" id="pwd" name="pwd">
	</form>
	<button onclick="leave()">탈퇴하기</button>
</body>
</html>