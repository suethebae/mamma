<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 등록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function actgryRegister() {
		var nm = $('nm');
		var regExp = new RegExp("^[0-9a-zA-Z가-힣]{1,10}$","g");
		if(nm.val()==null||nm.val()==""||!regExp.exec(nm.val())){
			alert('카테고리 이름을 정상적으로 입력하여 주십시오');
			nm.focus();
			nm.val('');
			return;
		}
		$('CRForm').submit();
	}
</script>
</head>
<body>
	<form action="/actgry/registerProc" method="post" id="CRForm">
		카테고리 제목<input type="text" id="nm" name="nm" maxlength="10">
	</form>
	<button onclick="actgryRegister()">등록</button>
</body>
</html>