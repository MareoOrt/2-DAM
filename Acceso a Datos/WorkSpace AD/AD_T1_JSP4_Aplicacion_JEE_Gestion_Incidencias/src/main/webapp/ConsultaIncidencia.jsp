<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%
int[] codigos = (getServletContext().getAttribute("codigos") != null)
		? (int[]) getServletContext().getAttribute("codigos")
		: new int[1];
String[][] incidencias = (getServletContext().getAttribute("incidencias") != null)
		? (String[][]) getServletContext().getAttribute("incidencias")
		: new String[2][1];
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
			if (codigos[i] > 0 && !incidencias[0][i].isEmpty() && !incidencias[i][i].isEmpty()){
		%>
		<tr>
			<td><%=codigos[i]%></td>
			<td><%=incidencias[0][i]%></td>
			<td><%=incidencias[1][i]%></td>
		</tr>
		<%
			}else{
				%>
				<tr>
			<td colspan="3" > <h4>No hay ninguna incidencia</h4></td>
		</tr>
				<% 
				}
			}
		%>
	</table>
</body>
</html>