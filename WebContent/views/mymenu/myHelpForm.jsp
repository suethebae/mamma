<%@page import="shop.mammastore.mamma.vo.HelpVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	HelpVo helpVo = (HelpVo) request.getAttribute("helpVo");
	ArrayList<HelpVo> list = (ArrayList<HelpVo>) request.getAttribute("list");
%>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 리스트</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">

   function help(){
		location.href="/help/register";
	 }

</script>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>

리스트 목록

	
	문의 리스트
	<table border="1">
		<tr>
			<td>글 번호</td>
			<td>글제목</td>
			<td>작성자</td>
		<!--     <td>작성일</td>  -->
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<tr onclick="location.href='/help/detail?help_sq=<%=list.get(i).getHelp_sq()%>'">
			<td><%=list.get(i).getHelp_sq()%></td>
			<td><%=list.get(i).getSj()%></td>
			<td><%=list.get(i).getId()%></td>
			<%-- <td><%=list.get(i).getDttm()%></td> --%>
		</tr>
		<%
			}
		%>
	</table>
<a href='/'>홈으로</a>
<button onclick="help()">문의작성</button>
</body>
</html>