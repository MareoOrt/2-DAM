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
<%
String fechaActual = (String) application.getAttribute("fechaActual");
String deshabilitar = ((Integer) application.getAttribute("acceso_" + fechaActual) > 0) ? "disabled" : "";
%>
<body>
	<form action="ServletCotizacion" method="post">
		<h1>COTIZAGF S.L.</h1>
		<h2>
			La sesion esta activa por el usuario:
			<%=session.getAttribute("user")%></h2>
		<p>
			Fecha nueva cotizacion:
			<%=fechaActual%></p>
		<table border="1">
			<tr>
				<td>Ciclo</td>
				<td>Valor actual</td>
				<td>Valor nuevo</td>
			</tr>
			<%
			HashMap<String, Cotizacion> cotizaciones = (HashMap<String, Cotizacion>) application.getAttribute("listCotizaicones");
			for (String key : cotizaciones.keySet()) {
			%>
			<tr>
				<td><%=key%></td>
				<td><%=cotizaciones.get(key).getAnterior()%></td>
				<td><%=cotizaciones.get(key).getEvalua()%></td>
				<td><input type="number" name="<%=key%>" value="" size="4"
					maxlenght="4" <%=deshabilitar%>></td>
			</tr>
			<%
			}
			%>
		</table>
		<input type="submir" name="boton" value="Enviar" <%=deshabilitar%>>
	</form>
</body>
</html>