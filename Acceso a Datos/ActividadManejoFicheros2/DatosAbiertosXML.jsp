<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="lecturayescritura.XmlFileHandler"%>
<%@page import="java.util.Map"%>
<%@page import="entitity.Museo"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T2: MANEJO DE FICHEROS</title>
</head>
<body>
	<%
	XmlFileHandler handle = new XmlFileHandler("C:\\Users\\mario\\OneDrive\\Documentos\\WorkSpace\\Prueba1\\src\\main\\resources\\turismo-museos-XML.xml");
	if (handle != null && handle.fillMap()) {
		HashMap<String, Museo> map = new HashMap<String, Museo>();
		map = handle.getMapMuseos();
	%>
	<h1>DATOS</h1>
	<table border="2">
		<tr>
			<td>NOMBRE</td>
			<td>DIRECCIÓN</td>
			<td>TELÉFONO</td>
		</tr>
		<%
		for (Museo m : map.values()) {
			String centro = m.getCentro();
			String direccion = m.getDireccion();
			String telf = m.getTelefono();
		%>
		<tr>
			<td><%=centro%></td>
			<td><%=direccion%></td>
			<td><%=telf%></td>
		</tr>
		<%
		}
		} else {
		%>
		<p style="color: red;">ERROR.</p>
		<%
		}
		%>
	</table>
</body>
</html>