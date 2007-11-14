package edu.hawaii.myisern.action;

import java.util.Iterator;
import edu.hawaii.myisern.model.MyIsernModel;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Provides a web interface for viewing, editing, and adding researchers, organizations, and
 * collaborations in MyIsern.
 * 
 * @author Marcius Bagwan
 */
public class MyIsernActionBean implements ActionBean {
	/** Required by the ActionBean interface. */
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
   * The type selected by user. 
   */
  private String type;
  
  /**
   * The name of a researcher, organization, or collaboration.
   */
  //private String name;
  
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
   * Invoked by the page to indicate the type selected.
   * 
   * @param type The type to verify.
   */
  public void setType(String type) {
    this.type = type;
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
  
  /**
   * Provides an iterator over a researcher list to the page.
   * 
   * @return A researchers list iterator.
   */
  public Iterator<Object> getResearchersIterator() {
    return myIsernModel.researchersIterator();
  }
  
  /**
   * Provides an iterator over an organization list to the page.
   * 
   * @return An organizations list iterator.
   */
  public Iterator<Object> getOrganizationsIterator() {
    return myIsernModel.organizationsIterator();
  }
  
  /**
   * Provides an iterator over a collaborations list to the page.
   * 
   * @return A collaborations list iterator.
   */
  public Iterator<Object> getCollaborationsIterator() {
    return myIsernModel.collaborationsIterator();
  }
  
  /**
   * A handler that performs an action when the 'addType' button is pressed.
   * 
   * @return A Resolution to go to add collaboration page.
   */
  public Resolution addType() {
  	int typeNum = myIsernModel.addNew(this.type);
  	if (typeNum == 1) {
  		return new ForwardResolution("/add_collaboration.jsp");
  	}
  	else if (typeNum == 2) {
  		return new ForwardResolution("/add_organization.jsp");
  	}
  	else if (typeNum == 3) {
  		return new ForwardResolution("/add_researcher.jsp");
  	}
  	else {
  		return new ForwardResolution("/index.jsp");
  	}
  }
}