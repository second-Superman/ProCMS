<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="list-group" style="margin-top: 40px">
	<a class="list-group-item active"> 最新的10条新闻 </a>
	<c:forEach items="${requestScope.newsTop10 }" var="item">
		<a href="<%=basePath%>InfoPageServlet.action?newsId=${item.id}&newsTypeId=${requestScope.newsType.id}" class="title-sidebar list-group-item">${item.title }</a>
	</c:forEach>
</div>




