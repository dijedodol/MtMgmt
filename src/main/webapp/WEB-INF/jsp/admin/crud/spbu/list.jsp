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
		<title>Admin - Spbu List</title>
	</head>
	<body>
		<a href="add.htm">Add Spbu</a>
		<table>
			<thead>
				<tr>
					<td>Spbu Code</td>
					<td>Address</td>
					<td>Phone</td>
					<td>Supervisor</td>
					<td>Machine Count</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viewData" items="${viewDatas}">
					<tr>
						<td>
							<s:url value="view.htm" var="viewUrl">
								<s:param name="spbuId" value="${viewData.id}"/>
							</s:url>
							<a href="${viewUrl}">${viewData.code}</a>
						</td>
						<td>${viewData.address}</td>
						<td>${viewData.phone}</td>
						<td>${viewData.supervisorName}</td>
						<td>${viewData.machineSerials.size()}</td>
						<td>
							<s:url value="update.htm" var="updateUrl">
								<s:param name="spbuId" value="${viewData.id}"/>
							</s:url>
							<a href="${updateUrl}">Update</a>
							<s:url value="delete.htm" var="deleteUrl">
								<s:param name="spbuId" value="${viewData.id}"/>
							</s:url>
							<a href="${deleteUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
