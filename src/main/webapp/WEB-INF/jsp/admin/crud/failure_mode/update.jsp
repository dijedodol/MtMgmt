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
		<title>Admin - Update Part Failure Mode</title>
	</head>
	<body>
		<a href="index.htm">List Part Failure Modes</a> | <a href="add.htm">Add Part Failure Mode</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="update.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="partId">Part ID</sf:label>
						<div>
						<sf:input path="partId" id="partId" readonly="true"/>
					</div>
				</section>
				<section>
					<sf:label path="code">Code</sf:label>
						<div>
						<sf:input path="code" readonly="true"/>
					</div>
				</section>
				<section>
					<sf:label path="name">Name</sf:label>
						<div>
						<sf:input path="name"/>
					</div>
				</section>
				<section>
					<sf:label path="description">Description</sf:label>
						<div>
						<sf:input path="description"/>
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
			var partCache = [];

			function loadPartCache() {
				var url = "../ajax/machine_parts.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$.each(data, function(index, part) {
							partCache.push({value: part.partId, label: part.partId + " : " + part.name});
						});

						$('#partId').autocomplete({
							minLength: 0,
							source: partCache
						});
					}
				});
			}

			$(document).ready(function() {
				loadPartCache();
			});
		</script>
	</body>
</html>
