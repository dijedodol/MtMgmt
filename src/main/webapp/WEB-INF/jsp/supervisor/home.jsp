<%-- 
    Document   : home
    Created on : Jul 1, 2013, 1:32:56 AM
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
		<title>Supervisor - Home</title>
		<link rel="stylesheet" href="../asset/css/default.css">
	</head>
	<body>
		<div id="sidebar">
				<ul>
					<li><a href="list_failure.htm"><s:message code="supervisor.home.list.failure"/></a></li>
					<li><a href="input_totalizer.htm"><s:message code="supervisor.home.input.totalizer"/></a></li>
				</ul>
		</div>
		<div id="content"></div>
	</body>
</html>
