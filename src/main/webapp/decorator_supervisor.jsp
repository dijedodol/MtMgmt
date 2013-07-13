<%--
    Document   : decorator
    Created on : 11 Jul 13, 14:58:06
    Author     : gde.satrigraha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="WEB-INF/jspf/common_head.jsp"/>
    <title><decorator:title default="SiteMesh Integration"/></title>
    <decorator:head/>
	</head>
	<body>
		<div id="pageoptions"></div>

    <header></header>

    <nav>
			<ul id="nav">
				<li><a href="home.htm"><span><s:message code="supervisor.menu.home"/></span></a></li>
				<li><a href="failure_history.htm"><span><s:message code="supervisor.menu.failureHistory"/></span></a></li>
				<li><a href="input_totalizer.htm"><span><s:message code="supervisor.menu.inputTotalizer"/></span></a></li>
			</ul>
		</nav>

    <section id="content">
			<decorator:body/>
		</section>

    <footer></footer>
	</body>
</html>
