<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
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
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="order__result">
		<div class="order__result">
			<div class="order__result__cntnt">
				<h2>회원탈퇴</h2>
				<p>비밀번호를 한번 더 입력해 주십시오</p>
				<hr>
				<form action="/member/leaveProc" method="post" id="lForm">
					비밀번호 <input class="leave__input" type="password" id="pwd" name="pwd">
				</form>
				<button class="leave__btn" onclick="leave()">탈퇴하기</button>
			</div>
		</div>
	</section>
</body>
</html>