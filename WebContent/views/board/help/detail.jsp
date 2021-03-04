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
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
function modify(){
	location.href="/help/modify?help_sq=<%=vo.getHelp_sq()%>";
}
function Delete(){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="/help/delete?help_sq=<%=vo.getHelp_sq()%>";
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="myHelp__detail">
		<div class="myHelp__detail">
			<div class="myHelp__detail__main">
				<table>
					<tr>
						<th>제목</th>
						<td colspan=3><%=vo.getSj()%></td>
					</tr>
					<tr class="line">
						<th>일시</th>
						<td style="width: 35%;"><%=vo.getDttm()%></td>
						<th style="width: 15%;">주문 번호</th>
						<td><%=vo.getOrder_cd() == null ? "-" : vo.getOrder_cd()%></td>
					</tr>

					<tr class="line">
						<th>내용</th>
						<td colspan=3><%=vo.getCntnt()%></td>
					</tr>
					<tr class="line">
						<th>답변</th>
						<td colspan=3>
							<%
							if (vo.isAnswer_fl() == true) {
							%>
							<div>
								<input type="text" id="answer" name="answer"
									value="<%=vo.getAnswer()%>" readonly="readonly">
							</div> <%
 } else {
 %><div class="loading__wrap">
								<div class="loading">
									<i class="fas fa-spinner"></i>
								</div>
								<span>답변 대기중</span>
							</div> <%
 }
 %>
						</td>
					</tr>
				</table>
				<div class="myHelp__detail__bottom">
					<button class="myHelp__detail__btn" onclick="location.href='/mymenu/myHelp'">목록</button>
					<button class="myHelp__detail__btn" onclick="modify()">수정</button>
					<button class="myHelp__detail__btn" onclick="Delete()">삭제</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>