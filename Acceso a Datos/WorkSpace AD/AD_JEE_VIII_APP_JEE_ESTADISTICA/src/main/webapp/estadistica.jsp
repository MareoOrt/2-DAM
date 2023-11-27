<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP3: APLICACIÓN JEE ESTADÍSTICA</title>
</head>
<body>
	<form action="ServletEstadistica" method="post">
		<%
		int contador = (request.getParameter("contador") != null) ? Integer.parseInt( (String) request.getAttribute("contador")): 1;
		if (contador > 1) {
		%>
		(*) La encuesta ya fue realizada

		<%
		} else {
		%>
		<h1>ESTADÍSTICAS WEB AD</h1>
		<p>
			Nombre <input type="text" name="nombre">
		</p>
		<h3>Cuando se acerca la fecha del examen, eres de los que ...</h3>
		<br>
		<p>
			<input type="radio" name="1" value="1"> Me lo se
			todo, con la clase es suficiente
		</p>
		<p>
			<input type="radio" name="2" value="2"> No tengo ni
			idea, no lo intento
		</p>
		<p>
			<input type="radio" name="3" value="3"> Estudio en el
			ultimo momento
		</p>
		<p>
			<input type="radio" name="4" value="4"> Estudio todo
			lo posible
		</p>
		<br>
		<input type="submit" name="enviar" value="Enviar">
		<br>
		<%
		String frase = (request.getAttribute("frase") != null) ? (String) request.getAttribute("frase") : "";
		%>
		<%=frase %>
		<p style="color:red;"><%=frase %></p>
		<%} %>
	</form>
</body>
</html>