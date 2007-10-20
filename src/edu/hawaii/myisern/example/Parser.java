package edu.hawaii.myisern.example;
//import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebLink;
//import com.meterware.httpunit.WebResponse;

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
  boolean isShowGuiOn = false;
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
    

  }
    
  /**
   * Checks first argument if it is valid.
   * @param args containing command Line arguments.
   * @return firstArgumentPass if first argument given is valid.
   */
  public boolean checkArguments2 (String[] args) {
    boolean argumentsEqualsThree = this.argumentsPass;
    
    if (args.length == 3) {
      if ("-listCollaborations".equals(args[0]) || "-describe".equals(args[0]) 
          || "-listOrganizations".equals(args[0])) {
        
        if ("-listCollaborations".equals(args[0])) {
          if ("-organization".equals(args[1])) {
            System.out.println("-o");
          }
          else if ("-year".equals(args[1])) {
            System.out.println("-y");
          }
          else if ("-researcher".equals(args[1])) {
            System.out.println("-r");
          }
          else {
            System.out.println("Second Argument invalid for '-listCollaborations.'  " +
                "Please Check Spelling...");
            argumentsEqualsThree = false;
          }
        }
        else if ("-describe".equals(args[0])) {
          System.out.println("-d");
        }
        else if ("-listOrganizations".equals(args[0])) {
          System.out.println("-l");
        }
      }
      else {
        System.out.println("First Argument Not Valid. Please check Spelling.");
      }
    }
    else if (args.length == 0) {
      System.out.println("Zero arguments entered.  Loading Menu...");
      argumentsEqualsThree = false;
    }
    else { 
      System.out.println("Invalid number of Arguments.  Please enter three arguments");
    }
    
    return argumentsEqualsThree;
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
    helpString += "\n  -showGui";
    helpString += "\n  -help";
    System.out.println(helpString);
    /*System.out.println("\t-c, --printCollaborations\tprints collaborations.");
    System.out.println("\t-o, --printOrganizations\tprints organizations.");
    System.out.println("\t-r, --printResearchers\t\tprints researchers.");
    System.out.println("\t    --help\t\t\tdisplay this help and exits.");*/
  }
}