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
				<li><a href="failure_history.htm"><s:message code="technician.home.list.failure"/></a></li>
				<li><a href="input_service_report.htm"><s:message code="technician.home.input.service_report"/></a></li>
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
						<td><sf:label path="machineIdentifier"><s:message code="technician.input.serviceReport.machineIdentifier"/>:</sf:label></td>
						<td><sf:select path="machineIdentifier" id="machineIdentifier"/></td>
						<td><sf:errors path="machineIdentifier"/></td>
					</tr>
					<tr>
						<td><sf:label path="date"><s:message code="technician.input.serviceReport.date"/>:</sf:label></td>
						<td><sf:input path="date" id="date"/>#yyyy-mm-dd</td>
						<td><sf:errors path="date"/></td>
					</tr>
					<tr>
						<td><sf:label path="machinePartId"><s:message code="technician.input.serviceReport.machinePartId"/>:</sf:label></td>
						<td><sf:select path="machinePartId" id="machinePartId"/></td>
						<td><sf:errors path="machinePartId"/></td>
					</tr>
					<tr>
						<td><sf:label path="failureModeId"><s:message code="technician.input.serviceReport.failureModeId"/>:</sf:label></td>
						<td><sf:select path="failureModeId" id="failureModeId"/></td>
						<td><sf:errors path="failureModeId"/></td>
					</tr>
					<tr>
						<td><sf:label path="failureModeHandlingId"><s:message code="technician.input.serviceReport.failureModeHandlingId"/>:</sf:label></td>
						<td><sf:select path="failureModeHandlingId" id="failureModeHandlingId"/></td>
						<td><sf:errors path="failureModeHandlingId"/></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2"><input type="button" id="btSubmit" value="Submit"/></td>
					</tr>
				</table>
			</sf:form>
		</div>

		<script type="text/javascript">
			$("#btSubmit").click(function() {
				$("#formTotalizer").submit();
			});
			$("#spbuId").change(function() {
				loadMachine();
			});
			$("#machineIdentifier").change(function() {
				loadMachinePart();
			});
			$("#machinePartId").change(function() {
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
							$("#machineIdentifier").children().remove();

							$.each(data, function(index, machine) {
								console.log("spbuMachine: " + JSON.stringify(machine));
								if (index === 0) {
									$("#machineIdentifier").append(new Option(machine.machineIdentifier, machine.machineIdentifier, true, true));
								} else {
									$("#machineIdentifier").append(new Option(machine.machineIdentifier, machine.machineIdentifier, false, false));
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
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineIdentifier").val() + "/part.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#machinePartId").children().remove();

							$.each(data, function(index, machinePart) {
								console.log("machinePartId " + JSON.stringify(machinePart));
								if (index === 0) {
									$("#machinePartId").append(new Option(machinePart.name, machinePart.id, true, true));
								} else {
									$("#machinePartId").append(new Option(machinePart.name, machinePart.id, false, false));
								}
							});
							loadFailureMode();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadFailureMode() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineIdentifier").val() + "/part/" + $("#machinePartId").val() + "/failure_mode.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#failureModeId").children().remove();

							$.each(data, function(index, failureMode) {
								console.log("failureMode " + JSON.stringify(failureMode));
								if (index === 0) {
									$("#failureModeId").append(new Option(failureMode.name, failureMode.id, true, true));
								} else {
									$("#failureModeId").append(new Option(failureMode.name, failureMode.id, false, false));
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
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineIdentifier").val() + "/part/" + $("#machinePartId").val() + "/failure_mode/" + $("#failureModeId").val() + "/handling.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#failureModeHandlingId").children().remove();

							$.each(data, function(index, failureModeHandling) {
								console.log("failureModeHandling " + JSON.stringify(failureModeHandling));
								if (index === 0) {
									$("#failureModeHandlingId").append(new Option(failureModeHandling.name, failureModeHandling.id, true, true));
								} else {
									$("#failureModeHandlingId").append(new Option(failureModeHandling.name, failureModeHandling.id, false, false));
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
