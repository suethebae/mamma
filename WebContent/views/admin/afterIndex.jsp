<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function registerItem() {
		location.href = "/aitem/register";
	}

	function showAmngrList(){
		location.href = "/amanager/list";
	}
	function registerMngr(){
		location.href = "/amanager/register"
	}
	function showAitemList(){
		location.href = "/aitem/list";
		}
</script>
</head>
<body>
	<button onclick="showAmngrList()">관리자목록</button>
	<button onclick="showAitemList()">상품목록 리스트</button>
	
	<button onclick="registerItem()">상품등록</button>
	<button onclick="showActgryList()">카테고리 목록</button>
	<button onclick="showList()">상품 목록</button>
	
</body>
</html>