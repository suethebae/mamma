<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	상품등록하는 페이지
	<form action="/admin/registerItemProc" method="post" id="irForm">
		<div>
			<input type="text" id="itemName" name="itemName" placeholder="상품명"
				oninput="">
		</div>
		<div>
			<input type="text" id="itemPrice" name="itemPrice" placeholder="상품가격"
				oninput="">
		</div>
		<div>
			<!-- 웹에디터 -->
		</div>
		<div>
			<!-- 이미지추가 -->

		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>
</body>
</html>