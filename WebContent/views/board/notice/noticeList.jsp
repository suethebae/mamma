<%@page import="shop.mammastore.admin.vo.AnoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<AnoticeVo> list = (ArrayList<AnoticeVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
	공지사항 게시판
	<table border='1'>
		<tr>
			<td>공지사항글 번호</td>
			<td>글 작성자</td>
			<td>제목</td>
			<td>게시일</td>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr
			onclick="location.href='/notice/detail?sq=<%=list.get(i).getNotice_sq()%>'">
			<td><%=list.get(i).getNotice_sq()%></td>
			<td><%=list.get(i).getMngr_sq()%></td>
			<td><%=list.get(i).getSj()%></td>
			<td><%=list.get(i).getDttm()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<a href='/views/index.jsp'>메인화면으로</a>
</body>
</html>