package edu.hawaii.myisern.example;

/**
 * Parses command line options.
 * 
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class Parser {
  boolean isCollaborationsOn = false;
  boolean isOrganizationsOn = false;
  boolean isResearchersOn = false;
  boolean firstArgumentPass = false;
  boolean secondArgumentPass = false;
  boolean thirdArgumentPass = false;
  
  int booleanCounter;
  int argsCounter;
  
  /**
   * Initializes command line information.
   * 
   * @param args arguments passed from the command line.
   */
  Parser(String[] args) {
    this.booleanCounter = 0;
    this.argsCounter = 0;
    
/*
    
    //Check if first argument is valid input
    this.firstArgumentPass = checkFirstArgument(args);
    
    
    if (firstArgumentPass) {
      System.out.println("First Argument Pass");
    }
    else {
      this.printHelp();
    } */
    
  /*  
    for (String commandLine : args) {
      if ("--help".equals(commandLine)) {
        argsCounter = 0;
        break;
      }
      
      if ("--printCollaborations".equals(commandLine) 
          || "-c".equals(commandLine)) {
        this.isCollaborationsOn = true;
        booleanCounter++;
      }
      else if ("--printOrganizations".equals(commandLine) 
          || "-o".equals(commandLine)) {
        this.isOrganizationsOn = true;
        booleanCounter++;
      }
      else if ("--printResearchers".equals(commandLine) 
          || "-r".equals(commandLine)) {
        this.isResearchersOn = true;
        booleanCounter++;
      }
      argsCounter++;
    } */
  }
  
  /**
   * Checks second argument to see if it is valid.
   * @param args containing command line arguments.
   * @return secondArgumentPass if second argument entered is valid.
   */
  public boolean checkSecondArgument (String[] args) {
    boolean secondArgumentPass = false;
    
    try {
      if ("-organization".equals(args[1])) {
        secondArgumentPass = true;
      }
      else if ("-year".equals(args[1])) {
        secondArgumentPass = true;
      }
      else if ("-researcher".equals(args[1])) {
        secondArgumentPass = true;
      }
      else if ("-collaboration".equals(args[1])) {
        secondArgumentPass = true;
      }
      else if ("-all".equals(args[1])) {
        secondArgumentPass = true;
      }
      else if ("-collaborationLevelEquals".equals(args[1])
          || ("-collaborationLevelGreaterThan".equals(args[1]))) {
        secondArgumentPass = true;
      }
    }
    catch (Exception e) {
      secondArgumentPass = false;
    }
    
    return secondArgumentPass;
  }
  
  /**
   * Checks first argument if it is valid.
   * @param args containing command Line arguments.
   * @return firstArgumentPass if first argument given is valid.
   */
  public boolean checkFirstArgument (String[] args) {
    boolean firstArgumentPass = false;
    try {
      if ("-describe".equals(args[0])) {
        //Display
        firstArgumentPass = true;
      } 
      else if ("-listCollaborations".equals(args[0])) {
        //list only collaborations
        firstArgumentPass = true;
      }
      else if ("-listOrganizations".equals((args[0]))) {
        //list only organizations
        firstArgumentPass = true;
      }
    }
    catch (ArrayIndexOutOfBoundsException e) {
      firstArgumentPass = false;
    }
    return firstArgumentPass;
  }
  
  /**
   * Prints helpful information for commmand line options
   */
  void printHelp() {
    //Provides a 'help' mechanism similar to the Unix style.
    System.out.println("Provides sample code for " + 
      "loading XML and marshalling it into their JAXB related classes.");
    System.out.println("\nUsage: MyIsernXmlLoader [OPTION]");
    
    System.out.println("\t-listCollaborations -organization <uniqueID>" +
    		"\n\t\tLists Collaborations known for specified organization\n");
    System.out.println("\t-listCollaborations -year <year>" +
    		"\n\t\tLists Collaborations known for specified year\n");
    System.out.println("\t-listCollaborations -researcher <uniqueID>" +
    		"\n\t\tLists Collaborations known for specified researcher\n");
    
    System.out.println("\t-describe -researcher <uniqueID>");
    System.out.println("\t-describe -organization <uniqueID>");
    System.out.println("\t-describe -collaboration <uniqueID>");
    System.out.println("\t-describe -all Researchers");
    System.out.println("\t-describe -all Organizations");
    System.out.println("\t-describe -all Collaborations");
    
    System.out.println("\t-listOrganizations -collaborationLevelEquals <integer>");
    System.out.println("\t-listOrganizations -collaborationLevelGreaterThan <integer>");
   
    /*System.out.println("\t-c, --printCollaborations\tprints collaborations.");
    System.out.println("\t-o, --printOrganizations\tprints organizations.");
    System.out.println("\t-r, --printResearchers\t\tprints researchers.");
    System.out.println("\t    --help\t\t\tdisplay this help and exits.");*/
  }
}
