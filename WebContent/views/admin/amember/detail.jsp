<%@page import="shop.mammastore.admin.vo.AmemberVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    AmemberVo vo =(AmemberVo) request.getAttribute("amemberVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function modify(){
	location.href="/amember/modify?mber_sq=<%=vo.getMber_sq()%>";
	
}

function Delete(){
	
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="amember/delete?mber_sq=<%=vo.getMber_sq()%>";

		} else {
			return;
		}
	}
</script>
</head>
<body>
	<!-- <form action="/aitem/amodifyProc" method="post" id="editorForm" enctype="multipare/form-data">	 -->
	<table border=1>
		<tr>
			<td>회원번호</td>
			<td><%=vo.getMber_sq()%></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=vo.getId()%></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=vo.getNm()%></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=vo.getPhone()%></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=vo.getEmail()%></td>
		</tr>
		<tr>
			<td>가입일시</td>
			<td><%=vo.getDttm()%></td>
		</tr>
	</table>
	
	<button onclick="modify()">수정</button>
	<button onclick="Delete()">삭제</button>
	
</body>
</html>