
<%@ include file="../include/doc_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<sf:form method="POST" modelAttribute="user"
	action="${pageContext.request.contextPath}${action}">
	<div class="row">
		<div class="col-md-12">
			<div class="table-header2">
				<spring:message code="${title}" />
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<sf:input path="id" hidden="true" />
				<label for="username"><spring:message code="user.field.name" /></label>
				<sf:input path="name" id="user_name" class="form-control" size="15"
					maxlength="30" />
				<sf:errors path="name" cssClass="error" />

			</div>
			<div class="form-group">
				<label for="useremail"><spring:message
						code="user.field.email" /></label>
				<sf:input path="email" id="email" class="form-control" size="15"
					maxlength="50" />
				<sf:errors path="email" cssClass="error" />
			</div>
			<div class="form-group">
				<label for="password"><spring:message code="user.field.pwd" /></label>
				<sf:password path="pwd" id="pwd" class="form-control" size="15"
					showPassword="true" maxlength="50" />
				<sf:errors path="pwd" cssClass="error" />
			</div>

			<div class="form-group">
				<label for="confirmpassword"><spring:message
						code="user.field.repwd" /></label>
				<sf:password path="retryPwd" id="retrypwd" class="form-control"
					size="15" maxlength="50" showPassword="true" />
				<sf:errors path="retryPwd" cssClass="error" />
			</div>
			<div class="form-group">
				<label></label>
				<c:if test="${showResetPwd}">
					<div class="checkbox">
						<label for="resetpwd"> <spring:message
								code="user.field.resetpwd" /></label>
						<sf:checkbox id="resetpwd" path="resetPwd" />
					</div>
				</c:if>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-gray">
				<div class="panel-heading">
					<spring:message code="user.field.usergroup" />
				</div>
				<div class="panel-body">

					<div class="box-max-H195">
						<sf:checkboxes items="${grouplist}" path="roleIdList"
							itemValue="id" itemLabel="name" element="div class='checkbox'" />
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">

			<input type="submit" name="action"
				value="<spring:message code="button.submit" />"
				class="btn btn-primary" />
			<c:if test="${updateMode}">
				<input type="submit" name="action"
					value="<spring:message code="button.delete" />"
					class="btn btn-primary" />
			</c:if>
			<input type="submit" name="action"
				value="<spring:message code="button.cancel" />"
				class="btn btn-primary" />
		</div>
	</div>
</sf:form>
