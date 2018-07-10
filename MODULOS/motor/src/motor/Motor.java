/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motor;

import java.io.File;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

/**
 *
 * @author Usuario
 */
public class Motor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("motor.Motor.main("+java.util.Arrays.toString(args)+")");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Centro-EducativoPU");
        System.out.println("emf: "+emf.getProperties());
    }
    public static void otro() {
        try {
            File archivo = new File("src/META-INF/persistence.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();
            System.out.println("Raiz: " + document.getDocumentElement().getNodeName());
            // persistence-unit
            NodeList persistenceUnit1 = document.getElementsByTagName("persistence-unit");
            for (int temp = 0; temp < persistenceUnit1.getLength(); temp++) {
                Node nodo = persistenceUnit1.item(temp);
                System.out.println("PU: " + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    System.out.println("name: " + element.getAttribute("name"));
                    System.out.println("transaction-type: " + element.getAttribute("transaction-type"));
                    System.out.println("provider: " + element.getElementsByTagName("provider").item(0).getTextContent());
                    System.out.println("class: " + element.getElementsByTagName("class").item(0).getTextContent());
                    //System.out.println("password: " + element.getElementsByTagName("321423").item(0).getTextContent());
                    Element clase = document.createElement("class");
                    clase.appendChild(document.createTextNode("asl.tlas.jal.ldj"));
                    element.appendChild(clase);
                    
                }
            }
            // persistence-unit
            /*Element persistenceUnit = (Element) document.getElementsByTagName("persistence-unit");
            // class
            Element nombre = document.createElement("class");
            nombre.appendChild(document.createTextNode("asl.tlas.jal.ldj"));
            persistenceUnit.appendChild(nombre);*/
            
            // guardando
            // escribimos el contenido en un archivo .xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/META-INF/persistence.xml"));
            //StreamResult result = new StreamResult(new File("archivo.xml"));

            // Si se quiere mostrar por la consola...
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
