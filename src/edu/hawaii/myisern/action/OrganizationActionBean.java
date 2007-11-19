package edu.hawaii.myisern.action;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import edu.hawaii.myisern.model.MyIsernModel;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import edu.hawaii.myisern.organizations.jaxb.AffiliatedResearchers;
import edu.hawaii.myisern.organizations.jaxb.ResearchKeywords;

/**
 * Provides a web interface for viewing, editing, and adding researchers, organizations, and
 * collaborations in MyIsern.
 * 
 * @author Marcius Bagwan
 * @author Jeffrey Lam
 */
public class OrganizationActionBean implements ActionBean {
	/** Required by the ActionBean interface. */
  private ActionBeanContext context;
  /** Our single MyIsern instance manipulated by all webapp users. */
  private MyIsernModel myIsernModel = MyIsernModel.getInstance();
  /** An error string, always displayed, but invisible if empty. */
  private String errorMessage = "";
  /** The organization name inputted by the user. */
  private String orgName;
  /** The organization type inputted by the user. */
  private String orgType;
  /** The contact inputted by the user. */
  private String orgContact;
  /** The Affiliated inputted by the user. */
  private String orgAffiliated;
  /** The Country inputted by the user. */
  private String orgCountry;
  /** The Keyword inputted by the user. */
  private String orgKeyword;
  /** The Description inputted by the user. */
  private String orgDescription;
  /** The HomePage inputted by the user. */
  private String orgHomePage;
  
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
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgName The organization Name to verify.
   */
  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgType The organization Type to verify.
   */
  public void setOrgType(String orgType) {
    this.orgType = orgType;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgContact The organization Contact to verify.
   */
  public void setOrgContact(String orgContact) {
    this.orgContact = orgContact;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgAffiliated The organization Affiliated to verify.
   */
  public void setOrgAffiliated(String orgAffiliated) {
    this.orgAffiliated = orgAffiliated;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgCountry The organization Country to verify.
   */
  public void setOrgCountry(String orgCountry) {
    this.orgCountry = orgCountry;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgKeyword The organization Keyword to verify.
   */
  public void setOrgKeyword(String orgKeyword) {
    this.orgKeyword = orgKeyword;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgDescription The organization Description to verify.
   */
  public void setOrgDescription(String orgDescription) {
    this.orgDescription = orgDescription;
  }
  
  /**
   * Invoked by the page to indicate the organization Name inputted.
   * 
   * @param orgHomePage The organization HomePage to verify.
   */
  public void setOrgHomePage(String orgHomePage) {
    this.orgHomePage = orgHomePage;
  }
  
  /**
   * Gets the name of the organization that was found.
   * 
   * @return The name of the organization that was found.
   */
  public String getOrgName() {
    return this.orgName;
  }
  
  /**
   * Gets the Type of the organization that was found.
   * 
   * @return The Type of the organization that was found.
   */
  public String getOrgType() {
    return this.orgType;
  }
  
  /**
   * Gets the Contact of the organization that was found.
   * 
   * @return The Contact of the organization that was found.
   */
  public String getOrgContact() {
    return this.orgContact;
  }
  
  /**
   * Gets the Affiliated of the organization that was found.
   * 
   * @return The Affiliated of the organization that was found.
   */
  public String getOrgAffiliated() {
    return this.orgAffiliated;
  }
  
  /**
   * Gets the Country of the organization that was found.
   * 
   * @return The Country of the organization that was found.
   */
  public String getOrgCountry() {
    return this.orgCountry;
  }
  
  /**
   * Gets the Keyword of the organization that was found.
   * 
   * @return The Keyword of the organization that was found.
   */
  public String getOrgKeyword() {
    return this.orgKeyword;
  }
  
  /**
   * Gets the Description of the organization that was found.
   * 
   * @return The Description of the organization that was found.
   */
  public String getOrgDescription() {
    return this.orgDescription;
  }
  
  /**
   * Gets the HomePage of the organization that was found.
   * 
   * @return The HomePage of the organization that was found.
   */
  public String getOrgHomePage() {
    return this.orgHomePage;
  }
  
  public Resolution orgAdd() throws IOException, JAXBException{
  	String sign = ",";
  	Organization oList = new Organization();
  	if (myIsernModel.findId(this.orgName)) {
  		this.errorMessage = "ID already existed!";
  		return new ForwardResolution( "/add_organization.jsp");
  	}
  	else {
	  	try {
	  		oList.setName(this.orgName.replace('_', ' '));
	  	}
	    catch (NullPointerException e) {
	    	oList.setName("");
	    }
	  	try {
	  		oList.setType(this.orgType);
	  	}
	    catch (NullPointerException e) {
	    	oList.setType("");
	    }
	  	try {
	  		oList.setContact(this.orgContact);
	  	}
	    catch (NullPointerException e) {
	    	oList.setContact("");
	    }
	    AffiliatedResearchers Arfiliate = new AffiliatedResearchers();
      try {
	      String[] tempAffilR = this.orgAffiliated.split(sign);
	      for (String curr : tempAffilR) {
	      	Arfiliate.getAffiliatedResearcher().add(curr.trim());
	      }
      }
      catch (NullPointerException e) {
      	Arfiliate.getAffiliatedResearcher().add("");
      }
      oList.setAffiliatedResearchers(Arfiliate);
	  	try {
	  		oList.setCountry(this.orgCountry);
	  	}
	    catch (NullPointerException e) {
	    	oList.setCountry("");
	    }
      ResearchKeywords newRk = new ResearchKeywords();
      try {
      String[] tempKeywords = this.orgKeyword.split(sign);
      for (String curr : tempKeywords) {
        newRk.getResearchKeyword().add(curr.trim());
      }
      }
      catch (NullPointerException e) {
        newRk.getResearchKeyword().add("");
      }
      oList.setResearchKeywords(newRk);
	  	try {
	  		oList.setResearchDescription(this.orgDescription);
	  	}
	    catch (NullPointerException e) {
	    	oList.setResearchDescription("");
	    }
	  	try {
	  		oList.setHomePage(this.orgHomePage);
	  	}
	    catch (NullPointerException e) {
	    	oList.setHomePage("");
	    }
	    myIsernModel.addOrganization(oList);
	    myIsernModel.saveOrganization();
	    this.errorMessage = "New researcher Added!";
	    return new ForwardResolution( "/add_organization.jsp");
  	}
  }
}