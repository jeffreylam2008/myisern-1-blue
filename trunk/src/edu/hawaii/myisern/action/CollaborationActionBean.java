package edu.hawaii.myisern.action;

import java.util.List;
import edu.hawaii.myisern.model.MyIsernModel;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class CollaborationActionBean implements ActionBean {
  /** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  
  private String collaborationName;
  
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
   * Gets a list of collaboration names.
   * 
   * @return A list of collaboration names.
   */
  public List<String> getCollaborations() {
    return myIsernModel.collaborationsList();
  }
  
  /**
   * A handler that performs an action when the 'login' button is pressed.
   * 
   * @return A Resolution to display the main page when the login information is correct.
   */
  public Resolution collabLink() {
      return new ForwardResolution("/view_collaboration.jsp");
  }
  
  /**
   * A handler that performs an action when the 'Search' button in view_collaobration.jsp is
   * pressed.
   * 
   * @return A resolution to display the edit_collaboration.jsp page if the collaboration exists.
   */
  public Resolution findCollaboration() {
    return new ForwardResolution("/edit_collaboration.jsp");
  }
  
  /**
   * Invoked by the page to indicate the collaboration being searched for.
   * 
   * @param collabName The name of the collaboration being searched for.
   */
  public void setCollabSearchField(String collabName) {
    this.collaborationName = collabName;
  }
}
