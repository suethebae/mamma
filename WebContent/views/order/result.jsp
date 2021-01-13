<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 결과</title>
<script>
	function showMyorder(){
		location.href ="/mymenu/myOrder"
	}
	function gotoHome(){
		location.href = "/"
	}
</script>
</head>
<body>
	구매해 주셔서 감사합니다.
	<button onclick="showMyorder()">주문 내역 보기</button><button onclick="gotoHome()">홈으로</button>
</body>
</html>