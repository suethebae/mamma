<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		var subject = $('#subject');

		if (!subject.val()) {
			alert('글 제목을 정상적으로 입력하여 주십시오.');
			subject.focus();
			subject.val('');
			return;
		}

		saveContent();
	}
	// getParameter에 content로 받아야 한다.
	// 태그를 문자열로 바꿔서 저장
	// 뿌릴때는 태그로 바꿔서 뿌려
	// 수정할때는 바꿀필요 없다.
	function cancle() {
		location.href = "/help/helpList"
	}
</script>
</head>
<body>
	문의글 작성 게시판입니다.
	<form action="/help/helpRegister" method="post" id="editorForm">
		제목<input type="text" name="subject" id="subject" maxlength="100">
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
		<input type="button" onclick="submit()" value="등록"> 
		<input type="button" onclick="cancle()" value="취소">
	</form>
</body>
</html>