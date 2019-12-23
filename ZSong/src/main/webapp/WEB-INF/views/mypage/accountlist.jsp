<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.*"%>

<%
   String accountList = (String)request.getAttribute("accountList");
   System.out.println(accountList);

   JSONObject jObject = new JSONObject();
 
   jObject.put("accountList", accountList);

   out.println(jObject);

%>
