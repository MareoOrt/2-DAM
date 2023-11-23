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
	<form action="ServletIncidencia1" method="post">
		<% if(request.getAttribute("codigo")==null){ %>
		
		<table>
			<tr>
				<td colspan="2"><h1>ALTA INCIDENCIA</h1></td>
			</tr>
			<tr>
				<td>Tema:</td>
				<td><input type="text" name="tema"></td>
			</tr>
			<tr>
				<td></td>
				<td rowspan="3"><textarea name="descripcion" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td rowspan="2">Descripción:</td>
			</tr>
		</table>
		<% if (request.getAttribute("error")!=null){ %>
		<p style="color:red;">(*) El tema y la descripcion son obligatorios</p> 
		<%} %>
		<input type="submit" name="boton" value="Confirmar">
		<%} else{ %>
		
		<h1>ALTA INCIDENCIA</H1>
		
		<p>Su incidencia ha sido dada de alta en nuestro sistema con el
			codigo:</p>
		<br>
		<h2><%=request.getAttribute("codigo") %></h2>
		<br>
		<input type="submit" name="boton" value="Consultar">
		
		<%}%>
	</form>
</body>
</html>