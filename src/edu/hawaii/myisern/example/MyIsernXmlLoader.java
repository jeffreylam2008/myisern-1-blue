package edu.hawaii.myisern.example;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
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
import org.xml.sax.SAXException;

import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.researchers.jaxb.Researchers;
import edu.hawaii.myisern.researchers.jaxb.Researcher;

import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

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
    String collabFilePath = currentWorkingDirectory + "/xml/examples/collaborations.example.xml";
    File collabFile = new File(collabFilePath);
    Unmarshaller unmarshaller = this.collaborationsJaxbContext.createUnmarshaller();
    Collaborations unmarshalledCollab = (Collaborations) unmarshaller.unmarshal(collabFile);
    this.collaborations = new Collaborations();
    for (Collaboration org : unmarshalledCollab.getCollaboration()) {
      // Unique IDs are the name with having spaces replaced by underscores.
      String uniqueId = org.getName().replace(' ', '_');
        // Check that ID isn't empty then add new Unique ID to list.
        if ((uniqueId == null) || ("".equals(uniqueId))) {
          System.out.println("Invalid name found in collaboration xml file.");
        }
        else {
          if (this.uniqueIdList.add(uniqueId)) {
            // If the unique ID was successfully added to the unique list
            // then we can add it to the collaboration list too.
            this.collaborations.getCollaboration().add(org);
            }
            else {
              System.out.println("Name: " + org.getName() + " already exists in the database.");
            }
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
      // Check that ID isn't empty then add new Unique ID to list.
      if ((uniqueId == null) || ("".equals(uniqueId))) {
        System.out.println("Invalid name found in organization xml file.");
      }
      else {
        if (this.uniqueIdList.add(uniqueId)) {
          // If the unique ID was successfully added to the unique list
          // then we can add it to the organization list too.
          this.organizations.getOrganization().add(collab);
        }
        else {
          System.out.println("Name: " + collab.getName() + " already exists in the database.");
        }
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
      // Check that ID isn't empty then add new Unique ID to list.
      if ((uniqueId == null) && ("".equals(uniqueId))) {
        System.out.println("Invalid name found in organization xml file.");
      }
      else {
        if (this.uniqueIdList.add(uniqueId)) {
          // If the unique ID was successfully added to the unique list
          // then we can add it to the researcher list too.
          this.researchers.getResearcher().add(_researcher);
        }
        else {
          System.out.println("Name: " + _researcher.getName() + " already exists in the database.");
        }
      }
    }
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
   * Gets Collaborations.
   * 
   * @return collaborations.
   */
  public Collaborations getCollaborations() {
    return this.collaborations;
  }
  
  /**
   * Gets list of collaborations.
   * @return list of collaborations.
   */
  public List<Collaboration> getListCollaborations() {
    return this.collaborations.getCollaboration();
  }
  
  /**
   * Gets organizations.
   * 
   * @return organizations
   */
  public Organizations getOrganizations() {
    return this.organizations;
  }
  
  /**
   * Gets list of organizations.
   * 
   * @return organizations
   */
  public List<Organization> getListOrganizations() {
    return this.organizations.getOrganization();
  }
  
  /**
   * Gets researchers.
   * 
   * @return researchers
   */
  public Researchers getResearchers() {
    return this.researchers;
  }
  
  /**
   * Gets a list of all unique Ids available.
   * 
   * @return A list of all unique Ids.
   */
  public Set<String> getUniqueIds() {
    return this.uniqueIdList;
  }
  
  /**
   * Returns true if uniqueIdList contains unique ID.
   * @param possibleUniqueId containing Id to be compared to with list of Unique Id's.
   * @return boolean containing whether uniqueIdList contains possibeUniqueId.
   */
  public boolean containsUniqueId (String possibleUniqueId) {
    return this.uniqueIdList.contains(possibleUniqueId);
  }
  /**
   * Checks validation of web links..
   * @param url URL to test.
   * @throws SAXException Thrown exception.
   * @throws IOException Thrown exception.
   * @throws MalformedURLException Thrown exception.
   * @return if valid or not.
   */
  public boolean isLinkValid(String url) throws MalformedURLException, IOException, SAXException {
    try {
      WebConversation wc = new WebConversation();
      WebResponse response = wc.getResponse(url);
      return true;
    }
    catch (UnknownHostException e) {
    	return false;
    }
    catch (Exception e) {
    	return false;
    }
    
  }
}