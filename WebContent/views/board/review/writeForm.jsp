<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<script>
function submit(){
	saveContent();
}
</script>
</head>
<body>
	글작성 게시판입니다
	<form action="/board/reviewRegister" method="post" id="editorForm">
		내용
		<div>
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false"></jsp:include>
		</div>
	</form>
	<button type="button" onclick="submit()">등록</button>
</body>
</html>