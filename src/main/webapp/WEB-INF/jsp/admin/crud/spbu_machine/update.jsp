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
		<title>Admin - Update Spbu Machine</title>
	</head>
	<body>
		<a href="index.htm">List Spbu Machines</a> | <a href="add.htm">Add Spbu Machine</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="update.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="machineSerial">Machine Serial</sf:label>
						<div>
						<sf:input path="machineSerial" readonly="true"/>
					</div>
				</section>
				<section>
					<sf:label path="spbuCode">Spbu Code</sf:label>
						<div>
						<sf:input path="spbuCode" id="spbuCode"/>
						<sf:hidden path="spbuId" id="spbuId"/>
					</div>
				</section>
				<section>
					<sf:label path="modelId">Model ID</sf:label>
						<div>
						<sf:input path="modelId" id="modelId"/>
					</div>
				</section>
				<section>
					<sf:label path="machineIdentifier">Machine Identifier</sf:label>
						<div>
						<sf:input path="machineIdentifier"/>
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
			var spbuCache = [];
			var machineModelCache = [];

			function loadSpbuCache() {
				var url = "../ajax/spbus.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$.each(data, function(index, spbu) {
							spbuCache.push({value: spbu.id, label: spbu.code + " : " + spbu.address});
						});

						$('#spbuCode').autocomplete({
							minLength: 0,
							source: spbuCache,
							focus: function(event, ui) {
								$("#spbuCode").val(ui.item.label);
								return false;
							},
							select: function(event, ui) {
								$('#spbuId').val(ui.item.value);
								$('#spbuCode').val(ui.item.label);
								return false;
							}
						});
					}
				});
			}

			function loadMachineModelCache() {
				var url = "../ajax/machine_models.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$.each(data, function(index, machineModel) {
							machineModelCache.push({value: machineModel.modelId, label: machineModel.modelId + " : " + machineModel.name});
						});

						$('#modelId').autocomplete({
							minLength: 0,
							source: machineModelCache
						});
					}
				});
			}

			$(document).ready(function() {
				loadSpbuCache();
				loadMachineModelCache();
			});
		</script>
	</body>
</html>
