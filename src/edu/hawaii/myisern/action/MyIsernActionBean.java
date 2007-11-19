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
public class MyIsernActionBean implements ActionBean {
	/** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  /** The username inputted by the user. */
  private String username;
  private String password;
  /** The type selected by user. */
  private String type;

  private List<String> researcherData = new ArrayList<String>();
  private String resSearchField;
  private String researcherName;
  private String researcherOrg;
  private String researcherEmail;
  private String researcherPicLink;
  private String researcherBio;
  
  private List<String> organizationData = new ArrayList<String>();
  private String orgSearchField;
  private String organizationName;
  private String organizationType;
  private String organizationContact;
  private String organizationResearchers;
  private String organizationCountry;
  private String organizationKeywords;
  private String organizationDescription;
  private String organizationHomepage;

  private List<String> collaborationData = new ArrayList<String>();
  private String collabSearchField;
  private String collaborationName;
  private String collaborationOrgs;
  private String collaborationTypes;
  private String collaborationYears;
  private String collaborationOutcomes;
  private String collaborationDesc;
  
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
   * Invoked by the page to indicate the researcher being searched for.
   * 
   * @param name The name of the researcher being searched for.
   */
  public void setResSearchField(String name) {
    this.resSearchField = name;
  }

  /**
   * Invoked by the page to indicate the organization being searched for.
   * 
   * @param name The name of the organization being searched for.
   */
  public void setOrgSearchField(String name) {
    this.orgSearchField = name;
  }
  
