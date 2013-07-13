<%-- 
    Document   : add
    Created on : Jul 12, 2013, 4:02:17 PM
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
		<title>Admin - Create Machine Models</title>
	</head>
	<body>
		<a href="index.htm">List Machine Models</a>
		<sf:errors path="*"/>
		<sf:form commandName="formData" data-ajax="false" action="add.htm" method="post">
			<fieldset>
				<section>
					<sf:label path="modelId">Model ID</sf:label>
						<div>
						<sf:input path="modelId"/>
					</div>
				</section>
				<section>
					<sf:label path="name">Name</sf:label>
						<div>
						<sf:input path="name"/>
					</div>
				</section>
				<section>
					<label for="totalizerCount">Totalizer Count</label>
					<div>
						<input type="number" name="totalizerCount" id="totalizerCount" min="1" class="g1 integer" value="${formData.totalizerIds.size()}"/><br/>
						<table id="tblTotalizers">
							<thead>
								<tr>
									<td>Totalizer Index</td>
									<td>Totalizer Name</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${formData.totalizerIds}" var="totalizerId" varStatus="totalizerIdStatus">
									<tr>
										<td>${totalizerIdStatus.index}</td>
										<td><input name="totalizerIds" type="text" value="${totalizerId}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</section>
				<section>
					<label for="partCount">Part Count</label>
					<div>
						<input type="number" name="partCount" id="partCount" min="0" class="g1 integer" value="${formData.partIds.size()}"/><br/>
						<table id="tblParts">
							<thead>
								<tr>
									<td>Part Index</td>
									<td>Part Type</td>
									<td>Machine Model Part Identifier</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${formData.partIds}" var="partId" varStatus="partIdStatus">
									<tr>
										<td>${partIdStatus.index}</td>
										<td><input name="partIds" type="text" value="${partId}"  id="partId_${partIdStatus.index}"/></td>
										<td><input name="partIdentifiers" type="text" value="${formData.partIdentifiers[partIdStatus.index]}" id="partIdentifier_${partIdStatus.index}"/></td>
									</tr>
								</c:forEach>
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
		<script>
			var currentTotalizerCount = 0;
			var currentPartCount = 0;
			var partCache = [];

			function partIdAutoComplete(partIdId, partIdentifierIdId, index) {
				$('#' + partIdId).autocomplete({
					minLength: 0,
					source: partCache,
					select: function(event, ui) {
						$('#' + partIdId).val(ui.item.value);
						$('#' + partIdentifierIdId).val(ui.item.value + '_' + index);
					}
				});
			}

			$(document).ready(function() {
				var totalizerCountStr = $("#totalizerCount").val();
				if (isFinite(totalizerCountStr)) {
					var currentTotalizerCount = parseInt(totalizerCountStr);
				}
				var partCountStr = $("#partCount").val();
				if (isFinite(partCountStr)) {
					var currentPartCount = parseInt(partCountStr);
				}

				$.ajax({
					url: "../ajax/machine_parts.json",
					dataType: "json",
					success: function(data, textStatus, jqXHR) {
						console.log("../ajax/machine_parts.json: " + JSON.stringify(data));
						$.each(data, function(index, part) {
							partCache.push({value: part.partId, label: part.name});
						});
					}
				});

				for (var i = 0; i < currentPartCount; i++) {
					var partIdId = "partId_" + i;
					var partIdentifierId = "partIdentifier_" + i;
					partIdAutoComplete(partIdId, partIdentifierId, i);
				}

				$("#partCount").change(function() {
					var partCountStr = $("#partCount").val();
					if (isFinite(partCountStr)) {
						var partCount = parseInt(partCountStr);
						if (partCount < currentPartCount) {
							$('#tblParts tbody').children().remove();
							for (var i = 0; i < partCount; i++) {
								var partIdId = "partId_" + i;
								var partIdentifierId = "partIdentifier_" + i;
								var newRow = "";

								newRow += '<tr>';
								newRow += '<td>' + i + '</td>';
								newRow += '<td>' + '<input name="partIds" type="text" id="' + partIdId + '"/>' + '</td>';
								newRow += '<td>' + '<input name="partIdentifiers" type="text" id="' + partIdentifierId + '" value="Part_' + i + '"/>' + '</td>';
								newRow += '</tr>';

								$('#tblParts tbody').append(newRow);
								partIdAutoComplete(partIdId, partIdentifierId, i);
							}
						} else {
							for (var i = currentPartCount; i < partCount; i++) {
								var partIdId = "partId_" + i;
								var partIdentifierId = "partIdentifier_" + i;
								var newRow = "";

								newRow += '<tr>';
								newRow += '<td>' + i + '</td>';
								newRow += '<td>' + '<input name="partIds" type="text" id="' + partIdId + '"/>' + '</td>';
								newRow += '<td>' + '<input name="partIdentifiers" type="text" id="' + partIdentifierId + '" value="Part_' + i + '"/>' + '</td>';
								newRow += '</tr>';

								$('#tblParts tbody').append(newRow);
								partIdAutoComplete(partIdId, partIdentifierId, i);
							}
						}
						currentPartCount = partCount;
					}
				});

				$("#totalizerCount").change(function() {
					var totalizerCountStr = $("#totalizerCount").val();
					if (isFinite(totalizerCountStr)) {
						var totalizerCount = parseInt(totalizerCountStr);
						if (totalizerCount > 0) {
							if (totalizerCount < currentTotalizerCount) {
								$('#tblTotalizers tbody').children().remove();
								for (var i = 0; i < totalizerCount; i++) {
									var newRow = "";
									newRow += '<tr>';
									newRow += '<td>' + i + '</td>';
									newRow += '<td>' + '<input name="totalizerIds" type="text" value="Totalizer_' + i + '"/>' + '</td>';
									newRow += '</tr>';
									$('#tblTotalizers tbody').append(newRow);
								}
							} else {
								for (var i = currentTotalizerCount; i < totalizerCount; i++) {
									var newRow = "";
									newRow += '<tr>';
									newRow += '<td>' + i + '</td>';
									newRow += '<td>' + '<input name="totalizerIds" type="text" value="Totalizer_' + i + '"/>' + '</td>';
									newRow += '</tr>';
									$('#tblTotalizers tbody').append(newRow);
								}
							}
							currentTotalizerCount = totalizerCount;
						}
					}
				});
			});
		</script>
	</body>
</html>
