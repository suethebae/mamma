<%@page import="shop.mammastore.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	LoginManager lm = LoginManager.getInstance();
String member_sq = lm.getMemberId(session);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>+ mamma store +</title>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<section>
	메인페이지
	</section>

 <!-- footer -->
  <section>
    <div class="footer">
      <div class="footer__info">
        <span>lechance7@naver.com | 0507-1327-3547 ㅣ 운영 시간: 월 - 금요일 / 주말, 공휴일 휴무 / 13:00 - 17:00
          <br>상호 : 맘마상점 | 대표 : 하지윤
        </span>
        <br>
        <span>주소 : 대구광역시 동구 둔산로2길 18-1</span>
        <br>
        <span>사업자등록번호 : 889-04-01185 | 통신판매 : 제 2019-대구동구-0114 호 | Hosting by 우리조</span>
      </div>
      <div class="footer__mainmenu">
        <ul>
          <li>
            <a href="/">
              <sapn>SHOP</sapn>
            </a>
          </li>
          <li>
            <a href="/">
              <sapn>ABOUT</sapn>
            </a>
          </li>
          <li>
            <a href="/">
              <sapn>CONTACT</sapn>
            </a>
          </li>
          <li>
            <a href="/">
              <sapn>NOTICE</sapn>
            </a>
          </li>
        </ul>
      </div>
      <div class="footer__agremenu">
        <ul>
          <li><a href="">이용약관</a></li>
          <li><a href="">개인정보처리방침</a></li>
        </ul>
      </div>
      <div class="footer__snslink">
        <a href=""><i class="fab fa-instagram-square"></i></a>
        <a href=""><i class="fab fa-twitter-square"></i></a>
      </div>
    </div>
    <div class="footer__copyright">Copyright ⓒ 2021 맘마상점 mamma store All rights reserved.</div>
  </section>
</body>
</html>