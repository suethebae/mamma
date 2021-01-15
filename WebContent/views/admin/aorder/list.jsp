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
<title>관리자 주문 내역</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
	function chgSttus(data) {
		switch (data) {
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

	function chgDisplay(e) {
		var targetTr = $(e);
		var hiddenTr = $(e).next();
		if (hiddenTr.css("display") == "none") {
			targetTr.children().first().attr('rowspan','2');
			hiddenTr.css("display", "block");
		} else {
			targetTr.children().first().attr('rowspan','1');
			hiddenTr.css("display", "none");
		}
	}

	function orderCancel(order_sq) {
		location.href = "/order/cancel?order_sq=" + order_sq;
	}

	function orderConfirm(order_sq) {
		location.href = "/order/confirm?order_sq=" + order_sq;
	}

	function registerReview(itm_sq) {
		location.href = "/review/register?itm_sq=" + itm_sq;
	}
	function chgSttusStart() {
		var sttus = $('#sttus');
		var order_sq = $('[name="order_sq"]');
		if(!order_sq[0]){
			alert('선택된 주문이 없습니다.');
			return;
		}
		sttus.val(3);
		$('#sttusForm').submit();
	}
	function chgSttusEnd() {
		var sttus = $('#sttus');
		var order_sq = $('[name="order_sq"]');
		if(!order_sq[0]){
			alert('선택된 주문이 없습니다.');
			return;
		}
		sttus.val(4);
		$('#sttusForm').submit();
	}
	function chgSttusConfirm() {
		var sttus = $('#sttus');
		var order_sq = $('[name="order_sq"]');
		if(!order_sq[0]){
			alert('선택된 주문이 없습니다.');
			return;
		}
		sttus.val(5);
		$('#sttusForm').submit();
	}
	function chgSttusCancel() {
		var sttus = $('#sttus');
		var order_sq = $('[name="order_sq"]');
		if(!order_sq[0]){
			alert('선택된 주문이 없습니다.');
			return;
		}
		sttus.val(6);
		$('#sttusForm').submit();
	}
</script>
</head>
<body>
	주문내역
	<form action="/aorder/changeSttus" method="post" id="sttusForm">
		<table border="1">
			<tr>
				<th>체크 박스</th>
				<th>주문 코드</th>
				<th>아이디</th>
				<th>이미지</th>
				<th>상품</th>
				<th>총 가격</th>
				<th>주문 상태</th>
				<th>주문 날짜</th>
			</tr>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>
			<tr onclick="chgDisplay(this)">
				<td rowspan="1" onclick="event.cancelBubble=true"><input
					type="checkbox" value="<%=list.get(i).getOrder_sq()%>" name="order_sq"  /></td>
				<td><%=list.get(i).getOrder_code()%></td>
				<td><%=list.get(i).getId()%></td>
				<td><img
					src="<%=list.get(i).getItemList().get(0).getFl_pth()%>" alt=""
					width="50px" height="50px" /></td>
				<td><%=list.get(i).getItemList().get(0).getNm()%> <%=list.get(i).getItemList().size() == 1 ? "" : "외 " + (list.get(i).getItemList().size() - 1) + "개"%></td>
				<td><%=list.get(i).getAll_pc()%></td>
				<td><script>
					document.write(chgSttus(
				<%=list.get(i).getSttus()%>
					));
				</script></td>
				<td><%=list.get(i).getDttm()%></td>
			</tr>
			<tr style="display: none">
				<td colspan="7">
					<table>
						<tr>
							<th>상품 주문 번호</th>
							<th>상품 이미지</th>
							<th>상품 이름</th>
							<th>상품 가격</th>
							<th>상품 갯수</th>
							<th>상품 총 가격</th>
						</tr>
						<%
						for (int j = 0; j < list.get(i).getItemList().size(); j++) {
						%>
						<tr>
							<td><%=list.get(i).getItemList().get(j).getOrderDetail_cd()%></td>
							<td><img alt=""
								src="<%=list.get(i).getItemList().get(j).getFl_pth()%>"
								width="50px" height="50px"></td>
							<td><%=list.get(i).getItemList().get(j).getNm()%></td>
							<td><%=list.get(i).getItemList().get(j).getPc()%></td>
							<td><%=list.get(i).getItemList().get(j).getItm_cnt()%></td>
							<td><%=list.get(i).getItemList().get(j).getPc() * list.get(i).getItemList().get(j).getItm_cnt()%></td>
						</tr>
						<%
						}
						%>
						<tr>
							<td colspan="6">
								<table>
									<tr>
										<th>주문자 이름</th>
										<th>우편 번호</th>
										<th>주소</th>
										<th>전화번호</th>
										<th>배송 메세지</th>
									</tr>
									<tr>
										<td><%=list.get(i).getMber_nm()%></td>
										<td><%=list.get(i).getZip_cd()%></td>
										<td><%=list.get(i).getAdres() + list.get(i).getAdres_detail()%></td>
										<td><%=list.get(i).getPhone()%></td>
										<td><%=list.get(i).getMssage()%></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<%
			}
			%>
		</table>
		<input type="hidden" id="sttus" name="sttus" value="0"/><br />
	</form>
	<button onclick="chgSttusStart()">배송 시작</button>
	<button onclick="chgSttusEnd()">배송 완료</button>
	<button onclick="chgSttusConfirm()">구매 확정</button>
	<button onclick="chgSttusCancel()">주문 취소</button>
	<a href='/'>홈으로</a>
</body>
</html>