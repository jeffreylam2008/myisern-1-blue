package edu.hawaii.myisern.example;


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
   * Tests ListCollaborations
   * @throws Exception if problem occurs
   */
  @Test
  public void testListCollaborations() throws Exception { 
    MyIsern myisern = new MyIsern(null); 
    String returnTrue = "Should return true";
    assertTrue(returnTrue, myisern.listCollaborations(this.researcher, pJohnson));
    assertTrue(returnTrue, myisern.listCollaborations(this.organization, uOfH));
    assertTrue(returnTrue, myisern.listCollaborations("-year", "2006"));
  }
  
  /**
   * Tests checkArguments method
   * @throws Exception if problem occurs
   */
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
    String[]args = new String[3];
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
  /**
   * Tests main method with different argument.
   * 
   * @throws Exception if problem occurs.
   */
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
    
    
    args[1] = "-organization";
    args[2] = "University of Hawaii";
    MyIsern.main(args);
    args[2] = this.uOfH;
    MyIsern.main(args);
    args[2] = "University_of_Peanutbutter_Island";
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    args[1] = "-collaboration";
    args[2] = "UM-UH-HPCS";
    MyIsern.main(args);
    args[2] = "UM-UH-hpcs";
    MyIsern.main(args);
    args[2] = "Applejacks";
    MyIsern.main(args);
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    args[1] = "-all";
    args[2] = "Researchers";
    MyIsern.main(args);
    args[2] = "researchers";
    MyIsern.main(args);
    args[2] = "Organizations";
    MyIsern.main(args);
    args[2] = "organizations";
    MyIsern.main(args);
    args[2] = "Collaborations";
    MyIsern.main(args);
    args[2] = "collaborations";
    MyIsern.main(args);
    args[2] = "fuddy_duddies";
    MyIsern.main(args);
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    
    args[0] = "-listOrganizations";
    args[1] = "-collaborationLevelEquals";
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    args[1] = "-collaborationLevelGreaterThan";
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    args[0] = "-listCollaborations ";
    args[1] = "-organization";
    args[2] = "University of Hawaii";
    MyIsern.main(args);
    args[2] = this.uOfH;
    MyIsern.main(args);
    args[2] = "University_of_Peanutbutter_Island";
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
    
    args[1] = "-year";
    for (String testArg : testArgs) {
    	args[2] = testArg;
	    MyIsern.main(args);
    }
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
    
    assertNotNull("Main method should pass.", args);
    System.out.println("**********************************************");
    
  }
}
