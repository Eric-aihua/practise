<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<jsp:include page="include/doc_head_login.jsp" />
<div class="logo_box col-md-5 col-xs-10 col-sm-10">
	<div class="row">
		<img src=<c:url value="/resources/img/logo2.png" /> alt="GCloudESB"
			width="100%" class="logo_login">
	</div>
	<div class="row">
		<div class="error">
			<spring:message text="${message_login}"></spring:message>
		</div>
		<div class="col-md-12">
			<form class="form-inline" name='loginForm' method='POST'
				action="${pageContext.request.contextPath}/user/loginprocess">
				<div class="form-group">
					<label class="sr-only" for="exampleInputEmail2"><spring:message
							code="user.name" /></label> <input type="text" class="form-control"
						id="usernameInpt" name="username" value=''>
				</div>
				<div class="form-group">
					<label class="sr-only" for="exampleInputPassword2"><spring:message
							code="user.pwd" /></label> <input type="password" class="form-control"
						id="pswInpt" name='password'>
				</div>
				<button type="submit" class="btn btn-primary">
					<spring:message code="user.login" />
				</button>
			</form>
		</div>
	</div>
</div>
<script src=<c:url value="/resources/js/jquery.js" />></script>
<script src=<c:url value="/resources/js/bootstrap.js" />></script>
<script src=<c:url value="/resources/js/ui.js" />></script>
<script>
	$(function() {
		$(".logo_box").fadeIn(1000);
		$(document).load(function() {
			$("#usernameInpt").focus();
		});
	});
</script>
</body>
</html>