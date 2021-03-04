<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="shop.mammastore.common.Parser"%>
<%@page import="shop.mammastore.mamma.vo.ReviewVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!-- 빵야! 제 총을 맞은 이 폼은 코드자동정렬을 하면 폼이 엉망이 될겁니다. -->
<%
	AitemVo vo = (AitemVo) request.getAttribute("aitemVo");
String order_cd = (String) request.getAttribute("order_cd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/views/css/evaluation.css">
<script src="/views/js/evaluation.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function submit() {
		var sj = $('#sj');

		if (!sj.val()) {
			alert('제목을 형식에 맞게 입력 해 주세요.')
			sj.focus();
			sj.val('');
			return;
		}
		saveContent();
	}
	function cancel() {
		location.href = "/mymenu/myReview";
	}
</script>
<style type="text/css"> 

</style>
</head>
<body>
	리뷰 글작성 페이지 입니다.
	<form action="/review/registerProc" method="post" id="editorForm">
		제목 <input type="text" name="sj" id="sj" maxlength="100"> 
		상품 <input type="text" name="itm_sq" id="itm_sq" value="<%=vo.getItm_sq()%>" readonly="readonly" /> 
		주문코드 <input type="text" name="order_cd" id="order_cd" value="<%=order_cd%>" readonly="readonly" /> 
		<br>
		<img src="<%=vo.getFl_pth()%>" alt="" width="400px" height="400px" /> <!-- 상품이미지.. 리뷰 작성할때 이미지도 가져오길래 넣었어요 -->
		<br> 
		당신의 평점은 :
		<span class="star-input">
  			<span class="input">
			    <input type="radio" name="star-input" id="p1" value="1"><label for="p1">1</label>
    			<input type="radio" name="star-input" id="p2" value="2"><label for="p2">2</label>
    			<input type="radio" name="star-input" id="p3" value="3"><label for="p3">3</label>
			    <input type="radio" name="star-input" id="p4" value="4"><label for="p4">4</label>
 			    <input type="radio" name="star-input" id="p5" value="5"><label for="p5">5</label>
			    <input type="radio" name="star-input" id="p6" value="6"><label for="p6">6</label>
 			    <input type="radio" name="star-input" id="p7" value="7"><label for="p7">7</label>
 			    <input type="radio" name="star-input" id="p8" value="8"><label for="p8">8</label>
  			    <input type="radio" name="star-input" id="p9" value="9"><label for="p9">9</label>
 			    <input type="radio" name="star-input" id="p10" value="10"><label for="p10">10</label>
 			</span>
  			<output for="star-input">개</output>
		</span>
		 
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="submit()">등록</button>
	<button onclick="cancle()">취소</button>
	<a href="/mymenu/myReview">나의리뷰로</a>
</body>
</html>