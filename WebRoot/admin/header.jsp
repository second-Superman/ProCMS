<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<header class="main-header">
	<!-- Logo -->
	<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>HZJ</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>HZJ</b>管理系统</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="push-menu"
			role="button"> <span class="icon-bar"></span> <span
			class="icon-bar"></span> <span class="icon-bar"></span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<li><a> <i class="fa fa-user-circle-o"></i>&nbsp;管理员：${sessionScope.admin.username }
				</a></li>
				<li ><a> <i class="fa fa-bell-o"></i>&nbsp;<span id="timeLi">sdsdsdsd</span>
				</a></li>
			</ul>
		</div>
	</nav>
</header>