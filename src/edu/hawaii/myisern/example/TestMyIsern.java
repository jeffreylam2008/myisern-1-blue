package edu.hawaii.myisern.example;


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
  
  /**
   * Tests ListCollaborations
   * @throws Exception if problem occurs
   */
  @Test
  public void testListCollaborations() throws Exception { 
    MyIsern myisern = new MyIsern(null); 
    String returnTrue = "Should return true";
    assertTrue(returnTrue, myisern.listCollaborations("-researcher", "Philip_Johnson"));
    assertTrue(returnTrue, myisern.listCollaborations("-organization", "University_of_Hawaii"));
    assertTrue(returnTrue, myisern.listCollaborations("-year", "2006"));
  }
  
  /**
   * Tests checkArguments method
   * @throws Exception if problem occurs
   */
  @Test
  public void testCheckArguments() throws Exception {
    String year = "-year";
    String organization = "-organization";
    String researcher = "-researcher";
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
    args[1] = organization;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = organization;
    args[2] = "University_of_Hawaii";
    assertTrue(returnTrue, myisern.checkArguments(args));
    
    args[0] = listCollab;
    args[1] = researcher;
    args[2] = "Philip_Johnson";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = listCollab;
    args[1] = researcher;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));
    
    args[0] = describe;
    args[1] = researcher;
    args[2] = "Philip_Johnson";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = researcher;
    args[2] = "";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = "";
    args[2] = "";
    assertFalse(returnFalse, myisern.checkArguments(args));
    
    args[0] = describe;
    args[1] = organization;
    args[2] = "University_of_Hawaii";
    assertTrue(returnTrue, myisern.checkArguments(args));
    args[0] = describe;
    args[1] = organization;
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
  
}
