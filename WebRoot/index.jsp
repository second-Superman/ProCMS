<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="pageLink.jsp" />
</head>
<body>




	<!-- 导航 -->
	<jsp:include page="nav.jsp" />
	<!-- banner -->
	<jsp:include page="banner.jsp" />
	<!-- 内容 -->
	<div class="container">
		<c:forEach items="${requestScope.indexListItemBeans }" var="item">

			<div class="page-header">
				<h2>${item.newsType.name }
					<small>最新前沿资讯【更多】</small>
				</h2>
				<div class="row">
					<div class="col-xs-6">
						<!--具体新闻-->
						<c:forEach items="${item.leftNews }" var="itemLeftNews">
							<div class="row my-news">
								<div class="col-xs-4">
								<a href="<%=basePath%>InfoPageServlet.action?newsId=${itemLeftNews.id}&newsTypeId=${item.newsType.id}">
									<img class="img-thumbnail" style="width: 180px;height: 135px" src="<%=basePath%>upload/${itemLeftNews.photo}">
									</a>
								</div>
								<div class="col-xs-8">
									<a href="<%=basePath%>InfoPageServlet.action?newsId=${itemLeftNews.id}&newsTypeId=${item.newsType.id}">  <h4 class="title my-news-title">${itemLeftNews.title }</h4></a>
									<p class="st-author">
										<i class="fa fa-calendar"></i>&nbsp;<span>${itemLeftNews.st }</span>&nbsp;<i class="fa fa-group"></i>&nbsp;<span>${itemLeftNews.author}</span>
									</p>
									<p class="brower-count">
										<i class="fa fa-eye">浏览量</i>&nbsp;<span>${itemLeftNews.browserCount}</span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>

					<div class="col-xs-6">
						<!--具体新闻-->



						<c:forEach items="${item.rightNews }" var="itemRightNews">

							<div class="row my-news">
							<div class="col-xs-4">
							<a href="<%=basePath%>InfoPageServlet.action?newsId=${itemRightNews.id}&newsTypeId=${item.newsType.id}">
								<img class="img-thumbnail" style="width: 180px;height: 135px" src="<%=basePath%>upload/${itemRightNews.photo}">
							</a>
							</div>
							<div class="col-xs-8">
							<a href="<%=basePath%>InfoPageServlet.action?newsId=${itemRightNews.id}&newsTypeId=${item.newsType.id}">
								<h4 class="title my-news-title">${itemRightNews.title}</h4>
								</a>
								<p class="st-author">
									<span>${itemRightNews.st}</span><span>${itemRightNews.author}</span>
								</p>
								<p class="brower-count">
									<span>浏览数：</span><span>${itemRightNews.browserCount}</span>
								</p>
							</div>
						</div>

						</c:forEach>



					</div>
				</div>

			</div>

		</c:forEach>

	</div>








	<!-- 脚注 -->
	<jsp:include page="footer.jsp" />
</body>
</html>


<jsp:include page="pageJs.jsp" />

