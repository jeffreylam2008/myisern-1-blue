package edu.hawaii.myisern.action;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import java.math.BigInteger;
import edu.hawaii.myisern.model.MyIsernModel;
import edu.hawaii.myisern.collaborations.jaxb.CollaboratingOrganizations;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.CollaborationTypes;
import edu.hawaii.myisern.collaborations.jaxb.OutcomeTypes;
import edu.hawaii.myisern.collaborations.jaxb.Years;
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
public class CollaborationActionBean implements ActionBean {
	/** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  /** The collaboration name inputted by the user. */
  private String collabName;
  /** The collaboration organization inputted by the user. */
  private String collabOrganizations;
  /** The collaboration type inputted by the user. */
  private String collabType;
  /** The collaboration years inputted by the user. */
  private String collabYears;
  /** The collaboration outcome type inputted by the user. */
  private String collabOutcomeTypes;
  /** The collaboration discription inputted by the user. */
  private String collabDescription;
  
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
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabName The collaboration Name to verify.
   */
  public void setCollabName(String collabName) {
    this.collabName = collabName;
  }
  
  /**
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabOrganizations The collaboration Name to verify.
   */
  public void setCollabOrganizations(String collabOrganizations) {
    this.collabOrganizations = collabOrganizations;
  }
  
  /**
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabType The collaboration Type to verify.
   */
  public void setCollabType(String collabType) {
    this.collabType = collabType;
  }
  
  /**
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabYears The collaboration Contact to verify.
   */
  public void setCollabYears(String collabYears) {
    this.collabYears = collabYears;
  }
  
  /**
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabOutcomeTypes The collaboration Affiliated to verify.
   */
  public void setCollabOutcomeTypes(String collabOutcomeTypes) {
    this.collabOutcomeTypes = collabOutcomeTypes;
  }
  
  /**
   * Invoked by the page to indicate the collaboration Name inputted.
   * 
   * @param collabDescription The collaboration Country to verify.
   */
  public void setCollabDescription(String collabDescription) {
    this.collabDescription = collabDescription;
  }
  
  /**
   * Gets the name of the collaboration that was found.
   * 
   * @return The name of the collaboration that was found.
   */
  public String getCollabName() {
    return this.collabName;
  }
  
  /**
   * Gets the Type of the collaboration that was found.
   * 
   * @return The Type of the collaboration that was found.
   */
  public String getCollabOrganizations() {
    return this.collabOrganizations;
  }
  
  /**
   * Gets the Contact of the collaboration that was found.
   * 
   * @return The Contact of the collaboration that was found.
   */
  public String getCollabType() {
    return this.collabType;
  }
  
  /**
   * Gets the Affiliated of the collaboration that was found.
   * 
   * @return The Affiliated of the collaboration that was found.
   */
  public String getCollabYears() {
    return this.collabYears;
  }
  
  /**
   * Gets the Country of the collaboration that was found.
   * 
   * @return The Country of the collaboration that was found.
   */
  public String getCollabOutcomeTypes() {
    return this.collabOutcomeTypes;
  }
  
  /**
   * Gets the Keyword of the collaboration that was found.
   * 
   * @return The Keyword of the collaboration that was found.
   */
  public String getCollabDescription() {
    return this.collabDescription;
  }
  
  public Resolution orgAdd() throws IOException, JAXBException{
  	String sign = ",";
  	Collaboration cList = new Collaboration();
  	if (myIsernModel.findId(this.collabName)) {
  		this.errorMessage = "ID already existed!";
  		return new ForwardResolution( "/add_collaboration.jsp");
  	}
  	else {
	  	try {
	  		cList.setName(this.collabName.replace('_', ' '));
	  	}
	    catch (NullPointerException e) {
	    	cList.setName("");
	    }
	    
	    // Add Collaborating Organizations
	    CollaboratingOrganizations newCollabOrgs = new CollaboratingOrganizations();
      try {
        String[] tempCollabOrg = this.collabOrganizations.split(sign);
        for (String curr : tempCollabOrg) {
        	newCollabOrgs.getCollaboratingOrganization().add(curr.trim());
        }
      }
      catch (NullPointerException e) {
      	newCollabOrgs.getCollaboratingOrganization().add("");

      }
      cList.setCollaboratingOrganizations(newCollabOrgs);
      
      // Add Collaboration Types
	    CollaborationTypes newCollabTypes = new CollaborationTypes();
      try {
	      String[] tempCollabTypes = this.collabType.split(sign);
	      for (String curr : tempCollabTypes) {
	      	newCollabTypes.getCollaborationType().add(curr.trim());
	      }
      }
      catch (NullPointerException e) {
      	newCollabTypes.getCollaborationType().add("");
      }
      cList.setCollaborationTypes(newCollabTypes);

      // Add Collaboration Years
      Years newYears = new Years();
      try {
	      String[] tempYears = this.collabYears.split(sign);
	      for (String curr : tempYears) {
	        BigInteger bigNum = new BigInteger(curr.trim());
	        newYears.getYear().add(bigNum);
	      }
      }
      catch (NullPointerException e) {
        BigInteger bigNum = new BigInteger("1990");
        newYears.getYear().add(bigNum);
      }
      cList.setYears(newYears);
      
      // Add Collaboration outcomes
      OutcomeTypes newOutcomeTypes = new OutcomeTypes();
      try {
	      String[] tempOutcomeTypes = this.collabOutcomeTypes.split(sign);
	      for (String curr : tempOutcomeTypes) {
	        newOutcomeTypes.getOutcomeType().add(curr.trim());
	      }
      }
      catch (NullPointerException e) {
        newOutcomeTypes.getOutcomeType().add("");
      }
      cList.setOutcomeTypes(newOutcomeTypes);

	    
	    // Add collaboration discription 
	  	try {
	  		cList.setDescription(this.collabDescription);
	  	}
	    catch (NullPointerException e) {
	    	cList.setDescription("");
	    }
	    myIsernModel.addCollaboration(cList);
	    myIsernModel.saveCollaboration();
	    this.errorMessage = "New researcher Added!";
	    return new ForwardResolution( "/add_collaboration.jsp");
  	}
  }
}