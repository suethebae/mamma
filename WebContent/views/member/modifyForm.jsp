<%@page import="shop.mammastore.mamma.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	//패스워드 체크
	var isPwdChecked = false;
	function initCheckPwd() {
		isPwdChecked = false;
	}

	function checkPwd() {
		var pwd = $('#pwd');
		initCheckPwd()
		if (!pwd.val() || pwd.val() == "") {
			$('#pwdMessage').text('영어 대소문자, 숫자, 특수문자(!@#$%^&*) 4~20자');
			isPwdChecked = false;
			return;
		} else {
			isPwdChecked = true;
		}

		var regExp = new RegExp("^[a-zA-Z0-9!@#$%^&*]{4,20}$", "g");
		if (regExp.exec(pwd.val()) == null) {
			$('#pwdMessage').text('잘못된 비밀번호 형식입니다.');
			isPwdChecked = false;
			return;
		} else {
			$('#pwdMessage').text('사용가능한 비밀번호입니다.');
			isPwdChecked = true;
		}
	}

	//비밀번호 확인 체크
	var isPwdcChecked = false;
	function initCheckPwdc() {
		isPwdcChecked = false;
	}

	function checkPwdc() {
		var pwd = $('#pwd');
		var pwdc = $('#pwdc');
		initCheckPwdc()
		if (!pwdc.val() || pwdc.val() == "") {
			$('#pwdcMessage').text('비밀번호 확인을 입력해 주십시오');
			isPwdcChecked = false;
			return;
		} else {
			isPwdcChecked = true;
		}

		if (pwd.val() != pwdc.val()) {
			$('#pwdcMessage').text('비밀번호가 일치하지 않습니다.');
			isPwdcChecked = false;
			return;
		} else {
			$('#pwdcMessage').text('비밀번호가 일치합니다.');
			isPwdcChecked = true;
		}
	}

	//이메일 체크
	var isEmailChecked = false;
	function initCheckEmail() {
		isEmailChecked = false;
	}

	function checkEmail() {
		initCheckEmail();
		var email = $('#email');
		if (!email.val() || email.val() == "") {
			$('#emailMessage').text('이메일 양식');
			isEmailChecked = false;
			return;
		} else {
			isEmailChecked = true;
		}

		var regExp = new RegExp(
				"^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
				"g");
		if (regExp.exec(email.val()) == null) {
			$('#emailMessage').text('잘못된 이메일 형식입니다.');
			isEmailChecked = false;
			return;
		} else {
			isEmailChecked = true;
		}

	}

	//전화번호 체크
	var isPhoneChecked = false;
	function initCheckPhone() {
		isPhoneChecked = false;
	}
	function checkPhone() {
		initCheckPhone();
		var phone = $('#phone');
		if (!phone.val() || phone.val() == "") {
			$('#phoneMessage').text('휴대전화번호를 입력하십시오');
			isEmailChecked = false;
			return;
		} else {
			isEmailChecked = true;
		}

		var regExp = new RegExp("^[01]{2,2}[0-9]{8,9}$", "g");
		if (regExp.exec(phone.val()) == null) {
			$('#phoneMessage').text('잘못된 전화번호 형식입니다.');
			isPhoneChecked = false;
			return;
		} else {
			isPhoneChecked = true;
		}

	}

	//회원정보수정
	function save() {
		if (!isPwdChecked) {
			$('#pwd').focus();
			return;
		}
		if (!isPwdcChecked) {
			$('#pwdc').focus();
			return;
		}
		if (!isEmailChecked) {
			$('#email').focus();
			return;
		}
		if (!isPhoneChecked) {
			$('#phone').focus();
			return;
		}

		$('#mForm').submit();
	}
</script>
</head>
<body>
	<a href="/">Mamma store</a>
	<h1>회원정보</h1>
	<hr>
	<form action="/member/modifyProc" method="post" id="mForm">
		<p>
			이름
			<%=memberVo.getName()%>
		</p>
		<p>
			아이디
			<%=memberVo.getId()%>
		</p>
		<p>
			비밀번호 <input type="password" id="pwd" name="pwd" oninput="checkPwd()">
			<span id="pwdMessage">영어 대소문자, 숫자, 특수문자(!@#$%^&*) 4~20자</span>
		</p>
		<p>
			비밀번호 확인 <input type="password" id="pwdc" name="pwdc"
				oninput="checkPwdc()"> <span id="cpwdMessage"> 비밀번호
				확인을 입력해 주십시오</span>
		</p>
		<p>
			이메일 <input type="email" id="email" name="email"
				oninput="checkEmail()" value=<%=memberVo.getEmail()%>> <span
				id="emailMessage">이메일을 입력해주십시오</span>
		</p>
		<p>
			휴대폰 <input type="tel" id="phone" name="phone" oninput="checkPhone()" value=<%=memberVo.getPhone()%>>
			<span id="phoneMessage">전화번호를 입력해주십시오 </span>
		</p>
	</form>
	<hr>
	<button onclick="save()">저장하기</button>
</body>
</html>