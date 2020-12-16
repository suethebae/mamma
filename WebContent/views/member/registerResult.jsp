<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인할래? 홈으로갈래?</title>
<script type="text/javascript">
function goToLogin() {
	location.href="/views/member/loginForm.jsp";
}
function goToHome() {
	location.href="/";
}
</script>
</head>
<body>
임시로 구현한 창입니다! 모달창으로 구현하세요 휴먼!
<button onclick="goToLogin()">로그인 창으로</button>
<button onclick="goToHome()">홈으로</button>
</body>
</html>