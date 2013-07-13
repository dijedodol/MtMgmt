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
		<title>Admin - View Machine Model Part Totalizer</title>
	</head>
	<body>
		<a href="index.htm">List Machine Model Part Totalizers</a> | <a href="add.htm">Add Machine Model Part Totalizer</a>
		<table>
			<tr>
				<td>Model ID</td>
				<td>
					<s:url value="update.htm" var="viewUrl">
						<s:param name="modelId" value="${viewData.modelId}"/>
						<s:param name="partId" value="${viewData.partId}"/>
						<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
					</s:url>
					<a href="${viewUrl}">${viewData.modelId}</a>
				</td>
			</tr>
			<tr>
				<td>Part ID</td>
				<td><a href="${viewUrl}">${viewData.partId}</a></td>
			</tr>
			<tr>
				<td>Machine Model Part Identifier</td>
				<td><a href="${viewUrl}">${viewData.machineModelPartIdentifier}</a></td>
			</tr>
			<tr>
				<td>Totalizers</td>
				<td>
					<c:forEach var="totalizerId" items="${viewData.totalizerIds}">
						${totalizerId}<br/>
					</c:forEach>
				</td>
			</tr>
		</table>
	</body>
</html>
