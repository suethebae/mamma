<%@page import="shop.mammastore.mamma.vo.MemberOrderVo"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
	MemberOrderVo MOVo = (MemberOrderVo) request.getAttribute("MOVo");
%>


{"nm" : "<%=MOVo.getNm()%>",
 "phone" : "<%=MOVo.getPhone()%>",
 "email" : "<%=MOVo.getEmail()%>",
 "adres" : "<%=MOVo.getAdres()%>",
 "adres_detail" : "<%=MOVo.getAdres_detail()%>",
 "zip_cd" : "<%=MOVo.getZip_cd()%>"}