<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.common.Parser"%>
<%@page import="shop.mammastore.mamma.vo.ReviewVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ReviewVo vo = (ReviewVo) request.getAttribute("reviewVo");
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
	var content = '<%=Parser.chgToHTML(vo.getCntnt())%>';
	
	function submit() {
		var sj = $('#sj');

		if (!sj.val()) {
			alert('제목 입력하여 주십시오.');
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

</head>
<body>
	리뷰 수정 게시판입니다.
	<form action="/review/modifyProc" method="post" id="editorForm">
		<div>
			<input type="text" name="review_sq" id="review_sq"
				value="<%=vo.getReview_sq()%>" style="display: none">
		</div>
		<div>
			주문번호 <input type="text" name="order_cd" id="order_cd"
				value="<%=vo.getOrder_cd()%>" readonly="readonly">
		</div>
		<div>
			작성일자 <input type="text" name="dttm" id="dttm"
				value="<%=vo.getDttm()%>" readonly="readonly">
		</div>
		<div>
			제목 <input type="text" name="sj" id="sj" value="<%=vo.getSj()%>">
		</div>
		마음이 바뀌었나요? :
		<span class="star-input">
  			<span class="input">
			    <input type="radio" name="star-input" id="p1" value="1"<%=vo.getEvl()==1?"checked":"" %>><label for="p1">1</label>
    			<input type="radio" name="star-input" id="p2" value="2"<%=vo.getEvl()==2?"checked":"" %>><label for="p2">2</label>
    			<input type="radio" name="star-input" id="p3" value="3"<%=vo.getEvl()==3?"checked":"" %>><label for="p3">3</label>
			    <input type="radio" name="star-input" id="p4" value="4"<%=vo.getEvl()==4?"checked":"" %>><label for="p4">4</label>
 			    <input type="radio" name="star-input" id="p5" value="5"<%=vo.getEvl()==5?"checked":"" %>><label for="p5">5</label>
			    <input type="radio" name="star-input" id="p6" value="6"<%=vo.getEvl()==6?"checked":"" %>><label for="p6">6</label>
 			    <input type="radio" name="star-input" id="p7" value="7"<%=vo.getEvl()==7?"checked":"" %>><label for="p7">7</label>
 			    <input type="radio" name="star-input" id="p8" value="8"<%=vo.getEvl()==8?"checked":"" %>><label for="p8">8</label>
  			    <input type="radio" name="star-input" id="p9" value="9"<%=vo.getEvl()==9?"checked":"" %>><label for="p9">9</label>
 			    <input type="radio" name="star-input" id="p10" value="10"<%=vo.getEvl()==10?"checked":"" %>><label for="p10">10</label>
 			</span>
  			<output for="star-input">개</output>
		</span>
		<div>
			<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
		</div>
	</form>
	<button onclick="submit()">저장</button>
	<a href="/mymenu/myReview">나의리뷰로</a>
</body>
</html>