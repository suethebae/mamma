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
	// getParameter에 content로 받아야 한다.
	// 태그를 문자열로 바꿔서 저장
	// 뿌릴때는 태그로 바꿔서 뿌려
	// 수정할때는 바꿀필요 없다.
</script>
</head>
<body>
공지사항 게시글 작성 페이지 입니다.
	<form action="/board/questionRegister" method="post" id="editorForm">
		제목<input type="text" name="subject" id="subject" maxlength="100">
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
		<input type="button" onclick="sumit()" value="등록">
	</form>
</body>
</html>