<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<ActgryVo> actgryVo = (ArrayList<ActgryVo>) request.getAttribute("actgryVo");
ArrayList<AitemVo> aitemVo = (ArrayList<AitemVo>) request.getAttribute("aitemVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<title>Insert title here</title>
<script>
	function showItemList(sq){
		var itm_sq = $('#mngr_sq');
		var ctgry_sq = $('#ctgry_sq');
		var pc = $('#pc');
		var nm = $('#nm');
		var thumb_pth = $('#thumb_pth');

		$.ajax({
			url : "/ajax/itemList",
			type : "post",
			dataType : "json",
			data : {
				sq : sq
			},
			error : function() {
				alert("통신 실패");
			},
			success : function(data) {
				itm_sq.val(data.itm_sq);
				ctgry_sq.val(data.ctgry_sq);
				pc.val(data.pc);
				nm.val(data.nm);
				thumb_pth.val(data.thumb_pth);
			}
		});
	}

	function showItemAll() {
		location.href="/item/ctgry";
	}
</script>
</head>
<body>
	<!-- 메인 네비바 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<hr>
	<!-- 카테고리 네비바 -->
	<ul>
		<li onclick="showItemAll()">ALL</li>
		<%for (int i = 0; i < actgryVo.size(); i++) {%>
		<li onclick="showItemList(<%=actgryVo.get(i).getCtgry_sq()%>)"><%=actgryVo.get(i).getNm()%></li>
		<%}%>
	</ul>
	<hr>
	<!-- 모든 아이템 다 나오게 -->
	<%for (int i = 0; i < aitemVo.size(); i++) {%>
	<div>
		<div>이미지</div>
		<div>이름</div>
		<div>가격</div>
	</div>
	<%}%>
</body>
</html>