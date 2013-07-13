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
		<title>Admin - Create Machine Model Part Totalizer</title>
	</head>
	<body>
		<a href="index.htm">List Machine Model Part Totalizers</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="add.htm" method="post">
			<fieldset>
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
						<sf:input path="machineModelPartIdentifier" id="machineModelPartIdentifier" readonly="true"/>
					</div>
				</section>
				<section>
					<sf:label path="totalizerIds">Totalizers</sf:label>
						<div>
							<pre>*Hold Ctrl Key to Select Multiple Items</pre>
						<sf:select path="totalizerIds" items="${totalizerIdOptions}" multiple="true"/>
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
			var modelCache = [];
			var partCache = [];

			function modelIdAutoComplete() {
				var url = "../ajax/machine_models.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						modelCache = [];
						$.each(data, function(index, machineModel) {
							console.log("machineModel: " + JSON.stringify(machineModel));
							modelCache.push({value: machineModel.modelId, label: machineModel.modelId + " : " + machineModel.name});
						});

						$('#modelId').autocomplete({
							minLength: 0,
							source: modelCache,
							select: function(event, ui) {
								$('#modelId').val(ui.item.value);
								loadTotalizerIds();
								partIdAutoComplete();
							}
						});
					}
				});
			}

			function partIdAutoComplete() {
				var url = "../ajax/machine_model/" + $("#modelId").val() + "/machine_model_parts.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						partCache = [];
						$.each(data, function(index, part) {
							console.log("part: " + JSON.stringify(part));
							partCache.push({value: part.partId, secondValue: part.machineModelPartIdentifier, label: part.partId + " : " + part.machineModelPartIdentifier});
						});

						$('#partId').autocomplete({
							minLength: 0,
							source: partCache,
							select: function(event, ui) {
								$('#partId').val(ui.item.value);
								$('#machineModelPartIdentifier').val(ui.item.secondValue);
							}
						});
					}
				});
			}

			function loadTotalizerIds() {
				var url = "../ajax/machine_model/" + $("#modelId").val() + "/machine_model_totalizers.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$("#totalizerIds").children().remove();

						//var totalizerOptions = {};
						$.each(data, function(index, totalizer) {
							console.log("totalizer: " + JSON.stringify(totalizer));
							$("#totalizerIds").append(new Option(totalizer.totalizerId, totalizer.totalizerId, false, false));
						});
					}
				});
			}

			$(document).ready(function() {
				modelIdAutoComplete("modelId", "partId", "machineModelPartIdentifier");
			});
		</script>
	</body>
</html>
