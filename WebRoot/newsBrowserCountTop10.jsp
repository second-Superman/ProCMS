<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="list-group" style="margin-top: 40px">
	<a class="list-group-item active"> 浏览量最多的10条新闻 </a>
	<c:forEach items="${requestScope.newsBrowserCountTop10 }" var="item"
		varStatus="status">


		<a href="<%=basePath%>InfoPageServlet.action?newsId=${item.id}&newsTypeId=${requestScope.newsType.id}"
			class="list-group-item"> <span class="badge"
			style="background-color: #df6813;float: left">${10-status.index }</span>&nbsp;<span class="title-sidebar">${item.title }</span>
		</a>
	</c:forEach>
</div>


