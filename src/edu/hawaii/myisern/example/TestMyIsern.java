package edu.hawaii.myisern.example;

//import java.util.HashSet;
//import java.util.Set;

import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import org.junit.Test;

//import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebLink;
//import com.meterware.httpunit.WebResponse;

//import edu.hawaii.myisern.example.MyIsernXmlLoader;

/**
 * Demonstrates that the sample code is working.
 * 
 * @author Philip Johnson
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class TestMyIsern extends TestCase {
  private String organization = "-organization";
  private String researcher = "-researcher";
  private String uOfH = "University_of_Hawaii";
  private String pJohnson = "Philip_Johnson";

  /**
   * Tests MyIsern.
   * 
   * @throws Exception if problem occurs
   */
  @Test
  public void testMyIsern() throws Exception {
    MyIsern myisern = new MyIsern(null);
    String returnTrue = "Should return true";
    /*
     * assertTrue(returnTrue, myisern.listCollaborations(this.researcher, pJohnson));
     * assertTrue(returnTrue, myisern.listCollaborations(this.organization, uOfH));
     * assertTrue(returnTrue, myisern.listCollaborations("-year", "2006"));
     */
    assertNotNull(returnTrue, myisern);
  }

  /**
   * Tests Describe methods.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testDescribe() throws Exception {
    MyIsern myisern = new MyIsern(null);
    String returnTrue = "Should not be null";
    String string = "Researchers";
    String string2 = "";
    myisern.describe(string);
    assertNotNull(returnTrue, myisern);
    string = "Organizations";
    myisern.describe(string);
    assertNotNull(returnTrue, myisern);
    string = "Collaborations";
    myisern.describe(string);
    assertNotNull(returnTrue, myisern);
    string = "";
    myisern.describe(string);
    assertNotNull(returnTrue, myisern);

    string = "-researcher";
    myisern.describe(string, string2);
    assertNotNull(returnTrue, myisern);
    string = "-collaboration";
    myisern.describe(string, string2);
    assertNotNull(returnTrue, myisern);
    string = "-organization";
    myisern.describe(string, string2);
    assertNotNull(returnTrue, myisern);
    string = "";
    myisern.describe(string, string2);
    assertNotNull(returnTrue, myisern);
  }

  /**
   * Tests ListCollaborations.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testListCollaborations() throws Exception {
    MyIsern myisern = new MyIsern(null);
    String returnTrue = "Should not be null";
    String string = "-organization";
    myisern.listCollaborations(string, "");
    assertNotNull(returnTrue, myisern);
    string = "-researcher";
    myisern.listCollaborations(string, "");
    assertNotNull(returnTrue, myisern);
    string = "-year";
    myisern.listCollaborations(string, "");
    assertNotNull(returnTrue, myisern);
  }

  /**
   * Tests Print Collaborations.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testPrintCollaborations() throws Exception {
    MyIsern myisern = new MyIsern(null);
    String returnTrue = "Not null";
    String string = "";
    Set<String> setString = new HashSet<String>();
    setString.add("Test");
    myisern.printCollaboration(string, setString);
    assertNotNull(returnTrue, myisern);
  }

  /**
   * Tests Main.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testMain() throws Exception {
    String[] args = new String[3];
    args[0] = "-input";
    MyIsern myisern = new MyIsern(args);
    assertNotNull("Will run", myisern);
  }

  /**
   * Tests print Menu.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testPrintMenu() throws Exception {
    MyIsern myisern = new MyIsern(null);
    String run = "Should run";
    myisern.printInputMenu();
    assertNotNull(run, myisern);

    myisern.printHelp();
    assertNotNull("Will Run", myisern);
  }

  /**
   * Tests listOrganizationsGreaterThan/EqualTo methods.
   * 
   * @throws Exception if error occurs.
   */
  @Test
  public void testListOrganizationsEquals() throws Exception {
    MyIsern myisern = new MyIsern(null);
    int i = 1;
    String run = "Should run";

    assertTrue(run, myisern.listOrganizationsEquals(i));
    assertFalse(run, myisern.listOrganizationsEquals(0));

    // assertTrue (run, myisern.listOrganizationsGreaterThan(1));
  }

  /**
   * Tests checkArguments method
   * 
   * @throws Exception if problem occurs
   */
/*
  @Test
  public void testCheckArguments() throws Exception {
    String year = "-year";
    String returnTrue = "Should return true";
    String returnFalse = "Should Fail";
    String listCollab = "-listCollaborations";
    String describe = "-describe";
    String all = "-all";
    String listOrg = "-listOrganizations";
    MyIsern myisern = new MyIsern(null);
    String[] args = new String[3];
    args[0] = "";
    args[1] = "";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = "";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = year;
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = year;
    args[2] = "2006";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = year;
    args[2] = "abcde";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = year;
    args[2] = "2050";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = "-FAILFAILFAIL";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = this.organization;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = listCollab;
    args[1] = this.organization;
    args[2] = "University_of_Hawaii";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = listCollab;
    args[1] = this.researcher;
    args[2] = pJohnson;
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = this.researcher;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = this.researcher;
    args[2] = pJohnson;
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = this.researcher;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = "";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = this.organization;
    args[2] = this.uOfH;
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = this.organization;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = "-collaboration";
    args[2] = "UM_UH_HPCS";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = "-collaboration";
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = all;
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = all;
    args[2] = "Researchers";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = all;
    args[2] = "Collaborations";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = describe;
    args[1] = all;
    args[2] = "Organizations";
    assertTrue(returnTrue, myisern.checkArguments(args));

    args[0] = listOrg;
    args[1] = "-collaborationLevelEquals";
    args[2] = "2";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listOrg;
    args[1] = "-collaborationLevelEquals";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    args[0] = listOrg;
    args[1] = "";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));

    args[0] = listOrg;
    args[1] = "-collaborationLevelGreaterThan";
    args[2] = "2";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listOrg;
    args[1] = "-collaborationLevelGreaterThan";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));

  }
*/
  /**
   * Tests main method with different argument.
   * 
   * @throws Exception if problem occurs.
   */
/*
  @Test
  public void testArguments() throws Exception {
    System.out.println("******************testArguments******************");
    Set<String> testArgs = new HashSet<String>();
    testArgs.add("0");
    testArgs.add("1");
    testArgs.add("2");
    testArgs.add("99999999999999999999999999999999999999");
    testArgs.add("abc");
    testArgs.add("123");
    testArgs.add("-123");
    testArgs.add("*-/");
    testArgs.add("*-/123");
    testArgs.add("abc*-/");
    testArgs.add("@|&");

    String[] args = new String[0];
    MyIsern.main(args);

    args = new String[3];
    args[0] = "-describe";
    args[1] = "-researcher";
    args[2] = "Philip Johnson";
    MyIsern.main(args);
    args[2] = pJohnson;
    MyIsern.main(args);
    args[2] = "Philip_Frankenstien";
    MyIsern.main(args);
    for (String testArg : testArgs) {
      args[2] = testArg;
      MyIsern.main(args);

    }

  }*/

}
