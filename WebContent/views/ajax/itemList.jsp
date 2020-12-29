<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
	AitemVo aitemVo = (AitemVo) request.getAttribute("showItemList");
%>

{ "itm_sq" : "<%=aitemVo.getItm_sq()%>",
  "ctgry_sq" : "<%=aitemVo.getCtgry_sq()%>",
  "pc" : "<%=aitemVo.getPc()%>",
  "nm" : "<%=aitemVo.getNm()%>",
  "thumb_pth" : "<%=aitemVo.getThumb_pth()%>"}
