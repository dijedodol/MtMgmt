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
				<li><a href="<%=application.getContextPath()%>/admin/home.htm"><span><s:message code="admin.menu.home"/></span></a></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/machine_model/home.htm"><span>Machine Models</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/machine_part_type/home.htm"><span>Machine Part Types</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/machine_model_part_totalizer/home.htm"><span>Machine Model<br/>Part'sTotalizers</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/spbu/home.htm"><span>Spbu</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/spbu_machine/home.htm"><span>Spbu Machines</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/spbu_machine_part_mttf/home.htm"><span>Spbu Machine <br/>Part'sMTTF</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/failure_mode/home.htm"><span>Failure Modes</span></li>
				<li><a href="<%=application.getContextPath()%>/admin/crud/failure_mode_handling/home.htm"><span>Failure Mode Handlings</span></li>
			</ul>
		</nav>

    <section id="content">
			<decorator:body/>
		</section>

    <footer></footer>
	</body>
</html>
