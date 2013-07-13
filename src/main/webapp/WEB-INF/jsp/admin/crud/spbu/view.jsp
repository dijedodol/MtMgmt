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
		<title>Admin - View Spbu</title>
	</head>
	<body>
		<a href="index.htm">Spbu List</a> | <a href="add.htm">Add Spbu</a>
		<table>
			<tr>
				<td>Spbu Code</td>
				<td>
					<s:url value="update.htm" var="updateUrl">
						<s:param name="spbuId" value="${viewData.id}"/>
					</s:url>
					<a href="${updateUrl}">${viewData.code}</a>
				</td>
			</tr>
			<tr>
				<td>Address</td>
				<td><a href="${updateUrl}">${viewData.address}</a></td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><a href="${updateUrl}">${viewData.phone}</a></td>
			</tr>
			<tr>
				<td>Supervisor</td>
				<td><a href="${updateUrl}">${viewData.supervisorName}</a></td>
			</tr>
			<tr>
				<td>Machines</td>
				<td>
					<table>
						<thead>
							<tr>
								<td>Machine Serial</td>
								<td>Model ID</td>
								<td>Machine Identifier</td>
							</tr>
						</thead>
						<c:forEach var="machineSerial" varStatus="machineSerialStatus" items="${viewData.machineSerials}">
							<tr>
								<td>${machineSerial}</td>
								<td>${viewData.modelIds[machineSerialStatus.index]}</td>
								<td>${viewData.machineIdentifiers[machineSerialStatus.index]}</td>
							</tr>
						</c:forEach>
					</table>
			</td>
		</tr>
	</table>
</body>
</html>
