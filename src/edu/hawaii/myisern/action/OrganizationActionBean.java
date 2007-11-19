package edu.hawaii.myisern.action;

import java.util.List;
import edu.hawaii.myisern.model.MyIsernModel;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class OrganizationActionBean implements ActionBean {
  /** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  private String organizationName;
  
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
   * Invoked by the page to indicate the organization being searched for.
   * 
   * @param orgName The name of the organization being searched for.
   */
  public void setOrgSearchField(String orgName) {
    this.organizationName = orgName;
  }
  
  /**
   * Gets a list of organization names.
   * 
   * @return A list of organization names.
   */
  public List<String> getOrganizations() {
    return myIsernModel.organizationsList();
  }
  
  /**
   * A handler that performs an action when the 'login' button is pressed.
   * 
   * @return A Resolution to display the main page when the login information is correct.
   */
  public Resolution orgLink() {
      return new ForwardResolution("/view_organization.jsp");
  }
  
  /**
   * A handler that performs an action when the 'Search' button in view_organization.jsp is pressed.
   *  
   * @return A resolution to display the edit_organization.jsp page if the organization exists.
   */
  public Resolution findOrganization() {
    return new ForwardResolution("/edit_organization.jsp");
  }
}
