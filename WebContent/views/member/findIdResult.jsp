<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = (String) request.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script type="text/javascript">
	function findPwd() {
		location.href = "/member/findPwd";
	}
	function goToLogin() {
		location.href = "/member/login";
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="order__result">
		<div class="order__result">
			<img class="order__result__img" alt="order__result__img"
				src="/views/img/result.png">
			<div class="order__result__cntnt">
				<span>고객님의 아이디는 "<%=id%>" 입니다.</span>
			</div>

			<div class="order__result__btns">
				<button class="order__result__btn" onclick="findPwd()">비밀번호 찾기</button>
				<button class="order__result__btn" onclick="goToLogin()">로그인</button>
			</div>
		</div>
	</section>
</body>
</html>