<%@page import="shop.mammastore.admin.vo.AnoticeVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AnoticeVo vo = (AnoticeVo) request.getAttribute("anoticeVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="notice__detail">
		<div class="notice__detail">
			<div class="notice__detail__main">
				<table>
					<tr>
						<td>제목</td>
						<td><%=vo.getSj()%></td>
					</tr>
					<tr class="line">
						<td>No.</td>
						<td><%=vo.getNotice_sq()%></td>
					</tr>
					<tr class="line">
						<td>작성일</td>
						<td><%=vo.getDttm()%></td>
					</tr>

					<tr class="line">
						<td>내용</td>
						<td><%=vo.getCntnt()%></td>
					</tr>
				</table>
				<div class="notice__detail__bottom">
					<button class="notice__detail__btn" onclick="location.href='/notice/list'">목록</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>