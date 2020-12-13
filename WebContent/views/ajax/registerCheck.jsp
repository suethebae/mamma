<%@ page language="java" contentType="text/plain; charset=UTF-8"%>
<%
	boolean isDuplicate = (boolean) request.getAttribute("isDuplicate");
%>

<%
	if (isDuplicate) {
%>
{ "isDuplicate" : "true"}
<%
	} else {
%>
{ "isDuplicate" : "false"}
<%
	}
%>
