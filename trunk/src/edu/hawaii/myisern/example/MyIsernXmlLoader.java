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
 * @author Philip Johnson, Marcius Bagawan, Sonwright Gomez, and John Hauge
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
   * Checks for user input and then runs the print methods accordingly.
   * If the user does not enter any arguments, nothing will be printed out.
   * 
   * 
   * @param args containing command line arguments.
   * @throws Exception if there is an exception
   */
  public static void main(String[] args) throws Exception {

    boolean collaborationsFlag = false;
    boolean organizationsFlag = false;
    boolean researchersFlag = false;
    int booleanCounter = 0;
    int argsCounter = 0;

    //Checks for user input
    for (String commandLine : args) {
      if ("--help".equals(commandLine)) {
    	  argsCounter = 0;
    	  break;
      }
      if ("--printCollaborations".equals(commandLine) 
    		  || "-c".equals(commandLine)) {
        collaborationsFlag = true;
        booleanCounter++;
      }
      else if ("--printOrganizations".equals(commandLine) 
    		  || "-o".equals(commandLine)) {
        organizationsFlag = true;
        booleanCounter++;
      }
      else if ("--printResearchers".equals(commandLine) 
    		  || "-r".equals(commandLine)) {
        researchersFlag = true;
        booleanCounter++;
      }
      argsCounter++;
    }
    
    //Prints according to what boolean is true
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    if (argsCounter == 0) {
      //Provides a 'help' mechanism similar to the Unix style.
      System.out.println("Provides sample code for " + 
		  "loading XML and marshalling it into their JAXB related classes.");
      System.out.println("\nUsage: MyIsernXmlLoader [OPTION]");
      System.out.println("\t-c, --printCollaborations\tprints collaborations.");
      System.out.println("\t-o, --printOrganizations\tprints organizations.");
      System.out.println("\t-r, --printResearchers\t\tprints researchers.");
      System.out.println("\t    --help\t\t\tdisplay this help and exits.");
    }
    else {
      if (collaborationsFlag) {
        printCollaborations(mixl.collaborations);
      }
      if (organizationsFlag) {
        printOrganizations(mixl.organizations);
      }
      if (researchersFlag) {
        printResearchers(mixl.researchers);
      }
    }

  }

  /**
   * Prints collaborations.
   * 
   * @param collaborations containing collaborations to be printed
   */
  public static void printCollaborations(Collaborations collaborations) {
    List<Collaboration> collaborationList;
    collaborationList = collaborations.getCollaboration();
    StringBuffer sb = new StringBuffer(3000);
    String newLineNewTab = "\n\t";

    sb.append("\n\n + + + + + + + + + + + + COLLABORATIONS + + + + + + + + + + + + +");

    // Prints contents from loaded Xml files
    for (Collaboration current : collaborationList) {
      List<String> stringList;
      List<BigInteger> bigIntList;

      sb.append("\nName: ");
      sb.append(current.getName());

      //Prints Organizations part of Collaboration
      sb.append("\nCollaborating Organizations:");
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = current.getCollaboratingOrganizations();
      stringList = collaboratingOrganizations.getCollaboratingOrganization();
      
      for (String currentOrg : stringList) {
        sb.append(newLineNewTab);
        sb.append(currentOrg);
      }

      //Prints type of collaboration
      sb.append("\nCollaboration Types:");
      CollaborationTypes collaborationTypes;
      collaborationTypes = current.getCollaborationTypes();
      stringList = collaborationTypes.getCollaborationType();
      
      for (String currentCollabType : stringList) {
        sb.append(newLineNewTab);
        sb.append(currentCollabType);
      }

      //Prints all Years that Organizations were in Collaboration
      sb.append("\nYears:");
      Years years;
      years = current.getYears();
      bigIntList = years.getYear();
      
      for (BigInteger currentYears : bigIntList) {
        sb.append(newLineNewTab);
        sb.append(currentYears.toString());
      }
      //Prints all Outcome types
      sb.append("\nOutcome Types:");
      OutcomeTypes outcomeTypes;
      outcomeTypes = current.getOutcomeTypes();
      stringList = outcomeTypes.getOutcomeType();
      
      for (String currentOutcomeType : stringList) {
        sb.append(newLineNewTab);
        sb.append(currentOutcomeType);
      }

      sb.append("\nDescription: ");
      sb.append(current.getDescription());
      sb.append("\n+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +");
      // sb.append(current.getCollaboratingOrganizations());

    }
    System.out.println(sb.toString());
  }

  /**
   * 
   * @param organizations Contains organizations to be printed.
   */
  public static void printOrganizations(Organizations organizations) {
    List<Organization> organizationList;
    organizationList = organizations.getOrganization();
    StringBuffer sb = new StringBuffer(3000);
    String newLineNewTab = "\n\t";

    sb.append("\n\n========================= ORGANIZATIONS ==========================");
    
    // Prints contents from loaded Xml files
    for (Organization current : organizationList) {
      List<String> stringList;

      sb.append("\nName:");
      sb.append(current.getName());
      
      sb.append("\nType: ");
      sb.append(current.getType());
      
      sb.append("\nContact: ");
      sb.append(current.getContact());

      //Prints all affiliated researchers with organization
      sb.append("\nAffiliated Researchers:");
      AffiliatedResearchers affiliatedResearchers;
      affiliatedResearchers = current.getAffiliatedResearchers();
      stringList = affiliatedResearchers.getAffiliatedResearcher();
      
      for (String currentString : stringList) {
        sb.append(newLineNewTab);
        sb.append(currentString);
      }

      sb.append("\nCounter");
      sb.append(current.getCountry());

      //Prints Research Keywords for organization
      sb.append("\nResearch Keywords:");
      ResearchKeywords researchKeywords;
      researchKeywords = current.getResearchKeywords();
      stringList = researchKeywords.getResearchKeyword();
      
      for (String currentString : stringList) {
        sb.append(newLineNewTab);
        sb.append(currentString);
      }

      sb.append("\nResearch Description: ");
      sb.append(current.getResearchDescription());
      
      sb.append("\nHome Page: ");
      sb.append(current.getHomePage());
      
      sb.append("\n==================================================================\n");

    }
    System.out.print(sb.toString());
  }

  /**
   * Prints Researchers.
   * 
   * @param researchers containing researchers to be printed.
   */
  public static void printResearchers(Researchers researchers) {
    List<Researcher> researcherList;
    researcherList = researchers.getResearcher();
    // String newLineNewTab = "\n\t";
    StringBuffer sb = new StringBuffer(3000);
    sb.append("\n......................... RESEARCHERS ............................ \n");
    for (Researcher currentResearcher : researcherList) {

      sb.append("\nName: ");
      sb.append(currentResearcher.getName());
      
      sb.append("\nOrganization: ");
      sb.append(currentResearcher.getOrganization());
      
      sb.append("\nBio Statement: ");
      sb.append(currentResearcher.getBioStatement());
      
      sb.append("\nPicture Link: ");
      sb.append(currentResearcher.getPictureLink());
      
      sb.append("\nEmail: ");
      sb.append(currentResearcher.getEmail());
      
      sb.append("\n.................................................................. \n");

    }
    System.out.print(sb.toString());
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
