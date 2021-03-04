<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Link -->
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<!-- Script -->
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
	function openNav() {
		document.getElementById("myNav").style.width = "100%";
	}

	function closeNav() {
		document.getElementById("myNav").style.width = "0%";
	}
</script>
<title>+ mamma store +</title>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="about">
		<div class="about">
			<div class="about__img">
				<img alt="about_img" src="/views/img/about_img.png">
			</div>
			<div class="about__logo">
				<img alt="about_logo" src="/views/img/logo.png">
			</div>
			<div class="about__cntnt">
				<img alt="about_cntnt" src="/views/img/about_cntnt.png">
			</div>
		</div>
	</section>
	<!-- footer -->
	<footer>
		<jsp:include page="/views/footer.jsp"></jsp:include>
	</footer>

</body>
</html>