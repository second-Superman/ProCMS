<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=basePath %>res/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>res/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="<%=basePath %>res/my.css" rel="stylesheet">