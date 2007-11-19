package edu.hawaii.myisern.action;

import java.util.ArrayList;
import java.util.List;
import edu.hawaii.myisern.model.MyIsernModel;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class ResearcherActionBean implements ActionBean {
  /** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  
  private String researcherName;
  private String researcherOrg;
  private String researcherEmail;
  private String researcherPicLink;
  private String researcherBio;
  
  private List<String> researcherData = new ArrayList<String>();
  
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
   * Invoked by the page to indicate the researcher being searched for.
   * 
   * @param researcherName The name of the researcher being searched for.
   */
  public void setResSearchField(String researcherName) {
    this.researcherName = researcherName;
  }
  
  /**
   * Gets a list of researcher names.
   * 
   * @return A list of researcher names.
   */
  public List<String> getResearchers() {
    return myIsernModel.researchersList();
  }
  
  /**
   * A handler that performs an action when the 'login' button is pressed.
   * 
   * @return A Resolution to display the main page when the login information is correct.
   */
  public Resolution resLink() {
      return new ForwardResolution("/view_researcher.jsp");
  }

  /**
   * A handler that performs an action when the 'Search' button in view_researcher.jsp is pressed.
   * 
   * @return a resolution to display the edit_researcher.jsp page if the researcher exists.
   */
  public Resolution findResearcher() {
    boolean idExists = myIsernModel.findId(this.researcherName);
    
    if (idExists) {
      this.researcherData = myIsernModel.getResearcher(this.researcherName);
      
      
      return new ForwardResolution("/edit_researcher.jsp");
    }
    else {
      return new ForwardResolution("/view_researcher.jsp");
    }   
  }
}
