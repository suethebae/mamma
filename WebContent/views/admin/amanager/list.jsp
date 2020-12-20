<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<AmanagerVo> list = (ArrayList<AmanagerVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 목록</title>
</head>
<body>
	<table border=1>
	<tr>
		<th>관리자 번호</th>
		<th>관리자 권한</th>
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>전화번호</th>
	</tr>
	<%for(int i=0; i<list.size(); i++) {%>
	<tr>
		<th><%=list.get(i).getMngr_sq() %></th>
		<th><%=list.get(i).isAuthor() %></th>
		<th><%=list.get(i).getId() %></th>
		<th><%=list.get(i).getNm() %></th>
		<th><%=list.get(i).getEmail() %></th>
		<th><%=list.get(i).getPhone() %></th>
	</tr>
	<%} %>
	</table>
</body>
</html>