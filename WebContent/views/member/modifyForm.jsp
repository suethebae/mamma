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
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	//패스워드 체크
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

		if (email.val() != '<%=memberVo.getEmail()%>') {
			$.ajax({
				url : "/ajax/checkEmail",
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

		if (phone.val() != '<%=memberVo.getPhone()%>') {
			$.ajax({
				url : "/ajax/checkPhone",
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
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="myInfo">
		<div class="myInfo">
			<div class="myInfo__header">
				<img src="/views/img/myinformation.png" alt="myInfo">
			</div>
			<div class="myModi__main">
				<form action="/member/modifyProc" method="post" id="mForm">
					<table>

						<tr>
							<td>이름</td>
							<td class="padding"><%=memberVo.getNm()%></td>
							<td></td>
						</tr>
						<tr class="line">
							<td>아이디</td>
							<td class="padding"><%=memberVo.getId()%></td>
							<td></td>
						</tr>
						<tr class="line">
							<td>비밀번호</td>
							<td class="padding"><input type="password" id="pwd" name="pwd"
								oninput="checkPwd()"></td>
							<td class="padding"><span id="pwdMessage">영어 대소문자, 숫자, 특수문자(!@#$%^&*)
									4~20자</span></td>
						</tr>
						<tr class="line">
							<td>비밀번호 확인</td>
							<td class="padding"><input type="password" id="pwdc" name="pwdc"
								oninput="checkPwdc()"></td>
							<td class="padding"><span id="cpwdMessage"> 비밀번호 확인을 입력해 주십시오.</span></td>
						</tr>
						<tr class="line">
							<td>이메일</td>
							<td class="padding"><input type="email" id="email" name="email"
								oninput="checkEmail()" value=<%=memberVo.getEmail()%>></td>
							<td class="padding"><span id="emailMessage">이메일을 입력해주십시오.</span></td>
						</tr>
						<tr class="line">
							<td>휴대폰</td>
							<td class="padding"><input type="tel" id="phone" name="phone"
								oninput="checkPhone()" value=<%=memberVo.getPhone()%>></td>
							<td class="padding"><span id="phoneMessage">전화번호를 입력해주십시오.</span></td>
						</tr>
					</table>
				</form>
				</div>
				<div class="myModi__btns">
				<button class="myModi__btn" onclick="save()">저장하기</button>
				<button class="myModi__btn" onclick="location.href='/mymenu/myInfo'">취소</button>
				</div>
		</div>
	</section>
</body>
</html>