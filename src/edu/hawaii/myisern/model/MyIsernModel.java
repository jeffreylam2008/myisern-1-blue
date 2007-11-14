package edu.hawaii.myisern.model;

import java.util.Iterator;
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
    if (username.equals("myisern")) {
      this.validUsername = true;
    }
    if (password.equals("password")) {
      this.validPassword = true;
    }
    if (validUsername && validPassword ) {
      this.allowLogin = true;
    }
    return this.allowLogin;
  }
  
  /**
   * Action to add collaboration.
   */
  public synchronized int addNew(String type) {
  	if (type.equals("1")) {
  		return 1;
  	}
  	if (type.equals("2")) {
  		return 2;
  	}
  	if (type.equals("3")) {
  		return 3;
  	}
  	return 0;
  }

  /**
   * Gets the researcher iterator attribute of the MyIsernModel object.
   *
   * @return The iterator value.
   */
  public synchronized List<String> researchersList() {
    return this.myIsern.printResearchers();
  }

  /**
   * Gets the organization iterator attribute of the MyIsernModel object.
   *
   * @return The iterator value.
   */
  public synchronized List<String> organizationsList() {
    return this.myIsern.printOrganizations();
  }

  /**
   * Gets the collaboration iterator attribute of the MyIsernModel object.
   *
   * @return The iterator value.
   */
  public synchronized List<String> collaborationsList() {
    return this.myIsern.printCollaborations();
  }
  
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