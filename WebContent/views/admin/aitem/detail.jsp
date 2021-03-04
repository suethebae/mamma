<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
AitemVo vo = (AitemVo) request.getAttribute("aitemVo");
ActgryVo avo = (ActgryVo) request.getAttribute("actgryVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function modify(){
	location.href="/aitem/modify?sq=<%=vo.getItm_sq()%>";
}
function aDelete(){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="aitem/delete?sq=<%=vo.getItm_sq()%>";
		} else {
			return;
		}
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	<!-- <form action="/aitem/amodifyProc" method="post" id="editorForm" enctype="multipare/form-data">	 -->
	<table border=1>
		<tr>
			<td>상품번호</td>
			<td><%=vo.getItm_sq()%></td>
		</tr>
		<tr>
			<td>판매상태</td>
			<td><%=vo.isSttus_fl()%></td>
		</tr>
		<tr>
			<td>카테고리</td>
			<td><%=vo.getCtgry_sq()%></td>
		</tr>
		<tr>
			<td>상품명</td>
			<td><%=vo.getNm()%></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><%=vo.getPc()%></td>
		</tr>
		<tr>
			<td>등록일자</td>
			<td><%=vo.getDttm()%></td>
		</tr>
		<tr>
			<td>재고</td>
			<td><%=vo.getStock()%></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src="<%=vo.getFl_pth()%>" width="100px" height="100px" alt="" /></td>
		</tr>
		<tr>
			<td>상품상세설명</td>
			<td><%=vo.getCntnt()%></td>
		</tr>
	</table>
	<button onclick="location.href='/aitem/saleOn?sq=<%=vo.getItm_sq()%>'">상품
		판매 시작</button>
	<button onclick="location.href='/aitem/saleOff?sq=<%=vo.getItm_sq()%>'">상품
		판매 중지</button>
	<!-- </form> -->
	<%
	if (!vo.isSttus_fl()) {
	%>
	<button onclick="modify()">수정</button>
	<button onclick="aDelete()">삭제</button>
	<%
	}
	%>

</body>
</html>