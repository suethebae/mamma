<%@page import="shop.mammastore.mamma.vo.OrderListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<OrderListVo> list = (ArrayList<OrderListVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
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

	function registerReview(itm_sq){
		location.href="/review/register?itm_sq="+itm_sq;
	}	
</script>
</head>
<body>
	내주문내역
		<%
			for (int i = 0; i < list.size(); i++) {
		%>
		<div>
		<div class="orderListDiv" onclick="chgDisplay(this)">
			<span><%=list.get(i).getOrder_code()%></span>
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
			<span><%=list.get(i).getItemList().get(j).getPc()*list.get(i).getItemList().get(j).getItm_cnt()%></span>
			<%if(list.get(i).getSttus()==5){%>
			<button onclick="registerReview(<%=list.get(i).getItemList().get(j).getItm_sq()%>)">리뷰 쓰기</button>
			<%} %>
		</div>
		<%
			}
		%>
			<div>
				<span>배송지 정보</span><br />
				<span>주문자 이름 : <%=list.get(i).getMber_nm() %></span><br />
				<span>우편 번호 : <%=list.get(i).getZip_cd() %></span><br />
				<span>주소 : <%=list.get(i).getAdres()+list.get(i).getAdres_detail()%></span><br />
				<span>전화번호 : <%=list.get(i).getPhone() %></span><br />
				<span>배송 메세지 : <%=list.get(i).getMssage()%></span><br />
			</div>
			<%if(list.get(i).getSttus()<=2){ %>
			<span>
			<button onclick="orderCancel(<%=list.get(i).getOrder_sq()%>)">취소 요청</button>
			</span>
			<%} else if(list.get(i).getSttus()<=4){%>
			<button onclick="orderConfirm(<%=list.get(i).getOrder_sq()%>)">구매 확정</button>
			<%}%>
		</div>
		
		</div>
		<%
			}
		%>
	<a href='/'>홈으로</a>
</body>
</html>