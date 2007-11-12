package edu.hawaii.myisern.action;

import edu.hawaii.myisern.model.MyIsernModel;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * Provides a web interface for viewing, editing, and adding researchers, organizations, and
 * collaborations in MyIsern.
 * 
 * @author Marcius Bagwan
 */
public class MyIsernActionBean implements ActionBean {

  /**
   * Required by the ActionBean interface.
   */
  private ActionBeanContext context;

  /**
   * Our single MyIsern instance manipulated by all webapp users.
   */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();

  /** 
   * An error string, always displayed, but invisible if empty.
   */
  private String errorMessage = "";

  /** 
   * The username inputted by the user.
   */
  private String username;

  /**
   * The password inputted by the user.
   */
  private String password;

  /**
   * Returns the context. Required by the interface.
   * 
   * @return the ActionBeanContext.
   */
  public ActionBeanContext getContext() {
    return context;
  }

  /**
   * Sets the context. Required by the interface.
   * 
   * @param context The ActionBeanContext to set.
   */
  public void setContext(ActionBeanContext context) {
    this.context = context;
  }

  /**
   * Returns the error message, which may be empty. Indicates if someone tried to pop an empty
   * stack.
   * 
   * @return The error message.
   */
  public String getErrorMessage() {
    return this.errorMessage;
  }

  /**
   * Invoked by the page to indicate the username inputted.
   * 
   * @param username The username to verify.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Invoked by the page to indicate the password inputted.
   * 
   * @param password The password to verify.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * A handler that performs an action when the 'login' button is pressed.
   * 
   * @return A Resolution to display the main page when the login information is correct.
   */
  public Resolution login() {
    boolean loginUser = myIsernModel.login(this.username, this.password);

    if (loginUser) {
      return new ForwardResolution("/main.jsp");
    }
    else {
      this.errorMessage = "Error: Incorrect username or password entered.";
      return new ForwardResolution("/index.jsp");
    }
  }
}