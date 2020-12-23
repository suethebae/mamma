<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	
    	AitemVo vo = (AitemVo) request.getAttribute("aitemVo");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function delete() {
	location.href="/aitem/delete";
}

function modify(){
	location.href="/aitem/amodify";
}
</script>
</head>
<body>
	
<table border=1>
	<tr>
		<td>상품번호</td>
		<td><input type="text" id="nm" name="nm" value=<%=vo.getItem_sq() %>></td>
	</tr>
	<tr>
		<td>판매상태</td>
		<td><%=vo.isSttus_fl() %></td>
	</tr>
	<%-- <tr>
		<td>카테고리</td>
		<td><%=vo.getCtgry_sq() %></td>
	</tr> --%>
	<tr>
		<td>상품이름</td>
		<td><%=vo.getNm() %></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><%=vo.getPc() %></td>
	</tr>
	<tr>
		<td>등록일자</td>
		<td><%=vo.getDttm()%></td>
	</tr>
	<tr>
		<td>이미지</td>
		<td><%=vo.getThumb_pth() %></td>
	</tr>
	<tr>
		<td>상품상세설명</td>
		<td><%=vo.getCntnt()%></td>
	</tr>
	<tr>
	<td>재고</td>
	<td><%=vo.getStock()%></td>
	</tr>
</table>
<button onclick="modify()">수정</button>
<button onclick="delete()">삭제</button>


</body>
</html>