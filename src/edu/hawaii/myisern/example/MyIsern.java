package edu.hawaii.myisern.example;

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
    /*boolean myIsernRunCheck = */myIsern.runMyIsern();
    
    //if (myIsernRunCheck) {
    //System.out.println("MyIsern Ran successfully.");
    //}
    //else {
    //  System.out.println("MyIsern Did not run successfully.");
    //}
  }
  
  /**
   * Checks for user input and then runs the print methods accordingly. 
   * If the user does not enter any arguments, nothing will be printed out.
   * 
   * @throws Exception If XML data did not load properly.
   */
  private void runMyIsern() throws Exception {
    Parser parser = new Parser(this.commandLineArgs);    
    //Prints according to what boolean is true
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();

    
    if (parser.argsCounter == 0) {
      parser.printHelp();
    }
    else {
      if (parser.isCollaborationsOn) {
        mixl.printCollaborations();
      }
      if (parser.isOrganizationsOn) {
        mixl.printOrganizations();
      }
      if (parser.isResearchersOn) {
        mixl.printResearchers();
      }
    }
    //@return boolean Returns true if no errors were encountered.
    //return true;
  }
}
