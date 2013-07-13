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
		<title>Admin - List Part Failure Mode Handlings</title>
	</head>
	<body>
		<a href="add.htm">Add Part Failure Mode Handling</a>
		<table>
			<thead>
				<tr>
					<td>Part ID</td>
					<td>Code</td>
					<td>Handling Code</td>
					<td>Name</td>
					<td>Description</td>
					<td>Manage</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viewData" items="${viewDatas}">
					<tr>
						<td>
							<s:url value="view.htm" var="viewUrl">
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="code" value="${viewData.code}"/>
								<s:param name="handlingCode" value="${viewData.handlingCode}"/>
							</s:url>
							<a href="${viewUrl}">${viewData.partId}</a>
						</td>
						<td>${viewData.code}</td>
						<td>${viewData.handlingCode}</td>
						<td>${viewData.name}</td>
						<td>${viewData.description}</td>
						<td>
							<s:url value="update.htm" var="updateUrl">
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="code" value="${viewData.code}"/>
								<s:param name="handlingCode" value="${viewData.handlingCode}"/>
							</s:url>
							<a href="${updateUrl}">Update</a>
							<s:url value="delete.htm" var="deleteUrl">
								<s:param name="partId" value="${viewData.partId}"/>
								<s:param name="code" value="${viewData.code}"/>
								<s:param name="handlingCode" value="${viewData.handlingCode}"/>
							</s:url>
							<a href="${deleteUrl}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>
