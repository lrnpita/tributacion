package ec.eac.sitac.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 * CreaciÃ³n y manipulacion de archivos XML
 *
 * @author Luis M. Teijon gdsram@gmail.com
 * @version 1.0
 *
 * @since 1.0
 */
public class XMLManager {
	String filePath;
	Document doc;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	
	/**
	 * Crea un DocumentBuilder a partir de la direccion del archivo XML
	 * 
	 * @param path La direccion del archivo XML
	 *
	 * @return Verdadero si el Documento fue creado con Éxito

	 * 
	 * @since 1.0
	 */
	public boolean initialize(String path) {
		filePath = path;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

		try
		{
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse("file:///" + filePath);
		}
		catch (Exception e)
		{
			return false;
		}

		return true;
	}
	
	/**
	 * Elimina una etiqueta específica del documento XML.
	 * 
	 * @param tagName El nombre de la etiqueta a eliminar
	 *
	 * @return Verdadero si el tag fue eliminado
	 * 
	 * @since 1.0
	 */
	public boolean removeTag(String tagName) {
		Document xml = doc;
		
		Node node = xml.getElementsByTagName(tagName).item(0);
		
		if (node != null) {
			node.getParentNode().removeChild(node);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Elimina la última sub-etiqueta de una etiqueta padre. 
	 * 
	 * @param tagName El nombre de la etiqueta padre
	 *
	 * @return Verdadero si la etiqueta fue eliminada
	 * 
	 * @since 1.0
	 */
	public boolean removeLastTag(String tagName) {
		Document xml = doc;
		
		Node node = xml.getElementsByTagName(tagName).item(0);

		if (node != null) {
			Node signature = node.getLastChild();
			if (signature != null)
				node.removeChild(signature);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Elimina la etiqueta <![CDATA[...]]> del documento XML
	 * 
	 * @return El documento sin la etiqueta
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 * 
	 * @since 1.0
	 */
	public void removeCDataTag() throws SAXException, IOException, ParserConfigurationException, TransformerException {
		String text = convertDocumentToString(doc);

		String pattern = "<![CDATA[<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"?>";
		text = text.replace(pattern, "");  
		text = text.replace("]]>", "");

		doc = convertStringToDocument(text);
	}
	
	/**
	 * Guarda los cambios realizados al documento.
	 * 
	 * @return Cadena de texto correspondiente al XML convertido.
	 * 
	 * @since 1.0
	 */
	public void saveChanges() throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.transform(source, result);
	}

	 /**
	 * Convierte un Documento XML en una cadena de texto.
	 * 
	 * @param doc Documento XML que sera convertido en una cadena de texto
	 *
	 * @return Cadena de texto correspondiente al XML convertido.
	 * @throws TransformerException 
	 * 
	 * @since 1.0
	 */
    private String convertDocumentToString(Document doc) throws TransformerException {
    	TransformerFactory tf = TransformerFactory.newInstance();
    	Transformer transformer;

    	transformer = tf.newTransformer();
    	// below code to remove XML declaration
    	StringWriter writer = new StringWriter();
    	transformer.transform(new DOMSource(doc), new StreamResult(writer));
    	String output = writer.getBuffer().toString();
    	return output;
    }
 
 	/**
	 * Convierte un String en un Documento XML.
	 * 
	 * @param xmlStr Cadena de texto que sera convertida en un documento XML
	 
	 * @return Documento XML correspondiente a la cadena de texto convertida
 	 * @throws IOException 
 	 * @throws SAXException 
 	 * @throws ParserConfigurationException 
	 * 
	 * @since 1.0
	 */
    private Document convertStringToDocument(String xmlStr) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
 
        builder = factory.newDocumentBuilder();  
        Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
 
		return doc;
    }
	
	
	public Marshaller getMarshaller() {
		return marshaller;
	}
 
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}
 
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}
 
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
	public Object convertFromXMLToObject(String xmlfile) throws IOException {
		Object xmlObject = null;
		// convirtiendo el XML
		FileInputStream is = null;
		try {
			
			is = new FileInputStream(xmlfile);
			xmlObject = getUnmarshaller().unmarshal(new StreamSource(is));

		} catch(Exception e) {
			throw e;
		}finally {
			if (is != null) {
				is.close();
			}
		}
		return xmlObject;
	}
	
	public void convertFromObjectToXML(Object object, String filepath)
		throws IOException {
 
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}
