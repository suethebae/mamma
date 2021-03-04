<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="myPage">
		<div class="myPage">
			<div class="myPage__header">
				<img src="/views/img/mypage.png" alt="myPage">
			</div>
			<div class="myPage__main">
				<div>
					<div>
						<a href='/mymenu/myOrder'> <i class="fas fa-clipboard-list"></i></a>
					</div>
					<span>주문 내역</span> 
				</div>
				<div>
					<div>
						<a href='/mymenu/myReview'><i class="fas fa-pen-alt"></i></a>
					</div>
					<span>나의 리뷰</span> 
				</div>
				<div>
					<div>
						<a href='/mymenu/myHelp'> <i class="fas fa-question"></i></a>
					</div>
					<span>1:1 문의</span> 
				</div>
				<div>
					<div>
						<a href='/mymenu/myInfo'> <i class="fas fa-user-alt"></i></a>
					</div>
					<span>나의 정보</span>
				</div>
			</div>
		</div>
	</section>
</body>
</html>