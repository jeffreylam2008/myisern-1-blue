package edu.hawaii.myisern.model;

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

  /**
   * Private constructor used to create a single instance of stack.
   */
  private MyIsernModel() {
    try {
      this.myIsern = new MyIsern();
    }
    catch (Exception e) {
      String exceptionHandler = e.toString();
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
}
