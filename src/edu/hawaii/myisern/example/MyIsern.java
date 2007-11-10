package edu.hawaii.myisern.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
  /** Holds values passed from the command line */
  private String[] commandLineArgs;
  /** MyIsern's own xml loader */
  private MyIsernXmlLoader mixl;
  /** MyIsern's own xml saver */
  MyIsernXmlSaver mixs;
  /** String to use for adding new lines tabs */
  private String newLineNewTab = "\n\t";
  /** String to use for the field Name */
  private String nameTableField = "\nName: ";
  /** String to remind user to save, checkstyle fix */
  private String reminderToSave = "Adding to List...\nPlease remember to save.\n";
  /** String to use with all edit functions, checkstyle fix */
  private String reenterMessage = "Please reenter information for all fields.";
  /** String to prompt user to enter for all following fields, checkstyle fix */
  private String enterPrompt = " --- Please Enter information for following fields.\n";
  /** boolean value that holds true if a new researcher is added */
  private boolean newrExists = false;
  /** boolean value that holds true if a new organization is added */
  private boolean newoExists = false;
  /** boolean value that holds true if a new collaboration is added */
  private boolean newcExists = false;
  /** Researcher object that will hold all new and old researchers */
  private Researchers rList = new Researchers();
  /** Organization object that will hold all new and old organizations */
  private Organizations oList = new Organizations();
  /** Collaboration object that will hold all new and old collaborations */
  private Collaborations cList = new Collaborations();
  /** Array List of all Collaborations */
  private List<Collaboration> collabList = new ArrayList<Collaboration>();
  /** Array List of all Organizations */
  private List<Organization> orgList = new ArrayList<Organization>();
  /** Array List of all Researchers */
  private List<Researcher> resList = new ArrayList<Researcher>();

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
    this.collabList = this.cList.getCollaboration();
    this.newcExists = false;
    this.newoExists = false;
    this.newrExists = false;
  }

  /**
   * Passes the command line options given to the program.
   * 
   * @param args containing command line arguments.
   * @throws Exception if there is an exception
   */
  public static void main(String[] args) throws Exception {
    MyIsern myIsern = new MyIsern();
    myIsern.parseCommandLine();
  }

  /**
   * Method handle -describe command line argument.
   * 
   * @param typeDescribing Object to describe.
   */
  public void describe(String typeDescribing) {
    try {
      if (typeDescribing != null) {
        if ("Researchers".equals(typeDescribing)) {
          this.printResearchers(this.mixl.researchers);
        }
        else if ("Organizations".equals(typeDescribing)) {
          this.printOrganizations(this.mixl.organizations);
        }
        else if ("Collaborations".equals(typeDescribing)) {
          this.printCollaborations(this.mixl.collaborations);
        }
        else {
          this.printHelp();
        }
      }
    }
    catch (Exception e) {
      // to do exception handling
      System.out.println(e.toString());
    }
  }

  /**
   * Overload describe to handle searching.
   * 
   * @param typeDescribing Object looking for.
   * @param uniqueId Unique identifier of Object.
   */
  public void describe(String typeDescribing, String uniqueId) {
    try {
      if (typeDescribing != null) {
        if ("-researcher".equals(typeDescribing)) {
          this.printResearcher(uniqueId, mixl.getUniqueIds());
        }
        else if ("-organization".equals(typeDescribing)) {
          this.printOrganization(uniqueId, mixl.getUniqueIds());
        }
        else if ("-collaboration".equals(typeDescribing)) {
          this.printCollaboration(uniqueId, mixl.getUniqueIds());
        }
        else {
          this.printHelp();
        }
      }
    }
    catch (Exception e) {
      // to do execption handling
      System.out.println(e.toString());
    }
  }

  /**
   * Method to parse the command line and do designated task.
   */
  public void parseCommandLine() {
    try {
      if (this.commandLineArgs.length > 0) {
        String arg0 = this.commandLineArgs[0];
        String arg1 = "";
        String arg2 = "";
        if (this.commandLineArgs.length > 1) {
          arg1 = this.commandLineArgs[1];
        }
        if (this.commandLineArgs.length > 2) {
          arg2 = this.commandLineArgs[2];
        }
        if ("-listCollaboration".equals(arg0)) {
          this.listCollaborations(arg1, arg2);
        }
        else if ("-describe".equals(arg0) && "-all".equals(arg1)) {
          this.describe(arg2);
        }
        else if ("-describe".equals(arg0) && !"-all".equals(arg1)) {
          this.describe(arg1, arg2);
        }
        else if ("-listOrganizations".equals(arg0) && "-collaborationLevelEquals".equals(arg1)) {
          this.listOrganizationsEquals(Integer.parseInt(arg2));
        }
        else if ("-listOrganizations".equals(arg0) && 
            "-collaborationLevelGreaterThan".equals(arg1)) {
          this.listOrganizationsGreaterThan(Integer.parseInt(arg2));
        }
        else if ("-input".equals(arg0)) {
          this.addToIsern();
        }
        else {
          this.printHelp();
        }
      }
      else {
        this.printHelp();
      }
    }
    catch (Exception e) {
      // to do exception handling.
      System.out.println(e.toString());
    }
  }

  /**
   * Lists collaborations for given organization, researcher, or year.
   * 
   * @param collaborationType containing what user wants to list.
   * @param yearOrId containing the uniqueId or the year the user wants to list.
   * @return boolean set true if collaborations exist for given field.
   */
  public boolean listCollaborations(String collaborationType, String yearOrId) {

    List<Collaboration> collaborationList;
    List<String> stringList;
    String description = "\nDescription";
    boolean collaborationsExist = false;
    collaborationList = this.mixl.getListCollaborations();
    if ("-organization".equals(collaborationType)) {
      String orgWithCollab = "";
      System.out.println("--- Collaborations for: " + yearOrId.replace('_', ' '));

      for (Collaboration currentCollab : collaborationList) {
        CollaboratingOrganizations collaboratingOrganizations;
        collaboratingOrganizations = currentCollab.getCollaboratingOrganizations();
        stringList = collaboratingOrganizations.getCollaboratingOrganization();
        for (String currentOrg : stringList) {
          if (yearOrId.replace('_', ' ').equals(currentOrg)) {
            orgWithCollab += "Collaboration: " + currentCollab.getName();
            orgWithCollab += description + currentCollab.getDescription() + "\n";
            collaborationsExist = true;
          }
        }
      }
      System.out.println(orgWithCollab);
    }
    else if ("-researcher".equals(collaborationType)) {
      List<Organization> organizationList;
      organizationList = this.mixl.getListOrganizations();

      String researchWithCollab = "";
      System.out.println("--- Collaborations for: " + yearOrId.replace('_', ' '));
      for (Collaboration currentCollab : collaborationList) {
        CollaboratingOrganizations collaboratingOrganizations;
        collaboratingOrganizations = currentCollab.getCollaboratingOrganizations();
        stringList = collaboratingOrganizations.getCollaboratingOrganization();
        for (String currentOrg : stringList) {
          for (Organization currentOrganization : organizationList) {
            if (currentOrganization.getName().equals(currentOrg)
                && currentOrganization.getContact().equals(yearOrId.replace('_', ' '))) {
              researchWithCollab += "Collaboration: " + currentCollab.getName();
              researchWithCollab += description + currentCollab.getDescription() + "\n";
              collaborationsExist = true;
            }
          }
        }
      }
    }
    else if ("-year".equals(collaborationType)) {

      List<BigInteger> bigIntList;

      String yearWithCollab = "";
      System.out.println("--- Collaborations for year: " + yearOrId.replace('_', ' '));
      for (Collaboration currentCollab : collaborationList) {
        Years years;
        years = currentCollab.getYears();
        bigIntList = years.getYear();

        for (BigInteger currentYear : bigIntList) {
          if (currentYear.toString().equals(yearOrId)) {
            yearWithCollab += "Collaboration: " + currentCollab.getName();
            yearWithCollab += description + currentCollab.getDescription() + "\n";
            collaborationsExist = true;
          }
        }
      }
    }

    return collaborationsExist;
  }

  /**
   * Prints the Collaboration table for the specified Id if it is found in the Id list.
   * 
   * @param id The id of the collaboration to be printed.
   * @param idList List of Ids.
   * @return True if a matching Id was found and corresponding table printed.
   */
  public boolean printCollaboration(String id, Set<String> idList) {
    boolean isIdValid = false;
    for (String collaborationId : idList) {
      if (id.equals(collaborationId)) {
        isIdValid = true;
        break;
      }
    }

    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);

      sb.append("\n\n + + + + + + + + + + + + + COLLABORATION + + + + + + + + + + + + +");

      for (Collaboration current : this.mixl.getCollaborations().getCollaboration()) {
        if (current.getName().replace(' ', '_').equals(id)) {
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
  public boolean printOrganization(String id, Set<String> idList) {
    boolean isIdValid = false;
    for (String organizationId : idList) {
      if (id.equals(organizationId)) {
        isIdValid = true;
        break;
      }
    }

    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);

      sb.append("\n\n========================= ORGANIZATION ===========================");

      for (Organization current : this.mixl.getOrganizations().getOrganization()) {
        if (current.getName().replace(' ', '_').equals(id)) {
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
  public boolean printResearcher(String id, Set<String> idList) {
    boolean isIdValid = false;
    for (String collaborationId : idList) {
      if (id.replace(' ', '_').equals(collaborationId)) {
        // System.out.println("Found");
        isIdValid = true;
        break;
      }
    }

    if (isIdValid) {
      StringBuffer sb = new StringBuffer(100);

      sb.append("\n......................... RESEARCHERS ............................ \n");

      for (Researcher currentResearcher : this.mixl.getResearchers().getResearcher()) {
        if (currentResearcher.getName().replace(' ', '_').equals(id)) {
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
      System.out.println("\n" + id + " was not found in Researchers.");
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

      sb.append("\nCountry");
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

  /**
   * Lists organizations with collaboration levels greater than what the user specifies.
   * 
   * @param collaborationNumber Contains the number of collaborations user has specified.
   * @return true If Organizations were found with values equal to specified number.
   */
  public boolean listOrganizationsEquals(int collaborationNumber) {
    Map<String, Integer> collabOrganizations = new HashMap<String, Integer>();

    for (Collaboration currentCollaboration : this.mixl.getCollaborations().getCollaboration()) {
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = currentCollaboration.getCollaboratingOrganizations();
      List<String> stringList = collaboratingOrganizations.getCollaboratingOrganization();

      for (String currentOrg : stringList) {
        if (collabOrganizations.containsKey(currentOrg)) {
          int valueFrequency;
          valueFrequency = collabOrganizations.get(currentOrg);
          collabOrganizations.put(currentOrg, ++valueFrequency);
        }
        else if (!collabOrganizations.containsKey(currentOrg)) {
          collabOrganizations.put(currentOrg, 1);
        }
      }
    }

    int organizationCount = 0;
    System.out.println("\nOrganizations involved in " + collaborationNumber + " collaborations.");

    Iterator<Map.Entry<String, Integer>> organizationIterator;
    organizationIterator = collabOrganizations.entrySet().iterator();
    while (organizationIterator.hasNext()) {
      Map.Entry<String, Integer> organizationEntry = organizationIterator.next();

      if (organizationEntry.getValue() == collaborationNumber) {
        System.out.println(organizationEntry.getKey());
        organizationCount++;
      }
    }

    if (organizationCount == 0) {
      System.out.println("No organizations found.");
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * Lists organizations with collaboration levels equal to what the user specifies.
   * 
   * @param collaborationNumber Contains the number of collaborations user has specified.
   * @return true If Organizations were found with values greater than specified number
   */
  public boolean listOrganizationsGreaterThan(int collaborationNumber) {
    Map<String, Integer> collabOrganizations = new HashMap<String, Integer>();

    for (Collaboration currentCollaboration : this.mixl.getCollaborations().getCollaboration()) {
      CollaboratingOrganizations collaboratingOrganizations;
      collaboratingOrganizations = currentCollaboration.getCollaboratingOrganizations();
      List<String> stringList = collaboratingOrganizations.getCollaboratingOrganization();

      for (String currentOrg : stringList) {
        if (collabOrganizations.containsKey(currentOrg)) {
          int valueFrequency;
          valueFrequency = collabOrganizations.get(currentOrg);
          collabOrganizations.put(currentOrg, ++valueFrequency);
        }
        else if (!collabOrganizations.containsKey(currentOrg)) {
          collabOrganizations.put(currentOrg, 1);
        }
      }
    }

    int organizationCount = 0;
    System.out.println("\nOrganizations involved in greater than" + collaborationNumber
        + " collaborations.");

    Iterator<Map.Entry<String, Integer>> organizationIterator;
    organizationIterator = collabOrganizations.entrySet().iterator();
    while (organizationIterator.hasNext()) {
      Map.Entry<String, Integer> organizationEntry = organizationIterator.next();

      if (organizationEntry.getValue() > collaborationNumber) {
        System.out.println(organizationEntry.getKey());
        organizationCount++;
      }
    }

    if (organizationCount == 0) {
      System.out.println("No organizations found.");
      return false;
    }
    else {
      return true;
    }
  }

  /**
   * Displays the menu to input researchers, organizations, and collaborations.
   */
  public void printInputMenu() {
    StringBuffer sb = new StringBuffer(100);
    String menuLine1 = "\n-+-+-+-+-+-+-+-+-+-+- MENU +-+-+-+-+-+-+-+-+-+-+-+-+-\n";
    String menuLine2 = "\n\tCommand\t\tFunction";
    String menuLine3 = "\n\t   r\t\t   Add Researchers";
    String menuLine4 = "\n\t   o\t\t   Add Organizations";
    String menuLine5 = "\n\t   c\t\t   Add Collaborations";
    String menuLine6 = "\n\t   s\t\t   Save";
    String menuLine7 = "\n\t   q\t\t   Quit";
    String menuLine8 = "\n\n-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-";

    sb.append(menuLine1);
    sb.append(menuLine2);
    sb.append(menuLine3);
    sb.append(menuLine4);
    sb.append(menuLine5);
    sb.append(menuLine6);
    sb.append(menuLine7);
    sb.append(menuLine8);

    System.out.println(sb.toString());
  }

  /**
   * Calls add methods that add researchers, organizations, and collaborations.
   * 
   * @return true If the method executed successfully.
   * @throws Exception if problems occur.
   * @throws NumberFormatException if problems occur.
   */

  private boolean addToIsern() throws NumberFormatException, Exception {
    boolean isDone = false;
    while (!isDone) {
      this.printInputMenu();
      Scanner scanner = new Scanner(System.in);
      String input;

      System.out.println("Enter the command: ");
      input = scanner.nextLine();

      if (input.equalsIgnoreCase("r")) {
        this.addResearcher();
      }
      else if (input.equalsIgnoreCase("o")) {
        this.addOrganization();
        System.out.println("o");
      }
      else if (input.equalsIgnoreCase("c")) {
        this.addCollaboration();
        System.out.println("c");
      }
      else if (input.equalsIgnoreCase("s")) {
        System.out.println("s");
        if (this.newrExists) {
          mixs.saveResearchersXml(this.rList);
          System.out.println("New Researcher(s) saved.");
          this.newrExists = false;
        }
        else if (this.newcExists) {
          mixs.saveCollaboratotionsXml(this.cList);
          System.out.println("New Collaboration(s) saved.");
          this.newcExists = false;
        }
        else if (this.newoExists) {
          mixs.saveOrganizationsXml(this.oList);
          System.out.println("New Organization(s) saved.");
          this.newoExists = false;
        }
      }
      else if (input.equalsIgnoreCase("q")) {
        if (this.newrExists || this.newcExists || this.newoExists) {
          System.out.println("There seems to be some unsaved information you entered. ");
          System.out.print("Would you like to save?(Y/N) ");
          try {
            if (userEntersYes()) {
              System.out.println("Saving...");
              if (this.newrExists) {
                mixs.saveResearchersXml(this.rList);
                System.out.println("New Researcher(s) saved.");
                this.newrExists = false;
              }
              else if (this.newcExists) {
                mixs.saveCollaboratotionsXml(this.cList);
                System.out.println("New Collaboration(s) saved.");
                this.newcExists = false;
              }
              else if (this.newoExists) {
                mixs.saveOrganizationsXml(this.oList);
                System.out.println("New Organization(s) saved.");
                this.newoExists = false;
              }
            }
            else {
              System.out.println("Nothing saved...");
            }
          }
          catch (IOException e) {
            System.out.println("There was an IOException");
            System.out.println(e.getMessage());
          }
          catch (JAXBException e) {
            System.out.println("There was a JAXB Exception");
            System.out.println(e.getMessage());
          }
        }
        System.out.println("Exiting MyIsern...Have a nice Day!");
        isDone = true;
      }
      // Also has "secret" print functions for testing purposes
      else if (input.equalsIgnoreCase("p")) {
        printResearchers(this.rList);
        printOrganizations(this.oList);
        printCollaborations(this.cList);
        System.out.println("Secret Print Command *Wink*Wink*");
      }
      else if (input.equalsIgnoreCase("pr")) {
        printResearchers(this.rList);
        System.out.println("Secret Print Command *Wink*");
      }
      else if (input.equalsIgnoreCase("po")) {
        printOrganizations(this.oList);
        System.out.println("Secret Print Command *Wink*Wink*");
      }
      else if (input.equalsIgnoreCase("pc")) {
        printCollaborations(this.cList);
        System.out.println("Secret Print Command *Wink*Wink*");
      }
      else {
        System.out.println(" The command you entered " + input + " is incorrect.");
        System.out.println(" Please enter a valid command.");
      }
      // if input is r call addResearchers method
      // if input is o call addOrganizations method
      // if input is c call addCollaborations method
      // if input is s save the current information to XML files then reload the xml data
      // if input is q, check if lists are saved, if not ask user if he wants to save
      // if yes, save, then leave the program
    }
    return true;
  }

  /**
   * Prints helpful information for commmand line options
   */
  void printHelp() {
    // Provides a 'help' mechanism similar to the Unix style.
    String helpString = "";
    helpString += "\nProvides sample code for loading XML ";
    helpString += "and marshalling it into their JAXB related classes.";
    helpString += "\nUsage: java -jar myisern-1-blue.jar [OPTION]";
    helpString += "\n  -listCollaborations -organization <uniqueID>";
    // helpString += "\tLists Collaborations known for specified organization";
    helpString += "\n  -listCollaborations -year <year>";
    // helpString += "\t\tLists Collaborations known for specified year";
    helpString += "\n  -listCollaborations -researcher <uniqueID>";
    // helpString += "\tLists Collaborations known for specified researcher";
    helpString += "\n  -describe -researcher <uniqueID>";
    helpString += "\n  -describe -organization <uniqueID>";
    helpString += "\n  -describe -collaboration <uniqueID>";
    helpString += "\n  -describe -all Researchers";
    helpString += "\n  -describe -all Organizations";
    helpString += "\n  -describe -all Collaborations";
    helpString += "\n  -listOrganizations -collaborationLevelEquals <integer>";
    helpString += "\n  -listOrganizations -collaborationLevelGreaterThan <integer>";
    helpString += "\n  -input";
    helpString += "\n  -showGui";
    helpString += "\n  -help";
    System.out.println(helpString);
  }

  /**
   * Adds Researchers to the researchers List.
   * 
   * @throws IOException when I/O error occurs.
   */
  public void addResearcher() throws IOException {
    String rName, rBio, rEmail, rOrg, rPictureLink;
    boolean userIsDone = false;

    while (!userIsDone) {
      Researcher newR = new Researcher();
      boolean userWantsToEdit = false;

      System.out.println(enterPrompt);
      System.out.print("Enter Researcher Name: ");
      // checks user input if its a unique id
      rName = userInput();

      if (this.mixl.containsUniqueId(rName.replace(' ', '_'))) {
        System.out.println("This Researcher already exists");
        System.out.print("Would you like to edit existing Researcher?(Y/N) ");

        if (userEntersYes()) {
          editResearcher(rName);
        }
        else {
          System.out.println("You chose not to edit");
        }
        // Need to add for editing
      }
      else {
        newR.setName(rName);

        System.out.print("\n Enter the researcher's organization: ");
        rOrg = (userInput());
        newR.setOrganization(rOrg);

        System.out.print("\n Enter the researcher's e-mail address: ");
        rEmail = (userInput());
        newR.setEmail(rEmail);

        System.out.print("\n Enter the researcher's picture link: ");
        rPictureLink = (userInput());
        newR.setPictureLink(rPictureLink);

        System.out.print("\n Enter the researcher's bio-statement: ");
        rBio = (userInput());
        newR.setBioStatement(rBio);

        // Show the information entered.
        System.out.println("You Entered:");
        System.out.println("Name: " + rName);
        System.out.println("Organization: " + rOrg);
        System.out.println("E-mail Address: " + rEmail);
        System.out.println("Picture Link: " + rPictureLink);
        System.out.println("Bio-Statement: " + rBio);

        System.out.print("Is the information you entered correct?(Y/N) ");

        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.rList.getResearcher().add(newR);
          this.mixl.addUniqueId(rName);
          this.newrExists = true;
          newOrganization(rName, rOrg);
          userWantsToEdit = false;
        }
        else {
          System.out.println(reenterMessage);
          userWantsToEdit = true;
        }
      } // Else for if researcher does not exist
      if (userWantsToEdit) {
        userIsDone = false;
      }
      else {
        System.out.print("Would you like to add another researcher?(Y/N) ");
        if (userEntersYes()) {
          userIsDone = false;
          System.out.println("Adding more Researchers");
        }
        else {
          System.out.println("Exiting to the Main Menu...");
          userIsDone = true;
        }
      }
    } // while loop for researcher input
  }

  /**
   * Edits an existing researcher with the name rName.
   * 
   * @param rName containing the name of the Researcher to be edited.
   * @throws IOException when I/O error occurs.
   */
  public void editResearcher(String rName) throws IOException {
    if (this.mixl.containsUniqueId(rName.replace(' ', '_'))) {
      List<Researcher> temp = new ArrayList<Researcher>();
      this.resList = this.rList.getResearcher();
      String rBio, rEmail, rOrg, rPictureLink;
      boolean userIsDone = false;

      // REMOVES RESEARCHER FROM LIST
      for (Researcher curr : resList) {
        if (rName.equals(curr.getName())) {
          temp.add(curr);
        }
      }
      this.resList.removeAll(temp);

      while (!userIsDone) {
        Researcher newR = new Researcher();

        System.out.println(enterPrompt);

        newR.setName(rName);

        System.out.print("\n Enter the researcher's organization: ");
        rOrg = (userInput());
        newR.setOrganization(rOrg);

        System.out.print("\n Enter the researcher's e-mail address: ");
        rEmail = (userInput());
        newR.setEmail(rEmail);

        System.out.print("\n Enter the researcher's picture link: ");
        rPictureLink = (userInput());
        newR.setPictureLink(rPictureLink);

        System.out.print("\n Enter the researcher's bio-statement: ");
        rBio = (userInput());
        newR.setBioStatement(rBio);

        // Show the information entered.
        System.out.println("You Entered:");
        System.out.println("Name: " + rName);
        System.out.println("Organization: " + rOrg);
        System.out.println("E-mail Address: " + rEmail);
        System.out.println("Picture Link: " + rPictureLink);
        System.out.println("Bio-Statement: " + rBio);

        System.out.print("Is the information you entered correct?(Y/N) ");

        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.rList.getResearcher().add(newR);
          this.mixl.addUniqueId(rName);
          this.newrExists = true;
          newOrganization(rName, rOrg);
          userIsDone = true;
        }
        else {
          System.out.println(reenterMessage);
          userIsDone = false;
        }

      } // while loop for researcher input
    }
  }

  /**
   * If the organization entered by Researcher does not exist, then it adds a brand new Organization
   * with Org name and Contact field provided but everything else blank.
   * 
   * @param rName String containing Researcher's/Contact name.
   * @param oName String Containing Organization's name.
   */
  public void newOrganization(String rName, String oName) {
    if (!this.mixl.containsUniqueId(oName.replace(' ', '_'))) {
      Organization o = new Organization();
      o.setName(oName);
      o.setContact(rName);
      AffiliatedResearchers ar = new AffiliatedResearchers();
      ar.getAffiliatedResearcher().add("");
      o.setAffiliatedResearchers(ar);
      o.setHomePage("");
      o.setResearchDescription("");
      ResearchKeywords rk = new ResearchKeywords();
      rk.getResearchKeyword().add("");
      o.setResearchKeywords(rk);
      this.mixl.addUniqueId(oName);
      this.oList.getOrganization().add(o);
    }
  }

  /**
   * Adds an organization and its required fields.
   * 
   * @throws IOException when I/O error occurs.
   * 
   */
  public void addOrganization() throws IOException {
    String oName, oType, oContact, oCountry, oResearcherDescription, oHomepage;
    boolean userIsDone = false;
    boolean innerLoopIsDone = false;

    while (!userIsDone) {
      Organization newOrg = new Organization();
      List<String> oAffilResearchers = new ArrayList<String>();
      List<String> oResearchKeywords = new ArrayList<String>();
      boolean userWantsToEdit = false;

      System.out.println(enterPrompt);
      System.out.print("Enter Organization Name: ");
      // checks user input if its a unique id
      oName = userInput();

      if (this.mixl.containsUniqueId(oName.replace(' ', '_'))) {
        System.out.println("This Organization already exists");
        System.out.println("Would you like to edit this Organization? (Y/N) ");
        if (userEntersYes()) {
          System.out.println("You chose to edit");
          editOrganization(oName);
        }
        else {
          System.out.println("You chose not to edit...");
        }
        // Need to add for editing
      }
      else {

        newOrg.setName(oName);

        System.out.print("Enter Type of Organization: ");
        oType = userInput();

        newOrg.setType(oType);

        System.out.print("Enter Contact: ");
        oContact = userInput();

        newOrg.setContact(oContact);

        System.out.print("Enter an Affiliated Researcher: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          oAffilResearchers.add(userInput());
          System.out.print("Would you like to add another Affiliated Researcher?(Y/N) ");

          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Enter another affiliated Researcher: ");
          }
          else {
            innerLoopIsDone = true;
          }

        } // While loop for adding affiliated researchers

        AffiliatedResearchers ar = new AffiliatedResearchers();
        newOrg.setAffiliatedResearchers(ar);
        for (String current : oAffilResearchers) {
          newOrg.getAffiliatedResearchers().getAffiliatedResearcher().add(current);

        }

        System.out.print("Enter Country: ");
        oCountry = userInput();
        newOrg.setCountry(oCountry);

        System.out.print("Enter a Research Keyword: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          oResearchKeywords.add(userInput());
          System.out.print("Would you like to add another research keyword?(Y/N) ");
          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Please enter another research keyword: ");
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for research keyword inner Loop

        ResearchKeywords rk = new ResearchKeywords();
        newOrg.setResearchKeywords(rk);
        for (String current : oResearchKeywords) {
          newOrg.getResearchKeywords().getResearchKeyword().add(current);
        }

        System.out.print("Enter Research Description:\n\t");
        oResearcherDescription = userInput();

        newOrg.setResearchDescription(oResearcherDescription);

        System.out.print("Enter Organization homepage: ");
        oHomepage = userInput();

        newOrg.setHomePage(oHomepage);

        System.out.println("--- You Entered:");
        System.out.println("Organization:" + oName);
        System.out.println("Type:" + oType);
        System.out.println("Contact: " + oContact);
        System.out.println("Affiliated Researchers");
        for (String curr : oAffilResearchers) {
          System.out.println(curr);
        }
        System.out.println("Country: " + oCountry);
        System.out.println("Research Keywords: ");
        for (String curr : oResearchKeywords) {
          System.out.println(curr);
        }
        System.out.println("Homepage: " + oHomepage);

        System.out.print("\nIs the information provided correct?(Y/N) ");

        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.oList.getOrganization().add(newOrg);
          this.mixl.addUniqueId(oName);
          this.newoExists = true;
          newResearcher(oContact, oName);
          userWantsToEdit = false;
        }
        else {
          System.out.println(reenterMessage);
          userWantsToEdit = true;
        }

      } // else if Organization entered is unique

      if (userWantsToEdit) {
        userIsDone = false;
      }
      else {
        System.out.print("Would you like to add another organization?(Y/N) ");
        if (userEntersYes()) {
          System.out.println("Adding more Organizations");
          userIsDone = false;
        }
        else {
          System.out.println("Exiting to the Main Menu...");
          userIsDone = true;
        }
      }

    } // Main while loop for entering organization info

  }

  /**
   * Edits an existing organization with the name oName.
   * 
   * @param oName containing the name of the Organization to be edited.
   * @throws IOException when I/O error occurs.
   */
  public void editOrganization(String oName) throws IOException {
    if (this.mixl.containsUniqueId(oName.replace(' ', '_'))) {
      List<Organization> temp = new ArrayList<Organization>();
      this.orgList = this.oList.getOrganization();
      String oType, oContact, oCountry, oResearcherDescription, oHomepage;
      boolean userIsDone = false;
      boolean innerLoopIsDone = false;

      // ACTUAL REMOVAL OF THE ORGANIZATION FROM LIST
      for (Organization curr : orgList) {
        if (oName.equals(curr.getName())) {
          temp.add(curr);
        }
      }
      this.orgList.removeAll(temp);

      while (!userIsDone) {
        Organization newOrg = new Organization();
        List<String> oAffilResearchers = new ArrayList<String>();
        List<String> oResearchKeywords = new ArrayList<String>();

        newOrg.setName(oName);

        System.out.print("Enter Type of Organization: ");
        oType = userInput();

        newOrg.setType(oType);

        System.out.print("Enter Contact: ");
        oContact = userInput();

        newOrg.setContact(oContact);

        System.out.print("Enter an Affiliated Researcher: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          oAffilResearchers.add(userInput());
          System.out.print("Would you like to add another Affiliated Researcher?(Y/N) ");

          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Enter another affiliated Researcher: ");
          }
          else {
            innerLoopIsDone = true;
          }

        } // While loop for adding affiliated researchers

        AffiliatedResearchers ar = new AffiliatedResearchers();
        newOrg.setAffiliatedResearchers(ar);
        for (String current : oAffilResearchers) {
          newOrg.getAffiliatedResearchers().getAffiliatedResearcher().add(current);

        }

        System.out.print("Enter Country: ");
        oCountry = userInput();
        newOrg.setCountry(oCountry);

        System.out.print("Enter a Research Keyword: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          oResearchKeywords.add(userInput());
          System.out.print("Would you like to add another research keyword?(Y/N) ");

          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Please enter another research keyword: ");
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for research keyword inner Loop

        ResearchKeywords rk = new ResearchKeywords();
        newOrg.setResearchKeywords(rk);
        for (String current : oResearchKeywords) {
          newOrg.getResearchKeywords().getResearchKeyword().add(current);
        }

        System.out.print("Enter Research Description:\n\t");
        oResearcherDescription = userInput();

        newOrg.setResearchDescription(oResearcherDescription);

        System.out.print("Enter Organization homepage: ");
        oHomepage = userInput();

        newOrg.setHomePage(oHomepage);

        System.out.println("--- You Entered:");
        System.out.println("Organization:" + oName);
        System.out.println("Type:" + oType);
        System.out.println("Contact: " + oContact);
        System.out.println("Affiliated Researchers");
        for (String curr : oAffilResearchers) {
          System.out.println(curr);
        }
        System.out.println("Country: " + oCountry);
        System.out.println("Research Keywords: ");
        for (String curr : oResearchKeywords) {
          System.out.println(curr);
        }
        System.out.println("Homepage: " + oHomepage);

        System.out.print("\nIs the information provided correct?(Y/N) ");

        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.oList.getOrganization().add(newOrg);
          this.mixl.addUniqueId(oName);
          this.newoExists = true;
          newResearcher(oContact, oName);
          userIsDone = true;
        }
        else {
          System.out.println(reenterMessage);
          userIsDone = false;
        }

      } // Main while loop for entering organization info

    } // if statement for Organization that already exists
  }

  /**
   * This method adds a new Researcher if the contact for the Organization given is a new contact.
   * 
   * @param rName String containing Researcher's/Contact's name.
   * @param oName String containing Organization name.
   */
  public void newResearcher(String rName, String oName) {
    if (!this.mixl.containsUniqueId(rName.replace(' ', '_'))) {
      Researcher r = new Researcher();
      r.setName(rName);
      r.setOrganization(oName);
      r.setEmail("");
      r.setBioStatement("");
      r.setPictureLink("");
    }
  }

  /**
   * Adds a Collaboration and its required fields.
   * 
   * @throws Exception If problems occur.
   * @throws NumberFormatException If problems occur.
   */

  public void addCollaboration() throws NumberFormatException, Exception {
    Collaboration newCollab = new Collaboration();
    String collabName, collabDescription;
    String tab = "\t";
    boolean userIsDone = false;
    boolean innerLoopIsDone = false;

    while (!userIsDone) {
      List<String> collabOrganizations = new ArrayList<String>();
      List<String> collabTypes = new ArrayList<String>();
      List<String> collabYears = new ArrayList<String>();
      List<String> collabOutcomeTypes = new ArrayList<String>();
      boolean userWantsToEdit = false;

      System.out.println(enterPrompt);
      System.out.print("Please enter Collaboration name: ");
      collabName = userInput();

      if (this.mixl.containsUniqueId(collabName.replace(' ', '_'))) {
        System.out.println("This Collaboration already exists");
        System.out.print("Would you like to edit this collaboration? (Y/N) ");

        if (userEntersYes()) {
          editCollaboration(collabName);
        }
        else {
          System.out.println("You chose not to Edit...");
        }
        // Need to add for editing
      }
      else {

        newCollab.setName(collabName);

        System.out.print("Please enter A collaborating Organization: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          collabOrganizations.add(userInput());

          if (collabOrganizations.size() < 2) {
            System.out.print("Please add second collaborating Organization: ");
          }
          else {
            System.out.print("Would you like to add more Organizations? (Y/N) ");
            if (userEntersYes()) {
              innerLoopIsDone = false;
              System.out.print("Please add another collaborating Organization: ");
            }
            else {
              innerLoopIsDone = true;
            }
          }
        } // while for collaborating organization inner Loop

        CollaboratingOrganizations co = new CollaboratingOrganizations();
        newCollab.setCollaboratingOrganizations(co);

        for (String current : collabOrganizations) {
          newCollab.getCollaboratingOrganizations().getCollaboratingOrganization().add(current);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter a Collaboration Type: ");
        while (!innerLoopIsDone) {
          collabTypes.add(userInput());
          System.out.print("Would you like to add another Collaboration Type?(Y/N) ");

          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Enter another Collaboration Type: ");
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for inner Loop

        CollaborationTypes ct = new CollaborationTypes();
        newCollab.setCollaborationTypes(ct);

        for (String current : collabTypes) {
          newCollab.getCollaborationTypes().getCollaborationType().add(current);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter year of collaboration: ");
        while (!innerLoopIsDone) {
          String tempYear = userInput();
          if (yearIsValid(tempYear)) {
            collabYears.add(tempYear);
            System.out.print("Would you like to add another Year?(Y/N) ");

            if (userEntersYes()) {
              innerLoopIsDone = false;
              System.out.print("Enter another Year: ");
            }
            else {
              innerLoopIsDone = true;
            }
          }
          else {
            System.out.print("Please enter a valid year between 1990 and 2010: ");
          }
        } // while for inner Loop

        Years y = new Years();
        newCollab.setYears(y);

        for (String current : collabYears) {
          BigInteger bi = new BigInteger(current);
          newCollab.getYears().getYear().add(bi);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter Outcome Type of collaboration: ");
        while (!innerLoopIsDone) {
          collabOutcomeTypes.add(userInput());

          System.out.print("Would you like to add another Outcome Type?(Y/N) ");
          if (userEntersYes()) {
            System.out.print("Enter another Outcome type: ");
            innerLoopIsDone = false;
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for inner Loop

        OutcomeTypes ot = new OutcomeTypes();
        newCollab.setOutcomeTypes(ot);
        for (String current : collabOutcomeTypes) {
          newCollab.getOutcomeTypes().getOutcomeType().add(current);
        }

        System.out.print("Please enter a description for this collaboration: ");
        collabDescription = userInput();
        newCollab.setDescription(collabDescription);

        // Prints out user input
        System.out.println("You Entered: ");
        System.out.println("Collaboration Name: " + collabName);
        System.out.print("Collaborating Organizations: ");
        for (String curr : collabOrganizations) {
          System.out.println(tab + curr);
        }
        System.out.println("Collaboration Types: ");
        for (String curr : collabTypes) {
          System.out.println(tab + curr);
        }
        System.out.println("Years: ");
        for (String curr : collabYears) {
          System.out.println(tab + curr);
        }
        System.out.println("Outcome Types: ");
        for (String curr : collabOutcomeTypes) {
          System.out.println(tab + curr);
        }
        System.out.println("Description: ");
        System.out.println(collabDescription);

        System.out.print("\nIs the information correct? (Y/N) ");
        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.cList.getCollaboration().add(newCollab);
          this.mixl.addUniqueId(collabName);
          this.newcExists = true;
          userWantsToEdit = false;
        }
        else {
          System.out.println(reenterMessage);
          userWantsToEdit = true;
        }
      } // Else for if collaboration is unique

      if (userWantsToEdit) {
        userIsDone = false;
      }
      else {
        System.out.println("Would you like to add another Collaboration?(Y/N) ");

        if (userEntersYes()) {
          System.out.println("Adding more Collaborations");
          userIsDone = false;
        }
        else {
          System.out.println("Exiting to Main menu...");
          userIsDone = true;
        }
      }
    } // while for main loop

  }

  /**
   * Edits an existing collaboration with the name collabName.
   * 
   * @param collabName String containing name of collaboration.
   * @throws Exception when error occurs.
   */
  public void editCollaboration(String collabName) throws Exception {
    if (this.mixl.containsUniqueId(collabName.replace(' ', '_'))) {
      List<Collaboration> temp = new ArrayList<Collaboration>();
      Collaboration newCollab = new Collaboration();
      this.collabList = this.cList.getCollaboration();

      String collabDescription;
      String tab = "\t";
      boolean userIsDone = false;
      boolean innerLoopIsDone = false;

      // ACTUAL REMOVAL OF THE COLLABORATION FROM LIST
      for (Collaboration curr : collabList) {
        if (collabName.equals(curr.getName())) {
          temp.add(curr);
        }
      }
      this.collabList.removeAll(temp);
      while (!userIsDone) {
        List<String> collabOrganizations = new ArrayList<String>();
        List<String> collabTypes = new ArrayList<String>();
        List<String> collabYears = new ArrayList<String>();
        List<String> collabOutcomeTypes = new ArrayList<String>();

        System.out.println(enterPrompt);
        newCollab.setName(collabName);

        System.out.print("Please enter A collaborating Organization: ");
        innerLoopIsDone = false;
        while (!innerLoopIsDone) {
          collabOrganizations.add(userInput());

          if (collabOrganizations.size() < 2) {
            System.out.print("Please add second collaborating Organization: ");
          }
          else {
            System.out.print("Would you like to add more Organizations? (Y/N) ");
            if (userEntersYes()) {
              innerLoopIsDone = false;
              System.out.print("Please add another collaborating Organization: ");
            }
            else {
              innerLoopIsDone = true;
            }
          }
        } // while for collaborating organization inner Loop

        CollaboratingOrganizations co = new CollaboratingOrganizations();
        newCollab.setCollaboratingOrganizations(co);

        for (String current : collabOrganizations) {
          newCollab.getCollaboratingOrganizations().getCollaboratingOrganization().add(current);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter a Collaboration Type: ");
        while (!innerLoopIsDone) {
          collabTypes.add(userInput());
          System.out.print("Would you like to add another Collaboration Type?(Y/N) ");

          if (userEntersYes()) {
            innerLoopIsDone = false;
            System.out.print("Enter another Collaboration Type: ");
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for inner Loop

        CollaborationTypes ct = new CollaborationTypes();
        newCollab.setCollaborationTypes(ct);

        for (String current : collabTypes) {
          newCollab.getCollaborationTypes().getCollaborationType().add(current);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter year of collaboration: ");
        while (!innerLoopIsDone) {
          String tempYear = userInput();
          if (yearIsValid(tempYear)) {
            collabYears.add(tempYear);
            System.out.print("Would you like to add another Year?(Y/N) ");

            if (userEntersYes()) {
              innerLoopIsDone = false;
              System.out.print("Enter another Year: ");
            }
            else {
              innerLoopIsDone = true;
            }
          }
          else {
            System.out.print("Please enter a valid year between 1990 and 2010: ");
          }
        } // while for inner Loop

        Years y = new Years();
        newCollab.setYears(y);

        for (String current : collabYears) {
          BigInteger bi = new BigInteger(current);
          newCollab.getYears().getYear().add(bi);
        }

        innerLoopIsDone = false;
        System.out.print("Please enter Outcome Type of collaboration: ");
        while (!innerLoopIsDone) {
          collabOutcomeTypes.add(userInput());

          System.out.print("Would you like to add another Outcome Type?(Y/N) ");
          if (userEntersYes()) {
            System.out.print("Enter another Outcome type: ");
            innerLoopIsDone = false;
          }
          else {
            innerLoopIsDone = true;
          }
        } // while for inner Loop

        OutcomeTypes ot = new OutcomeTypes();
        newCollab.setOutcomeTypes(ot);
        for (String current : collabOutcomeTypes) {
          newCollab.getOutcomeTypes().getOutcomeType().add(current);
        }

        System.out.print("Please enter a description for this collaboration: ");
        collabDescription = userInput();
        newCollab.setDescription(collabDescription);

        // Prints out user input
        System.out.println("You Entered: ");
        System.out.println("Collaboration Name: " + collabName);
        System.out.print("Collaborating Organizations: ");
        for (String curr : collabOrganizations) {
          System.out.println(tab + curr);
        }
        System.out.println("Collaboration Types: ");
        for (String curr : collabTypes) {
          System.out.println(tab + curr);
        }
        System.out.println("Years: ");
        for (String curr : collabYears) {
          System.out.println(tab + curr);
        }
        System.out.println("Outcome Types: ");
        for (String curr : collabOutcomeTypes) {
          System.out.println(tab + curr);
        }
        System.out.println("Description: ");
        System.out.println(collabDescription);

        System.out.print("\nIs the information correct? (Y/N) ");
        if (userEntersYes()) {
          System.out.println(reminderToSave);
          this.cList.getCollaboration().add(newCollab);
          this.mixl.addUniqueId(collabName);
          this.newcExists = true;
          userIsDone = true;
        }
        else {
          System.out.println(reenterMessage);
          userIsDone = false;
        }
      } // while for main loop
    }
  }

  /**
   * Checks if year entered is valid.
   * 
   * @param currentYear String that contains year to be converted and examined.
   * @return boolean whether year entered is valid.
   */

  public boolean yearIsValid(String currentYear) {
    boolean yearIsValid = false;
    try {
      BigInteger bigNum = new BigInteger(currentYear);
      if (bigNum.intValue() >= 1990 && bigNum.intValue() <= 2010) {
        yearIsValid = true;
      }
    }
    catch (Exception e) {
      System.out.println("Year format wrong.");
      yearIsValid = false;
    }
    return yearIsValid;
  }

  /**
   * Checks to see if user enters yes, y, no, or n.
   * 
   * @return boolean true if the entered yes, or false if the user entered false.
   * @throws IOException when an Input/output error occurs.
   */
  public boolean userEntersYes() throws IOException {
    boolean hitYes = false;
    boolean correctInput = false;

    while (!correctInput) {
      String input = userInput();
      if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
        hitYes = true;
        correctInput = true;
      }
      else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
        hitYes = false;
        correctInput = true;
      }
      else {
        System.out.print("Please enter Y, Yes, N or No:  ");
      }
    }
    return hitYes;
  }

  /**
   * Reads in a user input from the line.
   * 
   * @return String containing user's input.
   */
  public String userInput() {
    String userInput = "";
    boolean userInputValid = false;
    while (!userInputValid) {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      try {
        userInput = in.readLine();
        if (userInput == null || userInput.equals("")) {
          userInputValid = false;
          System.out.print("Nothing entered. Please Enter something in the field: ");
        }
        else {
          userInputValid = true;
        }
      }
      catch (IOException e) {
        System.out.print("Invalid input, Please enter information again");
      }
    }
    return userInput;
  }
}