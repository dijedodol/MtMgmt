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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Technician - Input Service Report</title>
		<link rel="stylesheet" href="../asset/css/default.css"/>
		<script type="text/javascript" src="../asset/js/jquery.js"></script>
	</head>
	<body>
		<div id="sidebar">
			<ul>
				<li><a href="home.htm"><s:message code="technician.menu.home"/></a></li>
				<li><a href="failure_history.htm"><s:message code="technician.menu.failureHistory"/></a></li>
				<li><a href="input_service_report.htm"><s:message code="technician.menu.serviceReport"/></a></li>
			</ul>
		</div>
		<div id="content">
			<sf:form commandName="formData" method="post" id="formTotalizer">
				<sf:errors/>
				<table>
					<tr>
						<td><sf:label path="spbuId"><s:message code="technician.input.serviceReport.spbuId"/>:</sf:label></td>
						<td><sf:select path="spbuId" id="spbuId"/></td>
						<td><sf:errors path="spbuId"/></td>
					</tr>
					<tr>
						<td><sf:label path="machineSerial"><s:message code="technician.input.serviceReport.machineIdentifier"/>:</sf:label></td>
						<td><sf:select path="machineSerial" id="machineSerial"/></td>
						<td><sf:errors path="machineSerial"/></td>
					</tr>
					<tr>
						<td><sf:label path="date"><s:message code="technician.input.serviceReport.date"/>:</sf:label></td>
						<td><sf:input path="date" id="date"/>#yyyy-mm-dd</td>
						<td><sf:errors path="date"/></td>
					</tr>
					<tr>
						<td><sf:label path="partId"><s:message code="technician.input.serviceReport.machinePartId"/>:</sf:label></td>
						<td><sf:select path="partId" id="partId"/></td>
						<td><sf:errors path="partId"/></td>
					</tr>
					<tr>
						<td><sf:label path="machineModelPartIdentifier"><s:message code="technician.input.serviceReport.machineModelPartIdentifier"/>:</sf:label></td>
						<td><sf:select path="machineModelPartIdentifier" id="machineModelPartIdentifier"/></td>
						<td><sf:errors path="machineModelPartIdentifier"/></td>
					</tr>
					<tr>
						<td><sf:label path="failureModeCode"><s:message code="technician.input.serviceReport.failureModeId"/>:</sf:label></td>
						<td><sf:select path="failureModeCode" id="failureModeCode"/></td>
						<td><sf:errors path="failureModeCode"/></td>
					</tr>
					<tr>
						<td><sf:label path="failureModeHandlingCode"><s:message code="technician.input.serviceReport.failureModeHandlingId"/>:</sf:label></td>
						<td><sf:select path="failureModeHandlingCode" id="failureModeHandlingCode"/></td>
						<td><sf:errors path="failureModeHandlingCode"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2"><input type="button" id="btSubmit" value="Submit"/></td>
					</tr>
				</table>
			</sf:form>
		</div>

		<script type="text/javascript">
			var machineModelParts = {};

			$("#btSubmit").click(function() {
				$("#formTotalizer").submit();
			});
			$("#spbuId").change(function() {
				loadMachine();
			});
			$("#machineIdentifier").change(function() {
				loadMachinePart();
			});
			$("#partId").change(function() {
				loadMachineModelPart();
				loadFailureMode();
			});
			$("#failureModeId").change(function() {
				loadFailureModeHandling();
			});
			loadSpbu();

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
								if (index === 0) {
									$("#spbuId").append(new Option(spbu.code, spbu.id, true, true));
								} else {
									$("#spbuId").append(new Option(spbu.code, spbu.id, false, false));
								}
							});
							loadMachine();
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
								if (index === 0) {
									$("#machineSerial").append(new Option(machine.machineIdentifier, machine.machineSerial, true, true));
								} else {
									$("#machineSerial").append(new Option(machine.machineIdentifier, machine.machineSerial, false, false));
								}
							});
							loadMachinePart();
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
								if (machineModelParts[machinePart.partId] == null) {
									machineModelParts[machinePart.partId] = [];
									if (index === 0) {
										$("#partId").append(new Option(machinePart.name, machinePart.partId, true, true));
									} else {
										$("#partId").append(new Option(machinePart.name, machinePart.partId, false, false));
									}
								}
								machineModelParts[machinePart.partId].push(machinePart.machineModelPartIdentifier);
							});
							loadMachineModelPart();
							loadFailureMode();
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
					if (index === 0) {
						$("#machineModelPartIdentifier").append(new Option(machineModelPartIdentifier, machineModelPartIdentifier, true, true));
					} else {
						$("#machineModelPartIdentifier").append(new Option(machineModelPartIdentifier, machineModelPartIdentifier, false, false));
					}
				});
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
								if (index === 0) {
									$("#failureModeCode").append(new Option(failureMode.name, failureMode.failureModeCode, true, true));
								} else {
									$("#failureModeCode").append(new Option(failureMode.name, failureMode.failureModeCode, false, false));
								}
							});
							loadFailureModeHandling();
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
								if (index === 0) {
									$("#failureModeHandlingCode").append(new Option(failureModeHandling.name, failureModeHandling.failureModeHandlingCode, true, true));
								} else {
									$("#failureModeHandlingCode").append(new Option(failureModeHandling.name, failureModeHandling.failureModeHandlingCode, false, false));
								}
							});
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}
		</script>
	</body>
</html>
