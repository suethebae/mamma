<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
	AmanagerVo amanagerVo = (AmanagerVo) request.getAttribute("detailMngr");
%>

{ "mngr_sq" : "<%=amanagerVo.getMngr_sq()%>",
  "author" : "<%=amanagerVo.isAuthor()%>",
  "id" : "<%=amanagerVo.getId()%>",
  "nm" : "<%=amanagerVo.getNm()%>",
  "email" : "<%=amanagerVo.getEmail()%>",
  "phone" : "<%=amanagerVo.getPhone()%>",
  "dttm" : "<%=amanagerVo.getDttm()%>"}


