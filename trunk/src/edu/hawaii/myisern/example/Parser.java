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
  boolean argumentsPass = false;
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
   * Checks first argument if it is valid.
   * @param args containing command Line arguments.
   * @return firstArgumentPass if first argument given is valid.
   */
  public boolean checkArguments (String[] args) {
    boolean argumentsPass = this.argumentsPass;
    this.argsCounter = args.length;
    try {
      //Checking if first argument entered is '-listCollaborations'
      if ("-listCollaborations".equals(args[0])) {
        try {
          //Checks if second arguments are valid for given first argument
          if ("-organization".equals(args[1])) {
            //Need to check for unique ID
            System.out.println("Check for unique ID");
          }
          else if ("-year".equals(args[1])) {
            int year = 0;
            try {
              year = Integer.parseInt(args[2]);
              if (year >= 1990 && year <= 2010) {
                argumentsPass = true;
              }
            }
            catch (Exception e) {
              System.out.println("Year provided invalid.");
              argumentsPass = false;
            }
          }
          else if ("-researcher".equals(args[1])) {
            //Need to check for unique ID
            System.out.println("Check for unique ID");
          }
          else {
            argumentsPass = false;
          }
        }
        catch (Exception e) {
          System.out.println("Second Argument Error");
        }
      }
      
      //Checking if first argument entered is '-describe'
      else if ("-describe".equals(args[0])) {
        try {
          if ("-researcher".equals(args[1]) || "-organization".equals(args[1])
              || "-collaboration".equals(args[1])) {
            //Need to check for Unique ID
            System.out.println("Check for unique ID");
          }
          else if ("-all".equals(args[1])) {
            try {
              if ("Researchers".equals(args[2])) {
                isResearchersOn = true;
              }
              else if ("Organizations".equals(args[2])) {
               isOrganizationsOn = true; 
              }
              else if ("Collaborations".equals(args[2])) {
                isCollaborationsOn = true;
              }
              else {
                System.out.println("Last argument for '-all' invalid.");
                argumentsPass = false;
              }
            }
            catch (Exception e) {
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
      
      //Checking if first argument entered is '-collaborationLevelEquals'
      //or '-collaborationLevelGreaterThan'
      else if ("-listOrganizations".equals((args[0]))) {
        if ("-collaborationLevelEquals".equals(args[1])
            || ("-collaborationLevelGreaterThan".equals(args[1]))) {
          //int numberOfCollabs = 0;
          
          try {
            int numberOfCollabs = Integer.parseInt(args[2]);
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
    }
    catch (ArrayIndexOutOfBoundsException aibe) {
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
    helpString +=   "and marshalling it into their JAXB related classes.";
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
    System.out.println(helpString);
    /*System.out.println("\t-c, --printCollaborations\tprints collaborations.");
    System.out.println("\t-o, --printOrganizations\tprints organizations.");
    System.out.println("\t-r, --printResearchers\t\tprints researchers.");
    System.out.println("\t    --help\t\t\tdisplay this help and exits.");*/
  }
}
