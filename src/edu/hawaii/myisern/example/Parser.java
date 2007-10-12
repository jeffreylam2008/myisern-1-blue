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

    //Checks for user input
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
    }
  }
  
  /**
   * Prints helpful information for commmand line options
   */
  void printHelp() {
    //Provides a 'help' mechanism similar to the Unix style.
    System.out.println("Provides sample code for " + 
      "loading XML and marshalling it into their JAXB related classes.");
    System.out.println("\nUsage: MyIsernXmlLoader [OPTION]");
    System.out.println("\t-c, --printCollaborations\tprints collaborations.");
    System.out.println("\t-o, --printOrganizations\tprints organizations.");
    System.out.println("\t-r, --printResearchers\t\tprints researchers.");
    System.out.println("\t    --help\t\t\tdisplay this help and exits.");
  }
}
