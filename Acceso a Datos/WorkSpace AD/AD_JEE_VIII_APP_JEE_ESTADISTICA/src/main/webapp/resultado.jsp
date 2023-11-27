<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP3: APLICACI�N JEE ESTAD�STICA</title>
</head>
<body>
	<form action="ServletEstadistica" method="get">
		<%
		String nombre = (request.getAttribute("nombre")!= null)? (String) request.getAttribute("nombre"):"";
		HashMap<String, Integer> mapa = (request.getAttribute("opciones") != null)?
				(HashMap<String, Integer>) request.getAttribute("opciones"): new HashMap<>();
	int contadorTotal =  (request.getAttribute("contador") != null) ? Integer.parseInt( (String) request.getAttribute("contador")): 1;
		%>
		<h1>ESTAD�STICAS WEB AD</h1>
		<h2>
			Tu respuesta ha sido registrada
			<%=nombre.toUpperCase()%></h2>
		<h3>Los resultados de la estadistica son:</h3>
		<table border="1" width="50%">
			<tr>
				<td>Opci�n</td>
				<td>N�m.Respuestas</td>
			</tr>
			<%
			for (String key : mapa.keySet()) {
			%>
			<tr>
				<td><%=key%></td>
				<td><%=mapa.get(key)%></td>
			</tr>
			<%
			contadorTotal += mapa.get(key);
			}
			%>
			<h3>
				Se han realizado
				<%=contadorTotal%>
				encuestas
			</h3>
		</table>
		<br>
		<input type="submit" name="volver" value="Volver">
	</form>
</body>
</html>