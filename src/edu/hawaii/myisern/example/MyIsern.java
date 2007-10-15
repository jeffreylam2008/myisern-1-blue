package edu.hawaii.myisern.example;

import java.util.HashMap;
import java.util.Iterator; //import java.util.Collection;
import java.util.List;
import java.util.Map;
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
import org.eclipse.swt.SWT; //import org.eclipse.swt.SWTException;
//import org.eclipse.swt.custom.StyledText;
//import org.eclipse.swt.events.PaintEvent;
//import org.eclipse.swt.events.PaintListener;
//import org.eclipse.swt.graphics.Color;
////import org.eclipse.swt.graphics.Font;
//import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC; //import org.eclipse.swt.graphics.Path;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Canvas;
//import org.eclipse.swt.widgets.Caret;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Control;

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
  boolean isCollaborationsOn = false;
  boolean isOrganizationsOn = false;
  boolean isResearchersOn = false;
  boolean argumentsPass = false;

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
   * Adds a text box to the Gui.
   * @param text to fill in the text box.
   * @param shell to put the text box in.
   * @param x location in the shell.
   * @param y location in the shell.
   */
  public static void addTextBoxToGui(String text, Shell shell, int x, int y) {
    //Code from eclipse documentaion archive 
    //http://dev.eclipse.org/viewcvs/index.cgi/~checkout~/org.
    //	eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet55.java
    //with some modification.
    Text textBox = new Text(shell, SWT.BORDER);
    GC gc = new GC(textBox);
    FontMetrics fm = gc.getFontMetrics();
    int width = text.length() * fm.getAverageCharWidth();
    int height = fm.getHeight();
    gc.dispose();
    textBox.setSize(textBox.computeSize(width, height));
    textBox.setLocation(x, y);
    textBox.insert(text);
  }

  /**
   * Creates a Gui representation of the organizations, researchers
   * and their collaborations.
   * @param collaborations list of.
   * @param organizations list of.
   * @param researchers list of.
   */
  public static void createGui(Collaborations collaborations, Organizations organizations,
      Researchers researchers) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("myIsern-1-blue baby, cool like the ocean breeze.");
    shell.setSize(640, 480);
    int startX = 0;
    int startY = 50;
    int curX = startX;
    int curY = startY;
    curX += 100;
    // start with organizations
    for (Organization org : organizations.getOrganization()) {
      // create organization text box representation
      addTextBoxToGui(org.getName(), shell, curX, curY);
      curX += 250;
    }
    //process each control in the shell to find organization text boxes
    for (Control ctrl : shell.getChildren()) {
      //check to see if control is a Text 
      if ("Text {}".equals(ctrl.toString())) {
        Text textBox = (Text) ctrl;
        //spawn child researcher text boxes under organization
        curX = textBox.getLocation().x;
        curY = textBox.getLocation().y;
        curX += 20;
        for (Researcher researcher : researchers.getResearcher()) {
          if (textBox.getText().equals(researcher.getOrganization())) {
            curY += textBox.getSize().y + 10;
            addTextBoxToGui(researcher.getName(), shell, curX, curY);
            //draw tree links from organization text box to each 
            //researcher text box 

            GC gc = new GC(shell);
            gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
            gc.fillRectangle(0, 0, 100, 100);
            gc.setLineWidth(50);
            gc.drawLine(0, 20, 50, 20);
            gc.dispose();

          }
        }
      }
    }
    // process each collaboration
    //shell.pack ();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }

  /*
   public static void textDisplay() {
   Display display = new Display ();
   Shell shell = new Shell (display);
   Caret caret = new Caret (shell, SWT.NONE);
   caret.setBounds (10, 10, 2, 32);
   shell.open ();
   while (!shell.isDisposed ()) {
   if (!display.readAndDispatch ()) display.sleep ();
   }
   display.dispose ();
   }
   */
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

    // @return boolean Returns true if no errors were encountered.
    // return true;
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
      else if ("-showGui".equals(args[0])) {
    	  createGui(mixl.getCollaborations(),
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
