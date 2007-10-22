package edu.hawaii.myisern.example;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  /** The year found cannot be earlier than 1990 */
  private final int VALID_MIN_YEAR = 1990;
  /** The year found cannot be later than 2010 */
  private final int VALID_MAX_YEAR = 2010;
  
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext collaborationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext organizationsJaxbContext;
  /** Holds the class-wide JAXBContext, which is thread-safe. */
  private JAXBContext researchersJaxbContext;
  /** set to public to allow outside access for testing */
  public Collaborations collaborations;
  /** set to public to allow outside access for testing */
  public Organizations organizations;
  /** set to public to allow outside access for testing */
  public Researchers researchers;
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
    String collabFilePath = currentWorkingDirectory + "/xml/examples/collaborations.save.xml";
    File collabFile = new File(collabFilePath);
    Unmarshaller unmarshaller = this.collaborationsJaxbContext.createUnmarshaller();
    Collaborations unmarshalledCollab = (Collaborations) unmarshaller.unmarshal(collabFile);
    this.collaborations = new Collaborations();
    //System.out.println("Loading collaborations.");
    
    for (Collaboration collab : unmarshalledCollab.getCollaboration()) {
      //validate collaboration object
      if (isValidCollaboration(collab)) {
        //generate unique ID
        String uniqueId = collab.getName().replace(' ', '_');
        //attempt to add unique id to list
        if (this.uniqueIdList.add(uniqueId)) {
          //if successful, add collaboration to collaboration list
          this.collaborations.getCollaboration().add(collab);
        }
        else {
          //if not successful, log name exists & move on to next
          System.out.println("Name: " + collab.getName() + 
              " already exists in the database.");
        }
          
      }
    }
    // Do the same for organizations.
    jaxbContentString = "edu.hawaii.myisern.organizations.jaxb";
    this.organizationsJaxbContext = JAXBContext.newInstance(jaxbContentString);
    String orgFilePath = currentWorkingDirectory + "/xml/examples/organizations.save.xml";
    File orgFile = new File(orgFilePath);
    unmarshaller = this.organizationsJaxbContext.createUnmarshaller();
    Organizations unmarshalledOrg = (Organizations) unmarshaller.unmarshal(orgFile);
    this.organizations = new Organizations();
    //System.out.println("Loading organizations.");
    for (Organization org : unmarshalledOrg.getOrganization()) {
      //validate collaboration object
      if (isValidOrganization(org)) {
        //generate unique ID
        String uniqueId = org.getName().replace(' ', '_');
        //attempt to add unique id to list
        if (this.uniqueIdList.add(uniqueId)) {
          //if successful, add collaboration to collaboration list
          this.organizations.getOrganization().add(org);
        }
        else {
          //if not successful, log name exists & move on to next
          System.out.println("Name: " + org.getName() + 
              " already exists in the database.");
        }
          
      }
    }
    // Now do it once more for the researchers.
    jaxbContentString = "edu.hawaii.myisern.researchers.jaxb";
    this.researchersJaxbContext = JAXBContext.newInstance(jaxbContentString);
    String researcherFilePath = currentWorkingDirectory + "/xml/examples/researchers.save.xml";
    File researchersFile = new File(researcherFilePath);
    unmarshaller = this.researchersJaxbContext.createUnmarshaller();
    Researchers unmarshalledResearchers = (Researchers) unmarshaller.unmarshal(researchersFile);
    this.researchers = new Researchers();
    //System.out.println("Loading researchers.");
    for (Researcher researcher : unmarshalledResearchers.getResearcher()) {
      //validate collaboration object
      if (isValidResearcher(researcher)) {
        //generate unique ID
        String uniqueId = researcher.getName().replace(' ', '_');
        //attempt to add unique id to list
        if (this.uniqueIdList.add(uniqueId)) {
          //if successful, add collaboration to collaboration list
          this.researchers.getResearcher().add(researcher);
        }
        else {
          //if not successful, log name exists & move on to next
          System.out.println("Name: " + researcher.getName() + 
              " already exists in the database.");
        }
          
      }
    }
  }
  /**
   * Overloads isValidString to handle List of strings.
   * @param stringList List of strings to validate.
   * @return True if all strings are valid, False if any one string is not valid.
   */
  private static boolean isValidString(List<String> stringList) {
    try {
      for (String s : stringList) {
        if (!isValidString(s)) {
          return false;
        }
      }
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Validates a string.
   * @param s String to validate.
   * @return True if string is valid, False if not valid.
   */
  private static boolean isValidString(String s) {
    boolean pass = true;
    try {
      //not ""
      if ("".equals(s)) {
        pass = false;
      }
      
      //not null
      else if (s == null) {
    	  pass = false;
      }
      else {
    	  pass = true;
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Validates the year according to the VALID_MIN_YEAR & VALID_MAX_YEAR values.
   * @param year Year to validate.
   * @return True if year is valid, False if not valid.
   * @throws Exception Handle thrown exceptions.
   */
  public static boolean isValidYear(BigInteger year) throws Exception {
    boolean pass = true;
    try {
      int curYear = year.intValue();
      //then check most recent year is between min year && max year
      if (1990 <= curYear && curYear <= 2010) {
        //year is valid and we can attempt to add the unique id to the list
      pass = true;
      }
      else {
        pass = false;
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
    
  }
  
  /**
   * Overloads isValidYear to handles a list of years. Method finds
   * most recent year and validates it.
   * @param yearList List of years to validate.
   * @return True if most recent year is valid, False if not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private static boolean isValidYear(List<BigInteger> yearList) throws Exception {
    try {
      //check year field between min year && max year
      //first get most current year
      BigInteger mostRecentYear = BigInteger.valueOf(0);
      for (BigInteger year : yearList) {
        /* b =   bi1.compareTo(bi2);  
         * Returns negative number if bi1<bi2, 
         *   0 if bi1==bi2, 
         *   or positive number if bi1>bi2.
         */
        if (year.compareTo(mostRecentYear) > 0) {
          mostRecentYear = year;
        }
      }
      return isValidYear(mostRecentYear);
    }
    catch (Exception e) {
      return false;
    }
   }
  
  /**
   * Validates an email address.
   * @param email Email address to validate.
   * @return True if email is valid, False if not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidEmail(String email) throws Exception {
    /* Code borrowed and inspired by Sun Corporation
     * http://java.sun.com/developer/technicalArticles/releases/1.4regex/index.html
     */
    boolean pass = true;
    try {
      //Checks for email addresses starting with
      //inappropriate symbols like dots or @ signs.
     Pattern p = Pattern.compile("^\\.|^\\@");
      Matcher m = p.matcher(email);
      if (m.find()) {
        pass = false;
      }
      //Checks for email addresses that start with
      //www. and prints a message if it does.
      p = Pattern.compile("^www\\.");
      m = p.matcher(email);
      if (m.find()) {
        pass = false;
      }
      //Check for email to be in [name]@[domain] format
      //'\\w' == [a-zA-Z_0-9]
      p = Pattern.compile("^[\\w]+@[\\w|\\.]+");
      m = p.matcher(email);
      if (m.find()) {
        pass = true;
      }
      else {
        pass = false;
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
  }
  /**
   * Overloads isValidResearcher to handle a list of researchers.
   * @param researcherList List of researchers.
   * @return True if all researchers are valid, False if any are not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidResearcher(List<Researcher> researcherList) throws Exception {
    boolean pass = true;
    try {
      for (Researcher researcher : researcherList) {
        if (!isValidResearcher(researcher)) {
          pass = false;
        }
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
    
  }
  /**
   * Validates a researcher.
   * @param researcher Researcher to validate.
   * @return True if researcher is valid, False if not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidResearcher(Researcher researcher) throws Exception {
    try {
    //valid name
      //valid string
    //valid organization
      //valid string
    //valid email
      //email formated string
    //valid picture-link
      //valid web link
    //valid bio-statement
      //valid string

      return (
          isValidString(researcher.getName()) &&
          isValidString(researcher.getOrganization()) && 
          isValidEmail(researcher.getEmail()) &&
          //isValidWebLink(researcher.getPictureLink())
          isValidString(researcher.getBioStatement())
      );
    }
    catch (Exception e) {
      return false;
    }
  }
  /**
   * Overloads isValidOrganization to handle list of organizations.
   * @param orgList List of organizations.
   * @return True if all organizations are valid, False if any are not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidOrganization(List<Organization> orgList) throws Exception {
    boolean pass = true;
    try {
      for (Organization org : orgList) {
        if (!isValidOrganization(org)) {
          pass = false;
        }
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Validates an organization.
   * @param org Organization to validate.
   * @return True if organization is valid, False if not.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidOrganization(Organization org) throws Exception {
    try {
      List<String> affilateResearcherList = 
        org.getAffiliatedResearchers().getAffiliatedResearcher();
      List<String> researchKeywordList = org.getResearchKeywords().getResearchKeyword();
      
    //valid name
      //valid string
    //valid type
      //valid string
    //valid contact
      //valid string
    //valid affiliated-researcher
      //valid string
    //valid country
      //valid string
    //valid research-keyword
      //valid string
      //minimum amount of one
    //valid research-description
      //valid string
    //valid home-page
      //valid web link
      return (
        isValidString(org.getName()) && 
        isValidString(org.getType()) &&
        isValidString(org.getContact()) &&
        isValidString(affilateResearcherList) &&
        isValidString(org.getCountry()) &&
        isValidString(researchKeywordList) &&
        !researchKeywordList.isEmpty() &&
        isValidString(org.getResearchDescription())
        //isValidWebLink(org.getHomePage())
      );
    }
    catch (Exception e) {
      return false;
    }
  }
  /**
   * Overloads isValidCollaboration.
   * @param collabList List of collaborations.
   * @return True if all collaborations in list are valid, False if any one is not valid.
   * @throws Exception Handle thrown exceptions.
   */
  private boolean isValidCollaboration(List<Collaboration> collabList) throws Exception {
    boolean pass = true;
    try {
      for (Collaboration collab : collabList) {
        if (!isValidCollaboration(collab)) {
          pass = false;
        }
      }
      return pass;
    }
    catch (Exception e) {
      return false;
    }
  }
  
  /**
   * Checks the validation of a supplied collaboration object.
   * @param collab Collaboration to validate.
   * @return True if valid, False if not valid.
   * @throws Exception Handle thrown exceptions.
   */ 
  public static boolean isValidCollaboration(Collaboration collab) throws Exception {
    try {
      List<String> collabOrgList =
        collab.getCollaboratingOrganizations().getCollaboratingOrganization();
      List<String> collabTypeList = collab.getCollaborationTypes().getCollaborationType();
      List<String> outcomeTypeList = collab.getOutcomeTypes().getOutcomeType();
      
      //valid name
        //valid string
      //valid Collaborating-Organizations
        //valid string
        //minimum amount of two
      //valid Collaboration-Type
        //valid string
        //minimum amount of one
      //valid Collaboration-Type
        //valid string
        //minimum amount of one
      //valid Year
        //between VALID_MIN_YEAR and VALID_MAX_YEAR
      //valid Outcome-Type
        //valid string
        //minimum amount of one
      //valid Description  
        //valid string
      return (
        isValidString(collab.getName()) &&
        isValidString(collabOrgList) &&
        (collabOrgList.size() > 1 ) &&
        isValidString(collabTypeList) &&
        !collabTypeList.isEmpty() &&
        isValidYear(collab.getYears().getYear()) &&
        isValidString(outcomeTypeList) &&
        !outcomeTypeList.isEmpty() &&
        isValidString(collab.getDescription())
      );
    }
    catch (Exception e) {
      return false;
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
   * Gets list of Researchers.
   * @return list of Researchers.
   */
  public List<Researcher> getListResearchers() {
    return this.researchers.getResearcher();
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
   * Adds a unique Id to the set of unique Id's that already exist.
   * @param uniqueId String containing uniqueId.
   */
  public void addUniqueId(String uniqueId) {
    this.uniqueIdList.add(uniqueId.replace(' ', '_'));
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
  public boolean isValidWebLink(String url) throws MalformedURLException, 
                                                   IOException, 
                                                   SAXException {
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