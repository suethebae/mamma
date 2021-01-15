<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
My page<br>

<a href='/mymenu/myOrder'>주문 내역</a>
<a href='/mymenu/myReview'>나의 리뷰</a>
<a href='/mymenu/myHelp'>1:1 문의</a>
<a href='/mymenu/myInfo'>나의 정보</a>

</body>
</html>