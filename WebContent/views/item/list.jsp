<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="shop.mammastore.admin.vo.ActgryVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<ActgryVo> actgryVo = (ArrayList<ActgryVo>) request.getAttribute("ctgryList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/views/css/itemList.css">
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<title>상품리스트 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>

	function showItemDetail(itm_sq){
		location.href="/item/detail?itm_sq="+itm_sq;
	}
	
	function showItemList(sq){
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
			success : function(data){
				var itemList = $('#itemList');
				var html="";
				for(var i=0; i<data.itemList.length;i++){
					html += '<div class="responsive" onclick="showItemDetail('+data.itemList[i].itm_sq+')">';
					html += '<div class="gallery">';
					html += '<a target="_blank" href='+data.itemList[i].fl_pth+'>';
					html += '<img height="400px" src='+data.itemList[i].fl_pth+'></a>';
					html += '<div class="desc"><span>'+data.itemList[i].nm+'</span><br>';
					html += '<span>'+data.itemList[i].pc+'</span>';
					html += '</div>';
					html += '</div>';
					html += '</div>';
				}
				itemList.empty();
				itemList.append(html);
				
			}
		});
	}
</script>
</head>
<body>

	<!-- 메인 네비바 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<hr>
	<!-- 카테고리 네비바 -->
	<ul class="ctgry__bar">
		<li onclick="showItemList(0)">ALL</li>
		<%
		for (int i = 0; i < actgryVo.size(); i++) {
		%>
		<li onclick="showItemList(<%=actgryVo.get(i).getCtgry_sq()%>)"><%=actgryVo.get(i).getNm()%></li>
		<%
		}
		%>
	</ul>
	<hr>
	<main id="itemList">

	</main>
</body>
</html>