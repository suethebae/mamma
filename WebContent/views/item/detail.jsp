<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< Updated upstream
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
상품 디테일 페이지 입니다.(이건 페이지 여러개어떻게???)<br>
리뷰게시판도 들어가야함<br>
<a href='/'>홈으로</a>
=======
<title>상품 상세 정보</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
	<script>
	function registerCart(itm_sq){
		var itm_cnt = $('#itm_cnt');
		location.href="/cart/add?itm_sq="+itm_sq+"&itm_cnt="+itm_cnt.val();
	}
	</script>
</head>
<body>
<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div>
		<div>
			<img src="<%=aitemVo.getFl_pth()%>" alt="" width="400px"
				height="400px" />
		</div>
		<div><%=aitemVo.getNm()%></div>
		<div><%=aitemVo.getPc()%></div>
		<div><%=aitemVo.getDttm()%></div>
		<div><%=aitemVo.getCntnt()%></div>
	</div>
	<div>
	개수 <input type="number" id="itm_cnt" />
	<button onclick="registerCart(<%=aitemVo.getItm_sq()%>)">장바구니</button>
	<button onclick="">구매</button>
	</div>
>>>>>>> Stashed changes
</body>
</html>