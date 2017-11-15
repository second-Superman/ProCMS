<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<nav class="navbar navbar-inverse my-nav">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<%=basePath%>IndexServlet.action">我的资讯站</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li   data-tag="-1"><a  href="<%=basePath%>IndexServlet.action">首页</a></li>

				<c:forEach items="${requestScope.newsTypes }" var="item">
					<li   data-tag="${item.id}"  ><a href="<%=basePath%>NewsPageServlet.action?newsTypeId=${item.id}">${item.name }</a></li>
				</c:forEach>


			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>