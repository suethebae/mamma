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
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="/views/js/evaluation.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
function modify(){
	location.href="/review/modify?review_sq=<%=vo.getReview_sq()%>";
}
function Delete(){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="review/delete?review_sq=<%=vo.getReview_sq()%>";
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

	<section id="myReview__detail">
		<div class="myReview__detail">
			<div class="myReview__detail__main">
				<table>
					<tr>
						<td>리뷰 번호</td>
						<td><%=vo.getReview_sq()%></td>
					</tr>
					<tr class="line">
						<td>작성 일자</td>
						<td><%=vo.getDttm()%></td>
					</tr>
					<tr class="line">
						<td>주문 코드</td>
						<td><%=vo.getOrder_cd()%></td>
					</tr>
					<tr class="line">
						<td>제목</td>
						<td><%=vo.getSj()%></td>
					</tr>
					<tr class="line">
						<td>내용</td>
						<td><%=vo.getCntnt()%></td>
					</tr>
					<tr class="line">
						<td>평점</td>
						<td><span class="star-input"> <span class="input">
									<input type="radio" name="star-input" id="p1" value="1"
									<%=vo.getEvl() == 1 ? "checked" : ""%> onclick="return false"><label
									for="p1" onclick="return false">1</label> <input type="radio"
									name="star-input" id="p2" value="2"
									<%=vo.getEvl() == 2 ? "checked" : ""%> onclick="return false"><label
									for="p2" onclick="return false">2</label> <input type="radio"
									name="star-input" id="p3" value="3"
									<%=vo.getEvl() == 3 ? "checked" : ""%> onclick="return false"><label
									for="p3" onclick="return false">3</label> <input type="radio"
									name="star-input" id="p4" value="4"
									<%=vo.getEvl() == 4 ? "checked" : ""%> onclick="return false"><label
									for="p4" onclick="return false">4</label> <input type="radio"
									name="star-input" id="p5" value="5"
									<%=vo.getEvl() == 5 ? "checked" : ""%> onclick="return false"><label
									for="p5" onclick="return false">5</label> <input type="radio"
									name="star-input" id="p6" value="6"
									<%=vo.getEvl() == 6 ? "checked" : ""%> onclick="return false"><label
									for="p6" onclick="return false">6</label> <input type="radio"
									name="star-input" id="p7" value="7"
									<%=vo.getEvl() == 7 ? "checked" : ""%> onclick="return false"><label
									for="p7" onclick="return false">7</label> <input type="radio"
									name="star-input" id="p8" value="8"
									<%=vo.getEvl() == 8 ? "checked" : ""%> onclick="return false"><label
									for="p8" onclick="return false">8</label> <input type="radio"
									name="star-input" id="p9" value="9"
									<%=vo.getEvl() == 9 ? "checked" : ""%> onclick="return false"><label
									for="p9" onclick="return false">9</label> <input type="radio"
									name="star-input" id="p10" value="10"
									<%=vo.getEvl() == 10 ? "checked" : ""%> onclick="return false"><label
									for="p10" onclick="return false">10</label>
							</span>
						</span></td>
					</tr>
				</table>

				<div class="myReview__detail__bottom">
					<div class="myReview__detail__btns">
						<button class="myReview__detail__btn"
							onclick="location.href='/mymenu/myReview'">목록</button>
						<button class="myReview__detail__btn" onclick="modify()">수정</button>
						<button class="myReview__detail__btn" onclick="Delete()">삭제</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>