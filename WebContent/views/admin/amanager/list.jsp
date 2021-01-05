<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<AmanagerVo> list = (ArrayList<AmanagerVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 목록</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function showDetail(sq){
		var mngr_sq = $('#mngr_sq');
		var id = $('#id');
		var nm = $('#nm');
		var dttm = $('#dttm');
		var phone = $('#phone');
		var email = $('#email');

		$.ajax({
			url : "/ajax/detailMngr",
			type : "post",
			dataType : "json",
			data : {
				sq : sq
			},
			error : function() {
				alert("통신 실패");
			},
			success : function(data) {
				mngr_sq.val(data.mngr_sq);
				id.val(data.id+"("+data.author+")");
				nm.val(data.nm);
				dttm.val(data.dttm);
				phone.val(data.phone);
				email.val(data.email);
			}
		});
	}
	function modifyMngr(){
		location.href="/amanager/modify?mngr_sq="+$('#mngr_sq').val();
	}

	function leaveMngr(){
		var con = confirm("정말로 탈퇴 하시겠습니까?");
		if(con){
			location.href="/amanager/leave?mngr_sq="+$('#mngr_sq').val();
		}
		else{
			return;
		}
	}

	function registerMngr(){
		location.href="/amanager/register"
	}
</script>
</head>
<body>
	<jsp:include page="/views/admin/aNavbar.jsp"></jsp:include>
	<!-- 직원 이름 표시  -->
	<div>
		<table border=1>
			<tr>
				<th>직원 목록</th>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><button onclick="showDetail(<%=list.get(i).getMngr_sq()%>)"><%=list.get(i).getNm()%></button></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<button onclick="registerMngr()">관리자 회원 가입</button>
	<!-- 상세 내용 표시 -->
	<div>
		<table>
			<tr>
				<th>매니저 정보</th>
			</tr>
			<tr>
				<th><input type="text" id="mngr_sq" readonly="readonly">
				</th>
			</tr>
			<tr>
				<th><input type="text" id="id" readonly="readonly"></th>
			</tr>
			<tr>
				<th><input type="text" id="nm" readonly="readonly"></th>
			</tr>
			<tr>
				<th><input type="text" id="dttm" readonly="readonly"></th>
			</tr>
			<tr>
				<th><input type="text" id="phone" readonly="readonly"></th>
			</tr>
			<tr>
				<th><input type="text" id="email" readonly="readonly"></th>
			</tr>
		</table>
	</div>
	<button onclick="modifyMngr()">관리자 회원 수정</button>
	<button onclick="leaveMngr()">관리자 회원 탈퇴</button>

</body>
</html>