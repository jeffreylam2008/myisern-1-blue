package edu.hawaii.myisern.example;

import java.io.File;
import java.io.StringWriter;
import java.math.BigInteger;
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
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import edu.hawaii.myisern.collaborations.jaxb.CollaboratingOrganizations;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.CollaborationTypes;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.collaborations.jaxb.OutcomeTypes;
import edu.hawaii.myisern.collaborations.jaxb.Years;
import edu.hawaii.myisern.organizations.jaxb.AffiliatedResearchers;
import edu.hawaii.myisern.organizations.jaxb.ResearchKeywords;
import edu.hawaii.myisern.researchers.jaxb.Researchers;
import edu.hawaii.myisern.researchers.jaxb.Researcher;

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
    //printCollaborations(mixl.collaborations);
    printOrganizations(mixl.organizations);
    //printResearchers(mixl.researchers);
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
    List<Collaboration> collaborationList;
    collaborationList = collaborations.getCollaboration();

    for (Collaboration current : collaborationList) {
      StringBuffer sb = new StringBuffer(100);
      List<String> stringList;
      List<BigInteger> bigIntList;
      
      sb.append(current.getName());
      
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = current.getCollaboratingOrganizations();
      stringList = collaboratingOrganizations.getCollaboratingOrganization();
      for (String currentOrg : stringList) {
        sb.append(currentOrg);
      }
      
      CollaborationTypes collaborationTypes;
      collaborationTypes = current.getCollaborationTypes();
      stringList = collaborationTypes.getCollaborationType();
      for (String currentCollabType : stringList) {
        sb.append(currentCollabType);
      }
      
      Years years;
      years = current.getYears();
      bigIntList = years.getYear();
      for (BigInteger currentYears : bigIntList) {
        sb.append(currentYears.toString());
      }
      
      OutcomeTypes outcomeTypes;
      outcomeTypes = current.getOutcomeTypes();
      stringList = outcomeTypes.getOutcomeType();
      for (String currentOutcomeType : stringList) {
        sb.append(currentOutcomeType);
      }
      
      sb.append(current.getDescription());
      System.out.println(sb.toString());
    }

  }
  
  /**
   * 
   * @param organizations Contains organiztions to be printed.
   */
  public static void printOrganizations(Organizations organizations) {
    List<Organization> organizationList;
    organizationList = organizations.getOrganization();
    
    for (Organization current : organizationList) {
      StringBuffer sb = new StringBuffer(100);
      List<String> stringList;
      
      sb.append(current.getName());
      sb.append(current.getType());
      sb.append(current.getContact());
      
      AffiliatedResearchers affiliatedResearchers;
      affiliatedResearchers = current.getAffiliatedResearchers();
      stringList = affiliatedResearchers.getAffiliatedResearcher();
      for (String currentString : stringList) {
        sb.append(currentString);
      }
      
      sb.append(current.getCountry());
      
      ResearchKeywords researchKeywords;
      researchKeywords = current.getResearchKeywords();
      stringList = researchKeywords.getResearchKeyword();
      for (String currentString : stringList) {
        sb.append(currentString);
      }
      
      sb.append(current.getResearchDescription());
      sb.append(current.getHomePage());
      System.out.println(sb.toString());
    }
  }

  /**
   * Prints Researchers.
   * 
   * @param researchers containing researchers to be printed.
   */
  public static void printResearchers(Researchers researchers) {
    List<Researcher> researcherList;
    researcherList = researchers.getResearcher();

    for (Researcher currentResearcher : researcherList) {
      StringBuffer sb = new StringBuffer(100);
      sb.append("========== RESEARCHERS ==========");
      sb.append(currentResearcher.getName());
      sb.append(currentResearcher.getOrganization());
      sb.append(currentResearcher.getBioStatement());
      sb.append(currentResearcher.getClass());
      sb.append(currentResearcher.getPictureLink());
      sb.append(currentResearcher.getEmail());
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
