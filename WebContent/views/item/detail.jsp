<%@page import="shop.mammastore.mamma.vo.ReviewNameVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.mamma.vo.ReviewVo"%>
<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ReviewVo vo = (ReviewVo) request.getAttribute("reviewVo");
AitemVo aitemVo = (AitemVo) request.getAttribute("aitemVo");
ArrayList<ReviewNameVo> list = (ArrayList<ReviewNameVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<link rel="stylesheet" type="text/css" href="/views/css/evaluation.css">
<script src="/views/js/evaluation.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function registerCart(itm_sq){
		var maintainBuy = confirm('계속 쇼핑 하시겠습니까?');
		var itm_cnt = $('#itm_cnt');
		location.href="/cart/add?itm_sq="+itm_sq+"&itm_cnt="+itm_cnt.val()+"&maintainBuy="+maintainBuy;
	}
	function parchsOne(itm_sq){
		var itm_cnt = $('#itm_cnt');
		location.href="/order/parchsOneForm?itm_sq="+itm_sq+"&itm_cnt="+itm_cnt.val();
	}
	</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="item__detail">
		<div class="item__detail">
			<div class="item__detail__main">
				<div class=item__detail__top>
					<div class="item__detail__top-left">
						<img src="<%=aitemVo.getFl_pth()%>" alt="" width="400px"
							height="400px" />
					</div>
					<div class="item__detail__top-right">
						<div class="item__detail__title">
							<%=aitemVo.getNm()%>
						</div>
						<div class="item__detail__amount">
							<%=aitemVo.getPc()%><span class="money"></span>
						</div>
							<hr>
						<div class="item__detail__btns">
							수량<input type="number" id="itm_cnt" min="1" value="1"/><span class="cntnt"></span>
							<button class="item__detail__btn"
								onclick="registerCart(<%=aitemVo.getItm_sq()%>)">장바구니</button>
							<button class="item__detail__btn"
								onclick="parchsOne(<%=aitemVo.getItm_sq()%>)">구매</button>
						</div>
					</div>
				</div>

				<!-- 글 내용 -->
				<hr>
				<div class="item__detail__cntnt">
					<h2>상세정보</h2>
					<%=aitemVo.getCntnt()%>

				</div>

				<hr>
				<div class="item__detail__review">
					<h2>Review</h2>
					<%
					for (int i = 0; i < list.size(); i++) {
					%>
					<hr>
					<!-- 평점 / 제목 -->
					<!-- 작성자 -->
					<!-- 작성일시 -->
					<!-- 로 배치되면 됩니다옹 -->
					<!-- 디폴트는 "제목 : onclick상태", "클릭 시 내용 출력" 형태입니다. -->
					<div>
						<!-- onclick : 여기누르면 내용출력. 수정끝나면 지워주세요 -RedSky -->
						<%=list.get(i).getSj()%>
						<span class="star-input"> <span class="input"> <input
								type="radio" name="star-input" id="p1" value="1"
								<%=list.get(i).getEvl() == 1 ? "checked" : ""%>
								onclick="return false"><label for="p1"
								onclick="return false">1</label> <input type="radio"
								name="star-input" id="p2" value="2"
								<%=list.get(i).getEvl() == 2 ? "checked" : ""%>
								onclick="return false"><label for="p2"
								onclick="return false">2</label> <input type="radio"
								name="star-input" id="p3" value="3"
								<%=list.get(i).getEvl() == 3 ? "checked" : ""%>
								onclick="return false"><label for="p3"
								onclick="return false">3</label> <input type="radio"
								name="star-input" id="p4" value="4"
								<%=list.get(i).getEvl() == 4 ? "checked" : ""%>
								onclick="return false"><label for="p4"
								onclick="return false">4</label> <input type="radio"
								name="star-input" id="p5" value="5"
								<%=list.get(i).getEvl() == 5 ? "checked" : ""%>
								onclick="return false"><label for="p5"
								onclick="return false">5</label> <input type="radio"
								name="star-input" id="p6" value="6"
								<%=list.get(i).getEvl() == 6 ? "checked" : ""%>
								onclick="return false"><label for="p6"
								onclick="return false">6</label> <input type="radio"
								name="star-input" id="p7" value="7"
								<%=list.get(i).getEvl() == 7 ? "checked" : ""%>
								onclick="return false"><label for="p7"
								onclick="return false">7</label> <input type="radio"
								name="star-input" id="p8" value="8"
								<%=list.get(i).getEvl() == 8 ? "checked" : ""%>
								onclick="return false"><label for="p8"
								onclick="return false">8</label> <input type="radio"
								name="star-input" id="p9" value="9"
								<%=list.get(i).getEvl() == 9 ? "checked" : ""%>
								onclick="return false"><label for="p9"
								onclick="return false">9</label> <input type="radio"
								name="star-input" id="p10" value="10"
								<%=list.get(i).getEvl() == 10 ? "checked" : ""%>
								onclick="return false"><label for="p10"
								onclick="return false">10</label>
						</span>
						</span>
					</div>
					<div>
						<div><%=list.get(i).getId()%></div>
						<div><%=list.get(i).getDttm()%></div>
					</div>
					<div>
						<%=list.get(i).getCntnt()%>
					</div>

					<%
					}
					%>
				</div>
				<hr>

			</div>
		</div>
	</section>
</body>
</html>