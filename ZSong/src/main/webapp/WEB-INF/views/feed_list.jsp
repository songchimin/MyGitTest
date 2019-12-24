<%@page import="com.study.springboot.dao.SeleeDao"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.simple.*"%>
<%@ page import="com.study.springboot.dto.FeedDto" %>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
SeleeDao dao;
JSONObject jsonMain = new JSONObject();
JSONArray jArray = new JSONArray();

String id = getInitParameter("member_id");
String num;

ArrayList<FeedDto> dto = (ArrayList<FeedDto>)request.getAttribute("list");

for(int i = 0 ; i < dto.size() ; i++)
{
   JSONObject jObject = new JSONObject();
   
    jObject.put("feed_num", dto.get(i).getFeed_num());
    jObject.put("member_id", dto.get(i).getMember_id());
    jObject.put("challenge_num", dto.get(i).getChallenge_num());
    jObject.put("challenge_title", dto.get(i).getChallenge_title());
    SimpleDateFormat date = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    jObject.put("feed_update_time", date.format(dto.get(i).getFeed_update_time()));
    jObject.put("feed_info", dto.get(i).getFeed_info());
    jObject.put("feed_comment", dto.get(i).getFeed_comment());
   
    jArray.add(i, jObject);
}
 jsonMain.put("List", jArray);
 out.println(jsonMain); 
%>