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
<meta name="content-type" content="text/html; charset=UTF-8">
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
			<div class="col-xs-8">
				<div class="page-header">
					<h2>
						${requestScope.newsType.name }<small>最新前沿资讯</small>
					</h2>
					<!--具体新闻-->


					<c:forEach items="${requestScope.news }" var="item">

						<div class="row my-news">
							<div class="col-xs-4">
							
							<a href="<%=basePath%>InfoPageServlet.action?newsId=${item.id}&newsTypeId=${requestScope.newsType.id}">
							
								<img style="width: 180px;height: 135px" class="img-thumbnail"
									src="<%=basePath %>upload/${item.photo }">
									</a>
							</div>
							<div class="col-xs-8">
							<a href="<%=basePath%>InfoPageServlet.action?newsId=${item.id}&newsTypeId=${requestScope.newsType.id}">
								<h4 class="title my-news-title">${item.title }</h4>
								</a>
								<p class="st-author">
									<i class="fa fa-calendar"></i>&nbsp;<span>${item.st }</span>&nbsp;<i
										class="fa fa-group"></i>&nbsp;<span>${item.author }</span>
								</p>
								<p class="browser-count">
									<i class="fa fa-eye">浏览量</i>&nbsp;<span>${item.browserCount }</span>
								</p>
							</div>
						</div>

					</c:forEach>
				</div>

				<!--分页-->
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#" class="active">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>


			<div class="col-xs-4">
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