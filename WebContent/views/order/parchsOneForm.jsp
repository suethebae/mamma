<%@page import="shop.mammastore.mamma.vo.CartListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	CartListVo CLVo = (CartListVo) request.getAttribute("CLVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 페이지</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
	function inputMber() {
		var cBox = $('#inputMberData');
		var nm = $('#nm');
		var phone = $('#phone');
		var email = $('#email');
		var zip_cd = $('#sample3_postcode');
		var adres = $('#sample3_address');
		var adres_detail = $('#sample3_detailAddress');
		if (cBox.is(":checked")) {
			$.ajax({
				url : "/ajax/inputMberData",
				type : "post",
				dataType : "json",
				data : {},
				error : function() {
					alert("통신 실패");
				},
				success : function(data) {
					nm.val(data.nm);
					phone.val(data.phone);
					email.val(data.email);
					if (data.zip_cd != "null") {
						zip_cd.val(data.zip_cd);
					}
					if (data.adres != "null") {
						adres.val(data.adres);
					}
					if (data.adres_detail != "null") {
						adres_detail.val(data.adres_detail)
					}
				}
			});
		} else {
			nm.val('');
			phone.val('');
			email.val('');
			zip_cd.val('');
			adres.val('');
			adres_detail.val('')
		}
	}

	function order() {
		var nm = $('#nm');
		var phone = $('#phone');
		var email = $('#email');
		var zip_cd = $('#sample3_postcode');
		var adres = $('#sample3_address');
		var adres_detail = $('#sample3_detailAddress');
		
		if(!nm.val()){
			alert('이름을 입력하여 주십시오');
			nm.focus();
			return;
		}
		if(!phone.val()){
			alert('휴대전화번호를 입력하여 주십시오');
			phone.focus();
			return;
		}
		if(!email.val()){
			alert('이메일을 입력하여 주십시오');
			email.focus();
			return;
		}
		if(!zip_cd.val()){
			alert('우편번호를 입력하여 주십시오');
			zip_cd.focus();
			return;
		}
		if(!adres.val()){
			alert('주소를 입력하여 주십시오');
			adres.focus();
			return;
		}
		
		if ($('#agree').is(":checked")) {
			$('#parchsProc').submit();
		} else {
			alert('약관에 동의 하여 주십시오');
			return;
		}
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<form action="/order/parchsOneProc" method="post" id="parchsProc">
		<table>
			<tr>
				<th>상품 번호</th>
				<th>상품 이미지</th>
				<th>상품 이름</th>
				<th>상품 가격</th>
				<th>상품 갯수</th>
				<th>총 가격</th>
			</tr>
			<tr>
				<td><%=CLVo.getItm_sq()%><input type="hidden" name="itm_sq" value="<%=CLVo.getItm_sq()%>" readonly="readonly"/></td> 
				<td><img src="<%=CLVo.getFl_pth()%>" alt="" /></td>
				<td><%=CLVo.getItm_nm()%></td>
				<td><%=CLVo.getItm_pc()%></td>
				<td><%=CLVo.getItm_cnt()%><input type="hidden" name="itm_cnt" value="<%=CLVo.getItm_cnt()%>" readonly="readonly"/></td>
				<td><%=CLVo.getItm_cnt() * CLVo.getItm_pc()%></td>
			</tr>
			<tr>
				<td colspan="6">총 금액 <%=CLVo.getItm_cnt() * CLVo.getItm_pc()%> <input type="hidden" name="pc" value="<%=CLVo.getItm_cnt() * CLVo.getItm_pc()%>" readonly="readonly"/> </td>
			</tr>
		</table>
		<div>
			<h1>구매자 정보</h1>
			로그인 아이디와 동일하게<input type="checkbox" id="inputMberData" onclick="inputMber()" /><br /> 
			<input type="text" id="nm" name="nm" placeholder="이름" /><br /> 
			<input type="text" id="email" name="email" placeholder="이메일" /><br /> 
			<input type="text" id="phone" name="phone" placeholder="휴대전화 번호" /><br />
			<input type="text" id="sample3_postcode" name="zip_cd" placeholder="우편번호"> 
			<input type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"><br> 
			<input type="text" id="sample3_address" name="adres" placeholder="주소"><br> 
			<input type="text" id="sample3_detailAddress" name="adres_detail" placeholder="상세주소"> 
			<input type="hidden" id="sample3_extraAddress" placeholder="참고항목">
			<div id="wrap" style="display: none; border: 1px solid; width: 500px; height: 300px; margin: 5px 0; position: relative">
				<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor: pointer; position: absolute; right: 0px; top: -1px; z-index: 1" onclick="foldDaumPostcode()" alt="접기 버튼">
			</div><br />
			<input type="text" id="message" name="message" placeholder="배송메세지"/><br />
			기본주소지로 저장 <input type="checkbox" id="saveBase" name="saveBase" value="1" /> <br />
			<div>
			결제 수단
			</div>
			약관 동의 <input type="checkbox" id="agree" name="agree" value="1" /> 

		</div>
	</form>
	<button onclick="order()">주문하기</button>

	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		// 우편번호 찾기 찾기 화면을 넣을 element
		var element_wrap = document.getElementById('wrap');

		function foldDaumPostcode() {
			// iframe을 넣은 element를 안보이게 한다.
			element_wrap.style.display = 'none';
		}

		function sample3_execDaumPostcode() {
			// 현재 scroll 위치를 저장해놓는다.
			var currentScroll = Math.max(document.body.scrollTop,
					document.documentElement.scrollTop);
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("sample3_extraAddress").value = extraAddr;

							} else {
								document.getElementById("sample3_extraAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample3_postcode').value = data.zonecode;
							document.getElementById("sample3_address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("sample3_detailAddress")
									.focus();

							// iframe을 넣은 element를 안보이게 한다.
							// (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
							element_wrap.style.display = 'none';

							// 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
							document.body.scrollTop = currentScroll;
						},
						// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
						onresize : function(size) {
							element_wrap.style.height = size.height + 'px';
						},
						width : '100%',
						height : '100%'
					}).embed(element_wrap);

			// iframe을 넣은 element를 보이게 한다.
			element_wrap.style.display = 'block';
		}
	</script>
</body>
</html>