  /**
   * Invoked by the page to indicate the collaboration being searched for
   * 
   * @param name The name of the collaboration being searched for.
   */
  public void setCollabSearchField(String name) {
    this.collabSearchField = name;
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
      return new ForwardResolution("/view_collaboration.jsp");
    }
    else {
      this.errorMessage = "Error: Incorrect username or password entered.";
      return new ForwardResolution("/index.jsp");
    }
  }
  
  /**
   * A handler that performs an action when the 'addType' button is pressed.
   * 
   * @return A Resolution to go to add collaboration page.
   */
  public Resolution addType() {
  	String dataType = myIsernModel.addNew(this.type);
  	if (dataType.contains("Collaboration")) {
  		return new ForwardResolution("/add_collaboration.jsp");
  	}
  	else if (dataType.contains("Organization")) {
  		return new ForwardResolution("/add_organization.jsp");
  	}
  	else if (dataType.contains("Researcher")) {
  		return new ForwardResolution("/add_researcher.jsp");
  	}
  	else {
  		return new ForwardResolution("/index.jsp");
  	}
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
   * Gets a list of organization names.
   * 
   * @return A list of organization names.
   */
  public List<String> getOrganizations() {
    return myIsernModel.organizationsList();
  }
  
  /**
   * Get a list of collaboration names.
   * 
   * @return A list of collaboration names.
   */
  public List<String> getCollaborations() {
    return myIsernModel.collaborationsList();
  }
  
  /**
   * Gets the name of the researcher that was found.
   * 
   * @return The name of the researcher that was found.
   */
  public String getResearcherName() {
    return this.researcherName;
  }

  /**
   * Gets the organization of the researcher that was found.
   * 
   * @return The organization of the researcher that was found.
   */
  public String getResearcherOrg() {
    return this.researcherOrg;
  }

  /**
   * Gets the email of the researcher that was found.
   * 
   * @return The email of the researcher that was found.
   */
  public String getResearcherEmail() {
    return this.researcherEmail;
  }

  /**
   * Gets the picture link of the researcher that was found.
   * 
   * @return The picture link of the researcher that was found.
   */
  public String getResearcherPicLink() {
    return this.researcherPicLink;
  }

  /**
   * Gets the bio-statement of the researcher that was found.
   * 
   * @return The bio-statement of the researcher that was found.
   */
  public String getResearcherBio() {
    return this.researcherBio;
  }
  
  /**
   * Gets the name of the organization that was found.
   * 
   * @return The name of the organization that was found.
   */
  public String getOrganizationName() {
    return this.organizationName;
  }

  /**
   * Gets the type of the organization that was found.
   * 
   * @return The type of the organization that was found.
   */
  public String getOrganizationType() {
    return this.organizationType;
  }

  /**
   * Gets the contact person of the organization that was found.
   * 
   * @return The contact person of the organization that was found.
   */
  public String getOrganizationContact() {
    return this.organizationContact;
  }

  /**
   * Gets the affiliated researchers of the organization that was found.
   * 
   * @return The affiliated researchers of the organization that was found.
   */
  public String getOrganizationResearchers() {
    return this.organizationResearchers;
  }

  /**
   * Gets the country of the organization that was found.
   * 
   * @return The country of the organization that was found.
   */
  public String getOrganizationCountry() {
    return this.organizationCountry;
  }

  /**
   * Gets the research keywords of the organization that was found.
   * 
   * @return The research keywords of the organization that was found.
   */
  public String getOrganizationKeywords() {
    return this.organizationKeywords;
  }

  /**
   * Gets the description of the organization that was found.
   * 
   * @return The description of the organization that was found.
   */
  public String getOrganizationDescription() {
    return this.organizationDescription;
  }

  /**
   * Gets the homepage of the organization that was found.
   * 
   * @return The homepage of the organization that was found.
   */
  public String getOrganizationHomepage() {
    return this.organizationHomepage;
  }

  /**
   * Gets the name of the collaboration that was found.
   * 
   * @return The name of the collaboration that was found.
   */
  public String getCollaborationName() {
    return this.collaborationName;
  }

  /**
   * Gets the collaborating-organizations of the collaboration that was found.
   * 
   * @return The collaborating-organizations of the collaboration that was found.
   */
  public String getCollaborationOrgs() {
    return this.collaborationOrgs;
  }

  /**
   * Gets the types of the collaboration that was found.
   * 
   * @return The types of the collaboration that was found.
   */
  public String getCollaborationTypes() {
    return this.collaborationTypes;
  }

  /**
   * Gets the years of the collaboration that was found.
   * 
   * @return The years of the collaboration that was found.
   */
  public String getCollaborationYears() {
    return this.collaborationYears;
  }

  /**
   * Gets the outcomes of the collaboration that was found.
   * 
   * @return The outcomes of the collaboration that was found.
   */
  public String getCollaborationOutcomes() {
    return this.collaborationOutcomes;
  }

  /**
   * Gets the description of the collaboration that was found.
   * 
   * @return The description of the collaboration that was found.
   */
  public String getCollaborationDesc() {
    return this.collaborationDesc;
  }
  
  /**
   * A handler that performs an action when the 'Researcher' button is pressed.
   * 
   * @return A Resolution to display the researchers list page.
   */
  public Resolution resLink() {
      return new ForwardResolution("/view_researcher.jsp");
  }

  /**
   * A handler that performs an action when the 'Organization' button is pressed.
   * 
   * @return A Resolution to display the organizations list page.
   */
  public Resolution orgLink() {
      return new ForwardResolution("/view_organization.jsp");
  }

  /**
   * A handler that performs an action when the 'Collaboration' button is pressed.
   * 
   * @return A Resolution to display the collaborations list page.
   */
  public Resolution collabLink() {
    return new ForwardResolution( "/view_collaboration.jsp");
  }

  /**
   * A handler that performs an action when the 'Search' button in view_researcher.jsp is pressed.
   * 
   * @return a resolution to display the edit_researcher.jsp page if the researcher exists.
   */
  public Resolution findResearcher() {
    boolean idExists = myIsernModel.findId(this.resSearchField);
    
    if (idExists) {
      this.researcherData = myIsernModel.getResearcher(this.resSearchField);
      this.researcherName = this.researcherData.remove(0);
      this.researcherOrg = this.researcherData.remove(0);
      this.researcherEmail = this.researcherData.remove(0);
      this.researcherPicLink = this.researcherData.remove(0);
      this.researcherBio = this.researcherData.remove(0);
      
      
      return new ForwardResolution("/edit_researcher.jsp");
    }
    else {
      return new ForwardResolution("/view_researcher.jsp");
    }   
  }
  
  /**
   * A handler that performs an action when the 'Search' button in view_organization.jsp is pressed.
   *  
   * @return A resolution to display the edit_organization.jsp page if the organization exists.
   */
  public Resolution findOrganization() {
    boolean idExists = myIsernModel.findId(this.orgSearchField);
    
    if (idExists) {
      this.organizationData = myIsernModel.getOrganization(this.orgSearchField);
      this.organizationName = this.organizationData.remove(0);
      this.organizationType = this.organizationData.remove(0);
      this.organizationContact = this.organizationData.remove(0);
      this.organizationResearchers = this.organizationData.remove(0);
      this.organizationCountry = this.organizationData.remove(0);
      this.organizationKeywords = this.organizationData.remove(0);
      this.organizationDescription = this.organizationData.remove(0);
      this.organizationHomepage = this.organizationData.remove(0);
      
      return new ForwardResolution("/edit_organization.jsp");
    }
    else {
      return new ForwardResolution("/view_organization.jsp");
    }
  }
  
  /**
   * A handler that performs an action when the 'Search' button in view_collaboration.jsp is 
   * pressed.
   * 
   * @return A resolution to display the edit_collaboration.jsp page if the collaboration exists.
   */
  public Resolution findCollaboration() {
    boolean idExists = myIsernModel.findId(this.collabSearchField);
    
    if (idExists) {
      this.collaborationData = myIsernModel.getCollaboration(this.collabSearchField);
      this.collaborationName = this.collaborationData.remove(0);
      this.collaborationOrgs = this.collaborationData.remove(0);
      this.collaborationTypes = this.collaborationData.remove(0);
      this.collaborationYears = this.collaborationData.remove(0);
      this.collaborationOutcomes = this.collaborationData.remove(0);
      this.collaborationDesc = this.collaborationData.remove(0);
      
      return new ForwardResolution("/edit_collaboration.jsp");
    }
    else {
      return new ForwardResolution("/view_collaboration.jsp");
    }
  }
}
