<%-- 
    Document   : failure_history
    Created on : Jul 7, 2013, 1:33:08 PM
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
		<title>Supervisor - Machine Failure History</title>
		<link rel="stylesheet" href="../asset/css/default.css"/>
		<script type="text/javascript" src="../asset/js/jquery.js"></script>
	</head>
	<body>
		<div id="sidebar">
			<ul>
				<li><a href="home.htm"><s:message code="supervisor.menu.home"/></a></li>
				<li><a href="failure_history.htm"><s:message code="supervisor.menu.failureHistory"/></a></li>
				<li><a href="input_totalizer.htm"><s:message code="supervisor.menu.inputTotalizer"/></a></li>
			</ul>
		</div>
		<div id="content">
			<sf:form commandName="formData" method="get" id="formTotalizer">
				<sf:errors/>
				<table>
					<tr>
						<td><sf:label path="spbuId"><s:message code="supervisor.input.totalizer.spbuId"/>:</sf:label></td>
						<td><sf:select path="spbuId" id="spbuId"/></td>
						<td><sf:errors path="spbuId"/></td>
					</tr>
					<tr>
						<td><s:message code="supervisor.input.totalizer.totalizers"/>:</td>
						<td colspan="2">
							<table id="tblServiceReports">
								<thead>
									<tr>
										<td><s:message code="supervisor.history.failure.date"/></td>
										<td><s:message code="supervisor.history.failure.machine"/></td>
										<td><s:message code="supervisor.history.failure.machine.part"/></td>
										<td><s:message code="supervisor.history.failure.failure.mode"/></td>
										<td><s:message code="supervisor.history.failure.failure.handling"/></td>
										<td><s:message code="supervisor.history.failure.technician"/></td>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</sf:form>
		</div>
		<div id="clear"></div>

		<script type="text/javascript">
			$("#spbuId").change(function() {
				loadServiceReport();
			});
			loadSpbuId();

			function loadSpbuId() {
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
							loadServiceReport();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadServiceReport() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/service_report.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#tblServiceReports tbody").children().remove();

							$.each(data, function(index, serviceReport) {
								console.log("serviceReport " + JSON.stringify(serviceReport));
								var newRow = "";
								newRow += "<tr>";
								newRow += "<td>";
								newRow += serviceReport.date;
								newRow += "</td>";
								newRow += "<td>";
								newRow += serviceReport.machineIdentifier;
								newRow += "</td>";
								newRow += "<td>";
								newRow += serviceReport.machinePart;
								newRow += "</td>";
								newRow += "<td>";
								newRow += serviceReport.failureMode;
								newRow += "</td>";
								newRow += "<td>";
								newRow += serviceReport.failureModeHandling;
								newRow += "</td>";
								newRow += "<td>";
								newRow += serviceReport.technician;
								newRow += "</td>";
								newRow += "</tr>";
								
								console.log("serviceReport newRow: " + newRow);
								$('#tblServiceReports tbody').append(newRow);
							})
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
