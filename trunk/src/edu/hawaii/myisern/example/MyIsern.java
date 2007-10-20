package edu.hawaii.myisern.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.math.BigInteger;
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

//import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebLink;
//import com.meterware.httpunit.WebResponse;

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
  private MyIsernXmlLoader mixl;
  private String newLineNewTab = "\n\t";
  private String nameTableField = "\nName: ";
  /*private boolean isCollaborationsOn = false;
  private boolean isOrganizationsOn = false;
  private boolean isResearchersOn = false;*/
  private boolean argumentsPass = false;

  /**
   * Initializes command line options.
   * 
   * @param args Command line arguments.
   */
  MyIsern(String[] args) {
    this.commandLineArgs = args;
    try {
      this.mixl = new MyIsernXmlLoader();
    }
    catch (Exception e) {
      System.out.println("Error in the constructor");
    }
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
    //textDisplay();
    myIsern.runMyIsern();
    /*
    createGui(myIsern.mixl.getCollaborations(),
    		  myIsern.mixl.getOrganizations(),
    		  myIsern.mixl.getResearchers());
     */
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
    // Prints according to what boolean is true
    checkArguments(this.commandLineArgs);
    boolean addSuccessful = this.addToIsern();
    // @return boolean Returns true if no errors were encountered.
    // return true;
  }

  /**
   * Lists collaborations for given organzation, researcher, or year.
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
  public boolean printOrganization(String id, Set<String> idList) {
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
  public boolean printResearcher(String id, Set<String> idList) {
    boolean isIdValid = false;
    for (String collaborationId : idList) {
      if (id.equals(collaborationId)) {
        System.out.println("Found");
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
  private void printInputMenu() {
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
   */
  private boolean addToIsern() {
    List<Researchers> newResearchers;
    List<Organizations> newOrganizations;
    List<Collaborations> newCollaborations;
    boolean isDone = false;

    // Print the menu.
    this.printInputMenu();

    while (!isDone) {
      Scanner scanner = new Scanner(System.in);
      String input;

      System.out.println("Enter the command: ");
      input = scanner.nextLine();

      if (input.equalsIgnoreCase("r")) {
        List<Researcher> newResearchersList = this.addResearcher();
      }
      else if (input.equalsIgnoreCase("o")) {
        //List<Organization> newOrganizationsList = this.addOrganization();
        System.out.println("o");
      }
      else if (input.equalsIgnoreCase("c")) {
        //List<Collaboration> newCollaborationsList = this.addCollaboration();
        System.out.println("c");
      }
      else if (input.equalsIgnoreCase("s")) {
        // Save into xml files.
        System.out.println("s");
      }
      else if (input.equalsIgnoreCase("q")) {
        isDone = true;
      }
      else {
        System.out.println(" The command you entered " + input + " is incorrect.");
        System.out.println(" Please enter a valid command.");
      }
      //if input is r call addResearchers method
      //if input is o call addOrganizations method
      //if input is c call addCollaborations method
      //if input is s save the current information to XML files then reload the xml data
      //if input is q, check if lists are saved, if not ask user if he wants to save
      //if yes, save, then leave the program
    }
    return true;
  }

  /**
   * Adds researchers.
   * 
   * @return A list of all researchers added.
   */
  private List<Researcher> addResearcher() {
    Scanner scanner = new Scanner(System.in);
    boolean isDone = false;
    boolean researcherExists = false;
    List<Researcher> newResearchersList = new ArrayList<Researcher>();

    while (!isDone) {
      String input;

      // Ask for name.
      System.out.println("\n Enter the researcher's name:\n ");
      input = scanner.nextLine();
      String name = input;

      // Access Researchers to see if the name is on the list.
      Researchers researchersList = this.mixl.getResearchers();
      for (Researcher currentResearcher : researchersList.getResearcher()) {
        if (currentResearcher.getName().equals(name)) {
          researcherExists = true;

          // Tell the user if the Researcher is on the list.
          // Ask if the user wants to modify the entry.
          System.out.println(input
              + "Is an existing Researcher. Would you like to modify the existing entry?");
          System.out.println("Enter y for yes, n for no: ");
          input = scanner.nextLine();

          break;
        }
      }

      // If there is no entry or the user wants to modify existing entry, ask for the information.
      if (!researcherExists || (researcherExists && input.equalsIgnoreCase("y"))) {
        String researcherName = name;
        String researcherOrg;
        String researcherEmail;
        String researcherPicLink;
        String researcherBio;

        System.out.print("\n Enter the researcher's organization: ");
        input = scanner.nextLine();
        researcherOrg = input;

        System.out.print("\n Enter the researcher's e-mail address: ");
        input = scanner.nextLine();
        researcherEmail = input;

        System.out.print("\n Enter the researcher's picture link: ");
        input = scanner.nextLine();
        researcherPicLink = input;

        System.out.print("\n Enter the researcher's bio-statement: ");
        input = scanner.nextLine();
        researcherBio = input;

        // Show the information entered.
        System.out.println("You Entered:");
        System.out.println("Name: " + researcherName);
        System.out.println("Organization: " + researcherOrg);
        System.out.println("E-mail Address: " + researcherEmail);
        System.out.println("Picture Link: " + researcherPicLink);
        System.out.println("Bio-Statement: " + researcherBio);

        // Confirm the information is correct.
        System.out.println("Is this correct?\nEnter y for yes, n for no: ");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("y")) {
          Researcher researcherInfo = new Researcher();
          researcherInfo.setName(researcherName);
          researcherInfo.setOrganization(researcherOrg);
          researcherInfo.setEmail(researcherEmail);
          researcherInfo.setPictureLink(researcherPicLink);
          researcherInfo.setBioStatement(researcherBio);

          // Ask if the user wants to add another Researcher entry.
          System.out.println("Do you want to add another Researcher?\nEnter y for yes, n for no: ");
          input = scanner.nextLine();

          // If the user doesn't want to add another Researcher entry, set flag to exit loop
          if (input.equalsIgnoreCase("n")) {
            isDone = true;
          }

          // Add researcher to list containing all Researchers added.
          newResearchersList.add(researcherInfo);
        }
      }
      //if not, go to the main menu
      //if yes, prompt for name 
    }

    return newResearchersList;
  }

  /**
   * Checks for valid arguments and calls corresponding print methods.
   * @param args containing command Line arguments.
   * @return argumentsPass if first argument given is valid.
   */
  public boolean checkArguments(String[] args) {
    boolean argumentsPass = this.argumentsPass;

    try {
      if ("-listCollaborations".equals(args[0])) {
        boolean collaborationsExist = false;
        try {
          if ("-organization".equals(args[1])) {
            collaborationsExist = listCollaborations(args[1], args[2]);
            if (!collaborationsExist) {
              System.out.println("There were no collaborations found for "
                  + args[2].replace('_', ' '));
            }
            argumentsPass = true;
          }
          else if ("-year".equals(args[1])) {
            int year = 0;
            try {
              if (args[2].isEmpty()) {
                System.out.println("Year field empty.  Please specify a year");
                argumentsPass = false;
              }
              else {
                year = Integer.parseInt(args[2]);
                if (year >= 1990 && year <= 2010) {
                  if (args[2].isEmpty()) {
                    System.out.println("Empty third argument for -organization");
                  }
                  else {
                    collaborationsExist = listCollaborations(args[1], args[2]);
                    if (!collaborationsExist) {
                      System.out.println("There were no collaborations found for " + args[2]
                          + args[2].replace('_', ' '));
                    }
                    argumentsPass = true;
                  }
                }
                else {
                  System.out.println("Year specified needs to fall between 1990 and 2010.");
                }
              }
            }
            catch (Exception e) {
              System.out.println("Year provided invalid.");
              argumentsPass = false;
            }
          }
          else if ("-researcher".equals(args[1])) {
            collaborationsExist = listCollaborations(args[1], args[2]);
            if (!collaborationsExist) {
              System.out.println("There were no collaborations found for " + args[2]
                  + args[2].replace('_', ' '));
            }
            argumentsPass = true;
          }
          else {
            System.out.println("Invalid Second Argument for -listCollaborations");
            System.out.println("Valid Second Argument for -listCollaborations:");
            System.out.println("\t-organization, -year, -researcher");
            argumentsPass = false;
          }
        }
        catch (Exception e) {
          System.out.println("Invalid Second argument");
          System.out.println(e.getMessage());
        }
      }

      else if ("-describe".equals(args[0])) {
        try {
          Set<String> idList;
          boolean isIdValid = false;
          if ("-researcher".equals(args[1])) {
            idList = this.mixl.getUniqueIds();
            isIdValid = printResearcher(args[2], idList);
            if (!isIdValid) {
              System.out.println("ID entered invalid.");
            }
            argumentsPass = true;
          }
          else if ("-organization".equals(args[1])) {
            idList = this.mixl.getUniqueIds();
            isIdValid = printOrganization(args[2], idList);
            if (!isIdValid) {
              System.out.println("ID entered invalid.");
            }
            argumentsPass = true;
          }
          else if ("-collaboration".equals(args[1])) {
            idList = this.mixl.getUniqueIds();
            isIdValid = printCollaboration(args[2], idList);
            if (!isIdValid) {
              System.out.println("ID entered invalid.");
            }
            argumentsPass = true;
          }
          else if ("-all".equals(args[1])) {
            try {
              if ("Researchers".equals(args[2])) {
                printResearchers(this.mixl.getResearchers());
                argumentsPass = true;
              }
              else if ("Organizations".equals(args[2])) {
                printOrganizations(this.mixl.getOrganizations());
                argumentsPass = true;
              }
              else if ("Collaborations".equals(args[2])) {
                printCollaborations(this.mixl.getCollaborations());
                argumentsPass = true;
              }
              else {
                System.out.println("Last argument for '-all' invalid.");
                argumentsPass = false;
              }
            }
            catch (Exception e) {
              System.out.println(e.getMessage());
              argumentsPass = false;
            }
          }
          else {
            argumentsPass = false;
          }
        }
        catch (Exception e) {
          System.out.println("Second Argument Error");
          argumentsPass = false;
        }
      }

      else if ("-listOrganizations".equals((args[0]))) {
        if ("-collaborationLevelEquals".equals(args[1])) {
          //int numberOfCollabs = 0;

          try {
            int numberOfCollabs = Integer.parseInt(args[2]);
            boolean listOrgsEqu = listOrganizationsEquals(numberOfCollabs);
            argumentsPass = true;
          }
          catch (Exception e) {
            System.out.println("Invalid third argument");
            argumentsPass = false;
          }
        }
        else if ("-collaborationLevelGreaterThan".equals(args[1])) {
          //int numberOfCollabs = 0;

          try {
            int numberOfCollabs = Integer.parseInt(args[2]);
            boolean listOrgsGreaterThan = listOrganizationsGreaterThan(numberOfCollabs);
            argumentsPass = true;
          }
          catch (Exception e) {
            System.out.println("Invalid third argument");
            argumentsPass = false;
          }
        }
        else {
          System.out.println("Second Argument error.");
          argumentsPass = false;
        }
      }
      /*else if ("-showGui".equals(args[0])) {
        MyIsernGui.createGui(mixl.getCollaborations(),
            mixl.getOrganizations(),
            mixl.getResearchers());

      }*/
      else if ("-help".equals(args[0])) {
        printHelp();
      }
      else {
        System.out.println("Error:  Invalid First Argument");
        //System.out.print("Valid First Arguments:");
        //System.out.println("\t-listCollaborations, -describe, -listOrganizations");
        argumentsPass = false;
      }
    }
    catch (Exception e) {
      System.out.println("Error on first argument passed");
      argumentsPass = false;
    }
    return argumentsPass;
  }

  /**
   * Prints helpful information for commmand line options
   */
  void printHelp() {
    //Provides a 'help' mechanism similar to the Unix style.
    String helpString = "";
    helpString += "\nProvides sample code for loading XML ";
    helpString += "and marshalling it into their JAXB related classes.";
    helpString += "\nUsage: MyIsernXmlLoader [OPTION]";
    helpString += "\n  -listCollaborations -organization <uniqueID>";
    //helpString +=   "\tLists Collaborations known for specified organization";
    helpString += "\n  -listCollaborations -year <year>";
    //helpString +=   "\t\tLists Collaborations known for specified year";
    helpString += "\n  -listCollaborations -researcher <uniqueID>";
    //helpString +=   "\tLists Collaborations known for specified researcher";
    helpString += "\n  -describe -researcher <uniqueID>";
    helpString += "\n  -describe -organization <uniqueID>";
    helpString += "\n  -describe -collaboration <uniqueID>";
    helpString += "\n  -describe -all Researchers";
    helpString += "\n  -describe -all Organizations";
    helpString += "\n  -describe -all Collaborations";
    helpString += "\n  -listOrganizations -collaborationLevelEquals <integer>";
    helpString += "\n  -listOrganizations -collaborationLevelGreaterThan <integer>";
    helpString += "\n  -showGui";
    helpString += "\n  -help";
    System.out.println(helpString);
    /*System.out.println("\t-c, --printCollaborations\tprints collaborations.");
    System.out.println("\t-o, --printOrganizations\tprints organizations.");
    System.out.println("\t-r, --printResearchers\t\tprints researchers.");
    System.out.println("\t    --help\t\t\tdisplay this help and exits.");*/
  }
}