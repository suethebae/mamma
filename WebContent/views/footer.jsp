<%@page import="shop.mammastore.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
LoginManager lm = LoginManager.getInstance();
String member_sq = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Link -->
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<title>mamma store</title>
</head>

<body>
	<section id="footer">
		<div class="footer">
			<div class="footer__info">
				<span>lechance7@naver.com | 0507-1327-3547 ㅣ 운영 시간: 월 - 금요일 /
					주말, 공휴일 휴무 / 13:00 - 17:00 <br>상호 : 맘마상점 | 대표 : 하지윤
				</span> <br> <span>사업자등록번호
					: 889-04-01185 | 통신판매 : 제 2019-대구동구-0114 호 | Hosting by 우리조</span>
			</div>
			<div class="footer__mainmenu">
				<ul>
					<li><a href="/item/list"> <span>SHOP</span>
					</a></li>
					<li><a href="/etc/about"> <span>ABOUT</span>
					</a></li>
					<li><a href="/notice/list"> <span>NOTICE</span>
					</a></li>
					<li><a href="/etc/contact"> <span>CONTACT</span>
					</a></li>

				</ul>
			</div>
			<div class="footer__agremenu">
				<ul>
					<li><a href="">이용약관</a></li>
					<li><a href="">개인정보처리방침</a></li>
				</ul>
			</div>
			<div class="footer__snslink">
				<a href=""><i class="fab fa-instagram-square"></i></a> <a href=""><i
					class="fab fa-twitter-square"></i></a>
			</div>
		</div>
		<div class="footer__copyright">Copyright ⓒ 2021 맘마상점 mamma store
			All rights reserved.</div>
	</section>
</body>
</html>