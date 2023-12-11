<%@page import="javax.lang.model.element.Element"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="java.io.File"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Node"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
File file = new File("archivo.xml");
try {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(file);

	// estos métodos podemos usarlos combinados para normalizar el archivo XML
	doc.getDocumentElement().normalize();

	// almacenamos los nodos para luego mostrar la
	// cantidad de ellos con el método getLength()
	NodeList nList = doc.getElementsByTagName("list-item");
} catch (Exception e) {
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AD_T2: MANEJO DE FICHEROS - Archivos XML con DOM</title>
</head>
<body>
	<form action="ServletAcceso" method="post">
		<table border="2">
			<tr>
				<td>NOMBRE DE LA ELECCIÓN</td>
				<td>NOMBRE</td>
				<td>CODIGO DE LA ELECCION</td>
				<td>CODIGO DE LA COMARCA</td>
				<td>DERECHO DE LA POBLACION</td>
				<td>MESA</td>
				<td>VOTANTES</td>
				<td>ABSTENCIONES</td>
				<td>NULOS</td>
				<td>BLANCOS</td>
				<td>CANDIDATURA</td>
				<td>ELECTORES</td>
				<td>VALIDOS</td>
				<td>NUMERO DE ELECTORES</td>
				<td>NUMERO DE CONSEJEROS</td>
			</tr>
			<c:forEach var="data" items="${nList}">
				<tr>
					<td>${data.nombre_elec}</td>
					<td>${data.nombre_o}</td>
					<td>${data.cod_elec}</td>
					<td>${data.cod_comarca}</td>
					<td>${data.pobl_derecho_o}</td>
					<td>${data.mesas_o}</td>
					<td>${data.votantes_o}</td>
					<td>${data.abstencion_o}</td>
					<td>${data.nulos_o}</td>
					<td>${data.blancos_o}</td>
					<td>${data.candidatura_o}</td>
					<td>${data.electores_o}</td>
					<td>${data.validos_o}</td>
					<td>${data.n_electos_o}</td>
					<td>${data.n_consejeros_o}</td>
				</tr>
			</c:forEach>
		</table>
		<br> <input type="submit" name="volver" value="Volver">
	</form>
</body>
</html>