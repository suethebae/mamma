<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	AitemVo aitemVo = (AitemVo) request.getAttribute("aitemVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div>
		<div>
			<img src="<%=aitemVo.getFl_pth()%>" alt="" width="400px"
				height="400px" />
		</div>
		<div><%=aitemVo.getNm()%></div>
		<div><%=aitemVo.getPc()%></div>
		<div><%=aitemVo.getDttm()%></div>
		<div><%=aitemVo.getCntnt()%></div>
	</div>
	<div>
	<button onclick="">장바구니</button>
	<button onclick="">구매</button>
	</div>
</body>
</html>