<%@page import="shop.mammastore.common.Pagenation"%>
<%@page import="shop.mammastore.mamma.vo.OrderListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<OrderListVo> list = (ArrayList<OrderListVo>) request.getAttribute("list");
Pagenation pagenation = (Pagenation) request.getAttribute("pagenation");
String pn = request.getParameter("pn");
String startDate = (String) request.getAttribute("startDate");
String endDate = (String) request.getAttribute("endDate");
String sfilter = (String) request.getAttribute("sfilter");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
	function chgSttus(data){
		switch (data){
		case 1:
			return "결재 대기"
			break;
		case 2:
			return "배송 준비"
			break;
		case 3:
			return "배송 중"
			break;
		case 4:
			return "배송 완료"
			break;
		case 5:
			return "구매 확정"
			break;
		case 6:
			return "주문 취소"
			break;
		}
	}

	function chgDisplay(e){
		var div = $(e).next();
		if(div.css("display")=="none"){
			div.css("display","block");
		}
		else{
			div.css("display","none");
		}
	}

	function orderCancel(order_sq){
		location.href="/order/cancel?order_sq="+order_sq;
	}

	function orderConfirm(order_sq){
		location.href="/order/confirm?order_sq="+order_sq;
	}

	function registerReview(itm_sq, order_cd){
		location.href="/review/register?itm_sq="+itm_sq+"&order_cd="+order_cd;
	}
	function search() {		
		var sfilter = $('#sfilter option:selected');
		var startDate = $('#startDate');
		var endDate = $('#endDate');
		location.href = "/mymenu/myOrder?pn=1&startDate=" + startDate.val()
				+ "&endDate=" + endDate.val()+"&sfilter="+sfilter.val();
	}
	function move(pn) {
		var sfilter = $('#sfilter option:selected');
		var startDate = $('#startDate');
		var endDate = $('#endDate');
		location.href = "/mymenu/myOrder?pn=" + pn + "&startDate=" + startDate.val()
				+ "&endDate=" + endDate.val()+"&sfilter="+sfilter.val();
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="myOrder">
		<div class="myOrder">
			<div class="myOrder__header">
				<img src="/views/img/myorder.png" alt="myOrder">
			</div>
			<div class="myOrder__main">
				<div class="myOrder__filter">
					<select name="sfilter" id="sfilter">
						<option value="" <%=sfilter.equals("") ? "selected" : ""%>>All</option>
						<option value="1" <%=sfilter.equals("1") ? "selected" : ""%>>결재 대기</option>
						<option value="2" <%=sfilter.equals("2") ? "selected" : ""%>>배송 준비</option>
						<option value="3" <%=sfilter.equals("3") ? "selected" : ""%>>배송 중</option>
						<option value="4" <%=sfilter.equals("4") ? "selected" : ""%>>배송 완료</option>
						<option value="5" <%=sfilter.equals("5") ? "selected" : ""%>>구매 확정</option>
						<option value="6" <%=sfilter.equals("6") ? "selected" : ""%>>주문 취소</option>
					</select> <input type="date" id="startDate" name="startDate" value="<%=startDate%>" /> <input type="date" id="endDate" name="endDate" value="<%=endDate%>" />
					<button onclick="search()">검색</button>
				</div>
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<div>
					<div class="orderListDiv" onclick="chgDisplay(this)">
						<span><%=list.get(i).getOrder_cd()%></span> 
						<span><img src="<%=list.get(i).getItemList().get(0).getFl_pth()%>" alt="" width="100px" height="100px" /></span> 
						<span><%=list.get(i).getItemList().get(0).getNm()%> <%=list.get(i).getItemList().size() == 1 ? "" : "외 " + (list.get(i).getItemList().size() - 1) + "개"%></span>
						<span><%=list.get(i).getAll_pc()%></span> 
						<span><script>document.write(chgSttus(<%=list.get(i).getSttus()%>));</script></span> 
						<span><%=list.get(i).getDttm()%></span>
					</div>
					<div class="orderListItemDiv" style="display: none">
						<%
							for (int j = 0; j < list.get(i).getItemList().size(); j++) {
						%>
						<div>
							<span><%=list.get(i).getItemList().get(j).getOrderDetail_cd()%></span> 
							<span><img alt="" src="<%=list.get(i).getItemList().get(j).getFl_pth()%>"></span> 
							<span><%=list.get(i).getItemList().get(j).getNm()%></span> 
							<span><%=list.get(i).getItemList().get(j).getPc()%></span> 
							<span><%=list.get(i).getItemList().get(j).getItm_cnt()%></span>
							<span><%=list.get(i).getItemList().get(j).getPc() * list.get(i).getItemList().get(j).getItm_cnt()%></span>
							<%
								if (list.get(i).getSttus() == 5) {
							%>
							<button onclick="registerReview(<%=list.get(i).getItemList().get(j).getItm_sq()%>,<%=list.get(i).getOrder_cd()%>)">리뷰 쓰기</button>
							<%
								}
							%>
						</div>
						<%
							}
						%>
						<div>
							<span>배송지 정보</span><br /> 
							<span>주문자 이름 : <%=list.get(i).getMber_nm()%></span><br /> 
							<span>우편 번호 : <%=list.get(i).getZip_cd()%></span><br /> 
							<span>주소 : <%=list.get(i).getAdres() + list.get(i).getAdres_detail()%></span><br /> 
							<span>전화번호 : <%=list.get(i).getPhone()%></span><br /> 
							<span>배송 메세지 : <%=list.get(i).getMssage()%></span><br />
						</div>
						<%
							if (list.get(i).getSttus() <= 2) {
						%>
						<span>
							<button onclick="orderCancel(<%=list.get(i).getOrder_sq()%>)">취소 요청</button>
						</span>
						<%
							} else if (list.get(i).getSttus() <= 4) {
						%>
						<button onclick="orderConfirm(<%=list.get(i).getOrder_sq()%>)">구매 확정</button>
						<%
							}
						%>
					</div>

				</div>
				<%
					}
				%>
				<div>
					<%
						if (pagenation.getStartPageNumber() != 1) {
					%>
					<a href="/mymenu/myOrder?pn=<%=pagenation.getStartPageNumber() - 1%>">prev</a>
					<%
						}
					%>
					<%
						for (int i = pagenation.getStartPageNumber(); i <= pagenation.getEndPageNumber(); i++) {
						if (i != Integer.parseInt(pn)) {
					%>
					<a href="/mymenu/myOrder?pn=<%=i%>"><%=i%></a>
					<%
						} else {
					%>
					<%=i%>
					<%
						}
					}
					%>
					<%
						if (pagenation.getEndPageNumber() != pagenation.getTotalPageCount()) {
					%>
					<a href="/mymenu/myOrder?pn=<%=pagenation.getStartPageNumber() + 1%>">next</a>
					<%
						}
					%>
				</div>

			</div>
		</div>
	</section>
</body>
</html>