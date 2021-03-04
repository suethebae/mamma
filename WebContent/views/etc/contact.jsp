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

	<section id="contact">
		<div class="contact">
			<div class="contact__header">
				<img alt="contact" src="/views/img/contact.png">
			</div>
			<div class="contact__cntnt">
			<span>e-mail : lechance7@naver.com</span>
			<span>tel : 0507-1327-3547</span>
			<span>blog : https://blog.naver.com/lechance7</span>
			<span>instagram : @____mamma(아랫줄 4개!)</span>
			<span>twitter : @wiggle_Eworm</span>
			
			</div>
			<div class="contact__img">
				<img alt="contact" src="/views/img/contact_img.png">
			</div>
		</div>
	</section>

	<!-- footer -->
	<footer>
		<jsp:include page="/views/footer.jsp"></jsp:include>
	</footer>

</body>
</html>