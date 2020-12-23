<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//getAttribute로 정보 받아온다
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
<%-- var content = '<%=vo.getContent()%>' --%>
</script>
</head>
<body>
<form action="/aitem/modifyItemProc" method="post" id="editorForm" enctype="multipare/form-data">
		<div>
			<input type="text" id="nm" name="nm" placeholder="상품이름" oninput=""
				maxlength="25">
		</div>
		<div>
			<input type="file" id="image" name="image" accept="image/*">
		</div>
		<div>
			<input type="text" id="pc" name="pc" placeholder="상품가격" oninput=""><input
				type="text" id="stock" name="stock" placeholder="재고">
		</div>
		<div style="width: 1000px">
			<jsp:include page="/editor/editorSkinForModify.jsp" flush="false" />
		</div>
	</form>
	<button onclick="register()">등록</button>
	<button onclick="cancel()">취소</button>
</body>
</html>