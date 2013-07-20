<%-- 
    Document   : show_service_report_detail
    Created on : Jul 20, 2013, 11:39:15 PM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Service Report Detail</title>
	</head>
	<body>
		<table border="1" width="800px">
			<tr align="center">
				<th colspan="6" ><h3>SERVICE REPORT</h3></th>
	</tr>
	<tr>
		<th>No. SPBU</th>
		<td>:</td>
		<td>${spbuCode}</td>
		<th>Pelapor</th>
		<td>:</td>
		<td>${supervisorName}</td>
	</tr>
	<tr>
		<th>Alamat</th>
		<td>:</td>
		<td>${spbuAddress}</td>
		<th>Tanggal</th>
		<td>:</td>
		<td>${date}</td>
	</tr>
	<tr>
		<th>Mesin</th>
		<td>:</td>
		<td>${machineIdentifier}</td>
		<th>Nomor Seri</th>
		<td>:</td>
		<td>${machineSerial}</td>
	</tr>
	<tr>
		<th>Model / Jenis</th>
		<td>:</td>
		<td>${machineModel}</td>
		<th>Totalizer</th>
		<td>:</td>
		<td>${totalizerString}</td>
	</tr>
	<tr>
		<th>Kerusakan yang dilaporkan</th>
		<td>:</td>
		<td colspan="4">${failureModeName}</td>
	</tr>
	<tr>
		<th colspan="2">No</th>
		<th>Spare parts yang diganti</th>
		<th>Diisi oleh bagian administrasi</th>
		<th colspan="2">Diisi oleh Teknisi</th>
	</tr>
	<c:forEach var="partId" items="${partIds}" varStatus="partIdStatus">
		<tr>
			<th colspan="2">${partIdStatus.index + 1}</th>
			<td>${partId}</td>
			<td></td>
			<td colspan="2"></td>
		</tr>
	</c:forEach>
	<tr>
		<th colspan="3">Apakah Service di atas telah dilakukan dengan baik dan memuaskan?</th>
		<th colspan="2">&nbsp;</th>
		<td>YA/TIDAK</td>
	</tr>
	<tr>
		<th colspan="3">Keterangan/Hasil Perbaikan </th>
		<td colspan="3">${failureModeHandlingName}</td>
	</tr>
	<tr>
		<th colspan="6">Sales & After Sales Service, Bali/Lombok:<br>Jl. Ulunswi No 26D, Jimbaran, Kuta-Bali 80361<br>Telp :12312412 </th>
	</tr>
</table>
</body>
</html>
