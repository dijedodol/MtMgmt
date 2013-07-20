<%--
    Document   : home
    Created on : Jul 7, 2013, 6:19:52 PM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Technician - Home</title>
	</head>
	<body>
		<h1>Maintenance Schedule</h1>
		<table>
			<tr>
				<td>SPBU&nbsp;</td>
				<td><select id="spbuId"/></td>
			</tr>
			<tr>
				<td>Part&nbsp;</td>
				<td><select id="partId"/></td>
			</tr>
		</table>
		<table id="tblPredictions">
			<thead>
				<tr>
					<td>Spbu</td>
					<td>Machine</td>
					<td>Part</td>
					<td>Unit Type</td>
					<td>Unit Time to Failure</td>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<script type="text/javascript">
			var loadSpbuOk = false;
			var loadMachinePartOk = false;

			$("#spbuId").change(function() {
				loadPredictions();
			});
			$("#partId").change(function() {
				loadPredictions();
			});

			$(document).ready(function() {
				loadSpbu();
				loadMachinePartType();
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
							loadPredictions();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadMachinePartType() {
				var url = "ajax/machine_parts.json";
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
							loadPredictions();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadPredictions() {
				if (!loadSpbuOk || !loadMachinePartOk) {
					return;
				}

				var url = "ajax/maintenance_predictions/" + $("#spbuId").val() + "/" + $("#partId").val() + ".json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#tblPredictions tbody").children().remove();

							$.each(data, function(index, prediction) {
								console.log("serviceReport " + JSON.stringify(prediction));
								var newRow = "";
								newRow += "<tr>";
								newRow += "<td>";
								newRow += prediction.spbuCode;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.machineIdentifier;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.machineModelPartIdentifier;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.predictionType;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.ttf.toFixed(2) + " day(s)";
								newRow += "</td>";
								newRow += "</tr>";

								console.log("serviceReport newRow: " + newRow);
								$('#tblPredictions tbody').append(newRow);
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