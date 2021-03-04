<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function findPwd() {
		var id = $('#id');
		var nm = $('#nm');
		var email = $('#email');

		if (!id.val() || id.val() == "") {
			alert("아이디를 입력하여주십시오");
			id.focus();
			return false;
		}
		if (!nm.val() || nm.val() == "") {
			alert("이름을 입력하여주십시오");
			nm.focus();
			return false;
		}
		if (!email.val() || email.val() == "") {
			alert("이메일을 입력하여주십시오");
			email.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="findPwd">
		<div class="findPwd">
			<div class="findPwd__header">
				<img src="/views/img/findpwd.png" alt="findPwd">
			</div>
			<form action="/member/findPwdProc" method="post"
				onsubmit="return findPwd()">
				<div class="findPwd__main">

					<div class="findPwd__box">
						<input type="text" id="id" name="id" placeholder="아이디" /> <input
							type="text" id="nm" name="nm" placeholder="이름" /> <input
							type="text" id="email" name="email" placeholder="이메일" />
					</div>

				</div>

				<div class="findPwd__btns">
					<button class="findPwd__btn" type="submit">비밀번호 찾기</button>
					<button class="findPwd__btn"
						onclick="location.href='/member/login'">취소</button>
				</div>
			</form>
		</div>
	</section>


</body>
</html>