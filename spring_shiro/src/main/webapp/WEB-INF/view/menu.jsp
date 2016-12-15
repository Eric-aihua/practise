<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="include/doc_head.jsp"%>
<div class="menu-side">
            <ul class="nav nav-pills nav-stacked">
               <li><a href="${pageContext.request.contextPath }/dashboard"><span class="esbicon esbicons-gauge"></span><spring:message code="menu.dashboard" /></a></li>
                <shiro:hasPermission name="setting">
                <li><a href="${pageContext.request.contextPath }/user/userlist"><span class="esbicon esbicons-cog"></span><spring:message code="menu.setting" /></a></li>
                </shiro:hasPermission>
            </ul>
</div>