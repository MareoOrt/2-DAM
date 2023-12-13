package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pojos.Candidaturas;

public class XML_DOM {

	// Atributos
	// Fichero xml
	private File file;

	// Clases necesarias para tratar el archivo
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;

	// Lista de candidaturas
	private HashMap<String, Candidaturas> list;
	// Lista de nodos candidaturas
	private NodeList nList;

	// Mensaje de error si surge alguno
	private String errorMessage = "";

	// Métodos
	// Constructor
	public XML_DOM() {
		super();

		// Se recoge el fichero
		this.file = new File("Resultados de participación electoral - Ámbito Comarcas de Aragón.xml");

		// Comprobamos que existe el fichero
		if (file.exists()) {
			// Si existe, recogemos los datos de los nodos y de sus datos
			initDocument(); // Atributos para tratar el documento
			setNList(); // Lista de nodos de cada candidatura
			setList(); // Lista de cada dato de la candidatura
		} else {
			// Si no existe, recogemos el error
			this.errorMessage = "Error con el fichero/No se encontró el archivo";
		}
	}

	// Métodos para inicializar los atributos para tratar el documento
	private void initDocument() {
		try {
			// Inicializamos
			this.dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			this.doc = db.parse(file);
		} catch (Exception e) {
			// Si surge un error, lo recogemos
			this.errorMessage += "Error al inicializar atributos./" + e.getMessage() + "/";
		}
	}

	// Método para recoger los nodos de las candidaturas
	private void setNList() {
		// Guardamos los nodos de cada candidatura y los guardamos
		this.nList = doc.getElementsByTagName("list-item");
		// Comporbamos que la lista de nodos no esta vacia
		if (nList.getLength() <= 0)
			this.errorMessage += "Error al leer los nodos./No se encontro ningun nodo." + "/";
	}

	// Método para recoger los datos de cada candidatura
	private void setList() {
		try {
			// Bucle para pasar por cada candidatura
			for (int temp = 0; temp < nList.getLength(); temp++) {

				// Recogemos un nodo de la candidatura
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String nombreCandidatura = eElement.getElementsByTagName("nombre_elec").item(0).getTextContent();

				// Recogemos cada subnodo de cada candidatura (datos de la candidatura), y
				// creamos una cadena con los datos
				Candidaturas c = new Candidaturas(nombreCandidatura,
						eElement.getElementsByTagName("nombre_o").item(0).getTextContent(),
						eElement.getElementsByTagName("cod_elec").item(0).getTextContent(),
						Integer.parseInt(eElement.getElementsByTagName("cod_comarca").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("pobl_derecho_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("mesas_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("votantes_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("abstencion_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("nulos_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("blancos_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("candidatura_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("electores_o").item(0).getTextContent()),
						Integer.parseInt(eElement.getElementsByTagName("validos_o").item(0).getTextContent()),
						eElement.getElementsByTagName("n_electos_o").item(0).getTextContent().isEmpty() ? "NINGUNO"
								: eElement.getElementsByTagName("n_electos_o").item(0).getTextContent(),
						Integer.parseInt(eElement.getElementsByTagName("n_consejeros_o").item(0).getTextContent()));

				// Añadimos la candidatura a la lista
				list.put(nombreCandidatura,c);
			}

			// Comprobamos que la lista no está vacía
			if (list.isEmpty())
				throw new Exception("No se pudo almacenar ningún dato.");

		} catch (Exception e) {
			// Si surge un error, lo recogemos
			this.errorMessage = "Error al recoger los datos./" + e.getMessage();
		}
	}

	// Método para añadir un elemento a la lista
	public void addList(Candidaturas node) {
		((List<Candidaturas>) this.list).add(node);
	}

	// Método para añadir un elemento en el archivo
	public void addNList(Candidaturas node) {
		try {
			// Definimos el elemento raíz del documento
			Element eRaiz = doc.getDocumentElement();
			// Definimos el nodo que contendrá los elementos
			Element eCandidatura = doc.createElement("list-item");

			// Definimos cada uno de los elementos y les asignamos un valor
			eCandidatura.appendChild(createElement(doc, "nombre_elec", node.getNombre_elec()));
			eCandidatura.appendChild(createElement(doc, "nombre_o", node.getNombre_o()));
			eCandidatura.appendChild(createElement(doc, "cod_elec", node.getCod_elec()));
			eCandidatura.appendChild(createElement(doc, "cod_comarca", String.valueOf(node.getCod_comarca())));
			eCandidatura.appendChild(createElement(doc, "pobl_derecho_o", String.valueOf(node.getPobl_derecho_o())));
			eCandidatura.appendChild(createElement(doc, "mesas_o", String.valueOf(node.getMesas_o())));
			eCandidatura.appendChild(createElement(doc, "votantes_o", String.valueOf(node.getVotantes_o())));
			eCandidatura.appendChild(createElement(doc, "abstencion_o", String.valueOf(node.getAbstencion_o())));
			eCandidatura.appendChild(createElement(doc, "nulos_o", String.valueOf(node.getNulos_o())));
			eCandidatura.appendChild(createElement(doc, "blancos_o", String.valueOf(node.getBlancos_o())));
			eCandidatura.appendChild(createElement(doc, "candidatura_o", String.valueOf(node.getCandidatura_())));
			eCandidatura.appendChild(createElement(doc, "electores_o", String.valueOf(node.getElectores_o())));
			eCandidatura.appendChild(createElement(doc, "validos_o", String.valueOf(node.getValidos_o())));
			eCandidatura.appendChild(createElement(doc, "n_electos_o", node.getN_electos_o()));
			eCandidatura.appendChild(createElement(doc, "n_consejeros_o", String.valueOf(node.getN_consejeros_o())));

			// Añadimos el nuevo nodo al elemento raíz
			eRaiz.appendChild(eCandidatura);

			// Guardamos los cambios en el archivo
			saveChangesToFile();
		} catch (Exception e) {
			// Si surge un error, lo recogemos
			this.errorMessage = "Error al sobrescribir los datos./" + e.getMessage();
		}
	}

	// Método para crear un elemento del documento
	private Element createElement(Document doc, String tagName, String textContent) {
		Element element = doc.createElement(tagName);
		element.appendChild(doc.createTextNode(textContent));
		return element;
	}

	// Método para guardar los cambios en el archivo
	private void saveChangesToFile() {
		try {
			// Clases necesarias para finalizar la sobrescritura del archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
		} catch (Exception e) {
			// Si surge un error, lo recogemos
			this.errorMessage = "Error al sobrescribir los datos./" + e.getMessage();
		}
	}

	// Getters
	public List<Candidaturas> getList() {
		return (List<Candidaturas>) list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
