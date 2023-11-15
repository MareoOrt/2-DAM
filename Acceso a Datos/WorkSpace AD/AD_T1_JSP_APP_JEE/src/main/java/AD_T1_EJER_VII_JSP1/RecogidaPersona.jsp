<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP1: APLICACIÓN JEE DE RECOGIDA DE DATOS</title>
</head>
<body>
	<form action="ServletPersona.java" method="post">
		<table>
			<tr>
				<td colspan="2">
					<h2>Formulario de datos</h2>
				</td>
			</tr>
			<tr>
				<td colspan="2">DNI/NIF: <input type="text" name="dni"></td>
			</tr>
			<tr>
				<td>Nombre: <input type="text" name="nombre"></td>
				<td>Apellidos: <input type="text" name="apellidos"></td>
			</tr>
			<tr>
				<td colspan="2">Dirección: <input type="text" name="direccion"></td>
			</tr>
			<tr>
				<td colspan="2">Telefono: <input type="text" name="telf"></td>
			</tr>
			<tr>
				<td colspan="2">Correo Electrónico: <input type="text"
					name="correo"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" name="enviar"
					value="Enviar"></td>
			</tr>
		</table>
	</form>
</body>
</html>