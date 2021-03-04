<%@page import="shop.mammastore.common.Pagenation"%>
<%@page import="shop.mammastore.mamma.vo.HelpVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
ArrayList<HelpVo> list = (ArrayList<HelpVo>) request.getAttribute("list");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pn = request.getParameter("pn");
%>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 리스트</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function help() {
		location.href = "/help/register";
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="myHelp">
		<div class="myHelp">
			<div class="myHelp__header">
				<img src="/views/img/myhelp.png" alt="myHelp">
			</div>
			<div class="myHelp__main">
				<table>
					<tr>
						<th>작성일</th>
						<th>제목</th>
						<th>상태</th>
					</tr>
					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr class="line"
						onclick="location.href='/help/detail?help_sq=<%=list.get(i).getHelp_sq()%>'">
						<td><%=list.get(i).getDttm()%></td>
						<td><%=list.get(i).getSj()%></td>
						<td><%=list.get(i).isAnswer_fl() ? "답변 완료" : "답변 대기중"%></td>
						<%-- <td><%=list.get(i).getDttm()%></td> --%>
					</tr>
					<%
					}
					%>
				</table>
			</div>
			<!-- pagenation -->
			<div class="myHelp__bottom">
				<div class="myHelp__pagenaiton">
					<%
					if (pagenation.getStartPageNumber() != 1) {
					%>
					<a
						href="/mymenu/myHelp?pn=<%=pagenation.getStartPageNumber() - 1%>">prev</a>
					<%
					}
					%>
					<%
					for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
						if (i != Integer.parseInt(pn)) {
					%>
					<a href="/mymenu/myHelp?pn=<%=i%>"><%=i%></a>
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
					<a
						href="/mymenu/myHelp?pn=<%=pagenation.getStartPageNumber() + 1%>">next</a>
					<%
					}
					%>
				</div>
				<button class="myHelp__btn" onclick="help()">문의작성</button>
			</div>
		</div>
	</section>
</body>
</html>