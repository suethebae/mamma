<%@page import="java.util.ArrayList"%>
<%@page import="shop.mammastore.admin.vo.AitemVo"%>
<%@page import="shop.mammastore.admin.vo.AmanagerVo"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
ArrayList<AitemVo> itemList = (ArrayList<AitemVo>) request.getAttribute("itemList");
%>

{ "itemList" : [
<%for (int i = 0; i < itemList.size(); i++) {%>
{ 	"itm_sq" : "<%=itemList.get(i).getItm_sq()%>",
 	"ctgry_sq" : "<%=itemList.get(i).getCtgry_sq()%>",
	"pc" : "<%=itemList.get(i).getPc()%>", 
	"nm" : "<%=itemList.get(i).getNm()%>",
	"fl_pth" : "<%=itemList.get(i).getFl_pth()%>" }
<%if(i<itemList.size()-1){%>
,
<%}%>
<%}%>
]}
