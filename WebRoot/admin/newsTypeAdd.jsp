<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AdminLTE 2 | Data Tables</title>
<jsp:include page="link.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="sidebar.jsp"></jsp:include>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<jsp:include page="infoHead.jsp">
				<jsp:param value="新闻类型数据添加" name="title" />
				<jsp:param value="在这里你可以添加新闻类型数据" name="subTitle" />
			</jsp:include>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">新闻类型数据添加表单</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">

								<form action="<%=basePath%>admin/NewTypeServlet.action?method=add" method="post" class="form-horizontal">
									<div class="box-body">
										<div class="form-group">
											<label  class="col-xs-1 control-label">名称</label>

											<div class="col-xs-6">
												<input name="name" type="text" class="form-control" placeholder="名称">
											</div>
										</div>





									</div>
									<!-- /.box-body -->
									<div class="box-footer">
										<button type="submit" class="btn btn-default">添加数据</button>

									</div>
									<!-- /.box-footer -->
								</form>

							</div>
							<!-- /.box-body -->
						</div>



					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<!-- ./wrapper -->
	<jsp:include page="js.jsp"></jsp:include>
</body>
</html>
