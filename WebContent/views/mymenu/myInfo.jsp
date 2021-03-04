<%@page import="shop.mammastore.mamma.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MemberVo memberVo = (MemberVo) request.getAttribute("memberVo");
//memberVo 라는 오브젝트형태로 데이터를 넣었기때문에 이렇게 써주자.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<link rel="stylesheet" type="text/css" href="/views/css/style.css">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script type="text/javascript">
	function modify() {
		location.href = "/member/modify";
	}
	function leave() {
		if (confirm('정말로 탈퇴 하시겠습니까?')) {
			location.href = "/member/leave";
		}
	}
</script>
</head>
<body>
	<nav>
		<jsp:include page="/views/navbar.jsp"></jsp:include>
	</nav>
	<section id="myInfo">
		<div class="myInfo">
			<div class="myInfo__header">
				<img src="/views/img/myinformation.png" alt="myInfo">
			</div>
			<div class="myInfo__main">
				<table>
					<tr>
						<td>이름</td>
						<td class="padding"><%=memberVo.getNm()%></td>
					</tr>
					<tr class="line">
						<td>아이디</td>
						<td class="padding"><%=memberVo.getId()%></td>
					</tr>
					<tr class="line">
						<td>이메일</td>
						<td class="padding"><%=memberVo.getEmail()%></td>
					</tr>
					<tr class="line">
						<td>휴대폰</td>
						<td class="padding"><%=memberVo.getPhone()%></td>
					</tr>
					<tr>
						<td class="center" colspan="2">
							<button onclick="modify()">수정하기</button>
						</td>
					</tr>
					<tr>
						<td class="right" colspan="2"><a href="#" onclick="leave()">회원탈퇴</a></td>
					</tr>
				</table>

			</div>
		</div>
	</section>
</body>
</html>