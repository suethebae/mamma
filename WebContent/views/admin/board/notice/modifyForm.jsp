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
공지사항 게시글 수정 페이지 입니다.
	<form action="/" id="editorForm">
		<div>
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="sumit()">저장</button>
</body>
</html>