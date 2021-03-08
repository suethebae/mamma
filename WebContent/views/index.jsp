<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.common.LoginManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
LoginManager lm = LoginManager.getInstance();
String member_sq = lm.getMemberId(session);

ArrayList<AitemVo> newList = (ArrayList<AitemVo>) request.getAttribute("newList");
ArrayList<AitemVo> hitList = (ArrayList<AitemVo>) request.getAttribute("hitList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Link -->
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<link rel="stylesheet" type="text/css" href="/views/css/bxslider.css">

<!-- Script -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
	$(document).ready(function() {
		$('.slider').bxSlider({
			auto : true,
			autoHover : true,
			controls : false
		});
	});
</script>
<script>
	function openNav() {
		document.getElementById("myNav").style.width = "100%";
	}

	function closeNav() {
		document.getElementById("myNav").style.width = "0%";
	}
</script>
<title>mamma store</title>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<!-- Img Slide -->
	<section>
		<div class="slider">
			<div>
				<img src="views/img/slide1.jpg" />
			</div>
			<div>
				<img src="views/img/slide2.jpg" />
			</div>
			<div>
				<img src="views/img/slide3.jpg" />
			</div>
			<div>
				<img src="views/img/slide4.jpg" />
			</div>
		</div>
	</section>

	<!-- items -->
	<section id="items">
		<h1>- NEW! -</h1>
		<div class="items">
			<%
			for (int i = 0; i < newList.size(); i++) {
			%>
			<div class="responsive">
				<div class="gallery">
					<a target="_blank"
						href="/item/detail?itm_sq=<%=newList.get(i).getItm_sq()%>"> <img
						src="<%=newList.get(i).getFl_pth()%>" alt="item" height="400">
					</a>
					<div class="desc">
						<span><%=newList.get(i).getNm()%></span><br> <span><%=newList.get(i).getPc()%></span>
					</div>
				</div>
			</div>
			<%
			}
			%>

			<div class="clearfix"></div>
		</div>
	</section>

	<section id="items">
		<h1>- HIT! -</h1>
		<div class="items">
			<%
			for (int i = 0; i < hitList.size(); i++) {
			%>
			<div class="responsive">
				<div class="gallery">
					<a target="_blank"
						href="/item/detail?itm_sq=<%=hitList.get(i).getItm_sq()%>"> <img
						src="<%=hitList.get(i).getFl_pth()%>" alt="item" height="400">
					</a>
					<div class="desc">
						<span><%=hitList.get(i).getNm()%></span><br> <span><%=hitList.get(i).getPc()%></span>
					</div>
				</div>
			</div>
			<%
			}
			%>
			<div class="clearfix"></div>
		</div>
	</section>
	<!-- footer -->
	<footer>
		<jsp:include page="/views/footer.jsp"></jsp:include>
	</footer>

	<!-- slide script -->
	<script
		src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
</body>
</html>