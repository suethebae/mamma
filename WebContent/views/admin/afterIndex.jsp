<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function noticeList() {
location.href="/notice/list"
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	관리자 페이지 메인
	<input type="button" onclick="noticeList()" value="공지사항">
	<br>
</body>
</html>