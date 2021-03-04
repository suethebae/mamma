<%@page import="shop.mammastore.common.Pagenation"%>
<%@page import="shop.mammastore.admin.vo.AmemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<AmemberVo> list = (ArrayList<AmemberVo>) request.getAttribute("list");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pn = request.getParameter("pn");
String filter = (String) request.getAttribute("filter");
String keyword = (String) request.getAttribute("keyword");
String startDate = (String) request.getAttribute("startDate");
String endDate = (String) request.getAttribute("endDate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function search() {
		var filter = $('#filter option:selected');
		var keyword = $('#keyword');
		var startDate = $('#startDate');
		var endDate = $('#endDate');
		location.href = "/amember/list?pn=1&filter=" + filter.val() + "&keyword="
				+ keyword.val() + "&startDate=" + startDate.val() + "&endDate="
				+ endDate.val();
	}
	function move(pn) {
		var filter = $('#filter option:selected');
		var keyword = $('#keyword');
		var startDate = $('#startDate');
		var endDate = $('#endDate');
		location.href = "/amember/list?pn=" + pn + "&filter=" + filter.val()
				+ "&keyword=" + keyword.val() + "&startDate=" + startDate.val()
				+ "&endDate=" + endDate.val();
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	<br>회원목록리스트<br>
	<select name="filter" id="filter">
		<option value="" <%=filter.equals("") ? "selected" : ""%>>All</option>
		<option value="id" <%=filter.equals("id") ? "selected" : ""%>>ID</option>
		<option value="nm" <%=filter.equals("nm") ? "selected" : ""%>>이름</option>
	</select>
	<input type="text" id="keyword" name="keyword" value="<%=keyword%>" />
	<input type="date" id="startDate" name="startDate"
		value="<%=startDate%>" />
	<input type="date" id="endDate" name="endDate" value="<%=endDate%>" />
	<button onclick="search()">검색</button>
	<table border=1>
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일시</th>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr
			onclick="location.href='/amember/detail?sq=<%=list.get(i).getMber_sq()%>'">
			<td><%=list.get(i).getMber_sq()%></td>
			<td><%=list.get(i).getId()%></td>
			<td><%=list.get(i).getNm()%></td>
			<td><%=list.get(i).getPhone()%></td>
			<td><%=list.get(i).getEmail()%></td>
			<td><%=list.get(i).getDttm()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<div>
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

	<a href='/views/admin/afterIndex.jsp'>관리자 홈으로</a>
</body>
</html>