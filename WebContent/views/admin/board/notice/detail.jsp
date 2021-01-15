<%@page import="shop.mammastore.common.Parser"%>
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
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function modify(){
		location.href="/anotice/modify?sq=<%=vo.getNotice_sq()%>";
	}
	function adelete(){
	  	  if ( confirm('삭제하시겠습니까?')) {
		    	location.href="anotice/delete?sq=<%=vo.getNotice_sq()%>";
			} else {
				return;
			}
		}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	공지사항 게시글 상세 페이지 입니다.
	<table border=1>
		<tr>
			<td>게시글 번호</td>
			<td><%=vo.getNotice_sq()%></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=vo.getMngr_sq()%></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><%=vo.getSj()%></td>
		</tr>
		<tr>
			<td>공지사항 내용</td>
			<td><%=vo.getCntnt()%></td>
		</tr>
		<tr>
			<td>작성일시</td>
			<td><%=vo.getDttm()%></td>
		</tr>
	</table>
	<button onclick="modify()">수정</button>
	<button onclick="adelete()">삭제</button>
</body>
</html>