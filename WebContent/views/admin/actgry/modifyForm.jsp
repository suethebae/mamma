<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ActgryVo actgryVo = (ActgryVo) request.getAttribute("actgryVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 수정 폼</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function modifyProc() {
		var nm = $('#nm');
		var regExp = new RegExp("^[0-9a-zA-Z가-힣]{1,10}$","g");
		if(nm.val()==null||nm.val()==""||!regExp.exec(nm.val())){
			alert('카테고리 이름을 정상적으로 입력하여 주십시오');
			nm.focus();
			nm.val('');
			return false;
		}
	}
</script>
</head>
<body>
	<form action="/actgry/modifyProc" method="post"	onsubmit="return modifyProc()">
		<div>
			카테고리 번호 <input type="text" id="ctgry_sq" name="ctgry_sq" value="<%=actgryVo.getCtgry_sq()%>" readonly="readonly"/>
		</div>
		<div>
			카테고리 이름<input type="text" id="nm" name="nm" value="<%=actgryVo.getNm()%>" />
		</div>
		<div>
			<button type="submit">수정</button>
		</div>
	</form>
</body>
</html>