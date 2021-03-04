<%@page import="shop.mammastore.admin.vo.AreviewNameVo"%>
<%@page import="shop.mammastore.admin.vo.AreviewVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<AreviewNameVo> list = (ArrayList<AreviewNameVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script>
	function showDetail(itm_sq) {
		location.href = "/item/detail?itm_sq="+itm_sq;
	}
	function deleteReview(review_sq){
	    if ( confirm('삭제하시겠습니까?')) {
	    	location.href="/areview/delete?review_sq="+review_sq;
		} else {
			return;
		}
	}
</script>
</head>
<body>
	관리자용 리뷰 페이지 입니다..
	<table border='1'>
		<tr>
			<td>리뷰 번호</td>
			<td>글 작성자</td>
			<td>제목</td>
			<td>게시일</td>
			<td>해당 상품으로</td>
			<td>삭제</td>
		</tr>
		<%
		for (int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td
				onclick="location.href='/areview/detail?sq=<%=list.get(i).getReview_sq()%>'"><%=list.get(i).getReview_sq()%></td>
			<td
				onclick="location.href='/areview/detail?sq=<%=list.get(i).getReview_sq()%>'"><%=list.get(i).getId()%></td>
			<td
				onclick="location.href='/areview/detail?sq=<%=list.get(i).getReview_sq()%>'"><%=list.get(i).getSj()%></td>
			<td
				onclick="location.href='/areview/detail?sq=<%=list.get(i).getReview_sq()%>'"><%=list.get(i).getDttm()%></td>
			<td><button onclick="showDetail(<%=list.get(i).getItm_sq()%>)">상품보기</button></td>
			<td><button onclick="deleteReview(<%=list.get(i).getReview_sq()%>)">삭제</button></td>
		</tr>
		<%
		}
		%>
	</table>
	<a href='/views/admin/afterIndex.jsp'>관리자 홈으로</a>
</body>
</html>