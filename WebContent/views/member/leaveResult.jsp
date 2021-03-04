<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script>
	function gotoHome() {
		location.href = "/"
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="order__result">
		<div class="order__result">
				<img class="order__result__img" alt="order__result__img" src="/views/img/result.png">
			<div class="order__result__cntnt">
				<span>Mamma store를 이용해주셔서 감사합니다.</span>
			</div>

			<div class="order__result__btns">
				<button class="order__result__btn" onclick="gotoHome()">홈으로</button>
			</div>
		</div>
	</section>
</body>
</html>