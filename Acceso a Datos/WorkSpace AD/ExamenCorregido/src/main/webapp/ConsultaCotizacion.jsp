<%@page import="entities.Cotizacion"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>COTIZAGF S.L.</h1>
	<table border="1">
		<tr>
			<td>Ciclo</td>
			<td>Valor actual</td>
			<td>Valor nuevo</td>
			<td>Condicion</td>
		</tr>
		<%
		HashMap<String, Cotizacion> cotizaciones = (HashMap<String, Cotizacion>) application.getAttribute("listCotizaicones");
		for (HashMap.Entry<String, Cotizacion> c : cotizaciones.entrySet()) {
		%>
		<tr>
			<td><%=c.getKey()%></td>
			<td><%=c.getValue().getAnterior()%></td>
			<td><%=c.getValue().getActual()%></td>
			<td><%=c.getValue().getEvalua()%></td>
		</tr>
		<%
		}
		%>
	</table>
	<form action="ServletCotizacion" method="post">
		<input type="submit" name="Volver" Value="Volver">
	</form>
</body>
</html>