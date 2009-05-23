/*
 * XMLDocument.java
 *
 * Created on May 25, 2005, 4:11 PM
 */

package rpg.XMLFile;

import org.apache.xml.serialize.LineSeparator;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.apache.xpath.XPathAPI;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
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
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * This class allows a xml file to be loaded and maintained. This class provides methods to store text values in XML element form (I.E. <node>value</node> instead of <node value='value'/>).
 * @author Mikael S Newquist
 */
public class XMLDocument {
    
    private String fileName;
    private Document doc;
    
    /**
     * Creates a new instance of XMLDocument
     * @param rootName The root node name that should be created for the document. This is a minimum requirement for well formed documents.
     */
    public XMLDocument( String rootName ) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.newDocument();
            Element root = doc.createElement(rootName);
            doc.appendChild(root);
        } catch (ParserConfigurationException e) {
        }
    }
    
    /**
     * Returns the name of the file the document represents.
     * @return  the file name being returned
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Sets the name of the file the document represents.
     * @param fileName The file name to be used for the document
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Parses an XML file and returns a DOM document.
     * If validating is true, the contents is validated against the DTD
     * specified in the file.
     * @param validating Whether the xml document should be validated against its DTD or schema. Note: the DTD or schema is defined in the XML dovument.
     */
    public void loadFile(boolean validating) {
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validating);
            
            // Create the builder and parse the file
            doc = factory.newDocumentBuilder().parse(new File(fileName));
        } catch (SAXException e) {
            // A parsing error occurred; the xml input is not valid
        } catch (ParserConfigurationException e) {
        } catch (IOException e) {
        }
    }
    
    /**
     * This method writes a DOM document to a file
     */
    public void writeFile() {
        try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);
            String curDir = System.getProperty("user.dir");
            // Prepare the output file
            File file = new File(curDir+"/dataFiles/Characters/"+fileName+".xml");
   System.out.println("File: "+file);
            Result result = new StreamResult(file);
            
            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
        	System.out.println("first: ");
        	e.printStackTrace();
        } catch (TransformerException e) {
        	System.out.println("second: ");
        	e.printStackTrace();
        }
    }
    
    /**
     * Return the string representation (XML) of the stored document
     * @throws java.lang.Exception 
     * @return A string representing the XML document being stored
     */
    public String generateXMLString() throws Exception {
        OutputFormat format = new OutputFormat(doc);
        format.setLineSeparator(LineSeparator.Windows);
        format.setIndenting(true);
        format.setLineWidth(0);
        format.setPreserveSpace(false);
        
        StringWriter stringOut = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(stringOut, format);
        serializer.asDOMSerializer();
        serializer.serialize(doc);
        
        return stringOut.toString();
    }
    
    /**
     *
     * @param xpath
     * @throws java.lang.Exception
     * @return
     */
    protected Element createElement( String xpath ) throws Exception {
        Element node = getElement( xpath );
        
        if ( node == null ) {
            String xpathParent = "";
            String childName = "";
            int nIndex = xpath.lastIndexOf("/");
            if ( nIndex >= 0 ) {
                childName = xpath.substring(nIndex+1);
                xpathParent = xpath.substring(0, nIndex);
            } else {
                // Assume root element
                childName = xpath;
            }
            
            Element nodeChild = doc.createElement(childName);
            if ( xpathParent.equals("") ) {
                doc.appendChild(nodeChild);
            } else {
                Element nodeParent = createElement( xpathParent );
                nodeParent.appendChild(nodeChild);
            }
            
            return nodeChild;
        }
        
        return node;
    }
    
    /**
     *
     * @param xpath
     * @throws java.lang.Exception
     * @return
     */
    public Element getElement( String xpath ) throws Exception {
        return (Element)XPathAPI.selectSingleNode(doc.getDocumentElement(), xpath );
    }
    
    /**
     *
     * @param xpath
     * @throws java.lang.Exception
     * @return
     */
    public String getValue( String xpath ) throws Exception {
        Element node = getElement( xpath );
        
        if ( node != null ) {
            NodeList nodeList = node.getChildNodes();
            for ( int i = 0; i < nodeList.getLength(); i++ ) {
                Node item = nodeList.item(i);
                
                if ( item.getNodeType() == Node.TEXT_NODE ) {
                    return item.getNodeValue();
                }
            }
        }
        
        throw new Exception("Document value '" + xpath + "' not found.");
    }
    
    /**
     *
     * @param xpath
     * @param value
     * @throws java.lang.Exception
     */
    public void setValue( String xpath, String value ) throws Exception {
        Element node = createElement( xpath );
        
        if ( node != null ) {
            NodeList nodeList = node.getChildNodes();
            boolean found = false;
            for ( int i = 0; i < nodeList.getLength(); i++ ) {
                Node item = nodeList.item(i);
                
                if ( item.getNodeType() == Node.TEXT_NODE ) {
                    item.setNodeValue(value);
                    found = true;
                    break;
                }
            }
            
            if ( !found ) {
                Text text = doc.createTextNode(value);
                node.appendChild(text);
            }
        }
    }
    
    /**
     *
     * @param xpath
     * @throws java.lang.Exception
     */
    public void removeValue( String xpath ) throws Exception {
        Element node = getElement( xpath );
        
        if ( node != null ) {
            Node parentNode = node.getParentNode();
            parentNode.removeChild(node);
            node = null;
        }
    }
    
    /**
     *
     * @param xpathParent
     * @param name
     * @param value
     * @throws java.lang.Exception
     */
    public void addValue( String xpathParent, String name, String value ) throws Exception {
        Element nodeParent = createElement( xpathParent );
        
        if ( nodeParent != null ) {
            Element nodeChild = doc.createElement(name);
            nodeParent.appendChild(nodeChild);
            
            Text text = doc.createTextNode(value);
            nodeChild.appendChild(text);
        }
    }

	public int getNodes(String string) {
		//do a XPathAPI.selectNodeList(doc, "/Character/skills") - returns a NodeList object
		//do a getLength() 
		//nodelist should have an "item(1)" method or there abouts - just setup a for loop
		int foo = 0;
		try {
			NodeList nodeList = XPathAPI.selectNodeList(doc, string);
			foo = nodeList.getLength();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foo;
		
	}

}
