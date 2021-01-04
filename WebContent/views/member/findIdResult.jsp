<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디를 찾으러 왔어요!</title>
<script type="text/javascript">
	function goToLogin() {
		location.href = "/member/login";
	}
	function goToHome() {
		location.href = "/";
	}
</script>
</head>
<body>
	당신의 아이디를 찾았어요!

	<p><%=id%></p>
	<button onclick="goToLogin()">로그인 화면으로</button>
	<button onclick="goToHome()">홈으로</button>
</body>
</html>