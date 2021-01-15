<%@page import="shop.mammastore.admin.vo.AhelpVo"%>
<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
AhelpVo helpVo = (AhelpVo) request.getAttribute("ahelpVo");
ArrayList<AhelpVo> list = (ArrayList<AhelpVo>) request.getAttribute("list");
%>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 리스트</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function modifyHelp(help_sq) {
		location.href = "/ahelp/modify?help_sq=" + help_sq;
	}
	function deleteHelp(help_sq) {
		location.href = "/ahelp/delete?help_sq=" + help_sq;
	}
</script>
</head>
<body>

	문의 리스트
	<table border="1">
		<tr>
			<td>글 번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>주문코드</td>
			<td>날짜</td>
			<td>답변상태</td>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr
			onclick="location.href='/ahelp/detail?help_sq=<%=list.get(i).getHelp_sq()%>'">
			<td><%=list.get(i).getHelp_sq()%></td>
			<td><%=list.get(i).getSj()%></td>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getOrder_cd()%></td>
			<td><%=list.get(i).getDttm()%></td>
			<td><%=list.get(i).isAnswer_fl()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>