<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300;500;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
	integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/views/css/style.css">

<title>JOIN</title>

<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	//아이디 체크
	var isIdChecked = false;
	function initCheckId() {
		isIdChecked = false;
	}

	function checkId() {
		initCheckId();
		var id = $('#id');
		if (!id.val() || id.val() == "") {
			$('#idMessage').text('영어 소문자,숫자 4~16자');
			isIdChecked = false;
			return;
		} else {
			isIdChecked = true;
		}

		var regExp = new RegExp("^[a-z0-9]{4,16}$", "g");
		if (regExp.exec(id.val()) == null) {
			$('#idMessage').text('잘못된 아이디 형식입니다.');
			isIdChecked = false;
			return;
		} else {
			isIdChecked = true;
		}

		$.ajax({
			url : "/ajax/checkId",
			type : "post",
			dataType : "json",
			data : {
				id : id.val()
			},
			error : function() {
				alert("통신 실패");
			},
			success : function(data) {
				if (data.isDuplicate == 'true') {
					$('#idMessage').text('중복된 아이디 입니다.');
					isIdChecked = false;
				} else {
					$('#idMessage').text('사용할 수 있는 아이디 입니다.');
					isIdChecked = true;
				}
			}
		});
	}

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

	//이름 체크
	var isNameChecked = false;
	function initCheckName() {
		isNameChecked = false;
	}
	function checkName() {
		var nm = $('#nm');
		initCheckName()
		if (!nm.val() || nm.val() == "") {
			$('#nameMessage').text('한글 2~8자');
			isNameChecked = false;
			return;
		} else {
			isNameChecked = true;
		}

		var regExp = new RegExp("^[가-힣]{2,8}$", "g");
		if (regExp.exec(nm.val()) == null) {
			$('#nameMessage').text('잘못된 이름 형식입니다.');
			isNameChecked = false;
			return;
		} else {
			$('#nameMessage').text('사용가능한 이름입니다.');
			isNameChecked = true;
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
					$('#phoneMessage').text('사용할 수 있는 휴대전화번호 입니다.');
					isEmailChecked = true;
				}
			}
		});
	}

	//회원 가입 버튼
	function register() {
		if (!isIdChecked) {
			$('#id').focus();
			return;
		}
		if (!isPwdChecked) {
			$('#pwd').focus();
			return;
		}
		if (!isPwdcChecked) {
			$('#pwdc').focus();
			return;
		}
		if (!isNameChecked) {
			$('#nm').focus();
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
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="join">
		<div class="join">
			<div class="join__header">
				<img src="/views/img/join.png" alt="join">
			</div>
			<!-- 가입내용 작성란 -->

			<div class="join__main">
				<form action="/member/registerProc" method="post" id="mrForm">
					<div class="join__box">
						<input class="join_box2" type="text" id="id" name="id"
							placeholder="아이디" oninput="checkId()"> <span
							class="join_check" id="idMessage">영어 소문자,숫자 4~16자</span>
					</div>
					<div class="join__box">
						<input class="join_box2" type="password" id="pwd" name="pwd"
							placeholder="비밀번호" oninput="checkPwd()"> <span
							class="join_check" id="pwdMessage">영어 대소문자, 숫자,
							특수문자(!@#$%^&*) 4~20자</span>
					</div>
					<div class="join__box">
						<input class="join_box2" type="password" id="pwdc" name="pwdc"
							placeholder="비밀번호 확인" oninput="checkPwdc()"> <span
							class="join_check" id="pwdcMessage">비밀번호 확인을 입력해 주십시오.</span>
					</div>
					<div class="join__box">
						<input class="join_box2" type="text" id="nm" name="nm"
							placeholder="이름" oninput="checkName()"> <span
							class="join_check" id="nameMessage">이름을 입력해 주십시오.</span>
					</div>
					<div class="join__box">
						<input class="join_box2" type="email" id="email" name="email"
							placeholder="이메일" oninput="checkEmail()"> <span
							class="join_check" id="emailMessage">이메일을 입력해주십시오.</span>
					</div>
					<div class="join__box">
						<input class="join_box2" type="tel" id="phone" name="phone"
							placeholder="전화번호" oninput="checkPhone()"> <span
							class="join_check" id="phoneMessage">휴대폰 번호를 입력해주십시오.</span>
					</div>

					<!-- 약관동의 -->
					<div class="join__agre">
						<h3>약관동의</h3>
						<div class="join__check">
							<label><input type="checkbox" id="magre" name="magre">이용약관
								동의</label><a>약관보기></a>
						</div>

						<div class="join__check">
							<label><input type="checkbox" id="pagre" name="pagre">개인정보처리방침
								동의</label><a>약관보기></a>
						</div>
					</div>

				</form>
				<div class="join__btns">
					<button class="join__btn" onclick="register()">등록</button>
					<button class="join__btn" onclick="cancel()">취소</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>