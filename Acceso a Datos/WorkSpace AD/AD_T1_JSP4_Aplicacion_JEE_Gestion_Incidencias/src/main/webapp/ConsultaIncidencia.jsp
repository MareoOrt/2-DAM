<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
int[] codigos = (int[]) getServletContext().getAttribute("codigos");
String[][] incidencias = (String[][]) getServletContext().getAttribute("incidencias");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP4: APLICACIÓN JEE DE GESTIÓN
	INCIDENCIAS</title>
</head>
<body>
	<table>
		<tr>
			<td><b>INCIDENCIA</b></td>
			<td><b>TEMA</b></td>
			<td><b>DESCRIPCIÓN</b></td>
		</tr>
		<%
		for (int i = 0; i < codigos.length; i++) {
		%>
		<tr>
			<td><%=codigos[i] %></td>
			<td><%=incidencias[0][i] %></td>
			<td><%=incidencias[1][i] %></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>