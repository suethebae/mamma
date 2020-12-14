<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function register() {
		var id = $('#id'); // id가 id인 컴포넌트를 가져옴. id가 id인 value를 가지고 오고 싶으면 $('#id').val(); 까지 있어야함.
		var pwd = $('#pwd');
		var pwdc = $('#pwdc');
		var name = $('#name');
		var email = $('#email');
		var phone = $('#phone');
		var magre = $('#magre');
		if (magre.is(":checked")) {
			magre.val(1); /* jquery에서 ()이 비었으면 : get, ()에 값이 있다면 : set + 체크가 되어 있으면 1 아니면 0*/
		} else {
			magre.val(0);
		}
		var pagre = $('#pagre');
		if (pagre.is(":checked")) {
			pagre.val(1);
		} else {
			pagre.val(0);
		}

		if (!id.val() || id.val() == "" /* ""id의 밸류값이 공백인지 확인하는부분임. */) {
			alert("아이디를 입력해 주세요.");
			id.focus();
			return;
		}
		var regExp = new RegExp("^[a-z0-9]{4,16}$", "g"); // g는 가져온 문자열 전체 검사
		if (regExp.exec(id.val()) == null) { // 형식이 맞으면 1?, 아니면 null + id.val() : 입력한 값 가져와서 비교해야하니
			alert("잘못된 아이디 형식 입니다.")
			id.val(''); // 잘못된 아이디 형식이면 원래있던 데이터를 공백으로
			id.focus();
			return;
		}
		if (!pwd.val() || pwd.val() == "") {
			alert("비밀번호를 입력해 주세요.");
			pwd.focus();
			return;
		}
		regExp = new RegExp("^[a-zA-Z0-9!@#$%^&*]{4,20}$", "g");
		if (regExp.exec(pwd.val()) == null) {
			alert("잘못된 비밀번호 형식 입니다.")
			pwd.val('');
			pwd.focus();
			return;
		}
		if (!pwdc.val() || pwdc.val() == "") {
			alert("비밀번호 확인값을 입력해 주세요.");
			pwdc.focus();
			return;
		}
		if (pwd.val() != pwdc.val()) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		}
		if (!name.val() || name.val() == "") {
			alert("이름을 입력해 주세요.");
			name.focus();
			return;
		}
		regExp = new RegExp("^[가-힣]{2,8}$", "g");
		if (regExp.exec(name.val()) == null) {
			alert("잘못된 이름 형식 입니다.")
			name.val('');
			name.focus();
			return;
		}
		if (!email.val() || email.val() == "") {
			alert("이메일을 입력해 주세요.");
			email.focus();
			return;
		}
		regExp = new RegExp("/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i", "g");
		if (!regExp.exec(email.val()) == null) {
			alert("잘못된 이메일 형식 입니다.")
			email.val('');
			email.focus();
			return;
			}
		if (!phone.val() || phone.val() == "") {
			alert("전화번호를 입력해 주세요.");
			phone.focus();
			return;
		}
		if (magre.val() != 1) {
			alert("약관에 동의해 주세요.");
			return;
		}
		if (pagre.val() != 1) {
			alert("개인정보 처리방침에 동의해 주세요.");
			return;
		}
	
		$('#mrForm').submit();
	}

	function cancel() {
		location.href = "/";
	}
</script>
</head>
<body>
	<form action="/member/registerProc" method="post" id="mrForm">
		<!-- 엔터눌러서 데이터 전송 안시키려면 form안에 데이터 전송 있으면X -->
		<!-- 1. 컨트롤러 타고 들어가야함. /registerProc (RegisterProcAction()) 경로 : member/registerProc -->
		<div>
			<input type="text" id="id" name="id" placeholder="아이디 입력">
		</div>
		<div>
			<input type="password" id="pwd" name="pwd" placeholder="비밀번호 입력">
		</div>
		<div>
			<input type="password" id="pwdc" name="pwdc" placeholder="비밀번호 확인">
		</div>
		<div>
			<input type="text" id="name" name="name" placeholder="이름 입력">
		</div>
		<div>
			<input type="email" id="email" name="email" placeholder="이메일">
		</div>
		<div>
			<input type="tel" id="phone" name="phone" placeholder="전화번호 입력">
		</div>
		<div>
			<div>회원가입약관</div>
			<div>약관내용입니다 ㅎ</div>
			<div>
				약관 내용에 동의합니다.<input type="checkbox" id="magre" name="magre">
			</div>
		</div>
		<div>
			<div>개인정보 처리 방침 안내</div>
			<div>처리방침 내용입니다</div>
			<div>
				개인정보 처리 방침에 동의합니다.<input type="checkbox" id="pagre" name="pagre">
			</div>
		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>
</body>
</html>