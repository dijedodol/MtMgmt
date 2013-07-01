<%-- 
    Document   : login
    Created on : Jun 30, 2013, 10:03:38 PM
    Author     : Uyeee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<sf:form method="post" commandName="loginForm">
		<sf:errors/>
		<table>
			<tr>
				<td>Login ID:</td>
				<td><sf:input path="loginId"/></td>
				<td><sf:errors path="loginId"/></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><sf:password path="password"/></td>
				<td><sf:errors path="password"/></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</sf:form>
</html>
