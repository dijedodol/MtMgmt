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
		<h1>Maintenance Predictions</h1>
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
			loadPredictions();

			function loadPredictions() {
				var url = "ajax/maintenance_predictions.json";
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