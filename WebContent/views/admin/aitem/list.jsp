<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArrayList<AitemVo> list = (ArrayList<AitemVo>) request.getAttribute("list");
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
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	��ǰ����Ʈ ������ ���� �� ���� �׺���̼� ����(ī�װ�)
	<br>
	<div>
		<table border=1>
			<tr>
				
				<th>��ǰ��ȣ</th>
				<th>�̹���</th>
				<th>�ǸŻ���</th>
				<th>��ǰ�̸�</th>
				<th>����</th>
				<th>���</th>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr onclick="location.href='/aitem/detail?sq=<%=list.get(i).getItem_sq() %>'">
				<td><%=list.get(i).getItem_sq() %></td>
				<td><img alt="" src="<%=list.get(i).getFl_pth() %>" width="200px" height="200px"></td>
				<td><%=list.get(i).isSttus_fl() %></td>
				<td><%=list.get(i).getNm() %></td>
				<td><%=list.get(i).getPc() %>
				<td><%=list.get(i).getStock()%>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<button onclick="register()">���</button>

	<a href='/item/detail'>��ǰ��������</a>
	<br>
	<a href='/'>Ȩ����</a>
</body>
</html>