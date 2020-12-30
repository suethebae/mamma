<%@page import="shop.mammastore.admin.vo.AmemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<AmemberVo> list = (ArrayList<AmemberVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원목록리스트

	<table border=1>
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일시</th>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr onclick="location.href='/amember/detail?sq=<%=list.get(i).getMber_sq()%>'">
			<td><%=list.get(i).getMber_sq()%></td>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getNm()%></td>
			<td><%=list.get(i).getPhone()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getDttm()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<a href='/views/admin/afterIndex.jsp'>관리자 홈으로</a>
</body>
</html>