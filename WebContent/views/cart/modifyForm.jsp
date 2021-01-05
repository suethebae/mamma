<%@page import="shop.mammastore.mamma.vo.CartListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
CartListVo cartListVo = (CartListVo) request.getAttribute("cartListVo");
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
	장바구니 수정페이지
	<form action="/cart/modifyProc" method="post" id="modifyCart">
		<table>
			<tr>
				<th>장바구니</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>수량</th>
				<th>가격</th>
			</tr>
			<tr>
				<td><%=cartListVo.getCart_sq()%> <input type="hidden"
					name="cart_sq" value="<%=cartListVo.getCart_sq()%>"
					readonly="readonly" /></td>
				<td><img src="<%=cartListVo.getFl_pth()%>" alt="" width="100px"
					height="100px" /></td>
				<td><%=cartListVo.getItm_nm()%></td>
				<td><input type="number" id="itm_cnt" name="itm_cnt"
					value="<%=cartListVo.getItm_cnt()%>" /></td>
				<td><%=cartListVo.getItm_pc()%></td>
			</tr>
		</table>
		<button onclick="saveCart()">저장</button>
		location.href=""
	</form>
</body>
</html>