<%-- 
    Document   : input_service_report
    Created on : Jul 7, 2013, 6:20:33 PM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Technician - Input Service Report</title>
	</head>
	<body>
		<sf:form commandName="formData" data-ajax="false" method="post" id="formTotalizer">
			<sf:errors/>
			<fieldset>
				<section>
					<sf:label path="spbuId"><s:message code="technician.input.serviceReport.spbuId"/>:</sf:label>
						<div>
						<sf:select path="spbuId" id="spbuId"/>
						<sf:errors path="spbuId"/>
					</div>
				</section>
				<section>
					<sf:label path="machineSerial"><s:message code="technician.input.serviceReport.machineIdentifier"/>:</sf:label>
						<div>
						<sf:select path="machineSerial" id="machineSerial"/>
						<sf:errors path="machineSerial"/>
					</div>
				</section>
				<section>
					<sf:label path="date"><s:message code="technician.input.serviceReport.date"/>:</sf:label>
						<div>
						<sf:input path="date" id="date" class="g2"/>#yyyy-mm-dd
						<sf:errors path="date"/>
					</div>
				</section>
				<section>
					<sf:label path="partId"><s:message code="technician.input.serviceReport.machinePartId"/>:</sf:label>
						<div>
						<sf:select path="partId" id="partId"/>
						<sf:errors path="partId"/>
					</div>
				</section>
				<section>
					<sf:label path="machineModelPartIdentifier"><s:message code="technician.input.serviceReport.machineModelPartIdentifier"/>:</sf:label>
						<div>
						<sf:select path="machineModelPartIdentifier" id="machineModelPartIdentifier"/>
						<sf:errors path="machineModelPartIdentifier"/>
					</div>
				</section>
				<section>
					<sf:label path="failureModeCode"><s:message code="technician.input.serviceReport.failureModeId"/>:</sf:label>
						<div>
						<sf:select path="failureModeCode" id="failureModeCode"/>
						<sf:errors path="failureModeCode"/>
					</div>
				</section>
				<section>
					<sf:label path="failureModeHandlingCode"><s:message code="technician.input.serviceReport.failureModeHandlingId"/>:</sf:label>
						<div>
						<sf:select path="failureModeHandlingCode" id="failureModeHandlingCode"/>
						<sf:errors path="failureModeHandlingCode"/>
					</div>
				</section>
				<section>
					<div>
						<button class="submit" id="btSubmit">Submit</button>
					</div>
				</section>
			</fieldset>
		</sf:form>

		<script type="text/javascript">
			var loadSpbuOk = false;
			var loadMachineOk = false;
			var loadMachinePartOk = false;
			var loadMachineModelPartOk = false;
			var loadFailureModeOk = false;
			var loadFailureModeHandlingOk = false;
			var machineModelParts = {};

			$("#spbuId").change(function() {
				loadMachine();
			});
			$("#machineSerial").change(function() {
				loadMachinePart();
			});
			$("#partId").change(function() {
				loadMachineModelPart();
				loadFailureMode();
			});
			$("#failureModeCode").change(function() {
				loadFailureModeHandling();
			});

			$(document).ready(function() {
				loadSpbu();
			});

			function loadSpbu() {
				$(document).ready(function() {
					$.ajax({
						url: "ajax/spbu.json",
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log("ajax/spbu.json: " + JSON.stringify(data));
							$("#spbuId").children().remove();

							$.each(data, function(index, spbu) {
								console.log("spbu: " + JSON.stringify(spbu));
								$("#spbuId").append(new Option(spbu.code, spbu.id, false, false));
							});

							if (data.length > 0) {
								loadSpbuOk = true;
							} else {
								$("#spbuId").append(new Option("N/A", "-", false, false));
								loadSpbuOk = false;
							}
							$("#spbuId option:first").attr('selected', 'selected').change();
							enableSubmitButton();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadMachine() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#machineSerial").children().remove();

							$.each(data, function(index, machine) {
								console.log("spbuMachine: " + JSON.stringify(machine));
								$("#machineSerial").append(new Option(machine.machineIdentifier, machine.machineSerial, false, false));
							});

							if (data.length > 0) {
								loadMachineOk = true;
							} else {
								$("#machineSerial").append(new Option("N/A", "-", false, false));
								loadMachineOk = false;
							}
							$("#machineSerial option:first").attr('selected', 'selected').change();
							enableSubmitButton();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadMachinePart() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineSerial").val() + "/part.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							machineModelParts = {};
							$("#partId").children().remove();

							$.each(data, function(index, machinePart) {
								if (!machineModelParts[machinePart.partId]) {
									machineModelParts[machinePart.partId] = [];
									$("#partId").append(new Option(machinePart.name, machinePart.partId, false, false));
								}
								machineModelParts[machinePart.partId].push(machinePart.machineModelPartIdentifier);
							});

							if (data.length > 0) {
								loadMachinePartOk = true;
							} else {
								$("#partId").append(new Option("N/A", "-", false, false));
								loadMachinePartOk = false;
							}
							$("#partId option:first").attr('selected', 'selected').change();
							enableSubmitButton();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadMachineModelPart() {
				$("#machineModelPartIdentifier").children().remove();
				$.each(machineModelParts[$("#partId").val()], function(index, machineModelPartIdentifier) {
					$("#machineModelPartIdentifier").append(new Option(machineModelPartIdentifier, machineModelPartIdentifier, false, false));
				});

				if (machineModelParts[$("#partId").val()].length > 0) {
					loadMachineModelPartOk = true;
				} else {
					$("#machineModelPartIdentifier").append(new Option("N/A", "-", false, false));
					loadMachineModelPartOk = false;
				}
				$("#machineModelPartIdentifier option:first").attr('selected', 'selected').change();
				enableSubmitButton();
			}

			function loadFailureMode() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineSerial").val() + "/part/" + $("#partId").val() + "/" + $("#machineModelPartIdentifier").val() + "/failure_mode.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#failureModeCode").children().remove();

							$.each(data, function(index, failureMode) {
								$("#failureModeCode").append(new Option(failureMode.name, failureMode.failureModeCode, false, false));
							});

							if (data.length > 0) {
								loadFailureModeOk = true;
							} else {
								$("#failureModeCode").append(new Option("N/A", "-", false, false));
								loadFailureModeOk = false;
							}
							$("#failureModeCode option:first").attr('selected', 'selected').change();
							enableSubmitButton();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadFailureModeHandling() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineSerial").val() + "/part/" + $("#partId").val() + "/" + $("#machineModelPartIdentifier").val() + "/failure_mode/" + $("#failureModeCode").val() + "/handling.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#failureModeHandlingCode").children().remove();

							$.each(data, function(index, failureModeHandling) {
								$("#failureModeHandlingCode").append(new Option(failureModeHandling.name, failureModeHandling.failureModeHandlingCode, false, false));
							});

							if (data.length > 0) {
								loadFailureModeHandlingOk = true;
							} else {
								$("#failureModeHandlingCode").append(new Option("N/A", "-", false, false));
								loadFailureModeHandlingOk = false;
							}
							$("#failureModeHandlingCode option:first").attr('selected', 'selected').change();
							enableSubmitButton();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function enableSubmitButton() {
				if (loadFailureModeHandlingOk && loadFailureModeOk && loadMachineModelPartOk && loadMachineOk && loadMachinePartOk && loadSpbuOk) {
					console.log("enabling submit button");
					$("#btSubmit").prop("disabled", false);
				} else {
					console.log("disabling submit button");
					$("#btSubmit").prop("disabled", true);
				}
			}
		</script>
	</body>
</html>