<%@page import="shop.mammastore.common.Pagenation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.mamma.vo.ReviewVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<ReviewVo> list = (ArrayList<ReviewVo>) request.getAttribute("list");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pn = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function register() {
		location.href = "/review/register"
	}
</script>
<script>
	function move(pn) {
		location.href = "/mymenu/myReview?pn="+pn;
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="myReview">
		<div class="myReview">
			<div class="myReview__header">
				<img src="/views/img/myreview.png" alt="myReview">
			</div>
			<div class="myReview__main">
				<table>
					<tr>
						<td>주문코드</td>
						<td>제목</td>
						<td>작성일</td>
					</tr>
					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr class="line"
						onclick="location.href='/review/detail?review_sq=<%=list.get(i).getReview_sq()%>'">
						<td><%=list.get(i).getOrder_cd()%></td>
						<td><%=list.get(i).getSj()%></td>
						<td><%=list.get(i).getDttm()%></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<div class="myReview__bottom">
				<%
				if (pagenation.getStartPageNumber() != 1) {
				%>
				<a href="#" onclick="move(<%=pagenation.getStartPageNumber() - 1%>)">">prev</a>

				<%
				}
				%>
				<%
				for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
					if (i != Integer.parseInt(pn)) {
				%>
				<a href="#" onclick="move(<%=i%>)"><%=i%></a>
				<%
				} else {
				%>
				<%=i%>
				<%
				}
				}
				%>
				<%
				if (pagenation.getEndPageNumber() != pagenation.getTotalPageCount()) {
				%>
				<a href="#" onclick="move(<%=pagenation.getStartPageNumber() + 1%>)">next</a>
				<%
				}
				%>
			</div>
		</div>
	</section>
</body>
</html>