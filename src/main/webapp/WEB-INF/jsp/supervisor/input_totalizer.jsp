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
		<title>Supervisor - Input Totalizer</title>
	</head>
	<body>
		<sf:form commandName="formData" data-ajax="false" method="post" id="formTotalizer">
			<sf:errors/>
			<fieldset>
				<section>
					<sf:label path="spbuId"><s:message code="supervisor.input.totalizer.spbuId"/></sf:label>
						<div>
						<sf:select path="spbuId" id="spbuId" items="${spbuOptions}"/>
						<sf:errors path="machineSerial"/>
					</div>
				</section>
				<section>
					<sf:label path="machineSerial"><s:message code="supervisor.input.totalizer.machineIdentifier"/></sf:label>
						<div>
						<sf:select path="machineSerial" id="machineSerial" items="${machineSerialOptions}"/>
						<sf:errors path="machineSerial"/>
					</div>
				</section>
				<section>
					<s:message code="supervisor.input.totalizer.totalizers"/>
					<div>
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
					</div>
				</section>
				<section>
					<div>
						<button class="submit">Submit</button>
					</div>
				</section>
			</fieldset>
		</sf:form>

		<script type="text/javascript">
			$("#spbuId").change(function() {
				loadMachineId();
			});
			$("#machineSerial").change(function() {
				loadTotalizers();
			});

			$(document).ready(function() {
				loadTotalizers();
			});

			function loadMachineId() {
				if ($("#spbuId").val() === null) {
					return;
				}
				
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
								$("#machineSerial").append(new Option(spbuMachine.machineIdentifier, spbuMachine.machineSerial, false, false));
							});

							if (data.length > 0) {
								$("#machineSerial option:first").attr('selected', 'selected').change();
							}
						},
						error: function(jqXHR, textStatus, errorThrown) {
							alert("Ajax data load fail!");
						}
					});
				});
			}

			function loadTotalizers() {
				if ($("#machineSerial").val() === null) {
					return;
				}

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
	</body>
</html>
