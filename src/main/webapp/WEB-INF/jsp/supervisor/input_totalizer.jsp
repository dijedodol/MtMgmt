<%-- 
    Document   : input_totalizer
    Created on : Jul 2, 2013, 12:52:32 AM
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
		<title>Supervisor - Input Machine Totalizer</title>
		<link rel="stylesheet" href="../asset/css/default.css"/>
		<script type="text/javascript" src="../asset/js/jquery.js"></script>
	</head>
	<body>
		<div id="sidebar">
			<ul>
				<li><a href="failure_history.htm"><s:message code="supervisor.home.list.failure"/></a></li>
				<li><a href="input_totalizer.htm"><s:message code="supervisor.home.input.totalizer"/></a></li>
			</ul>
		</div>
		<div id="content">
			<sf:form commandName="formData" method="post" id="formTotalizer">
				<sf:errors/>
				<table>
					<tr>
						<td><sf:label path="spbuId"><s:message code="supervisor.input.totalizer.spbuId"/>:</sf:label></td>
						<td><sf:select path="spbuId" id="spbuId"/></td>
						<td><sf:errors path="spbuId"/></td>
					</tr>
					<tr>
						<td><sf:label path="machineIdentifier"><s:message code="supervisor.input.totalizer.machineIdentifier"/>:</sf:label></td>
						<td><sf:select path="machineIdentifier" id="machineIdentifier"/></td>
						<td><sf:errors path="machineIdentifier"/></td>
					</tr>
					<tr>
						<td><s:message code="supervisor.input.totalizer.totalizers"/>:</td>
						<td colspan="2">
							<table id="tblTotalizer">
								<thead>
									<tr>
										<td><s:message code="supervisor.input.totalizer.totalizerName"/></td>
										<td><s:message code="supervisor.input.totalizer.totalizerNewValue"/></td>
										<td><s:message code="supervisor.input.totalizer.totalizerCurrentValue"/></td>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td colspan="2"><input type="button" id="btSubmit" value="Submit"/></td>
					</tr>
				</table>
			</sf:form>
		</div>
		<div id="clear"></div>

		<script type="text/javascript">
			$("#btSubmit").click(function() {
				$("#formTotalizer").submit();
			});
			$("#spbuId").change(function() {
				loadMachineId();
			});
			$("#machineIdentifier").change(function() {
				loadTotalizers();
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
							loadMachineId();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadMachineId() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#machineIdentifier").children().remove();

							$.each(data, function(index, spbuMachine) {
								console.log("spbuMachine: " + JSON.stringify(spbuMachine));
								if (index === 0) {
									$("#machineIdentifier").append(new Option(spbuMachine.machineIdentifier, spbuMachine.machineIdentifier, true, true));
								} else {
									$("#machineIdentifier").append(new Option(spbuMachine.machineIdentifier, spbuMachine.machineIdentifier, false, false));
								}
							});
							loadTotalizers();
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadTotalizers() {
				var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineIdentifier").val() + "/totalizer.json";
				console.log("url: " + url);
				$(document).ready(function() {
					$.ajax({
						url: url,
						dataType: "json",
						success: function(data, textStatus, jqXHR) {
							console.log(url + ": " + JSON.stringify(data));
							$("#tblTotalizer tbody").children().remove();

							$.each(data, function(index, totalizer) {
								console.log("totalizer " + JSON.stringify(totalizer));
								var newRow = "";
								newRow += "<tr>";
								newRow += "<td>";
								newRow += totalizer.machineTotalizerName;
								newRow += "</td>";
								newRow += "<td>";
								newRow += "<input type='text' name='totalizerValues[" + index + "]' value='" + totalizer.counter + "'/>";
								newRow += "</td>";
								newRow += "<td>";
								newRow += "<input type='text' name='totalizerValuesCurrent[" + index + "]' value='" + totalizer.counter + "' readonly tabindex='-1'/>";
								newRow += "<input type='hidden' name='totalizerIds[" + index + "]' value='" + totalizer.machineTotalizerId + "'/>";
								newRow += "</td>";
								newRow += "</tr>";

								console.log("totalizer newRow: " + newRow);
								$('#tblTotalizer tbody').append(newRow);
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
