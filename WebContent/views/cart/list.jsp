<%@page import="shop.mammastore.mamma.vo.CartListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<CartListVo> list = (ArrayList<CartListVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 보기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
	<table>
		<tr>
			<th>장바구니 번호</th>
			<th>상품 이미지</th>
			<th>상품 이름</th>
			<th>상품 가격</th>
			<th>갯수</th>
			<td>총가격</td>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<%
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getCart_sq()%></td>
			<td><img src="<%=list.get(i).getFl_pth()%>" alt=""
				width="100px" height="100px" /></td>
			<td><%=list.get(i).getItm_nm()%></td>
			<td><%=list.get(i).getItm_pc()%></td>
			<td><%=list.get(i).getItm_cnt()%></td>
			<td><%=list.get(i).getItm_pc() * list.get(i).getItm_cnt()%></td>
			<td><button onclick="">수정</button></td>
			<td><button onclick="">삭제</button></td>
		</tr>
		<%
		total += list.get(i).getItm_pc() * list.get(i).getItm_cnt();
		}
		%>
	</table>
	<button onclick="">초기화</button>
	총금액 <%=total%>
</body>
</html>