<%@page import="shop.mammastore.admin.vo.AmemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AmemberVo amemberVo = (AmemberVo) request.getAttribute("amemberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
//이름 체크
var isNameChecked = false;
function initCheckName() {
	isNameChecked = false;
}
function checkName() {
	var nm = $('#nm');
	initCheckName()
	if (!nm.val() || nm.val() == "") {
		$('#nameMessage').text('한글 2~8자 ');
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
		isNameChecked = true;
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

	if (email.val() != '<%=amemberVo.getEmail()%>') {
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

	if (phone.val() != '<%=amemberVo.getPhone()%>') {
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
	if(!isNameChecked) {
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

	$('#mForm').submit();
}
// 아이디, 회원번호 임의로 수정못하게 조건걸어두기(나중에)
</script>
</head>
<body>
	<h1>회원정보</h1>
	<a href="/amember/list">회원목록으로</a>
	<hr>
<%--<form action="/amember/modifyProc?sq=<%=amemberVo.getMber_sq()%>">--%>
	<form action="/amember/modifyProc" method="post" id="mForm">
		<div>
			회원번호
			<input type="text" id="mber_sq" name="mber_sq" value=<%=amemberVo.getMber_sq()%> readonly="readonly"/>
		</div>
		<div>
			이름 <input type="text" id="nm" name="nm" oninput="checkName()"
				value=<%=amemberVo.getNm()%>> <span id="nameMessage">이름을
				입력해주십시오 </span>
		</div>
		<div>
			아이디
			<input type="text" id="id" name="id" value=<%=amemberVo.getId()%> readonly="readonly"/>
			
		</div>
		<div>
			이메일 <input type="email" id="email" name="email"
				oninput="checkEmail()" value=<%=amemberVo.getEmail()%>> <span
				id="emailMessage">이메일을 입력해주십시오</span>
		</div>
		<div>
			휴대폰 <input type="tel" id="phone" name="phone" oninput="checkPhone()"
				value=<%=amemberVo.getPhone()%>> <span id="phoneMessage">전화번호를
				입력해주십시오 </span>
		</div>
	</form>
	<hr>
	<button onclick="save()">저장하기</button>
</body>
</html>