<%@page import="shop.mammastore.common.Parser"%>
<%@page import="shop.mammastore.admin.vo.AnoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AnoticeVo vo = (AnoticeVo) request.getAttribute("anoticeVo");
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
var content = '<%=Parser.chgToHTML(vo.getCntnt())%>';

	function register() {
		var sj = $('#sj');

		if (!sj.val()) {
			alert('게시글 제목을 입력해 주세요.');
			sj.focus();
			sj.val('');
			return;
		}
		saveContent();
	}
	
	function cancel() {
		history.back();
	}
</script>
</head>
<body>
	공지사항 게시글 수정 페이지 입니다.
	<form action="/anotice/modifyProc" method="post" id="editorForm">
		<div>
			게시글 번호 <input type="text" name="notice_sq"
				value="<%=vo.getNotice_sq()%>" style="display: none" />
		</div>
		<div>
			제목<input type="text" name="sj" id="sj" maxlength="100"
				value="<%=vo.getSj()%>">
		</div>
		<div>
			<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
		</div>
	</form>
	<button onclick="register()">수정</button>
	<button onclick="cancel()">취소</button>
</body>
</html>