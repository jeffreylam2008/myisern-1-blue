package edu.hawaii.myisern.model;

import java.util.List;
import java.util.Set;
import edu.hawaii.myisern.example.MyIsern;

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
    }
    catch (Exception e) {
      this.exceptionPlaceholder = e.toString();
    }
  }

  /**
   * Get the single instance of StackModel object.
   * 
   * @return A StackModel.
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