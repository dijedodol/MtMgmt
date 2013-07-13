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
		<title>Admin - List Machine Models</title>
	</head>
	<body>
		<a href="add.htm">Add Machine Model</a>
		<table>
			<thead>
				<tr>
					<td>Model ID</td>
					<td>Name</td>
					<td>Totalizer Count</td>
					<td>Machine Model Parts Count</td>
					<td>Manage</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viewData" items="${viewDatas}">
					<tr>
						<td>
							<s:url value="view.htm" var="viewUrl">
								<s:param name="modelId" value="${viewData.modelId}"/>
							</s:url>
							<a href="${viewUrl}">${viewData.modelId}</a>
						</td>
						<td>${viewData.name}</td>
						<td>${viewData.totalizerIds.size()}</td>
						<td>${viewData.partIds.size()}</td>
						<td>
							<s:url value="update.htm" var="updateUrl">
								<s:param name="modelId" value="${viewData.modelId}"/>
							</s:url>
							<a href="${updateUrl}">Update</a>
							<s:url value="delete.htm" var="deleteUrl">
								<s:param name="modelId" value="${viewData.modelId}"/>
							</s:url>
							<a href="${deleteUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
