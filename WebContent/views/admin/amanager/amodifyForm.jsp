<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	AmanagerVo amanagerVo = (AmanagerVo) request.getAttribute("amanagerVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>

	<script>
	//비밀번호 체크
	var isPwdChecked = true;
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
	var isPwdcChecked = true;
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
	var isEmailChecked = true;
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

		if (email.val() != '<%=amanagerVo.getEmail()%>') {
			$.ajax({
				url : "/ajax/checkAEmail",
				type : "post",
				dataType : "json",
				data : {
					email : email.val()
				},
				error : function() {
					alert("통신 실패");
				},
				success : function(data) {
					if (data.isDuplicate == 'true') {
						$('#emailMessage').text('중복된 이메일 입니다.');
						isEmailChecked = false;
					} else {
						$('#emailMessage').text('사용할 수 있는 이메일 입니다.');
						isEmailChecked = true;
					}
				}
			});
		}else{
			$('#emailMessage').text('사용할 수 있는 이메일 입니다.');
			isEmailChecked = true;
		}
	}

	//전화번호 체크
	var isPhoneChecked = true;
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

		if (phone.val() != "<%=amanagerVo.getPhone()%>") {
				$.ajax({
					url : "/ajax/checkAPhone",
					type : "post",
					dataType : "json",
					data : {
						phone : phone.val()
					},
					error : function() {
						alert("통신 실패");
					},
					success : function(data) {
						if (data.isDuplicate == 'true') {
							$('#phoneMessage').text('중복된 휴대전화번호 입니다.');
							isEmailChecked = false;
						} else {

						}
					}
				});
			} else {
				$('#phoneMessage').text('사용할 수 있는 휴대전화번호 입니다.');
				isEmailChecked = true;
			}
		}

		//회원정보수정
		function modify() {
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

			$('#AMForm').submit();
		}

		function cancel() {
			location.href = "/amanager/list";
		}
	</script>
</head>
<body>
	<form action="/amanager/modifyProc" id="AMForm" method="post">
		<div class="join_title">
			<img alt="X" src="/views/img/join.png">
		</div>
		<div class="join_box">
			<input class="join_box2" type="text" id="mngr_sq" name="mngr_sq"
				value="<%=amanagerVo.getMngr_sq()%>" readonly="readonly">
		</div>
		<div class="join_box">
			<input class="join_box2" type="text" id="id" name="id"
				value="<%=amanagerVo.getId()%>" readonly="readonly">
		</div>
		<div class="join_box">
			<input class="join_box2" type="password" id="pwd" name="pwd"
				placeholder="비밀번호 입력" oninput="checkPwd()"> <span
				class="join_check" id="pwdMessage">영어 대소문자, 숫자,
				특수문자(!@#$%^&*) 4~20자</span>
		</div>
		<div class="join_box">
			<input class="join_box2" type="password" id="pwdc" name="pwdc"
				placeholder="비밀번호 확인" oninput="checkPwdc()"> <span
				class="join_check" id="pwdcMessage">비밀번호 확인을 입력해 주십시오.</span>
		</div>
		<div class="join_box">
			<input class="join_box2" type="text" id="nm" name="nm"
				placeholder="이름 입력" oninput="checkName()"
				value="<%=amanagerVo.getNm()%>"> <span class="join_check"
				id="nameMessage">이름을 입력해 주십시오.</span>
		</div>
		<div class="join_box">
			<input class="join_box2" type="email" id="email" name="email"
				placeholder="이메일" oninput="checkEmail()"
				value="<%=amanagerVo.getEmail()%>"> <span class="join_check"
				id="emailMessage">이메일을 입력해주십시오.</span>
		</div>
		<div class="join_box">
			<input class="join_box2" type="tel" id="phone" name="phone"
				placeholder="전화번호 입력" oninput="checkPhone()"
				value="<%=amanagerVo.getPhone()%>"> <span class="join_check"
				id="phoneMessage">휴대폰 번호를 입력해주십시오.</span>
		</div>
	</form>
	<div class="join_button2">
		<button class="join_button" onclick="modify()">수정</button>
		<button class="join_button" onclick="cancel()">취소</button>
	</div>
</html>