<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP4: APLICACIÓN JEE DE GESTIÓN INCIDENCIAS</title>
</head>
<body>
	<form action="ServletIncidencia.java" method="post">
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
				<td rowspan="3"><textarea name="descripcion"></textarea></td>
			</tr>
			<tr>
				<td rowspan="2">Descripción:</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="confirmar" value="Confirmar" </td>
			</tr>
		</table>
	</form>
</body>
</html>