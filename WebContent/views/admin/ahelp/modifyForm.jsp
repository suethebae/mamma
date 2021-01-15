<%@page import="shop.mammastore.admin.vo.AhelpVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.common.Parser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	AhelpVo vo = (AhelpVo) request.getAttribute("ahelpVo");
ArrayList<AhelpVo> list = (ArrayList<AhelpVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
	<%-- 	var content = '<%=Parser.chgToHTML(vo.getCntnt())%>'; --%>

		function register() {
			var sj = $('#sj');
			var phone =$('#phone');
			var email =$('#email');

			if (!sj.val()) {
				alert('제목 입력하여 주십시오.');
				sj.focus();
				sj.val('');
				return;
			}
			saveContent();
		}
		function cancel() {
			location.href = "/ahelp/list";
		}
	</script>
</head>
<body>
	<form action="/ahelp/modifyProc" method="post" id="editorForm">
		<div>
			문의 번호 <input type="text" name="help_sq" id="help_sq"
				value="<%=vo.getHelp_sq()%>" style="display: none">
		</div>
		<div>
			문의 일자 <input type="text" id="dttm" name="dttm"
				value="<%=vo.getDttm()%>">
		</div>
		<div>
			문의 상태 <input type="text" id="answer_fl" name="answer_fl"
				value="<%=vo.isAnswer_fl()%>" readonly="readonly">
		</div>
		<div>
			작성자 <input type="text" id="id" name="id" value="<%=vo.getId()%>"
				readonly="readonly">
		</div>
		<div>
			주문코드 <input type="text" id="order_cd" name="order_cd"
				value="<%=vo.getOrder_cd()%>" readonly="readonly">
		</div>
		<div>
			연락처 <input type="text" id="phone" name="phone"
				value="<%=vo.getPhone()%>" readonly="readonly">
		</div>
		<div>
			이메일 <input type="text" id="email" name="email"
				value="<%=vo.getEmail()%>" readonly="readonly">
		</div>
		<div>
			글 제목 <input type="text" id="sj" name="sj" value="<%=vo.getSj()%>">
		</div>
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>
</body>
</html>