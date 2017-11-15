<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="<%=basePath%>res/dist/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>管理员操作区域</p>
				<a><i class="fa fa-circle text-success"></i>在线状态</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">新闻信息管理:</li>

			<li class="treeview  <%=request.getServletPath().equals("/admin/index.jsp")?"active":"" %> "><a style="cursor: pointer;" onclick="aClick('<%=basePath%>admin/NewTypeServlet.action?method=findbyall')" id="NewTypeServlet" > <i
					class="fa fa-dashboard"></i> <span>新闻类型管理</span>
			</a></li>
			<li class="treeview  <%=request.getServletPath().equals("/admin/news.jsp")?"active":"" %> "><a style="cursor: pointer;"  onclick="aClick('<%=basePath%>admin/NewsServlet.action?method=findbyall')" > <i class="fa fa-dashboard"></i>
					<span>新闻管理</span>
			</a></li>
			<li class="header">管理员信息管理</li>
			<li class="treeview"><a href="#"> <i class="fa fa-dashboard"></i>
					<span>管理员密码修改</span>
			</a></li>
			<li class="header">系统功能</li>
			<li class="treeview"><a href="#"> <i class="fa fa-dashboard"></i>
					<span>退出后台</span>
			</a></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>



