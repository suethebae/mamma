<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function findId() {
		var email = $('#email');
		var phone = $('#phone');
		var nm = $('#nm');
		var emailCheck = $('#emailCheck');
		var phoneCheck = $('#phoneCheck');

		if (!nm.val() || nm.val() == "") {
			alert("이름을 입력해 주세요");
			nm.focus();
			return false;
		} else {
			if (emailCheck.is(":checked")) {
				if (!email.val() || email.val() == "") {
					alert("이메일을 입력하세요.");
					email.focus();
					return false;
				}
			} else if (phoneCheck.is(":checked")) {
				if (!phone.val() || phone.val() == "") {
					alert("전화번호를 입력하세요");
					phone.focus();
					return false;
				}
			} else {
				return false;
			}
		}
	}

	$(function() {
		$('input[type="radio"][id="emailCheck"]')
				.on(
						'click',
						function() {
							var chkValue = $(
									'input[type=radio][id="emailCheck"]:checked')
									.val();
							if (chkValue) {
								$('#findId__email').css('display', 'block');
								$('#findId__phone').css('display', 'none');
							}
						});
	});

	$(function() {
		$('input[type="radio"][id="phoneCheck"]')
				.on(
						'click',
						function() {
							var chkValue = $(
									'input[type=radio][id="phoneCheck"]:checked')
									.val();
							if (chkValue) {
								$('#findId__phone').css('display', 'block');
								$('#findId__email').css('display', 'none');
							}
						});
	});
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="findId">
		<div class="findId">
			<div class="findId__header">
				<img src="/views/img/findid.png" alt="findId">
			</div>
			<form action="/member/findIdProc" method="post"
				onsubmit="return findId()">
				<div class="findId__main">

					<div class="findId__box">
						<input type="text" id="nm" name="nm" placeholder="이름">
					</div>
					<div class="findId__switch">
						<div class="findId__switch__input">
							<label> <input type="radio" id="emailCheck" name="radio"
								value="email" checked /> <span>가입한 이메일</span>
							</label>
						</div>

						<div class="findId__switch__input">
							<label> <input type="radio" id="phoneCheck" name="radio"
								value="phone" /> <span>가입한 휴대폰 번호</span>
							</label>
						</div>
					</div>

					<div class="findId__box findId__email" id="findId__email">
						<input type="email" id="email" name="email" placeholder="이메일">
					</div>

					<div class="findId__box findId__phone" id="findId__phone"
						style="display: none;">
						<input type="tel" id="phone" name="phone" placeholder="휴대폰 번호">
					</div>

				</div>
				<div class="findId__btns">
					<button class="findId__btn" type="submit">아이디 찾기</button>
					<button class="findId__btn" onclick="location.href='/member/login'">취소</button>
				</div>
			</form>
		</div>
	</section>
</body>
</html>