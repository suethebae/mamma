<%@page import="shop.mammastore.common.Pagenation"%>
<%@page import="shop.mammastore.admin.vo.AnoticeVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<AnoticeVo> list = (ArrayList<AnoticeVo>) request.getAttribute("list");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pn = request.getParameter("pn");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mamma store</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<script>
	function move(pn) {
		location.href = "/notice/list?pn="+pn;
	}
</script>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="notice">
		<div class="notice">
			<div class="notice__header">
				<img src="/views/img/notice.png" alt="notice">
			</div>
			<div class="notice__main">
				<table>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr class="line"
						onclick="location.href='/notice/detail?sq=<%=list.get(i).getNotice_sq()%>'">
						<td><%=list.get(i).getNotice_sq()%></td>
						<td><%=list.get(i).getSj()%></td>
						<td><%=list.get(i).getDttm()%></td>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<div class="notice__bottom">
				<div>
					<%
					if (pagenation.getStartPageNumber() != 1) {
					%>
					<a href="#"
						onclick="move(<%=pagenation.getStartPageNumber() - 1%>)">">prev</a>

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
					<a href="#"
						onclick="move(<%=pagenation.getStartPageNumber() + 1%>)">next</a>
					<%
					}
					%>
				</div>
			</div>
		</div>
		
	</section>
	
</body>
</html>