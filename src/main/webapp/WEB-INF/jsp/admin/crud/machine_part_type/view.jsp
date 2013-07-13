<%-- 
    Document   : view
    Created on : Jul 12, 2013, 4:02:12 PM
    Author     : Uyeee
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin - View Machine Part Type</title>
	</head>
	<body>
		<a href="index.htm">List Machine Models</a> | <a href="add.htm">Add Machine Part Type</a>
		<table>
			<tr>
				<td>Model ID</td>
				<td>
					<s:url value="update.htm" var="viewUrl">
						<s:param name="partId" value="${viewData.partId}"/>
					</s:url>
					<a href="${viewUrl}">${viewData.partId}</a>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>
					<a href="${viewUrl}">${viewData.name}</a>
				</td>
			</tr>
			<tr>
				<td>Default MTTF</td>
				<td>
					<a href="${viewUrl}">${viewData.defaultMttf}</a>
				</td>
			</tr>
			<tr>
				<td>Default MTTF Threshold</td>
				<td>
					<a href="${viewUrl}">${viewData.defaultMttfThreshold}</a>
				</td>
			</tr>
		</table>
	</body>
</html>
