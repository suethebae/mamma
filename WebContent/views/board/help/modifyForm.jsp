<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function sumit() {
		saveContent();
	}
	//getParameter에 Content로 받아야 한다.
</script>

</head>
<body>
	문의글수정 게시판입니다.
	<form action="/help/helpModify" id="editorForm">
		<div>
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="submit()">저장</button>
</body>
</html>