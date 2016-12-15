
<%@ include file="../include/doc_head.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<sf:form method="POST" modelAttribute="role"
	action="${pageContext.request.contextPath}${action}">
	<div class="row">
		<div class="col-md-12">
			<div class="table-header2">
				<spring:message code="${title}" />

			</div>
			<c:if test="${ErrorMessage!=null}">
				<div class="error">
					<spring:message code="${ErrorMessage}" />
				</div>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<sf:input path="id" hidden="true" />
				<label for="rolename"><spring:message code="role.field.name" /></label>
				<sf:input path="name" id="rolename" class="form-control" size="15"
					maxlength="30" />
				<sf:errors path="name" cssClass="error" />
			</div>
			<div class="form-group">
				<label for="description"><spring:message
						code="role.field.description" /></label>
				<sf:input path="description" id="description" class="form-control"
					size="15" maxlength="50" />
				<sf:errors path="description" cssClass="error" />
			</div>

		</div>
		<div class="col-md-6">
			<div class="panel panel-gray">
				<div class="panel-heading">
					<spring:message code="role.field.rightglist" />
				</div>
				<div class="panel-body">
					<div class="box-max-H195">
						<sf:checkboxes items="${rightlist}" path="rightIdList"
							itemValue="id" itemLabel="label" element="div class='checkbox'" />
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
