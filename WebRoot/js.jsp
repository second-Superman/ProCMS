<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- jQuery 3 -->
<script src="<%=basePath%>res/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath%>res/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%=basePath%>res/plugins/iCheck/icheck.min.js"></script>
<!--holder-->
<script src="<%=basePath%>res/holder/holder.js"></script>
<!--blockUI-->
<script src="<%=basePath%>res/blockUI/jquery.blockUI.min.js"></script>