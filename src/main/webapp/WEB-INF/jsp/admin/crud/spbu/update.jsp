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
		<title>Admin - Update Spbu</title>
	</head>
	<body>
		<a href="index.htm">Spbu List</a> | <a href="add.htm">Add Spbu</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="update.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="code">Spbu Code</sf:label>
						<div>
						<sf:input path="code"/>
						<sf:hidden path="id"/>
					</div>
				</section>
				<section>
					<sf:label path="Address">Address</sf:label>
						<div>
						<sf:input path="address"/>
					</div>
				</section>
				<section>
					<sf:label path="phone">Phone</sf:label>
						<div>
						<sf:input path="phone"/>
					</div>
				</section>
				<section>
					<sf:label path="supervisorName">Supervisor</sf:label>
						<div>
						<sf:input path="supervisorName" id="supervisorName"/>
						<sf:hidden path="supervisorId" id="supervisorId"/>
					</div>
				</section>
				<section>
					<label for="machineCount">Machine Count</label>
					<div>
						<input type="number" name="machineCount" id="machineCount" min="0" class="g1 integer" value="${formData.machineSerials.size()}" readonly="true"/><br/>
						<table id="tblMachines">
							<thead>
								<tr>
									<td>Machine Serial</td>
									<td>Model ID</td>
									<td>Machine Identifier</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${formData.machineSerials}" var="machineSerial" varStatus="machineSerialStatus">
									<tr>
										<td><input name="machineSerials" type="text" value="${machineSerial}" id="machineSerial_${machineSerialStatus.index}" readonly="true"/></td>
										<td><input name="modelIds" type="text" value="${formData.modelIds[machineSerialStatus.index]}" id="modelId_${machineSerialStatus.index}" readonly="true"/></td>
										<td><input name="machineIdentifiers" type="text" value="${formData.machineIdentifiers[machineSerialStatus.index]}" id="machineIdentifier_${machineSerialStatus.index}" readonly="true"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
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
			var currentMachineCount = 0;
			var supervisorCache = [];
			var machineCache = [];

			function loadSupervisor() {
				var url = "../ajax/supervisors.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$.each(data, function(index, supervisor) {
							supervisorCache.push({value: supervisor.id, label: supervisor.fullName});
						});

						$('#supervisorName').autocomplete({
							minLength: 0,
							source: supervisorCache,
							focus: function(event, ui) {
								$("#supervisorName").val(ui.item.label);
								return false;
							},
							select: function(event, ui) {
								$('#supervisorId').val(ui.item.value);
								$('#supervisorName').val(ui.item.label);
								return false;
							}
						});
					}
				});
			}

			function loadMachineModel() {
				var url = "../ajax/machine_models.json";
				$.ajax({
					url: url,
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log(url + ": " + JSON.stringify(data));
						$.each(data, function(index, machine) {
							machineCache.push({value: machine.modelId, label: machine.modelId + " : " + machine.name});
						});

						var machineCountStr = $("#machineCount").val();
						if (isFinite(machineCountStr)) {
							currentMachineCount = parseInt(machineCountStr);
						}

						for (var i = 0; i < currentMachineCount; i++) {
							var modelIdId = "modelId_" + i;
							machineModelAutoComplete(modelIdId);
						}
					}
				});
			}

			function machineModelAutoComplete(modelIdId) {
				modelIdId = "#" + modelIdId;
				$(modelIdId).autocomplete({
					minLength: 0,
					source: machineCache
				});
			}

			function setupMachineCountListener() {
				$("#machineCount").change(function() {
					var machineCountStr = $("#machineCount").val();
					if (isFinite(machineCountStr)) {
						var machineCount = parseInt(machineCountStr);
						while (currentMachineCount > machineCount) {
							$('#tblMachines tbody').children().last().remove();
							currentMachineCount--;
						}
						for (var i = currentMachineCount; i < machineCount; i++) {
							var machineSerialId = "machineSerial_" + i;
							var modelIdId = "modelId_" + i;
							var machineIdentifierId = "machineIdentifier_" + i;
							var newRow = "";

							newRow += '<tr>';
							newRow += '<td>' + '<input name="machineSerials" type="text" value="" id="' + machineSerialId + '" readonly="true"/>' + '</td>';
							newRow += '<td>' + '<input name="modelIds" type="text" value="" id="' + modelIdId + '" readonly="true"/>' + '</td>';
							newRow += '<td>' + '<input name="machineIdentifiers" type="text" value="" id="' + machineIdentifierId + '" readonly="true"/>' + '</td>';
							newRow += '</tr>';

							$('#tblMachines tbody').append(newRow);
							machineModelAutoComplete(modelIdId);
						}
						currentMachineCount = machineCount;
					}
				});
			}

			$(document).ready(function() {
				loadMachineModel();
				loadSupervisor();
				setupMachineCountListener();
			});
		</script>
	</body>
</html>
