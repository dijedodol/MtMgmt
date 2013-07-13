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
		<title>Admin - View Spbu Machine Part MTTF</title>
	</head>
	<body>
		<a href="index.htm">Spbu Machine Part MTTF List</a> | <a href="add.htm">Add Spbu Machine Part MTTF</a>
		<table>
			<tr>
				<td>Machine Serial</td>
				<td>
					<s:url value="update.htm" var="updateUrl">
						<s:param name="machineSerial" value="${viewData.machineSerial}"/>
						<s:param name="modelId" value="${viewData.modelId}"/>
						<s:param name="partId" value="${viewData.partId}"/>
						<s:param name="machineModelPartIdentifier" value="${viewData.machineModelPartIdentifier}"/>
					</s:url>
					<a href="${updateUrl}">${viewData.machineSerial}</a>
				</td>
			</tr>
			<tr>
				<td>Spbu Code</td>
				<td>
					<a href="${updateUrl}">${viewData.spbuCode}</a>
				</td>
			</tr>
			<tr>
				<td>Spbu's Machine Identifier</td>
				<td>
					<a href="${updateUrl}">${viewData.spbuMachineIdentifier}</a>
				</td>
			</tr>
			<tr>
				<td>Model ID</td>
				<td>
					<a href="${updateUrl}">${viewData.modelId}</a>
				</td>
			</tr>
			<tr>
				<td>Part ID</td>
				<td>
					<a href="${updateUrl}">${viewData.partId}</a>
				</td>
			</tr>
			<tr>
				<td>Machine's Part Identifier</td>
				<td>
					<a href="${updateUrl}">${viewData.machineModelPartIdentifier}</a>
				</td>
			</tr>
			<tr>
				<td>MTTF</td>
				<td>
					<a href="${updateUrl}">${viewData.mttf}</a>
				</td>
			</tr>
			<tr>
				<td>MTTF Threshold</td>
				<td>
					<a href="${updateUrl}">${viewData.mttfThreshold}</a>
				</td>
			</tr>
		</table>
	</body>
</html>
