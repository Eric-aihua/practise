
<%@ include file="../include/doc_head.jsp"%>
<%@ taglib prefix="page" uri="/paging"%>
<div class="row">
	<div class="col-md-12">
		<div class="table-header2">
			<spring:message code="setting.user.alluser" />
		</div>
	</div>
</div>
<div class="row toolbar">
	<div class="col-md-12">
		<form class="form-inline"
			action="${pageContext.request.contextPath}/user/new">
			<div class="form-group">
				<div class="btn-group">
					<button type="submit" class="btn btn-default">
						<spring:message code="setting.user.newuser" />
					</button>
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail2" class="sr-only"><spring:message
						code="setting.user.filter" /></label> <input
					placeholder="<spring:message code="setting.user.filter"/>"
					id="userName" class="form-control input-filter"> <span
					class="esbicon esbicons-search"></span>
			</div>
			<div class="form-group">
				<span class="esbicon esbicons-help"></span>
			</div>
		</form>
	</div>
</div>

<div class="row" id="userlist">
		<div class="col-md-12" id="tableBox"></div>
	</div>
	<input type="hidden" id="modulePath" value="/user">

