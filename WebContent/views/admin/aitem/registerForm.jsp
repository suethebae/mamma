<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
ArrayList<ActgryVo> list = (ArrayList<ActgryVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function register() {
		var nm = $('#nm');
		var pc = $('#pc');
		var stock = $('#stock');

		if (!nm.val()) {
			alert('상품 이름을 정상적으로 입력하여 주십시오.');
			nm.focus();
			nm.val('');
			return;
		}
		if (!pc.val()) {
			alert('가격을 입력하여 주십시오.');
			pc.focus();
			pc.val('');
			return;
		}
		if (!stock.val()) {
			alert('재고를 입력하여 주십시오.');
			stock.focus();
			stock.val('');
			return;
		}
		saveContent();
	}
	function cancel() {
		location.href = "/aitem/list";
	}
</script>
</head>
<body>
	상품등록하는 페이지
	<form action="/aitem/registerProc" method="post" id="editorForm"
		enctype="Multipart/form-data">
		카테고리 <select name="ctgry_sq" id="ctgry_sq" name="ctgry_sq">
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<option value="<%=list.get(i).getCtgry_sq()%>"><%=list.get(i).getNm()%></option>
			<%
			}
			%>
		</select>
		<div>
			상품명 <input type="text" id="nm" name="nm" maxlength="25">
		</div>
		<div>
			상품가격 <input type="text" id="pc" name="pc">
		</div>
		<div>
			재고 <input type="text" id="stock" name="stock">
		</div>
		<div>
			상품이미지 첨부 <input type="file" id="image" name="image" accept="image/*">
		</div>
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>
</body>
</html>