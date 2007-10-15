package edu.hawaii.myisern.example;

//import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
//import java.util.Map;
//import java.util.Set;
import java.math.BigInteger;
//import java.util.List;
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
  /** Holds values passed from the command line */
  private String[] commandLineArgs;
  MyIsernXmlLoader mixl;
  private String newLineNewTab = "\n\t";
  private String nameTableField = "\nName: ";

  /**
   * Initializes command line options.
   * 
   * @param args Command line arguments.
   */
  MyIsern(String[] args) {
    this.commandLineArgs = args;
  }

  /**
   * Passes the command line options given to the program.
   * 
   * @param args containing command line arguments.
   * @throws Exception if there is an exception
   */
  public static void main(String[] args) throws Exception {
    MyIsern myIsern = new MyIsern(args);
    /* boolean myIsernRunCheck = */
    myIsern.runMyIsern();

    /*
     * if (myIsernRunCheck) { System.out.println("MyIsern Ran successfully."); } else {
     * System.out.println("MyIsern Did not run successfully."); }
     */
  }

  /**
   * Checks for user input and then runs the print methods accordingly. If the user does not enter
   * any arguments, nothing will be printed out.
   * 
   * @throws Exception If XML data did not load properly.
   */
  private void runMyIsern() throws Exception {
    Parser parser = new Parser(this.commandLineArgs);
    // Prints according to what boolean is true
    this.mixl = new MyIsernXmlLoader();

    listCollaborations(mixl, "-year", "2007");
    listOrganizations(mixl, "-collaborationLevelEquals", 2);

    if (parser.argsCounter == 0) {
      parser.printHelp();
    }
    /*
    else {
      if (parser.isCollaborationsOn) {
        this.printCollaborations(mixl.getCollaborations());
      }
      if (parser.isOrganizationsOn) {
        this.printOrganizations(mixl.getOrganizations());
      }
      if (parser.isResearchersOn) {
        this.printResearchers(mixl.getResearchers());
      }
    } */
    // @return boolean Returns true if no errors were encountered.
    // return true;
  }

  /**
   * Lists organizations with collaboration levels equal or greater than what user specifies.
   * 
   * @param mixl containing the loader.
   * @param collaborationLevel containing the type of level the user wants listed.
   * @param collaborationNumber containing the number of collaborations user has specified.
   */
  public void listOrganizations(MyIsernXmlLoader mixl, String collaborationLevel,
      int collaborationNumber) {
    System.out.println("here to pass verify");
    /*List<Collaboration> collaborationList;
    List<String> stringList;
    collaborationList = mixl.getCollaborations();
    Map<String, Integer> collabOrganizations = new HashMap();
    /*
     * Iterator hashMapIterator = collabOrganizations.keySet().iterator(); Set hashMapSet =
     * collabOrganizations.entrySet();
     
    int hashValue = 0;

    for (Collaboration currentCollab : collaborationList) {
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = currentCollab.getCollaboratingOrganizations();
      stringList = collaboratingOrganizations.getCollaboratingOrganization();
      for (String currentCollabOrg : stringList) {
        if (collabOrganizations.containsKey(currentCollabOrg)) {
          hashValue = (Integer) collabOrganizations.get(currentCollabOrg);
          collabOrganizations.put(currentCollabOrg, hashValue++);
        }
        else {
          collabOrganizations.put(currentCollabOrg, 1);
        }
      }
    }

    Set<Map.Entry<String, Integer>> hashMapSet = collabOrganizations.entrySet();
    for (Map.Entry<String, Integer> currentHash : hashMapSet) {
      Integer currValue = currentHash.getValue();
      if (currValue == (Integer) collaborationNumber) {
        System.out.println(currValue);
      }
    }

    if ("-collaborationLevelEquals".equals(collaborationLevel)) {

    }
    else if ("-collaborationLevelGreaterThan".equals(collaborationLevel)) {
      System.out.println("greaterThan");
    } */
  }

  /**
   * Lists collaborations for given organzation, researcher, or year.
   * 
   * @param mixl containing Xml Loader.
   * @param collaborationType containing what user wants to list.
   * @param yearOrId containing the uniqueId or the year the user wants to list.
   */
  public void listCollaborations(MyIsernXmlLoader mixl, String collaborationType, String yearOrId) {

    List<Collaboration> collaborationList;
    List<String> stringList;
    String organizationsWithCollaborations = "";

    collaborationList = mixl.getListCollaborations();
    if ("-organization".equals(collaborationType)) {
      for (Collaboration currentCollab : collaborationList) {
        CollaboratingOrganizations collaboratingOrganizations;
        collaboratingOrganizations = currentCollab.getCollaboratingOrganizations();
        stringList = collaboratingOrganizations.getCollaboratingOrganization();
        for (String currentOrg : stringList) {
          if (yearOrId.replace('_', ' ').equals(currentOrg)) {
            System.out.println(currentCollab.getName());
          }
        }
      }
      System.out.println(organizationsWithCollaborations);
    }
    else if ("-researcher".equals(collaborationType)) {
      List<Organization> organizationList;
      organizationList = mixl.getListOrganizations();
      for (Collaboration currentCollab : collaborationList) {
        CollaboratingOrganizations collaboratingOrganizations;
        collaboratingOrganizations = currentCollab.getCollaboratingOrganizations();
        stringList = collaboratingOrganizations.getCollaboratingOrganization();
        for (String currentOrg : stringList) {
          for (Organization currentOrganization : organizationList) {
            if (currentOrganization.getName().equals(currentOrg)
                && currentOrganization.getContact().equals(yearOrId.replace('_', ' '))) {
              System.out.println(currentCollab.getName());
            }
          }
        }
      }
    }
    else if ("-year".equals(collaborationType)) {
      List<BigInteger> bigIntList;
      for (Collaboration currentCollab : collaborationList) {
        Years years;
        years = currentCollab.getYears();
        bigIntList = years.getYear();

        for (BigInteger currentYears : bigIntList) {
          if (currentYears.toString().equals(yearOrId)) {
            System.out.println(currentCollab.getName());
          }
        }
      }
    }
  }

  
  /**
   * Prints the Collaboration table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of the collaboration to be printed.
   * @param idList List of Ids.
   * @return True if a matching Id was found and corresponding table printed.
   */
  private boolean printCollaboration(String id, List<String> idList) {
    boolean isIdValid = false;
    for (String collaborationId : idList) {
      if (id.equals(collaborationId)) {
        isIdValid = true;
      }
    }
    
    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);      
      
      sb.append("\n\n + + + + + + + + + + + + + COLLABORATION + + + + + + + + + + + + +");
      
      for (Collaboration current : this.mixl.getCollaborations().getCollaboration()) {
        if (id.equals(current.getName())) {
          List<String> stringList;
          List<BigInteger> bigIntList;          
            
          sb.append(this.nameTableField);
          sb.append(current.getName());
  
          // Prints Organizations part of Collaboration
          sb.append("\nCollaborating Organizations:");
          CollaboratingOrganizations collaboratingOrganizations;
          collaboratingOrganizations = current.getCollaboratingOrganizations();
          stringList = collaboratingOrganizations.getCollaboratingOrganization();
  
          for (String currentOrg : stringList) {
            sb.append(this.newLineNewTab);
            sb.append(currentOrg);
          }
  
          // Prints type of collaboration
          sb.append("\nCollaboration Types:");
          CollaborationTypes collaborationTypes;
          collaborationTypes = current.getCollaborationTypes();
          stringList = collaborationTypes.getCollaborationType();
  
          for (String currentCollabType : stringList) {
            sb.append(this.newLineNewTab);
            sb.append(currentCollabType);
          }
  
          // Prints all Years that Organizations were in Collaboration
          sb.append("\nYears:");
          Years years;
          years = current.getYears();
          bigIntList = years.getYear();
  
          for (BigInteger currentYears : bigIntList) {
            sb.append(this.newLineNewTab);
            sb.append(currentYears.toString());
          }
          // Prints all Outcome types
          sb.append("\nOutcome Types:");
          OutcomeTypes outcomeTypes;
          outcomeTypes = current.getOutcomeTypes();
          stringList = outcomeTypes.getOutcomeType();
  
          for (String currentOutcomeType : stringList) {
            sb.append(this.newLineNewTab);
            sb.append(currentOutcomeType);
          }
  
          sb.append("\nDescription: ");
          sb.append(current.getDescription());
          sb.append("\n + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +");
          System.out.println(sb.toString());
        }
      }
      return true;
    }
    else {
      System.out.println("\n" + id + "was not found in Collaborations.");
      return false;
    }
  }
  
  /**
   * Prints the Organization table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of an organization to be printed.
   * @param idList List of Ids.
   * @return True if a matching Id was found and corresponding table printed.
   */
  private boolean printOrganization(String id, List<String> idList) {
    boolean isIdValid = false;
    for (String organizationId : idList) {
      if (id.equals(organizationId)) {
        isIdValid = true;
      }
    }
    
    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);
      
      sb.append("\n\n========================= ORGANIZATION ===========================");
      
      for (Organization current : this.mixl.getOrganizations().getOrganization()) {
        if (id.equals(current.getName())) {
          List<String> stringList;

          sb.append(this.nameTableField);
          sb.append(current.getName());

          sb.append("\nType: ");
          sb.append(current.getType());

          sb.append("\nContact: ");
          sb.append(current.getContact());

          // Prints all affiliated researchers with organization
          sb.append("\nAffiliated Researchers:");
          AffiliatedResearchers affiliatedResearchers;
          affiliatedResearchers = current.getAffiliatedResearchers();
          stringList = affiliatedResearchers.getAffiliatedResearcher();

          for (String currentString : stringList) {
            sb.append(this.newLineNewTab);
            sb.append(currentString);
          }

          sb.append("\nCounter");
          sb.append(current.getCountry());

          // Prints Research Keywords for organization
          sb.append("\nResearch Keywords:");
          ResearchKeywords researchKeywords;
          researchKeywords = current.getResearchKeywords();
          stringList = researchKeywords.getResearchKeyword();

          for (String currentString : stringList) {
            sb.append(this.newLineNewTab);
            sb.append(currentString);
          }

          sb.append("\nResearch Description: ");
          sb.append(current.getResearchDescription());

          sb.append("\nHome Page: ");
          sb.append(current.getHomePage());
          sb.append("\n==================================================================\n");
          System.out.print(sb.toString());
        }
      }
      return true;
    }
    else {
      System.out.println("\n" + id + "was not found in Organizations.");
      return false;
    }
  }
  
  /**
   * Prints the Researcher table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of the researcher to be printed.
   * @param idList List of Ids.
   * @return True if a matching Id was found and corresponding table printed.
   */
  private boolean printResearcher(String id, List<String> idList) {
    boolean isIdValid = false;
    for (String collaborationId : idList) {
      if (id.equals(collaborationId)) {
        isIdValid = true;
      }
    }
    
    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);
      
      sb.append("\n......................... RESEARCHERS ............................ \n");
      
      for (Researcher currentResearcher : this.mixl.getResearchers().getResearcher()) {
        if (id.equals(currentResearcher.getName())) {
          sb.append(this.nameTableField);
          sb.append(currentResearcher.getName());

          sb.append("\nOrganization: ");
          sb.append(currentResearcher.getOrganization());

          sb.append("\nBio Statement: ");
          sb.append(currentResearcher.getBioStatement());

          sb.append("\nPicture Link: ");
          sb.append(currentResearcher.getPictureLink());

          sb.append("\nEmail: ");
          sb.append(currentResearcher.getEmail());
          sb.append("\n.................................................................. \n");
          System.out.print(sb.toString());
        }
      }
      return true;
    }
    else {
      System.out.println("\n" + id + "was not found in Researchers.");
      return false;
    }
  }
  
  /**
   * Prints collaborations.
   * 
   * @param collaborations Collaborations to be printed.
   */
  public void printCollaborations(Collaborations collaborations) {
    List<Collaboration> collaborationList;
    collaborationList = collaborations.getCollaboration();
    StringBuffer sb = new StringBuffer(3000);

    sb.append("\n\n + + + + + + + + + + + + COLLABORATIONS + + + + + + + + + + + + +");

    // Prints contents from loaded Xml files
    for (Collaboration current : collaborationList) {
      List<String> stringList;
      List<BigInteger> bigIntList;

      sb.append(this.nameTableField);
      sb.append(current.getName());

      // Prints Organizations part of Collaboration
      sb.append("\nCollaborating Organizations:");
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = current.getCollaboratingOrganizations();
      stringList = collaboratingOrganizations.getCollaboratingOrganization();

      for (String currentOrg : stringList) {
        sb.append(this.newLineNewTab);
        sb.append(currentOrg);
      }

      // Prints type of collaboration
      sb.append("\nCollaboration Types:");
      CollaborationTypes collaborationTypes;
      collaborationTypes = current.getCollaborationTypes();
      stringList = collaborationTypes.getCollaborationType();

      for (String currentCollabType : stringList) {
        sb.append(this.newLineNewTab);
        sb.append(currentCollabType);
      }

      // Prints all Years that Organizations were in Collaboration
      sb.append("\nYears:");
      Years years;
      years = current.getYears();
      bigIntList = years.getYear();

      for (BigInteger currentYears : bigIntList) {
        sb.append(this.newLineNewTab);
        sb.append(currentYears.toString());
      }
      // Prints all Outcome types
      sb.append("\nOutcome Types:");
      OutcomeTypes outcomeTypes;
      outcomeTypes = current.getOutcomeTypes();
      stringList = outcomeTypes.getOutcomeType();

      for (String currentOutcomeType : stringList) {
        sb.append(this.newLineNewTab);
        sb.append(currentOutcomeType);
      }

      sb.append("\nDescription: ");
      sb.append(current.getDescription());
      sb.append("\n+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +");  
    }
    System.out.println(sb.toString());
  }

  /**
   * Prints organizations.
   * 
   * @param organizations Organizations to be printed.
   */
  public void printOrganizations(Organizations organizations) {
    List<Organization> organizationList;
    organizationList = organizations.getOrganization();
    StringBuffer sb = new StringBuffer(3000);

    sb.append("\n\n========================= ORGANIZATIONS ==========================");

    // Prints contents from loaded Xml files
    for (Organization current : organizationList) {
      List<String> stringList;

      sb.append(this.nameTableField);
      sb.append(current.getName());

      sb.append("\nType: ");
      sb.append(current.getType());

      sb.append("\nContact: ");
      sb.append(current.getContact());

      // Prints all affiliated researchers with organization
      sb.append("\nAffiliated Researchers:");
      AffiliatedResearchers affiliatedResearchers;
      affiliatedResearchers = current.getAffiliatedResearchers();
      stringList = affiliatedResearchers.getAffiliatedResearcher();

      for (String currentString : stringList) {
        sb.append(this.newLineNewTab);
        sb.append(currentString);
      }

      sb.append("\nCounter");
      sb.append(current.getCountry());

      // Prints Research Keywords for organization
      sb.append("\nResearch Keywords:");
      ResearchKeywords researchKeywords;
      researchKeywords = current.getResearchKeywords();
      stringList = researchKeywords.getResearchKeyword();

      for (String currentString : stringList) {
        sb.append(this.newLineNewTab);
        sb.append(currentString);
      }

      sb.append("\nResearch Description: ");
      sb.append(current.getResearchDescription());

      sb.append("\nHome Page: ");
      sb.append(current.getHomePage());

      sb.append("\n==================================================================\n");
    }
    System.out.print(sb.toString());
  }

  /**
   * Prints researchers.
   * 
   * @param researchers Researchers to be printed.
   */
  public void printResearchers(Researchers researchers) {
    List<Researcher> researcherList;
    researcherList = researchers.getResearcher();
    StringBuffer sb = new StringBuffer(3000);
    sb.append("\n......................... RESEARCHERS ............................ \n");
    
    for (Researcher currentResearcher : researcherList) {
      sb.append(this.nameTableField);
      sb.append(currentResearcher.getName());

      sb.append("\nOrganization: ");
      sb.append(currentResearcher.getOrganization());

      sb.append("\nBio Statement: ");
      sb.append(currentResearcher.getBioStatement());

      sb.append("\nPicture Link: ");
      sb.append(currentResearcher.getPictureLink());

      sb.append("\nEmail: ");
      sb.append(currentResearcher.getEmail());

      sb.append("\n.................................................................. \n");
    }
    System.out.print(sb.toString());
  } 
}
