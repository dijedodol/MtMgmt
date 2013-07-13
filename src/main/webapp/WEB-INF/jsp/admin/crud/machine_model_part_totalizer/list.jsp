<%-- 
    Document   : list
    Created on : Jul 12, 2013, 4:02:04 PM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin - List Machine Model Part Totalizers</title>
	</head>
	<body>
		<a href="add.htm">Add Machine Model Part Totalizer</a>
		<table>
			<thead>
				<tr>
					<td>Model ID</td>
					<td>Part ID</td>
					<td>Machine Model Part Identifier</td>
					<td>Connected Totalizers Count</td>
					<td>Manage</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viewData" items="${viewDatas}">
					<tr>
						<td>
							<s:url value="view.htm" var="viewUrl">
								<s:param name="modelId" value="${viewData.modelId}"/>
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
							</s:url>
							<a href="${viewUrl}">${viewData.modelId}</a>
						</td>
						<td>${viewData.partId}</td>
						<td>${viewData.machineModelPartIdentifier}</td>
						<td>${viewData.totalizerIds.size()}</td>
						<td>
							<s:url value="update.htm" var="updateUrl">
								<s:param name="modelId" value="${viewData.modelId}"/>
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
							</s:url>
							<a href="${updateUrl}">Update</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
