package logic;

import java.io.File;
import java.util.List;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pojos.Candidaturas;

public class LogicXML_DOM {

	// Atributos
	// Fichero xml
	private File file;

	// Clases necesarias para tratar el archivo
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;

	// Lista de cadenas con cada candidatura
	private List<Candidaturas> list;
	// Lista de nodos candidaturas
	private NodeList nList;

	// Mensaje del error si salta
	private String errorMessage = "";

	// Métodos
	// Constructor
	public LogicXML_DOM() {
		super();

		// Se recoge el fichero
		this.file = new File("Resultados de participación electoral - Ámbito Comarcas de Aragón.xml");

		// Comprobamos que existe el fichero
		if (file.exists()) {
			// Si existe recogemos los datos de los nodos y de sus datos
			initDocument(); // Atributos para tartar el documento
			setNList(); // Lista de nodos de cada candidatura
			setList(); // Lista de cada dato de la candidatura
		} else {
			// Si no existe recogemos el error
			this.errorMessage = "Error con el fichero/No se encontro el archivo";
		}
	}

	// Metodos para inicializar los atributos para tratar el documento
	private void initDocument() {
		try {// Control de errores
				// Inicializamos
			this.dbf = DocumentBuilderFactory.newInstance();
			this.db = dbf.newDocumentBuilder();
			this.doc = db.newDocument();
		} catch (Exception e) { // Si surge un error lo recogemos
			this.errorMessage += "Error al inicializar atributos./" + e.getMessage() + "/";
		}
	}

	// Metodo para recoger los nodos de las candidaturas
	private void setNList() {
		// Guardamos los nodos de cada candidatura y los guardamos
		this.nList = doc.getElementsByTagName("list-item");
		// Comporbamos que la lista de nodos no esta vacia
		if (nList.getLength() <= 0)
			this.errorMessage += "Error al leer los nodos./No se encontro ningun nodo." + "/";
	}

