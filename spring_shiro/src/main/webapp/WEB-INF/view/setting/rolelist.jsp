
<%@ include file="../include/doc_head.jsp"%>
<%@ taglib prefix="page" uri="/paging"%>
<div class="row">
	<div class="col-md-12">
		<div class="table-header2">
			<spring:message code="setting.role.allrole" />
		</div>
	</div>
</div>
<div class="row toolbar">
	<div class="col-md-12">
		<form class="form-inline"
			action="${pageContext.request.contextPath}/role/new" method="get">
			<div class="form-group">
				<div class="btn-group">
					<button class="btn btn-default" id="create_newrole">
						<spring:message code="setting.role.newrole" />
					</button>
				</div>
			</div>
			<div class="form-group">
				<label for="param" class="sr-only"><spring:message
						code="setting.user.filter" /></label> <input
					placeholder="<spring:message code="setting.user.filter"/>"
					id="roleName" class="form-control input-filter" name="roleName">
				<span class="esbicon esbicons-search"></span>
			</div>
			<div class="form-group">
				<span class="esbicon esbicons-help"></span>
			</div>
		</form>
	</div>
</div>
	<div class="row" id="rolelist">
		<div class="col-md-12" id="tableBox"></div>
	</div>
	<input type="hidden" id="modulePath" value="/role">


