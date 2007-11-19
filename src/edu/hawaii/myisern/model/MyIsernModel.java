package edu.hawaii.myisern.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;
import edu.hawaii.myisern.example.MyIsern;
import edu.hawaii.myisern.example.MyIsernXmlSaver;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.researchers.jaxb.Researcher;
import edu.hawaii.myisern.researchers.jaxb.Researchers;


/**
 * Implements a singleton MyIsern instance. The command classes manipulate this singleton in
 * response to user input. The MyIsernModel manipulates string objects only. Also, to prevent
 * multi-threaded access from corrupting the XML data files, all operations on the singleton
 * StackModel instance are synchronized.
 * 
 * @author Marcius Bagwan
 * @author Jeffrey Lam
 */
public class MyIsernModel {

  /**
   * The singleton MyIsernModel instance.
   */
  private static MyIsernModel myIsernModelInstance = new MyIsernModel();

  /**
   * The internal MyIsern implementation.
   */
  private MyIsern myIsern;
  private MyIsernXmlSaver myIsernXmlSaver;
  private Researchers researchers = new Researchers();
  private Organizations organizaitons = new Organizations();
  private List<Researcher> newResList = new ArrayList<Researcher>();
  private List<Organization> newOrgList = new ArrayList<Organization>();
  private boolean validUsername = false;
  private boolean validPassword = false;
  private boolean allowLogin = false;
  private String exceptionPlaceholder = "";

  /**
   * Private constructor used to create a single instance of MyIsern.
   */
  private MyIsernModel() {
    try {
      this.myIsern = new MyIsern();
      this.myIsernXmlSaver = new MyIsernXmlSaver();
      this.newResList = this.researchers.getResearcher();
      this.newOrgList = this.organizaitons.getOrganization();
    }
    catch (Exception e) {
      this.exceptionPlaceholder = e.toString();
    }
  }

  /**
   * Get the single instance of MyIsernModel object.
   * 
   * @return A MyIsernModel.
   */
  public static synchronized MyIsernModel getInstance() {
    return MyIsernModel.myIsernModelInstance;
  }
  
  /**
   * Verifies login information of users.
   * 
   * @param username The username inputted by the user.
   * @param password The password inputted by the user.
   * @return True if the login information is correct.
   */
  public synchronized boolean login(String username, String password) {
    if ("myisern".equals(username)) {
      this.validUsername = true;
    }
    if ("password".equals(password)) {
      this.validPassword = true;
    }
    if (validUsername && validPassword ) {
      this.allowLogin = true;
    }
    return this.allowLogin;
  }
  
  /**
   * Determines whether a researcher, organization,or collaboration is what the user wants to add.
   * 
   * @param type The type of data the user wants to add.
   * @return An integer representation of the type.
   */
  public synchronized String addNew(String type) {
  	if (type.contains("Collaboration")) {
  		return "Collaboration";
  	}
  	if (type.contains("Organization")) {
  		return "Organization";
  	}
  	if (type.contains("Researcher")) {
  		return "Researcher";
  	}
  	else {
  	  return "";
  	}
  }

  /**
   * Gets a String List of researcher names.
   *
   * @return A String List of researcher names.
   */
  public synchronized List<String> researchersList() {
    return this.myIsern.printResearchers();
  }

  /**
   * Gets a String List of organization names.
   *
   * @return A String List of organization names..
   */
  public synchronized List<String> organizationsList() {
    return this.myIsern.printOrganizations();
  }

  /**
   * Gets a String List of collaboration names.
   *
   * @return A String List of collaboration names.
   */
  public synchronized List<String> collaborationsList() {
    return this.myIsern.printCollaborations();
  }
  
  /**
   * Gets the researcher being searched for.
   * 
   * @param id The ID being searched for.
   * @return A String List of the data of the researcher.
   */
  public synchronized List<String> getResearcher(String id) {
    return this.myIsern.printResearcher(id);
  }
  
  /**
   * Gets the organization being searched for.
   * 
   * @param id The ID being searched for.
   * @return A String List of the data of the organization.
   */
  public synchronized List<String> getOrganization(String id) {
    return this.myIsern.printOrganization(id);
  }
  
  /**
   * Gets the collaboration being searched for.
   * 
   * @param id The ID being searched for.
   * @return A String List of the data of the collaboration.
   */
  public synchronized List<String> getCollaboration(String id) {
    return this.myIsern.printCollaboration(id);
  }
  
  /**
   * Adds a new researcher to the loader.
   * @param newResearcher containing the new researcher to be added.
   */
  public synchronized void addResearcher(Researcher newResearcher) {
    this.newResList.add(newResearcher);
  }
  
  /**
   * Adds a new organization to the loader.
   * @param newOrganization containing the new organization to be added.
   */
  public synchronized void addOrganization(Organization newOrganization) {
    this.newOrgList.add(newOrganization);
  }
  
  /**
   * Gets a String List of researcher.
   *
   * @return A String List of researcher.
   * @throws JAXBException 
   * @throws FileNotFoundException 
   */
  public synchronized void saveResearcher() throws FileNotFoundException, JAXBException {
  	Researchers newResearcher = new Researchers();
    for (Researcher curResList : this.newResList) {
    	newResearcher.getResearcher().add(curResList);
    }
    myIsernXmlSaver.saveResearchersXml(newResearcher);
  }
  
  /**
   * Gets a String List of organization.
   *
   * @return A String List of organization.
   * @throws JAXBException 
   * @throws IOException 
   */
  public synchronized void saveOrganization() throws JAXBException, IOException {
  	Organizations newOrganiztion = new Organizations();
    for (Organization curOrgList : this.newOrgList) {
    	newOrganiztion.getOrganization().add(curOrgList);
    }
    myIsernXmlSaver.saveOrganizationsXml(newOrganiztion);
  }
  
  /**
   * Searches for the ID being searched for.
   * 
   * @param id The ID being searched for.
   * @return True if the ID exists.
   */
  public synchronized boolean findId (String id) {
    boolean isIdValid = false;
    Set<String> idList = this.myIsern.mixl.getUniqueIds();
    
    for (String existingId : idList) {
      if (id.replace(' ', '_').equals(existingId)) {
        isIdValid = true;
        break;
      }
    }
    
    return isIdValid;
  }

}