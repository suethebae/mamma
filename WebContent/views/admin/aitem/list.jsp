<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<AitemVo> list = (ArrayList<AitemVo>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<%for(int i = 0 ; i<list.size(); i++) {%>
		<tr>
			<td>
			<img alt="" src="<%=list.get(i).getFl_pth()%>">
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>