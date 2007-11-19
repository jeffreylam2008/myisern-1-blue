package edu.hawaii.myisern.action;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import edu.hawaii.myisern.model.MyIsernModel;
import edu.hawaii.myisern.researchers.jaxb.Researcher;
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
  /** The name inputted by the user. */
  private String resName;
  /** The Organization inputted by the user. */
  private String resOrg;
  /** The email inputted by the user. */
  private String resEmail;
  /** The picture link inputted by the user. */
  private String resPicLink;
  /** The bio-statement inputted by the user. */
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
  
  /**
   * Gets the name of the researcher that was found.
   * 
   * @return The name of the researcher that was found.
   */
  public String getResName() {
    return this.resName;
  }
  
  /**
   * Gets the organization of the researcher that was found.
   * 
   * @return The organization of the researcher that was found.
   */
  public String getResOrg() {
    return this.resOrg;
  }
  
  /**
   * Gets the email of the researcher that was found.
   * 
   * @return The email of the researcher that was found.
   */
  public String getResEmail() {
    return this.resEmail;
  }
  
  /**
   * Gets the picture link of the researcher that was found.
   * 
   * @return The picture link of the researcher that was found.
   */
  public String getResPicLink() {
    return this.resPicLink;
  }
  
  /**
   * Gets the bio-statement of the researcher that was found.
   * 
   * @return The bio-statement of the researcher that was found.
   */
  public String getResBio() {
    return this.resBio;
  }
  
  public Resolution resAdd() throws IOException, JAXBException{
  	Researcher rList = new Researcher();
  	if (myIsernModel.findId(this.resName)) {
  		this.errorMessage = "ID already existed!";
  		return new ForwardResolution( "/add_researcher.jsp");
  	}
  	else {
	  	try {
	  		rList.setName(this.resName.replace('_', ' '));
	  	}
	    catch (NullPointerException e) {
	    	rList.setBioStatement("");
	    }
	  	try {
	  		rList.setOrganization(this.resOrg);
	  	}
	    catch (NullPointerException e) {
	    	rList.setBioStatement("");
	    }
	  	try {
	  		rList.setEmail(this.resEmail);
	  	}
	    catch (NullPointerException e) {
	    	rList.setBioStatement("");
	    }
	  	try {
	  		rList.setPictureLink(this.resPicLink);
	  	}
	    catch (NullPointerException e) {
	    	rList.setBioStatement("");
	    }
	  	try {
	  		rList.setBioStatement(this.resBio);
	  	}
	    catch (NullPointerException e) {
	    	rList.setBioStatement("");
	    }
	    myIsernModel.addResearcher(rList);
	    myIsernModel.saveResearcher();
	    this.errorMessage = "New researcher Added!";
	    return new ForwardResolution( "/add_researcher.jsp");
  	}
  }
}