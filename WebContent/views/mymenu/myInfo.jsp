<%@page import="shop.mammastore.mamma.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
//memberVo 라는 오브젝트형태로 데이터를 넣었기때문에 이렇께써주자.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
function modify() {
	location.href="/member/modify"
}
</script>
</head>
<body>
	<a href='/'>Mamma store</a>
	<hr> 
	<h1>회원정보</h1>
	<hr>
	<form action="">
		<p>
			이름
			<%=memberVo.getNm()%>
		</p>
		<p>
			아이디
			<%=memberVo.getId()%>
		</p>
		<p>
			이메일
			<%=memberVo.getEmail()%>
		</p>
		<p>
			휴대폰
			<%=memberVo.getPhone()%>
		</p>
	</form>
	<hr>
	<a href="/member/leave">회원탈퇴</a>
	<br>
	<!-- 누르면 회원정보 수정 폼으로 넘어감 -->
	<button onclick="modify()">수정하기</button>
</body>
</html>