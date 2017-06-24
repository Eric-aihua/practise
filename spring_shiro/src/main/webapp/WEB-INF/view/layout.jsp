<%@ include file="include/doc_head.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ESB</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-responsive.css" />"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span
				class="esbicon esbicons-GCloudESB"></span></a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<tiles:insertAttribute name="header" ignore="true" />
			<ul class="nav navbar-nav navbar-mini navbar-right">
				<li><a href="#"><span class="esbicon esbicons-user"></span><span
						class="text-light"><%=session.getAttribute("currentUser")%>
					</span></a></li>
				<li><a href="<s:url value="/user/logout"/>"><span
						class="esbicon esbicons-switch"></span></a></li>
			</ul>
		</div>
	</nav>
	<div class="sidebar-nav">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="container-fluid ESB-Layout ESB-Content">
		<div class="row"></div>
		<input type="hidden" id="projectPath" value="${pageContext.request.contextPath}" />
		<tiles:insertAttribute name="body" />
	</div>

	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script src="<c:url value="/resources/js/ui.js" />"></script>
	
	<tiles:insertAttribute name="footer" />
	
</body>
</html>