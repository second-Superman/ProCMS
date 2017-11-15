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
<jsp:include page="link.jsp" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="sidebar.jsp" />
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->

			<jsp:include page="infoHead.jsp">
				<jsp:param value="新闻管理" name="title" />
				<jsp:param value="新闻数据的添加、删除、修改和查询" name="subTitle" />
			</jsp:include>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<div>
									<button onclick="aClick('<%=basePath %>admin/NewsServlet.action?method=addBefore')"
										type="button" class="btn  btn-default ">
										<i class="fa fa-plus"></i>添加新闻
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<table id="example2" class="table table-bordered table-hover">
									<thead>
										<tr>
											<th>id</th>
											<th>图片</th>
											
											<th>标题</th>
											<th>时间</th>
											<th>作者</th>
											<th>类型</th>
											<th>浏览量</th>
											
											<th>操作</th>
										</tr>
									</thead>
									<tbody>


										<c:forEach items="${news }" var="item">
											<tr>
												<td>${item.id }</td>
												<td><img style="width: 180px;height: 135px"  src="<%=basePath %>upload/${item.photo }"  >   ${item.id }</td>
												<td>
												
												
												<a href="<%=basePath%>admin/NewInfoServlet.action?method=findbynewsid&newsId=${item.id }">${item.title }</a>
												
												
												
												
												
												</td>
												<td>${item.st }</td>
												<td>${item.author }</td>
												<td>${item.newsType.name }</td>
												<td>${item.browserCount }</td>
											
												<td>

													<button onclick="newsTypeEditBefore(${item.id })"  type="button" class="btn  btn-default ">
														<i class="fa fa-edit"></i>编辑
													</button> &nbsp;&nbsp;

													<button  onclick="delDialogOpen('${item.id }','${item.title }','NewsServlet.action')" type="button" class="btn  btn-default ">
														<i class="fa fa-close"></i>删除
													</button>

												</td>

											</tr>
										</c:forEach>

									</tbody>
									<tfoot>
										<tr>
											<th colspan="3">分页</th>

										</tr>
									</tfoot>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->

						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- ./wrapper -->
	<jsp:include page="js.jsp" />
</body>
</html>


<script type="text/javascript">


function newsTypeEditBefore(id){

window.location='<%=basePath%>admin/NewTypeServlet.action?method=editBefore&id='+id;

}


</script>
