<%@page import="shop.mammastore.admin.vo.AmemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	AmemberVo amemberVo = (AmemberVo) request.getAttribute("amemberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function modify() {
		location.href="/amember/modify?sq=<%=amemberVo.getMber_sq()%>";
	}
	function mdelete() {
		location.href="/amember/delete?sq=<%=amemberVo.getMber_sq()%>";
	}
</script>
</head>
<body>
	<h1>회원정보</h1>
	<a href="/amember/list">회원목록으로</a>
	<hr>
	<form action="/amember/modifyProc" method="post" id="mForm">
		<p>
			회원번호
			<%=amemberVo.getMber_sq()%>
		</p>
		<p>
			이름
			<%=amemberVo.getNm()%>
		</p>
		<p>
			아이디
			<%=amemberVo.getId()%>
		</p>
		<p>
			이메일
			<%=amemberVo.getEmail()%>
		</p>
		<p>
			휴대폰
			<%=amemberVo.getPhone()%>
		</p>
	</form>
	<hr>
	<button onclick="modify()">수정하기</button>
	<button onclick="mdelete()">회원정보삭제</button>
</body>
</html>