	// Metodo para recoger los datos de cada candidatura
	private void setList() {
		try {// Control de errores
				// Bucle pasar por cada candidatura
			for (int temp = 0; temp < nList.getLength(); temp++) {

				// Recogemos un nodo de la candidatura
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;

				// Recogemos cada subnodo de cada candidatura (datos de la candidatura), y
				// creamos una cadena con los datos
				Candidaturas c = new Candidaturas(
						((Document) eElement).getElementsByTagName("nombre_elec").item(0).getTextContent(),
						((Document) eElement).getElementsByTagName("nombre_o").item(0).getTextContent(),
						((Document) eElement).getElementsByTagName("cod_elec").item(0).getTextContent(),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("cod_comarca").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("pobl_derecho_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("mesas_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("votantes_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("abstencion_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("nulos_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("blancos_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("candidatura_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("electores_o").item(0).getTextContent()),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("validos_o").item(0).getTextContent()),
						((((Document) eElement).getElementsByTagName("n_electos_o").item(0).getTextContent() != null)
								? ((Document) eElement).getElementsByTagName("n_electos_o").item(0).getTextContent()
								: "NINGUNO"),
						Integer.parseInt(
								((Document) eElement).getElementsByTagName("n_consejeros_o").item(0).getTextContent()));

				/*
				 * Añadir que el nodo n_electos_o se sabe que puede estar vacio, por lo que se
				 * controla con un operador ternario
				 * 
				 */

				// Añadimos la cadena de datos de la candidatura
				list.add(c);
			}

			// Comprobamos que la lista no este vacia
			if (list.isEmpty())
				throw new Exception("No se pudo almacenar ningun dato.");

		} catch (Exception e) {// Si surge un error lo recogemos
			this.errorMessage = "Error al recoger los datos./" + e.getMessage();
		}
	}

	// Metodo para añadir un elemento a la lista
	public void addList(Candidaturas node) {
		this.list.add(node);
		addList(node);
	}

	// Metodo para añadir elemento en el archivo
	private void addNList(Candidaturas node) {
		try {// Control de errores

			// Definimos el elemento raíz del documento
			Element eRaiz = (Element) doc.createElement("root");
			doc.appendChild((Node) eRaiz);
			// Definimos el nodo que contendrá los elementos
			Element eCandidatura = (Element) doc.createElement("list-item");
			((Node) eRaiz).appendChild((Node) eCandidatura);

			// Definimos cada uno de los elementos y le asignamos un valor
			Element nombre_elec = (Element) doc.createElement("nombre_elec");
			((Node) nombre_elec).appendChild(doc.createTextNode(node.getNombre_elec()));
			((Node) eCandidatura).appendChild((Node) nombre_elec);

			Element nombre_o = (Element) doc.createElement("nombre_o");
			((Node) nombre_o).appendChild(doc.createTextNode(node.getNombre_o()));
			((Node) eCandidatura).appendChild((Node) nombre_o);

			Element cod_elec = (Element) doc.createElement("cod_elec");
			((Node) cod_elec).appendChild(doc.createTextNode(node.getCod_elec()));
			((Node) eCandidatura).appendChild((Node) cod_elec);

			Element cod_comarca = (Element) doc.createElement("cod_comarca");
			((Node) cod_comarca).appendChild(doc.createTextNode(node.getCod_comarca() + ""));
			((Node) eCandidatura).appendChild((Node) cod_comarca);

			Element pobl_derecho_o = (Element) doc.createElement("pobl_derecho_o");
			((Node) pobl_derecho_o).appendChild(doc.createTextNode(node.getPobl_derecho_o() + ""));
			((Node) eCandidatura).appendChild((Node) pobl_derecho_o);

			Element mesas_o = (Element) doc.createElement("mesas_o");
			((Node) mesas_o).appendChild(doc.createTextNode(node.getMesas_o() + ""));
			((Node) eCandidatura).appendChild((Node) mesas_o);

			Element votantes_o = (Element) doc.createElement("votantes_o");
			((Node) votantes_o).appendChild(doc.createTextNode(node.getVotantes_o() + ""));
			((Node) eCandidatura).appendChild((Node) votantes_o);

			Element abstencion_o = (Element) doc.createElement("abstencion_o");
			((Node) abstencion_o).appendChild(doc.createTextNode(node.getAbstencion_o() + ""));
			((Node) eCandidatura).appendChild((Node) abstencion_o);

			Element nulos_o = (Element) doc.createElement("nulos_o");
			((Node) nulos_o).appendChild(doc.createTextNode(node.getNulos_o() + ""));
			((Node) eCandidatura).appendChild((Node) nulos_o);

			Element blancos_o = (Element) doc.createElement("blancos_o");
			((Node) blancos_o).appendChild(doc.createTextNode(node.getBlancos_o() + ""));
			((Node) eCandidatura).appendChild((Node) blancos_o);

			Element candidatura_o = (Element) doc.createElement("candidatura_o");
			((Node) candidatura_o).appendChild(doc.createTextNode(node.getAbstencion_o() + ""));
			((Node) eCandidatura).appendChild((Node) candidatura_o);

			Element electores_o = (Element) doc.createElement("electores_o");
			((Node) electores_o).appendChild(doc.createTextNode(node.getElectores_o() + ""));
			((Node) eCandidatura).appendChild((Node) electores_o);

			Element validos_o = (Element) doc.createElement("validos_o");
			((Node) validos_o).appendChild(doc.createTextNode(node.getValidos_o() + ""));
			((Node) eCandidatura).appendChild((Node) validos_o);

			Element n_electos_o = (Element) doc.createElement("nombre_elec");
			((Node) n_electos_o).appendChild(doc.createTextNode(node.getN_electos_o()));
			((Node) eCandidatura).appendChild((Node) n_electos_o);

			Element n_consejeros_o = (Element) doc.createElement("n_consejeros_o");
			((Node) n_consejeros_o).appendChild(doc.createTextNode(node.getN_consejeros_o() + ""));
			((Node) eCandidatura).appendChild((Node) n_consejeros_o);

			// Clases necesarias finalizar la sobreescribir del archivo XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);

			transformer.transform(source, result);
		} catch (Exception e) {// Si surge un error lo recogemos
			this.errorMessage = "Error al sobreescribir los datos./" + e.getMessage();
		}
	}

	// Getters
	public List<Candidaturas> getList() {
		return list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
