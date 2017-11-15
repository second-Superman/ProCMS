<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>


<jsp:include page="pageLink.jsp" />


</head>
<body>

	<!--导航-->
	<jsp:include page="nav.jsp" />
	<!--banner-->
	<jsp:include page="banner.jsp" />
	<!--内容-->

	<div class="container">

		<div class="row">
			<div class="col-xs-9">
				<div class="page-header">
					<h2>${newsInfo.news.title }</h2>
					<p>
						<span>日期:${newsInfo.news.st }</span>&nbsp;&nbsp;<span>作者:${newsInfo.news.author }</span>
					</p>
					<p>
						<span>浏览量:${newsInfo.news.browserCount }</span>
					</p>
				</div>

				<div class="my-div-p">${newsInfo.info}</div>


			</div>

			<!--边栏-->
			<div class="col-xs-3">
				<jsp:include page="newsTop10.jsp" />
				<jsp:include page="newsBrowserCountTop10.jsp" />

			</div>

		</div>

	</div>

	<!--脚注-->
	<jsp:include page="footer.jsp" />


</body>


</html>

<jsp:include page="pageJs.jsp" />