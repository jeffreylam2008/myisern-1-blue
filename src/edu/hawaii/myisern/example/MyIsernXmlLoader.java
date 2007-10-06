package edu.hawaii.myisern.example;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.researchers.jaxb.Researcher;
import edu.hawaii.myisern.researchers.jaxb.Researchers;

/**
 * Provides sample code for loading XML and marshalling it into their JAXB related classes.
 * 
 * @author Philip Johnson
 */
public class MyIsernXmlLoader {

  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext collaborationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext organizationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext researchersJaxbContext;

  private Collaborations collaborations;
  private Organizations organizations;
  private Researchers researchers;

  /**
   * Initializes this instance by reading in the three example XML files.
   * 
   * @throws Exception If problems occur.
   */
  public MyIsernXmlLoader() throws Exception {
    // Load in the Collaborations XML example file.
    this.collaborationsJaxbContext = JAXBContext
        .newInstance("edu.hawaii.myisern.collaborations.jaxb");
    File collaborationsFile = new File(System.getProperty("user.dir")
        + "/xml/examples/collaborations.example.xml");
    Unmarshaller unmarshaller = this.collaborationsJaxbContext.createUnmarshaller();
    this.collaborations = (Collaborations) unmarshaller.unmarshal(collaborationsFile);

    // Do the same for organizations.
    this.organizationsJaxbContext = JAXBContext
        .newInstance("edu.hawaii.myisern.organizations.jaxb");
    File organizationsFile = new File(System.getProperty("user.dir")
        + "/xml/examples/organizations.example.xml");
    unmarshaller = this.organizationsJaxbContext.createUnmarshaller();
    this.organizations = (Organizations) unmarshaller.unmarshal(organizationsFile);

    // Now do it once more for the researchers.
    this.researchersJaxbContext = JAXBContext.newInstance("edu.hawaii.myisern.researchers.jaxb");
    File researchersFile = new File(System.getProperty("user.dir")
        + "/xml/examples/researchers.example.xml");
    unmarshaller = this.researchersJaxbContext.createUnmarshaller();
    this.researchers = (Researchers) unmarshaller.unmarshal(researchersFile);
  }

  /**
   * Runs the print methods.
   * 
   * @param args containing command line arguments.
   * @throws Exception if there is an exception
   */
  public static void main(String[] args) throws Exception {
    /*
     * boolean collaborationsFlag = false; boolean organizationsFlag = false; boolean
     * researchersFlag = false;
     * 
     * for ( String commandLine : args) { if (commandLine.equals("-printcollaborations")) {
     * collaborationsFlag = true; } if (commandLine.equals("-printOrganizations")) {
     * organizationsFlag = true; } if (commandLine.equals("-printResearchers")) { researchersFlag =
     * true; } }
     */

    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    // printCollaborations(mixl.collaborations);
    // printOrganizations();
    printResearchers(mixl.researchers);
    /*
     * if(collaborationsFlag) {
     *  } else if (organizationsFlag) {
     *  } else if()
     */
  }

  /**
   * Prints collaborations.
   * 
   * @param collaborations containing collaborations to be printed
   */
  public static void printCollaborations(Collaborations collaborations) {
    List<Collaboration> tempList;
    tempList = collaborations.getCollaboration();

    for (Collaboration current : tempList) {
      StringBuffer sb = new StringBuffer();
      sb.append(current.getName());
      System.out.println(sb.toString());
    }

  }
/*
  /**
   * Prints organizations
   
  public static void printOrganizations() {
    System.out.println("Prints orgn")
  }*/

  /**
   * Prints Researchers.
   * 
   * @param researchers containing researchers to be printed.
   */
  public static void printResearchers(Researchers researchers) {
    List<Researcher> tempList;
    tempList = researchers.getResearcher();

    for (Researcher current : tempList) {
      StringBuffer sb = new StringBuffer(100);
      sb.append("========== RESEARCHERS ==========");
      sb.append(current.getName());
      sb.append(current.getOrganization());
      sb.append(current.getBioStatement());
      sb.append(current.getClass());
      sb.append(current.getPictureLink());
      sb.append(current.getEmail());
      System.out.println(sb.toString());
    }
  }

  /**
   * Returns the number of Collaboration instances.
   * 
   * @return The number of collaborations.
   */
  public int getNumCollaborations() {
    return this.collaborations.getCollaboration().size();
  }

  /**
   * Returns the number of Organization instances.
   * 
   * @return The number of organizations.
   */
  public int getNumOrganizations() {
    return this.organizations.getOrganization().size();
  }

  /**
   * Returns the number of Researcher instances.
   * 
   * @return The number of researchers.
   */
  public int getNumResearchers() {
    return this.researchers.getResearcher().size();
  }

  /**
   * Returns the current Collaborations instance as a String encoding of its XML representation.
   * 
   * @return Its XML String representation.
   * @throws Exception If problems occur during translation.
   */
  public String getCollaborationsXml() throws Exception {
    Marshaller marshaller = this.collaborationsJaxbContext.createMarshaller();
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(true);
    DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
    Document doc = documentBuilder.newDocument();
    marshaller.marshal(this.collaborations, doc);
    DOMSource domSource = new DOMSource(doc);
    StringWriter writer = new StringWriter();
    StreamResult result = new StreamResult(writer);
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.transform(domSource, result);
    String xmlString = writer.toString();
    return xmlString;
  }

}
