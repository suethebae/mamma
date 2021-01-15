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
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	<div>
		<table border=1>
			<tr>
				<th>상품번호</th>
				<th>카테고리</th>
				<th>이미지</th>
				<th>판매상태</th>
				<th>상품이름</th>
				<th>가격</th>
				<th>재고</th>
			</tr>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr
				onclick="location.href='/aitem/detail?sq=<%=list.get(i).getItm_sq()%>'">
				<td><%=list.get(i).getItm_sq()%></td>
				<td><%=list.get(i).getCtgry_sq()%></td>
				<td><img
					onerror="this.src='https://t1.daumcdn.net/thumb/R600x0/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fqna%2Fimage%2F4b035cdf8372d67108f7e8d339660479dfb41bbd'"
					src="<%=list.get(i).getFl_pth()%>" width="200px" height="200px"></td>
				<td><%=list.get(i).isSttus_fl()%></td>
				<td><%=list.get(i).getNm()%></td>
				<td><%=list.get(i).getPc()%>
				<td><%=list.get(i).getStock()%>
			</tr>
			<%
			}
			%>
		</table>
	</div>

</body>
</html>