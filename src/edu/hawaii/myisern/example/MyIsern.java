package edu.hawaii.myisern.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.BigInteger;
import javax.xml.bind.JAXBException;
import edu.hawaii.myisern.collaborations.jaxb.CollaboratingOrganizations;
import edu.hawaii.myisern.collaborations.jaxb.Collaboration;
import edu.hawaii.myisern.collaborations.jaxb.CollaborationTypes;
import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.collaborations.jaxb.OutcomeTypes;
import edu.hawaii.myisern.collaborations.jaxb.Years;
import edu.hawaii.myisern.organizations.jaxb.AffiliatedResearchers;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.organizations.jaxb.ResearchKeywords;
import edu.hawaii.myisern.researchers.jaxb.Researcher;
import edu.hawaii.myisern.researchers.jaxb.Researchers;

/**
 * Provides information on the organizations, collaborations, and researchers of the ISERN
 * community.
 * 
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class MyIsern {
  /** MyIsern's own xml loader */
  private MyIsernXmlLoader mixl;
  /** MyIsern's own xml saver */
  MyIsernXmlSaver mixs;
  /** Researcher object that will hold all new and old researchers */
  private Researchers rList = new Researchers();
  /** Organization object that will hold all new and old organizations */
  private Organizations oList = new Organizations();
  /** Collaboration object that will hold all new and old collaborations */
  private Collaborations cList = new Collaborations();

  /**
   * Initializes command line options.
   * 
   * @throws Exception thrown if error is encountered.
   */
  public MyIsern() throws Exception {
    this.mixl = new MyIsernXmlLoader();
    this.mixs = new MyIsernXmlSaver();
    this.oList = this.mixl.getOrganizations();
    this.cList = this.mixl.getCollaborations();
    this.rList = this.mixl.getResearchers();
  }

  /**
   * Gets all researcher names in the XML data file and returns an iterator over the list of all
   * researchers found.
   * 
   * @return An iterator over the list of all researchers.
   */
  public Iterator<Object> printResearchers() {
    List<Researcher> researcherList;
    researcherList = this.rList.getResearcher();
    List<Object> elementsList = new ArrayList<Object>();

    for (Researcher currentResearcher : researcherList) {
      elementsList.add(currentResearcher.getName());
    }
    
    return elementsList.iterator();
  }

  /**
   * Gets all organization names in the XML data file and returns an iterator over the list of all
   * organizations found.
   * 
   * @return An iterator over the list of all organizations.
   */
  public Iterator<Object> printOrganizations() {
    List<Organization> organizationList;
    organizationList = this.oList.getOrganization();
    List<Object> elementsList = new ArrayList<Object>();

    for (Organization current : organizationList) {
      elementsList.add(current.getName());
    }
    
    return elementsList.iterator();
  }

  /**
   * Gets all collaboration names in the XML data file and returns an iterator over the list of all
   * collaborations found.
   * 
   * @return An iterator over the list of all collaborations.
   */
  public Iterator<Object> printCollaborations() {
    List<Collaboration> collaborationList;
    collaborationList = cList.getCollaboration();
    List<Object> elementsList = new ArrayList<Object>();

    for (Collaboration current : collaborationList) {
      elementsList.add(current.getName());
    }
    
    return elementsList.iterator();
  }
  
  /**
   * Prints the Researcher table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of the researcher to be printed.
   * @return A list of a list of objects containing the specified researcher's data.
   */
  public List<List<Object>> printResearcher(String id) {
    List<Object> researcherName = new ArrayList<Object>();
    List<Object> researcherOrganization = new ArrayList<Object>();
    List<Object> researcherBioStatement = new ArrayList<Object>();
    List<Object> researcherPictureLink = new ArrayList<Object>();
    List<Object> researcherEmail = new ArrayList<Object>();
    List<List<Object>> researcherDataList = new ArrayList<List<Object>>();
    
    for (Researcher currentResearcher : this.mixl.getResearchers().getResearcher()) {
      if (currentResearcher.getName().replace(' ', '_').equals(id)) {
        researcherName.add(currentResearcher.getName());
        researcherDataList.add(researcherName);
        
        researcherOrganization.add(currentResearcher.getOrganization());
        researcherDataList.add(researcherOrganization);
        
        researcherBioStatement.add(currentResearcher.getBioStatement());
        researcherDataList.add(researcherBioStatement);
        
        researcherPictureLink.add(currentResearcher.getPictureLink());
        researcherDataList.add(researcherPictureLink);
        
        researcherEmail.add(currentResearcher.getEmail());
        researcherDataList.add(researcherEmail);
      }
      break;
    }
    return researcherDataList;
  }
  
  /**
   * Prints the Organization table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of an organization to be printed.
   * @return A list of a list of objects containing the specified organization's data.
   */
  public List<List<Object>> printOrganization(String id) {
    List<Object> organizationName = new ArrayList<Object>();
    List<Object> organizationType = new ArrayList<Object>();
    List<Object> organizationContact = new ArrayList<Object>();
    List<Object> organizationAffiliatedResearchers = new ArrayList<Object>();
    List<Object> organizationCountry = new ArrayList<Object>();
    List<Object> organizationKeywords = new ArrayList<Object>();
    List<Object> organizationDescription = new ArrayList<Object>();
    List<Object> organizationHomepage = new ArrayList<Object>();
    List<List<Object>> organizationDataList = new ArrayList<List<Object>>();
    
    for (Organization current : this.mixl.getOrganizations().getOrganization()) {
      if (current.getName().replace(' ', '_').equals(id)) {
        List<String> stringList;
        organizationName.add(current.getName());
        organizationDataList.add(organizationName);

        organizationType.add(current.getType());
        organizationDataList.add(organizationType);

        organizationContact.add(current.getContact());
        organizationDataList.add(organizationContact);

        // Prints all affiliated researchers with organization
        AffiliatedResearchers affiliatedResearchers;
        affiliatedResearchers = current.getAffiliatedResearchers();
        stringList = affiliatedResearchers.getAffiliatedResearcher();
        
        for (String currentString : stringList) {
          organizationAffiliatedResearchers.add(currentString);
        }
        organizationDataList.add(organizationAffiliatedResearchers);

        organizationCountry.add(current.getCountry());
        organizationDataList.add(organizationCountry);

        // Prints Research Keywords for organization
        ResearchKeywords researchKeywords;
        researchKeywords = current.getResearchKeywords();
        stringList = researchKeywords.getResearchKeyword();

        for (String currentString : stringList) {
          organizationKeywords.add(currentString);
        }
        organizationDataList.add(organizationKeywords);

        organizationDescription.add(current.getResearchDescription());
        organizationDataList.add(organizationDescription);

        organizationHomepage.add(current.getHomePage());
        organizationDataList.add(organizationHomepage);
      }
      break;
    }
    return organizationDataList;
  }
}