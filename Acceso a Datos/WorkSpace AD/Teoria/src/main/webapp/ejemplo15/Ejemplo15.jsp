<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<!-- Activar la sesion o descativar con sesion=false, de base esta true -->
<!DOCTYPE html>
<%
Integer accesos = (Integer) session.getAttribute("acceso");
if (accesos == null) {
	accesos = 0;
}
accesos = accesos.intValue() + 1;
session.setAttribute("acceso", accesos);

if (request.getParameter("invalidaSesion") != null) {
	session.invalidate();
}
%>
<%!String cadena = "HOLA MUNDO JSP";%>
<%!int numero = 0;%>
<%!public String fecha() {
	return (new Date().toString());
}%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Ejemplo15 - HOLA MUNDO</title>
</head>
<body>

	<!--Ejemplo de comentario que se ve del lado del cliente -->
	<%--Ejemplo de comentario que queda oculto al navegador cliente --%>

	<h1><%=cadena%></h1>

	<p>
		La fecha actual del servidor es
		<%=new Date()%></p>

	<h1>TABLA CONTADOR</h1>
	<%
	for (int i = numero; i < numero + 18; i++) {
	%>
	<tr>
		<td>Numero</td>
		<td><%=i%></td>
	</tr>
	<%
	}
	%>

	<br>
	<hr>
	<br>
	<!--Ejemplo metodo-->

	<p>
		La fecha actual es
		<%=fecha()%></p>

	<br>
	<hr>
	<br>
	<!--Segundo ejemplo -->

	<form>
		<input type="submit" name="invalidaSesion" value="Invalidar sesion">
		<input type="submit" name="recargarPagina" value="Recargar pagina">
	</form>

	<br> Contador:
	<%=accesos.intValue() %>

	<br>
	<hr>
	<br>
	<!--Inclusio y cuerpo-->

	<jsp:include page="cuerpo.jsp"></jsp:include>
	<jsp:forward page="Segundo"/>
	

</body>
</html>