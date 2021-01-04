<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
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
</script>
</head>
<body>
	<form action="/member/findIdProc" method="post"
		onsubmit="return findId()">
		<div>
			이름 입력: <input type="text" id="nm" name="nm">
		</div>
		가입한 이메일로 찾기<input type="radio" id="emailCheck" name="radio"
			value="email" checked> 가입한 휴대폰으로 찾기<input type="radio"
			id="phoneCheck" name="radio" value="phone">
		<div id="emailDiv">
			이메일 입력 : <input type="email" id="email" name="email">
		</div>
		<div id="phoneDiv">
			전화번호 입력 : <input type="tel" id="phone" name="phone">
		</div>

		<button type="submit">제출</button>

	</form>
	<button onclick="location.href='/'">취소</button>
</body>
</html>