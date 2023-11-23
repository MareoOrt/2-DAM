<%@page import="Ejercicio1.Incidencia"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP4: APLICACIÓN JEE DE GESTIÓN
	INCIDENCIAS</title>
</head>
<body>
	<%
	ArrayList<Incidencia> incidencias = (application.getAttribute("incidencias") != null)
			? (ArrayList<Incidencia>) application.getAttribute("incidencias")
			: new ArrayList<>();
	%>
	<table>
		<tr>
			<td><b>INCIDENCIA</b></td>
			<td><b>TEMA</b></td>
			<td><b>DESCRIPCIÓN</b></td>
		</tr>
		<%
		if (!incidencias.isEmpty()) {
			for (Incidencia i : incidencias) {
		%>
		<tr>
			<td><%=i.getCodigo()%></td>
			<td><%=i.getTema()%></td>
			<td><%=i.getDescripcion()%></td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="3">
				<h4>No hay ninguna incidencia</h4>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<form action="AltaIncidencia.jsp">
		<input type="submit" name="boton" value="Volver">
	</form>
</body>
</html>