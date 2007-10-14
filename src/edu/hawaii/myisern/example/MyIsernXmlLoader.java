package edu.hawaii.myisern.example;

import java.io.File;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
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
  private Set<String> uniqueIdList;

  /**
   * Initializes this instance by reading in the three example XML files.
   * 
   * @throws Exception If problems occur.
   */
  public MyIsernXmlLoader() throws Exception {
    this.uniqueIdList = new HashSet<String>();
    // Load in the Collaborations XML example file.
	String jaxbContentString = "edu.hawaii.myisern.collaborations.jaxb";
    this.collaborationsJaxbContext = JAXBContext.newInstance(jaxbContentString);
	String currentWorkingDirectory = System.getProperty("user.dir");
	String collabFilePath =  currentWorkingDirectory + 
	                         "/xml/examples/collaborations.example.xml";
    File collabFile = new File(collabFilePath);
    Unmarshaller unmarshaller = this.collaborationsJaxbContext.createUnmarshaller();
    Collaborations unmarshalledCollab = (Collaborations) unmarshaller.unmarshal(collabFile);
    this.collaborations = new Collaborations();
    for (Collaboration collab : unmarshalledCollab.getCollaboration()) {
    	// Unique IDs are the name with having spaces replaced by underscores.
		String uniqueId = collab.getName().replace(' ', '_');
	   // Add new Unique ID.
	   if (this.uniqueIdList.add(uniqueId)) {
         // If the unique ID was successfully added to the unique list
         // then we can add it to the collaboration list too.
         this.collaborations.getCollaboration().add(collab);
	   }
     }
    
    // Do the same for organizations.
    jaxbContentString = "edu.hawaii.myisern.organizations.jaxb";
    this.organizationsJaxbContext = JAXBContext.newInstance(jaxbContentString);
    String orgFilePath = currentWorkingDirectory + "/xml/examples/organizations.example.xml";
    File orgFile = new File(orgFilePath);
    unmarshaller = this.organizationsJaxbContext.createUnmarshaller();
    Organizations unmarshalledOrg = (Organizations) unmarshaller.unmarshal(orgFile);
    this.organizations = new Organizations();
    for (Organization collab : unmarshalledOrg.getOrganization()) {
    	// Unique IDs are the name with having spaces replaced by underscores.
		String uniqueId = collab.getName().replace(' ', '_');
	   // Add new Unique ID.
	   if (this.uniqueIdList.add(uniqueId)) {
         // If the unique ID was successfully added to the unique list
         // then we can add it to the organization list too.
         this.organizations.getOrganization().add(collab);
	   }
     }
    
    // Now do it once more for the researchers.
    jaxbContentString = "edu.hawaii.myisern.researchers.jaxb";
    this.researchersJaxbContext = JAXBContext.newInstance(jaxbContentString);
    String researcherFilePath = currentWorkingDirectory + "/xml/examples/researchers.example.xml";
    File researchersFile = new File(researcherFilePath);
    unmarshaller = this.researchersJaxbContext.createUnmarshaller();
    Researchers unmarshalledResearchers = (Researchers) unmarshaller.unmarshal(researchersFile);
    this.researchers = new Researchers();
    for (Researcher _researcher : unmarshalledResearchers.getResearcher()) {
      // Unique IDs are the name with having spaces replaced by underscores.
	  String uniqueId = _researcher.getName().replace(' ', '_');
	  // Add new Unique ID.
	  if (this.uniqueIdList.add(uniqueId)) {
	    // If the unique ID was successfully added to the unique list
	    // then we can add it to the researcher list too.
	    this.researchers.getResearcher().add(_researcher);
	  }
    }
    
  }
  /*
  public List<String> generateUniqueIdList() {
	  // Create list of unique IDs to return.
	  List<String> idList = new ArrayList<String>();
	  // Add all distinct Unique IDs from collaborations list.
	  for (Collaboration collaborator : this.collaborations.getCollaboration()) {
		  // Unique IDs are the name with having spaces replaced by underscores.
		  String uniqueId = collaborator.getName().replace(' ', '_');
		  // Check to see if Unique ID is already registered.
		  if (this.uniqueIdList.contains(uniqueId) == false) {
			 // Add new Unique ID.
			 this.uniqueIdList.add(uniqueId); 
		  }
		  this.collaborations.getCollaboration().
	  }
	  
	  
	  return idList;	  
  }
  */

  /**
   * Prints collaborations.
   */
  public void printCollaborations() {
    List<Collaboration> collaborationList;
    collaborationList = this.collaborations.getCollaboration();
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
   * Prints organizations.
   */
  public void printOrganizations() {
    List<Organization> organizationList;
    organizationList = this.organizations.getOrganization();
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
   * Prints researchers.
   */
  public void printResearchers() {
    List<Researcher> researcherList;
    researcherList = this.researchers.getResearcher();
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
