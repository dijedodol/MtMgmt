<%-- 
    Document   : add
    Created on : Jul 12, 2013, 4:02:17 PM
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
		<title>Admin - Update Model Part Type</title>
	</head>
	<body>
		<a href="index.htm">List Machine Part Types</a> | <a href="add.htm">Add Machine Part Type</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="update.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="partId">Part ID</sf:label>
						<div>
						<sf:input path="partId" readonly="true"/>
					</div>
				</section>
				<section>
					<sf:label path="name">Name</sf:label>
						<div>
						<sf:input path="name"/>
					</div>
				</section>
				<section>
					<sf:label path="defaultMttf">Default MTTF</sf:label>
						<div>
						<sf:input path="defaultMttf"/>
					</div>
				</section>
				<section>
					<sf:label path="defaultMttfThreshold">Default MTTF Threshold</sf:label>
						<div>
						<sf:input path="defaultMttfThreshold"/>
					</div>
				</section>
        <section>
					<div>
						<button class="submit">Submit</button>
					</div>
        </section>
			</fieldset>
		</sf:form>
	</body>
</html>
