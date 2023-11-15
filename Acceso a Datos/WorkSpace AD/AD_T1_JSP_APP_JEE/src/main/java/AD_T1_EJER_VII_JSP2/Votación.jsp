<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP2: APLICACIÓN JEE ELECCIÓN DE 
DELEGADO</title>
</head>
<body>
<form action="ServletVotacion.java" method="post">
<p>ELECCIÓN DE DELEGADO</p>
<hr>
<P>Los delegados que se presentan a nuestro Consejo Escolar son...</P>
<p><input type="checkbox" name="delegado" value="Ruth">RUTH FERNANDEZ</p>
<p><input type="checkbox" name="delegado" value="Victor">VICTOR VERGEL</p>
<input type="submit" name="enviar" value="Enviar">
<hr>
</form>
</body>
</html>