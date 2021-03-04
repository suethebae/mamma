<%@page import="shop.mammastore.mamma.vo.CartVo"%>
<%@page import="shop.mammastore.mamma.vo.CartListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
CartListVo cartListVo = (CartListVo) request.getAttribute("cartListVo");
ArrayList<CartListVo> list = (ArrayList<CartListVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 보기</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function modifyCart(cart_sq) {
		location.href = "/cart/modify?cart_sq="+cart_sq;
	}
		function deleteCart(cart_sq){
		 location.href="/cart/delete?cart_sq="+cart_sq; 
	}

	function deleteAllCart(){
		location.href="/cart/deleteAll";
	}
	function order(){
		$('#PForm').submit();
	}
	function changeCheck(){
		var CCbox = $('#CCheckBox');
		if(CCbox.is(":checked")){
			$('input:checkbox[name=cart_sq]').prop('checked',true);
		}
		else{
			$('input:checkbox[name=cart_sq]').prop('checked',false);
		}
	}
</script>
<script>
$(document).ready(function(){
	var cart_sq = $('input:checkbox[name=cart_sq]').length;
	if(cart_sq==0){
		$('#CCheckBox').css("visibility","hidden");	
	}
});
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>

	<section id="cart">
		<div class="cart__header">
			<img src="/views/img/cart.png" alt="cart">
		</div>
		<div class="cart__main">
			<form action="/order/parchsForm" method="post" id="PForm">
				<table>
					<tr>
						<th>
						<input type="checkbox" onchange="changeCheck()"
							id="CCheckBox" checked="checked">
						</th>
						<th class="left">Item</th>
						<th class="right">수량</th>
						<th class="right">가격</th>
						<th></th>

					</tr>
					<%
					int total = 0;
					for (int i = 0; i < list.size(); i++) {
					%>
					<tr class="line">
						<td class="center"><input type="checkbox" name="cart_sq"
							value="<%=list.get(i).getCart_sq()%>" checked="checked" /></td>
						<td class="cart__item"><img
							src="<%=list.get(i).getFl_pth()%>" alt="" width="70px"
							height="70px" />
							<div class="cart__item__wrap">
								<span class="cart__item__info"><%=list.get(i).getItm_nm()%></span>
								<span class="cart__item__info"><%=String.format("%,d", list.get(i).getItm_pc())%><span
									class="money"></span></span>
							</div></td>
						<td class="right"><%=list.get(i).getItm_cnt()%><input
							type="hidden" name="itm_cnt"
							value="<%=list.get(i).getItm_cnt()%>" /><span class="cntnt"></span></td>
						<td class="right"><%=String.format("%,d", list.get(i).getItm_pc() * list.get(i).getItm_cnt())%><span
							class="money"></span></td>
						<td class="center"><button class="cart__btn" type="button"
								onclick="modifyCart(<%=list.get(i).getCart_sq()%>)">수정</button>
							<button class="cart__btn" type="button"
								onclick="deleteCart(<%=list.get(i).getCart_sq()%>)">삭제</button>
						</td>
						<%
						total += list.get(i).getItm_pc() * list.get(i).getItm_cnt();
						}
						%>
					</tr>
						<%if (!(list.isEmpty())) { %>
					<tr class="line">
						<td></td>
						<td></td>
						<td class="right"><span>상품가격</span><br>
						<span>배송비</span></td>
						<td class="right"><span><%=String.format("%,d", total)%><span class="money"></span></span><br>
						<span>4,000<span class="money"></span></span></td>
						<td></td>
					</tr>
			
					<tr class="line">
						<td class="center"><button class="cart__btn" type="button"
								onclick="deleteAllCart()">전체삭제</button></td>
						<td></td>
						<td class="right"><span>결제금액</span></td>
						<td class="right"><span><%=String.format("%,d", total+ 4000) %><span
								class="money"></span></span></td>
						<td></td>
					</tr>
					<%}else{%>
					<tr class="line">
						<td colspan="5">
						<div class="cart__empty">
						<i class="fas fa-shopping-cart"></i>
						<span>장바구니가 비었습니다.</span>
						</div>
						</td>
<!-- 						<td style="display:none;"></td>
						<td style="display:none;"></td>
						<td style="display:none;"></td>
						<td style="display:none;"></td> -->
					</tr>
					<%}	%>
				</table>
			</form>
		</div>
		<div class="cart__bottom">
			<button class="cart__btn-shopping" onclick="location.href='/item/list'">계속 쇼핑하기</button>
			<%
			if (!(list.isEmpty())) {
			%>
			<button class="cart__btn-order" onclick="order()">주문하기</button>
			<%
			}
			%>
		</div>
	</section>
</body>
</html>