<%--
/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="it.italiangrid.portal.dbapi.domain.*" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %> 
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %> 
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.security.auth.AuthTokenUtil" %>

<div id="container">

<aui:layout>

<aui:fieldset label="Import Sample Job">

<portlet:defineObjects />	
<liferay-theme:defineObjects />

<c:choose>
	<c:when test="<%= !themeDisplay.isSignedIn() %>">
		<p/>
		<strong> Non sei loggato!! </strong>
		<p/>
	</c:when>
	<c:when test="${isOK==true }">
		<jsp:useBean id="jobMap" type="java.util.Map" scope="request" />
		<jsp:useBean id="voList" type="java.util.List<it.italiangrid.portal.dbapi.domain.Vo>" scope="request" />
		
		<aui:layout>
			<aui:fieldset>
				<br/>
				<c:forEach var="vo" items="${voList}">
					
					<strong>${vo.vo }: </strong>
					<c:choose>
						<c:when test="${fn:length(jobMap[vo.vo]) == 0 }"> 
							<br/>No job to import. <br/><br/>
						</c:when>
						<c:otherwise>
							<form method="post" name="importTest" id="importTest" action="https://portal.italiangrid.it/web/guest/import?p_auth=<%=AuthTokenUtil.getToken(request)%>&p_p_id=wfimport_WAR_wspgrade&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_wfimport_WAR_wspgrade_guse=doJustDoIt">
									<input type="hidden" value="import" name="impMethode">
									<input type="hidden" value="appl" name="impWfType">
									<input type="hidden" value="https://portal.italiangrid.it/web/guest/workflow" name="returnPath">
									<input type="hidden" value="JOB_${vo.vo }" name="wfimp_newRealName">
									<select name="impItemId" >
										<c:forEach var="value" items="${jobMap[vo.vo] }">
	
												<option value="${fn:split(value,':')[1]}" >${fn:split(value,':')[0]}</option>
	
										</c:forEach>
									</select>
									<input type="submit" value="Import Job">
								</form>
							<br/>
						</c:otherwise>
					</c:choose>
						
				</c:forEach>
			
			</aui:fieldset>
		</aui:layout>
	</c:when>
	<c:otherwise>
		No job to import.
	</c:otherwise>
</c:choose>

</aui:fieldset>
</aui:layout>

</div>