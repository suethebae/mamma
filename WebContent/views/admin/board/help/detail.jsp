<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.admin.vo.AhelpVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AhelpVo vo = (AhelpVo) request.getAttribute("ahelpVo");
ArrayList<AhelpVo> list = (ArrayList<AhelpVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script type="text/javascript">
function modify(){
	location.href="/ahelp/modify?help_sq=<%=vo.getHelp_sq()%>";
}
function Delete(){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="ahelp/delete?help_sq=<%=vo.getHelp_sq()%>";
		} else {
			return;
		}
	}

	function answer() {
		var anForm = $('#anForm');
		var answer = $('#answer');

		if (answer.val() == "") {
			alert('답변을 입력하여 주십시오.');
			answer.focus();
			answer.val('');
			return;
		}

		$('#anForm').submit();
	}

	function cancel() {
		location.href = "/ahelp/list";
	}
</script>
</head>
<body>
<nav>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
</nav>

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

	<form action="/ahelp/answerProc" method="post" id="anForm">

		<div>
			<input type="hidden" name="help_sq" value="<%=vo.getHelp_sq()%>"
				readonly="readonly">
			<textarea id="answer" name="answer" rows="15" cols="20"><%=vo.getAnswer() == null ? "" : vo.getAnswer()%></textarea>
		</div>

	</form>
	<button onclick="answer()"><%=vo.isAnswer_fl() ? "수정" : "답변"%></button>
	<button onclick="cancel()">취소</button>

</body>
</html>