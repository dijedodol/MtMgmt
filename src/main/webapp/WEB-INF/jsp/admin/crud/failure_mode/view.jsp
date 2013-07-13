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
		<title>Admin - View Part Failure Mode</title>
	</head>
	<body>
		<a href="index.htm">List Part Failure Modes</a> | <a href="add.htm">Add Part Failure Mode</a>
		<table>
			<tr>
				<td>Part ID</td>
				<td>
					<s:url value="update.htm" var="updateUrl">
						<s:param name="partId" value="${viewData.partId}"/>
						<s:param name="code" value="${viewData.code}"/>
					</s:url>
					<a href="${updateUrl}">${viewData.partId}</a>
				</td>
			</tr>
			<tr>
				<td>Code</td>
				<td>
					<a href="${updateUrl}">${viewData.code}</a>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>
					<a href="${updateUrl}">${viewData.name}</a>
				</td>
			</tr>
			<tr>
				<td>Description</td>
				<td>
					<a href="${updateUrl}">${viewData.description}</a>
				</td>
			</tr>
		</table>
	</body>
</html>
