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
  MyIsern myIsern;
  MyIsernXmlLoader mixl;
  String[] args;
  
  /**
   * Initializes command line information.
   * 
   * @param args arguments passed from the command line.
   */
  Parser(String[] args) {
    this.args = args;
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
      System.out.println("Zero arguments entered.");
      
    }
    else { 
      System.out.println("Invalid number of Arguments.  Please enter three arguments");
    }
    
    return argumentsEqualsThree;
  }
  
 /** 
  * Checks for valid arguments and calls corresponding print methods.
  * @param args containing command Line arguments.
  * @return argumentsPass if first argument given is valid.
  */
 /*public boolean checkArguments(String[] args) {
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
     else if ("-showGui".equals(args[0])) {
       System.out.println("Show GUI");
       /*MyIsernGui.createGui(mixl.getCollaborations(),
             mixl.getOrganizations(),
             mixl.getResearchers());
        
       }
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
  */
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