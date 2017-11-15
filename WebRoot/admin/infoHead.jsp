<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<section class="content-header">
<h1>
	<%=request.getParameter("title") %> <small><%=request.getParameter("subTitle") %></small>
</h1>
</section>
