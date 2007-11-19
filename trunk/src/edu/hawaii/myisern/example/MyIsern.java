package edu.hawaii.myisern.example;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
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
  public MyIsernXmlLoader mixl;
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
  public List<String> printResearchers() {
    List<Researcher> researcherList;
    researcherList = this.rList.getResearcher();
    List<String> elementsList = new ArrayList<String>();

    for (Researcher currentResearcher : researcherList) {
      elementsList.add(currentResearcher.getName());
    }
    
    return elementsList;
  }

  /**
   * Gets all organization names in the XML data file and returns an iterator over the list of all
   * organizations found.
   * 
   * @return An iterator over the list of all organizations.
   */
  public List<String> printOrganizations() {
    List<Organization> organizationList;
    organizationList = this.oList.getOrganization();
    List<String> elementsList = new ArrayList<String>();

    for (Organization current : organizationList) {
      elementsList.add(current.getName());
    }
    
    return elementsList;
  }

  /**
   * Gets all collaboration names in the XML data file and returns an iterator over the list of all
   * collaborations found.
   * 
   * @return An iterator over the list of all collaborations.
   */
  public List<String> printCollaborations() {
    List<Collaboration> collaborationList;
    collaborationList = cList.getCollaboration();
    List<String> elementsList = new ArrayList<String>();

    for (Collaboration current : collaborationList) {
      elementsList.add(current.getName());
    }
    
    return elementsList;
  }
  
  /**
   * Prints the Researcher table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of the researcher to be printed.
   * @return A list of a list of objects containing the specified researcher's data.
   */
  public List<String> printResearcher(String id) {
    List<String> researcherData = new ArrayList<String>();
    
    for (Researcher currentResearcher : this.mixl.getResearchers().getResearcher()) {
      if (currentResearcher.getName().replace('_', ' ').equals(id)) {
        
        researcherData.add(currentResearcher.getName());
        
        researcherData.add(currentResearcher.getOrganization());
        
        researcherData.add(currentResearcher.getEmail());
        
        researcherData.add(currentResearcher.getPictureLink());
        
        researcherData.add(currentResearcher.getBioStatement());
        
        break;
      }
    }
    return researcherData;
  }
  
  /**
   * Searches for an organization and stores all data into a String List.
   * 
   * @param id The id of the organization being searched for.
   * @return A String List containing all data of the organization.
   */
  public List<String> printOrganization(String id) {
    List<String> organizationData = new ArrayList<String>();
    
    for (Organization current : this.mixl.getOrganizations().getOrganization()) {
      if (current.getName().replace('_', ' ').equals(id)) {
        List<String> stringList;
        String concatenationString = "";

        organizationData.add(current.getName());

        organizationData.add(current.getType());

        organizationData.add(current.getContact());

        AffiliatedResearchers affiliatedResearchers;
        affiliatedResearchers = current.getAffiliatedResearchers();
        stringList = affiliatedResearchers.getAffiliatedResearcher();

        for (String currentString : stringList) {
          concatenationString = concatenationString + currentString + ", ";
        }
        organizationData.add(concatenationString);

        organizationData.add(current.getCountry());

        // Prints Research Keywords for organization
        ResearchKeywords researchKeywords;
        researchKeywords = current.getResearchKeywords();
        stringList = researchKeywords.getResearchKeyword();
        concatenationString = "";

        for (String currentString : stringList) {
          concatenationString = concatenationString + currentString + ", ";
        }
        organizationData.add(concatenationString);

        organizationData.add(current.getResearchDescription());

        organizationData.add(current.getHomePage());
        
        break;
      }
    }
    return organizationData;
  }
  
  /**
   * Searches for a collaboration and stores all data into a String List.
   * 
   * @param id The id of the collaboration being searched for.
   * @return A String List containing all data of the collaboration.
   */
  public List<String> printCollaboration(String id) {
    List<String> collaborationData = new ArrayList<String>();

    for (Collaboration current : this.mixl.getCollaborations().getCollaboration()) {
      if (current.getName().replace('_', ' ').equals(id)) {
        List<String> stringList;
        List<BigInteger> bigIntList;
        String concatString = "";

        collaborationData.add(current.getName());

        // Prints Organizations part of Collaboration
        CollaboratingOrganizations collaboratingOrganizations;
        collaboratingOrganizations = current.getCollaboratingOrganizations();
        stringList = collaboratingOrganizations.getCollaboratingOrganization();

        for (String currentOrg : stringList) {
          concatString = concatString + currentOrg + ", ";
        }
        collaborationData.add(concatString);

        // Prints type of collaboration
        CollaborationTypes collaborationTypes;
        collaborationTypes = current.getCollaborationTypes();
        stringList = collaborationTypes.getCollaborationType();
        concatString = "";

        for (String currentCollabType : stringList) {
          concatString = concatString + currentCollabType + ", ";
        }
        collaborationData.add(concatString);

        // Prints all Years that Organizations were in Collaboration
        Years years;
        years = current.getYears();
        bigIntList = years.getYear();
        concatString = "";

        for (BigInteger currentYears : bigIntList) {
          concatString = concatString + currentYears.toString() + ", ";
        }
        collaborationData.add(concatString);
        
        // Prints all Outcome types
        OutcomeTypes outcomeTypes;
        outcomeTypes = current.getOutcomeTypes();
        stringList = outcomeTypes.getOutcomeType();
        concatString = "";

        for (String currentOutcomeType : stringList) {
          concatString = concatString + currentOutcomeType + ", ";
        }
        collaborationData.add(concatString);

        collaborationData.add(current.getDescription());
        
        break;
      }
    }
    return collaborationData;
  }
}