<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" type="text/css" href="/views/css/admin.css">
<script>
	function registerItem() {
		location.href = "/aitem/register";
	}
	function showAmngrList() {
		location.href = "/amanager/list";
	}
	function showAitemList() {
		location.href = "/aitem/list";
	}
	function showActgryList() {
		location.href = "/actgry/list";
	}
	function showMemberList() {
		location.href = "/amember/list";
	}
	function showAhelpList() {
		location.href = "/ahelp/list";
	}
	function showOrderList() {
		location.href = "/aorder/list";
	}
	function noticeList() {
		location.href = "/anotice/list";
	}
	function reviewList() {
		location.href = "/areview/list";
	}
	function logout() {
		location.href = "/amanager/logout";
	}
</script>
</head>
<body>
	<div class="nav">
		<button onclick="showAmngrList()">관리자목록</button>
		<button onclick="showAitemList()">상품목록</button>
		<button onclick="registerItem()">상품등록</button>
		<button onclick="showActgryList()">카테고리 목록</button>
		<button onclick="showMemberList()">회원 관리 목록</button>
		<button onclick="showAhelpList()">1:1 문의 목록</button>
		<button onclick="showOrderList()">주문 내역</button>
		<button onclick="noticeList()">공지사항</button>
		<button onclick="reviewList()">리뷰 목록</button>
		<button onclick="logout()">로그아웃</button>
	</div>
</body>
</html>