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
		<title>Admin - Create Spbu Machine Part MTTF</title>
	</head>
	<body>
		<a href="index.htm">Spbu Machine Part MTTF List</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="add.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="machineSerial">Machine Serial</sf:label>
						<div>
						<sf:input path="machineSerial" id="machineSerial"/>
					</div>
				</section>
				<section>
					<sf:label path="modelId">Model ID</sf:label>
						<div>
						<sf:input path="modelId" id="modelId"/>
					</div>
				</section>
				<section>
					<sf:label path="partId">Part ID</sf:label>
						<div>
						<sf:input path="partId" id="partId"/>
					</div>
				</section>
				<section>
					<sf:label path="machineModelPartIdentifier">Machine's Part Identifier</sf:label>
						<div>
						<sf:input path="machineModelPartIdentifier" id="machineModelPartIdentifier"/>
					</div>
				</section>
				<section>
					<sf:label path="mttf">MTTF</sf:label>
						<div>
						<sf:input path="mttf" id="mttf"/>
					</div>
				</section>
				<section>
					<sf:label path="mttfThreshold">MTTF Threshold</sf:label>
						<div>
						<sf:input path="mttfThreshold" id="mttfThreshold"/>
					</div>
				</section>
        <section>
					<div>
						<button class="submit">Submit</button>
					</div>
        </section>
			</fieldset>
		</sf:form>
		<script>
		</script>
	</body>
</html>
