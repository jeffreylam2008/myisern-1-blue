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
  public Iterator<Object> printResearcher(String id) {
    List<Object> researcherData = new ArrayList<Object>();
    for (Researcher currentResearcher : this.mixl.getResearchers().getResearcher()) {
      if (currentResearcher.getName().replace(' ', '_').equals(id)) {
        
        researcherData.add(currentResearcher.getName());
        
        researcherData.add(currentResearcher.getOrganization());
        
        researcherData.add(currentResearcher.getBioStatement());
        
        researcherData.add(currentResearcher.getPictureLink());
        
        researcherData.add(currentResearcher.getEmail());
        
        break;
      }
    }
    return researcherData.iterator();
  }
}