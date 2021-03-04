<%@page import="shop.mammastore.mamma.vo.HelpVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<HelpVo> list = (ArrayList<HelpVo>) request.getAttribute("olist");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function submit() {
		var sj = $('#sj');

		if (!sj.val()) {
			alert('글 제목을 입력하여 주십시오.');
			sj.focus();
			sj.val('');
			return;
		}

		saveContent();
	}
	
	function cancle() {
		location.href = "/mymenu/myhelp";
	}
</script>
</head>
<body>
	문의글 작성 게시판입니다.
	<br>
	<br>
	<form action="/help/registerProc" method="post" id="editorForm">
		<div>
			주문 코드
			<select name="order_cd" id="order_sq">
			<option value=""></option>
			<% for(int i = 0; i < list.size(); i++) { %>
			<option value="<%=list.get(i).getOrder_cd()%>"><%=list.get(i).getOrder_cd()%></option>
			<%} %>
		</select>
		</div>
		<div>
			제목 <input type="text" id="sj" name="sj">
		</div>
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<input type="button" onclick="submit()" value="등록"> 
	<input type="button" onclick="cancle()" value="취소">
</body>
</html>