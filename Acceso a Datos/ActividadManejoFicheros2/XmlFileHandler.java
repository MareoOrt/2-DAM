/*
 * 
 */
package lecturayescritura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entitity.Museo;

// TODO: Auto-generated Javadoc
/**
 * The Class XmlFileHandler. Clase que se encarga de la manipulacion del fichero
 * xml.
 * 
 */
public class XmlFileHandler {

	/**
	 * The map museos. Mapa que almacena los datos del museo.
	 */
	private HashMap<String, Museo> mapMuseos = new HashMap<String, Museo>();

	/**
	 * The path. Ruta del archivo xml.
	 */
	private String PATH = "";

	/**
	 * Instantiates a new xml file handler. Consttructor.
	 *
	 * @param path the path
	 */
	public XmlFileHandler(String path) {
		super();
		this.PATH = path;
	}

	/**
	 * Load xml document. Introduce el xml en la variable Document, y la retorna
	 * para su uso.
	 *
	 * @return the document
	 */
	private Document loadXmlDocument() {
		Document doc = null;
		try {
			File file = new File(PATH);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * Fill map. Reocogo la variable Document y controlo si se pudo cargar el
	 * archivo y se cargo correctamente devuelve true, sino false. Después se
	 * relleno el mapa con los datos del xml.
	 *
	 * @return true, if successful
	 */
	public boolean fillMap() {
		Document doc = loadXmlDocument();
		if (doc != null) {
			NodeList nodeList = doc.getElementsByTagName("turismo-museos");
			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);
				Element element = (Element) node;

				Museo m = new Museo(element.getElementsByTagName("CENTRO").item(0).getTextContent(),
						element.getElementsByTagName("DIRECCION").item(0).getTextContent(),
						element.getElementsByTagName("TELEFONO").item(0).getTextContent());

				mapMuseos.put(m.getCentro(), m);
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Write xml and map. Cargo el mapa, si se pudo recoger devuelve true sino
	 * false. Controlamos que insertamso los nodos al xml y al mapa
	 *
	 * @param museo the museo
	 * @return true, if successful
	 */
	public boolean writeXmlAndMap(Museo museo) {
		Document doc = loadXmlDocument();
		if (doc != null) {
			Element rootElement = doc.getDocumentElement();

			Element nodeMuseo = doc.createElement("turismo-museos");
			rootElement.appendChild(nodeMuseo);

			Element nodeCentro = doc.createElement("CENTRO");
			nodeCentro.appendChild(doc.createTextNode(museo.getCentro()));
			nodeMuseo.appendChild(nodeCentro);

			Element nodeDireccion = doc.createElement("DIRECCION");
			nodeDireccion.appendChild(doc.createTextNode(museo.getDireccion()));
			nodeMuseo.appendChild(nodeDireccion);

			Element nodeTelfono = doc.createElement("TELEFONO");
			nodeTelfono.appendChild(doc.createTextNode(museo.getTelefono()));
			nodeMuseo.appendChild(nodeTelfono);

			mapMuseos.put(museo.getCentro(), museo);
			try {
				DOMSource source = new DOMSource(doc);
				StreamResult result;
				result = new StreamResult(new PrintWriter(new File(PATH)));
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.transform(source, result);
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
				return false;
			} catch (TransformerException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Gets the map museos.
	 *
	 * @return the map museos
	 */
	public HashMap<String, Museo> getMapMuseos() {
		return mapMuseos;
	}

	/**
	 * Sets the map museos.
	 *
	 * @param mapaMuseos the mapa museos
	 */
	public void setMapMuseos(HashMap<String, Museo> mapaMuseos) {
		this.mapMuseos = mapaMuseos;
	}

}