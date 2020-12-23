<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    	
    	AitemVo vo = (AitemVo) request.getAttribute("aitemVo");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modify() {
		location.href = "/aitem/amodify";
	}
</script>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		function register() {
			var nm = $('#nm');
			var pc = $('#pc');
			var stock = $('#stock');
			var cntnt = $('#cntnt');
			if (!nm.val()) {
				alert('상품 이름을 정상적으로 입력하여 주십시오.');
				nm.focus();
				nm.val('');
				return;
			}
			if (!pc.val()) {
				alert('가격을 입력하여 주십시오.');
				pc.focus();
				pc.val('');
				return;
			}
			if (!stock.val()) {
				alert('재고를 입력하여 주십시오.');
				stock.focus();
				stock.val('');
				return;
			}
			if (!cntnt.val()) {
				alert('내용을 입력하여 주십시오.');
				stock.focus();
				stock.val('');
				return;
			}
			$('#editorForm').submit();
		}

		function cancel() {
			location.href = "/aitem/list";
		}
	</script>
</head>
<body>

	<form action="/aitem/aModifyProc" method="post" id="editorForm"
		enctype="multipare/form-data">
		<div>
			상품번호<%=vo.getItem_sq() %>
			<input name="item_sq" value="<%=vo.getItem_sq() %>" style="display: none" >
		</div>
		<div>
			판매상태
			<%=vo.isSttus_fl() %>>
		</div>
		<%-- <div>
			카테고리 <%=vo.getCtgry_sq() %>
		</div> --%>
		<div>
			이름 <input type="text" id="nm" name="nm" placeholder="상품이름" oninput=""
				maxlength="25" value="<%=vo.getNm() %>">
		</div>
		<div>
			<input type="text" id="pc" name="pc" placeholder="상품가격" oninput=""
				value="<%=vo.getPc()%>">
		</div>
		<div>
			날짜 <input type="date" id="dttm" name="dttm" oninput=""
				value="<%=vo.getDttm()%>">
		</div>
		<div>
			상품 상세 내용 <input type="text" id="cntnt" name="cntnt" oninput=""
				value="<%=vo.getCntnt()%>">
		</div>
		<div>
			재고 <input type="text" id="stock" name="stock" placeholder="재고"
				value="<%=vo.getStock()%>">
		</div>
		<div>
			<input type="file" id="image" name="image" accept="image/*">
		</div>

		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForRegister.jsp" flush="false" />
		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>

</body>
</html>