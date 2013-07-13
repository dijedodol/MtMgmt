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
		<title>Admin - View Machine Model</title>
	</head>
	<body>
		<a href="index.htm">List Machine Models</a> | <a href="add.htm">Add Machine Model</a>
		<table>
			<tr>
				<td>Model ID</td>
				<td>
					<s:url value="update.htm" var="updateUrl">
						<s:param name="modelId" value="${viewData.modelId}"/>
					</s:url>
					<a href="${updateUrl}">${viewData.modelId}</a>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td><a href="${updateUrl}">${viewData.name}</a></td>
			</tr>
			<tr>
				<td>Totalizers</td>
				<td>
					<c:forEach var="totalizerId" items="${viewData.totalizerIds}">
						${totalizerId}<br/>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>Machine Parts</td>
				<td>
					<table>
						<thead>
							<tr>
								<td>Part Type</td>
								<td>Machine Model Part Identifier</td>
							</tr>
						</thead>
						<c:forEach var="partId" varStatus="partIdStatus" items="${viewData.partIds}">
							<tr>
								<td>${partId}</td>
								<td>${viewData.partIdentifiers[partIdStatus.index]}</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
