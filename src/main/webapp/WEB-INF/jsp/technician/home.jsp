<%-- 
    Document   : home
    Created on : Jul 7, 2013, 6:19:52 PM
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
		<title>Technician - Home</title>
		<link rel="stylesheet" href="../asset/css/default.css"/>
	</head>
	<body>
		<div id="sidebar">
				<ul>
					<li><a href="failure_history.htm"><s:message code="technician.home.list.failure"/></a></li>
					<li><a href="input_service_report.htm"><s:message code="technician.home.input.service_report"/></a></li>
				</ul>
		</div>
		<div id="content"></div>
	</body>
</html>
