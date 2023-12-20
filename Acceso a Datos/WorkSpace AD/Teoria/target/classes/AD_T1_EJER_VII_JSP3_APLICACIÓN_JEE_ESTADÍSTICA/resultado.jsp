<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
HashMap<String, Integer> opciones = (HashMap<String, Integer>) getServletConfig().getServletContext()
		.getAttribute("opciones");
String nombre = (String) request.getAttribute("nombre");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP3: APLICACI�N JEE ESTAD�STICA</title>
</head>
<body>
	<form action="ServletEstadistica" method="get">
		<h1>ESTAD�STICAS WEB AD</h1>
		<h2>
			Tu respuesta ha sido registrada
			<%=nombre.toUpperCase()%></h2>
		<h3>Los resultados de la web son</h3>
		<table>
			<tr>
				<td>Opci�n</td>
				<td>N�m. respuestas</td>
			</tr>
			<%
			for (String opcion : opciones.keySet()) {
			%>
			<tr>
				<td><%=opcion%></td>
				<td><%=opciones.get(opcion)%></td>
			</tr>
			<%
			}
			%>
			</tr>
		</table>
		<input type="submit" name="vclver" value="Volver">
	</form>
</body>
</html>