package ec.eac.sitac.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class XMLModifier {
	static String filePath;
	static Document doc;
	
	public static boolean initialize(String path) {
		try {
			filePath = path;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(filePath);

			return true;

		} catch (Exception e) {
			return false;
		}
	}
	
	public static Document removeTag(String tagName) {
		Document xml = doc;
		
		Node node = xml.getElementsByTagName(tagName).item(0);
		xml.removeChild(node);
		
		return xml;
	}
	
	private static void removeCDataTag() {

		String text = convertDocumentToString(doc);

		// el tag CDATA
		String pattern = "<![CDATA[<?xml version=" + "\"" + "1.0" + "\"" + " encoding=" + "\"" + "UTF-8" + "\"?>";
		text = text.replace(pattern, "");  
		text = text.replace("]]>", "");

		doc = convertStringToDocument(text);

	}
	
	public static void removeUnnecesaryTags() {
		// eliminando el tag CDATA
		removeCDataTag();
		
		Document xml = doc;
		
		Node node = xml.getElementsByTagName("comprobanteRetencion").item(0);
		Node signature = node.getLastChild();
		node.removeChild(signature);
		
		node = xml.getElementsByTagName("mensajes").item(0);
		node.getParentNode().removeChild(node);

	}
	
	public static Node getRootTag() {
		return doc.getFirstChild();
	}
	
	public static void saveChanges() throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));
		transformer.transform(source, result);
	}

    private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }
 
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
}
