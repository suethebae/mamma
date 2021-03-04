<%@page import="shop.mammastore.common.Parser"%>
<%@page import="shop.mammastore.admin.vo.AnoticeVo"%>
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
		var sj = $('#sj');

		if (!sj.val()) {
			alert('공지사항 제목을 형식에 맞게 입력 해 주세요.')
			sj.focus();
			sj.val('');
			return;
		}
		saveContent();
	}
	function cancel() {
		location.href = "/anotice/noticeList";
	}
	// 태그를 문자열로 바꿔서 저장
	// 뿌릴때는 태그로 바꿔서 뿌려
</script>
</head>
<body>
	공지사항 게시글 작성 페이지 입니다.
	<form action="/anotice/registerProc" method="post" id="editorForm">
		제목<input type="text" name="sj" id="sj" maxlength="100">
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="submit()">등록</button>
	<button onclick="cancle()">취소</button>
</body>
</html>