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

<link
	href="<%=basePath %>res/umeditor1.2.3/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<link
	href="<%=basePath %>res/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link
	href="<%=basePath %>res/bootstrap-datetimepicker/css/bootstrap-datetimepicker-standalone.css"
	rel="stylesheet">


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
				<jsp:param value="新闻数据添加" name="title" />
				<jsp:param value="在这里你可以添加新闻数据" name="subTitle" />
			</jsp:include>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">新闻数据添加表单</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
							
								<form
									action="<%=basePath%>admin/NewsServlet.action?method=add"
									method="post" class="form-horizontal"
									enctype="multipart/form-data">
									<div class="box-body">

										<div class="form-group">
											<label class="col-xs-1 control-label">标题：</label>

											<div class="col-xs-6 input-group">
												<input name="title" type="text" class="form-control"
													placeholder="标题">
											</div>
										</div>

										<div class="form-group">
											<label class="col-xs-1 control-label">作者：</label>

											<div class="col-xs-6 input-group">
												<input name="author" type="text" class="form-control"
													placeholder="作者">
											</div>
										</div>


										<div class="form-group">
											<label class="col-xs-1 control-label">时间：</label>
											<div class='col-xs-6 input-group date' id='datetimepicker1'>
												<input name="st" type='text' class="form-control" /> <span
													class="input-group-addon"> <span
													class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>

										</div>


										<div class="form-group">
											<label class="col-xs-1 control-label">浏览总数：</label>
											<div class="col-xs-6 input-group">
												<input name="browser_count" value="0" type="text" class="form-control"
													placeholder="0">
											</div>
										</div>


										<div class="form-group">
											<label class="col-xs-1 control-label">新闻类型：</label>
											<div class="col-xs-6 input-group">


												<select name="news_type_id" class="form-control">
												
												<c:forEach items="${newsTypes }" var="item">
													<option value="${item.id }" >${item.name }</option>
												</c:forEach>
											
												
												</select>

											</div>
										</div>


										<div class="form-group">
											<label class="col-xs-1 control-label">新闻图片：</label>
											<div class="col-xs-6 input-group">

												<div class="row">
													<div class="col-xs-6">
														<input id="selectImg" name="photo" type="file"
															class="form-control" placeholder="0">
													</div>
													<div class="col-xs-6">
														<img style="width: 180px;height: 135px" id="selectImgView"
															src="holder.js/180x135">
													</div>

												</div>


											</div>

										</div>

										<div class="form-group">
											<label class="col-xs-1 control-label">新闻内容：</label>
											<div class="col-xs-10 input-group">
												<script style="height:400px;width:1000px" id="UMeditor" name="info"
													type="text/plain">
        这里写你的初始化内容




                                            </script>
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

	<script
		src="<%=basePath %>res/bootstrap-datetimepicker/js/moment.min.js"></script>
	<script
		src="<%=basePath %>res/bootstrap-datetimepicker/js/moment-with-locales.min.js"></script>
	<script
		src="<%=basePath %>res/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=basePath %>res/umeditor1.2.3/third-party/template.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath %>res/umeditor1.2.3/umeditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript"
		src="<%=basePath %>res/umeditor1.2.3/umeditor.js"></script>
		
		<script type="text/javascript" src="<%=basePath %>res/umeditor1.2.3/lang/zh-cn/zh-cn.js"></script>

</body>
</html>


<script>


    $(function () {

        //设置日期插件
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn')
        });


//        选择图片
        $('#selectImg').bind('change', function () {
            //兼容性
            var $file = $(this);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            dataURL = windowURL.createObjectURL(fileObj.files[0]);
            if (fileObj && fileObj.files && fileObj.files[0]) {
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
            } else {
                dataURL = $file.val();
            }
            //返回结果
            $('#selectImgView').attr('src', dataURL);
        });

//初始化富文本
        var ue = UM.getEditor('UMeditor',{
            autoHeightEnabled: false,
            toolbar:[
                'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
                'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
                '| justifyleft justifycenter justifyright justifyjustify |',
                'link unlink | emotion image video  | map',
                '| horizontal print preview', 'drafts', 'formula'
            ]

        });


    });


</script>
