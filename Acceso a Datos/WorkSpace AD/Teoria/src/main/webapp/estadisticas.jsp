<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T1_EJER(VII)_JSP3: APLICACI�N JEE ESTAD�STICA</title>
</head>
<body>
	<form action="ServletEstadistica" method="post">
		<%
		String nombre = (String) request.getAttribute("nombre");
		int contador = (request.getAttribute("contador") != null) ? Integer.parseInt((String) request.getAttribute("contador"))
				: 1;
		if (contador > 1) {
		%>
		<p>
			<b><%=nombre%>, ya has respondido anteriormente, no puedes
				participar.</b>
		</p>
		<%
		} else {
		%>
		<h1>ESTAD�STICAS WEB AD</h1>
		Nombre <input type="text" name="nombre">
		<h3>Cuando se aceerca �fecha de� examen, ers de los que...</h3>
		<br>
		<p>
			<input type="checkbox" name="estudio" value="1"> Me lo se
			todo, no necesito estudiar con la clase es suficiente
		</p>
		<p>
			<input type="checkbox" name="estudio" value="2"> No tengo ni
			idea, no lo entiendo
		</p>
		<p>
			<input type="checkbox" name="estudio" value="3"> Estudio en
			el �ltimo momento
		</p>
		<p>
			<input type="checkbox" name="estudio" value="4"> Estudio todo
			lo posible
		</p>
		<br> <input type="submit" name="enviar" value="Enviar">
	</form>
	<br>
	<%
	String frase = (request.getAttribute("frase") != null) ? (String) request.getAttribute("frase") : "";
	%>
	<%=frase %>
	<%} %>
</body>
</html>