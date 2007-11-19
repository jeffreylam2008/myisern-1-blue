package edu.hawaii.myisern.action;

import java.util.ArrayList;
import java.util.List;
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
 * @author Jeffrey Lam
 */
public class ResearcherActionBean implements ActionBean {
	/** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  /** The username inputted by the user. */
  private String resName;
  /** The username inputted by the user. */
  private String resOrg;
  /** The username inputted by the user. */
  private String resEmail;
  /** The username inputted by the user. */
  private String resPicLink;
  /** The username inputted by the user. */
  private String resBio;
  /**
   * Returns the context. Required by the interface.
   * 
   * @return the ActionBeanContext.
   */
  public ActionBeanContext getContext() {
    return context;
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
   * Sets the context. Required by the interface.
   * 
   * @param context The ActionBeanContext to set.
   */
  public void setContext(ActionBeanContext context) {
    this.context = context;
  }

  /**
   * Invoked by the page to indicate the researcher Name inputted.
   * 
   * @param resName The researcher Name to verify.
   */
  public void setResName(String resName) {
    this.resName = resName;
  }
  
  /**
   * Invoked by the page to indicate the researcher Name inputted.
   * 
   * @param resName The researcher Name to verify.
   */
  public void setResOrg(String resOrg) {
    this.resOrg = resOrg;
  }
  
  /**
   * Invoked by the page to indicate the researcher Name inputted.
   * 
   * @param resName The researcher Name to verify.
   */
  public void setResEmail(String resEmail) {
    this.resEmail = resEmail;
  }
  
  /**
   * Invoked by the page to indicate the researcher Name inputted.
   * 
   * @param resName The researcher Name to verify.
   */
  public void setResPicLink(String resPicLink) {
    this.resPicLink = resPicLink;
  }
  
  /**
   * Invoked by the page to indicate the researcher Name inputted.
   * 
   * @param resBio The researcher Name to verify.
   */
  public void setReBio(String resBio) {
    this.resBio = resBio;
  }
  
}