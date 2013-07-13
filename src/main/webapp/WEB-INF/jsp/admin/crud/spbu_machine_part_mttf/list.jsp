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
		<title>Admin - Spbu Machine Part MTTF List</title>
	</head>
	<body>
		<a href="add.htm">Add Spbu Machine Part MTTF</a>
		<table>
			<thead>
				<tr>
					<td>Machine Serial</td>
					<td>Spbu Code</td>
					<td>Spbu's Machine Identifier</td>
					<td>Model ID</td>
					<td>Part ID</td>
					<td>Machine's Part Identifier</td>
					<td>MTTF</td>
					<td>MTTF Threshold</td>
					<td>Manage</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viewData" items="${viewDatas}">
					<tr>
						<td>
							<s:url value="view.htm" var="viewUrl">
								<s:param name="machineSerial" value="${viewData.machineSerial}"/>
								<s:param name="modelId" value="${viewData.modelId}"/>
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
							</s:url>
							<a href="${viewUrl}">${viewData.machineSerial}</a>
						</td>
						<td>${viewData.spbuCode}</td>
						<td>${viewData.spbuMachineIdentifier}</td>
						<td>${viewData.modelId}</td>
						<td>${viewData.partId}</td>
						<td>${viewData.machineModelPartIdentifier}</td>
						<td>${viewData.mttf}</td>
						<td>${viewData.mttfThreshold}</td>
						<td>
							<s:url value="update.htm" var="updateUrl">
								<s:param name="machineSerial" value="${viewData.machineSerial}"/>
								<s:param name="modelId" value="${viewData.modelId}"/>
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
							</s:url>
							<a href="${updateUrl}">Update</a>
							<s:url value="delete.htm" var="deleteUrl">
								<s:param name="machineSerial" value="${viewData.machineSerial}"/>
								<s:param name="modelId" value="${viewData.modelId}"/>
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
							</s:url>
							<a href="${deleteUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
