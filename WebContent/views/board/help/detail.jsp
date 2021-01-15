<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.mamma.vo.HelpVo"%>
<%@page import="shop.mammastore.admin.vo.AhelpVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 HelpVo vo = (HelpVo) request.getAttribute("helpVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
<script>
function modify(){
	location.href="/help/modify?help_sq=<%=vo.getHelp_sq()%>";
}
function Delete(){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="help/delete?help_sq=<%=vo.getHelp_sq()%>";
		} else {
			return;
		}
	}
</script>
</head>
<body>

		<table border=1>
		<tr>
			<td>문의 번호</td>
			<td><%=vo.getHelp_sq()%></td>
		</tr>
		<tr>
			<td>문의 일자</td>
			<td><%=vo.getDttm()%></td>
		</tr>
		<tr>
			<td>문의 상태</td>
			<td><%=vo.isAnswer_fl()%></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=vo.getId()%></td>
		</tr>
		<tr>
			<td>주문 코드</td>
			<td><%=vo.getOrder_cd()%></td>
		</tr>
		<tr>
			<td>연락처</td>
			<td><%=vo.getPhone()%></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=vo.getEmail()%></td>
		</tr>
		<tr>
			<td>글 제목</td>
			<td><%=vo.getSj()%></td>
		</tr>

		<tr>
			<td>글 내용</td>
			<td><%=vo.getCntnt()%></td>
		</tr>
	</table>

	<button onclick="modify()">수정</button>
	<button onclick="Delete()">삭제</button>

<form>

	<div>
		답변 <input type="text" id="answer" name="answer" value="<%=vo.getAnswer()%>" readonly="readonly" >
	</div>
</form>  
</body>
</html>