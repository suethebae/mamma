<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
ArrayList<ActgryVo> list = (ArrayList<ActgryVo>) request.getAttribute("list");
%>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 리스트</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function actgryRegister() {
		location.href = "/actgry/register";
	}
	function modifyCtgry(ctgry_sq){
		location.href="/actgry/modify?ctgry_sq="+ctgry_sq;
	}
	function deleteCtgry(ctgry_sq){
		location.href="/actgry/delete?ctgry_sq="+ctgry_sq;
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	카테고리 리스트
	<table border="1">
		<tr>
			<td>카테고리 번호</td>
			<td>카테고리 이름</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getCtgry_sq()%></td>
			<td><%=list.get(i).getNm()%></td>
			<td><button
					onclick="modifyCtgry(<%=list.get(i).getCtgry_sq()%>)">수정</button></td>
			<td><button
					onclick="deleteCtgry(<%=list.get(i).getCtgry_sq()%>)">삭제</button></td>
		</tr>
		<%
		}
		%>
	</table>
	<button onclick="actgryRegister()">카테고리 등록</button>
</body>
</html>