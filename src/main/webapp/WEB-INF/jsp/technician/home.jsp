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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Technician - Home</title>
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
			<h1>Maintenance Predictions</h1>
			<table>
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
		</div>
		<script type="text/javascript">
			loadPredictions();
			
			function loadPredictions() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/service_report.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#tblServiceReports tbody").children().remove();

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
								newRow += prediction.partId;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.predictionType;
								newRow += "</td>";
								newRow += "<td>";
								newRow += prediction.ttf;
								newRow += "</td>";
								newRow += "</tr>";

								console.log("serviceReport newRow: " + newRow);
								$('#tblServiceReports tbody').append(newRow);
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
