<%-- 
    Document   : input_totalizer
    Created on : Jul 2, 2013, 12:52:32 AM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sf:form commandName="formData" method="post" id="formTotalizer">
	<sf:errors/>
	<table>
		<tr>
			<td><sf:label path="spbuId"><s:message code="supervisor.input.totalizer.spbuId"/>:</sf:label></td>
			<td><sf:select path="spbuId" id="spbuId"/></td>
			<td><sf:errors path="spbuId"/></td>
		</tr>
		<tr>
			<td><sf:label path="machineSerial"><s:message code="supervisor.input.totalizer.machineIdentifier"/>:</sf:label></td>
			<td><sf:select path="machineSerial" id="machineSerial"/></td>
			<td><sf:errors path="machineSerial"/></td>
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

<script type="text/javascript">
	$("#btSubmit").click(function() {
		$("#formTotalizer").submit();
	});
	$("#spbuId").change(function() {
		loadMachineId();
	});
	$("#machineSerial").change(function() {
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
					$("#machineSerial").children().remove();

					$.each(data, function(index, spbuMachine) {
						console.log("spbuMachine: " + JSON.stringify(spbuMachine));
						if (index === 0) {
							$("#machineSerial").append(new Option(spbuMachine.machineIdentifier, spbuMachine.machineSerial, true, true));
						} else {
							$("#machineSerial").append(new Option(spbuMachine.machineIdentifier, spbuMachine.machineSerial, false, false));
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
		var url = "ajax/spbu/" + $("#spbuId").val() + "/machine/" + $("#machineSerial").val() + "/totalizer.json";
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
						newRow += totalizer.totalizerId + " (" + totalizer.alias + ")";
						newRow += "</td>";
						newRow += "<td>";
						newRow += "<input type='text' name='totalizerValues[" + index + "]' value='" + totalizer.counter + "'/>";
						newRow += "</td>";
						newRow += "<td>";
						newRow += "<input type='text' name='totalizerValuesCurrent[" + index + "]' value='" + totalizer.counter + "' readonly tabindex='-1'/>";
						newRow += "<input type='hidden' name='totalizerIds[" + index + "]' value='" + totalizer.totalizerId + "'/>";
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
