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
 * @author Johne Hauge
 */
public class TestMyIsernXmlLoader extends TestCase {
  /**
   * Tests the Collaboration XML to Java conversion.
   * 
   * @throws Exception If problems occur.
   */
  @Test
  public void testLoader() throws Exception {
    MyIsernXmlLoader loader = new MyIsernXmlLoader();
    assertEquals("Check collaborations size", 3, loader.getNumCollaborations());
    assertEquals("Check organizations size", 2, loader.getNumOrganizations());
    assertEquals("Check researchers size", 3, loader.getNumResearchers());
    assertTrue("Check Collaborations", loader.getCollaborationsXml().contains("<Collaborations>"));
  }

  /**
   * Tests main method.
   * 
   * @throws Exception if problem occurs.
   */
  @Test
  public void testMain() throws Exception {
    String[] args = new String[3];
    args[0] = "--printCollaborations";
    args[1] = "--printResearchers";
    args[2] = "--printOrganizations";
    MyIsern.main(args);
    args[0] = "-c";
    args[1] = "-r";
    args[2] = "-o";
    MyIsern.main(args);
    assertNotNull("Main method should pass.", args);
  }
  
  /**
   * Tests main method with no command line arguments.
   * 
   * @throws Exception if problem occurs.
   */
  @Test
  public void testNoArguments() throws Exception {
    String[] args = new String[0];
    MyIsern.main(args);
    assertNotNull("Main method should pass.", args);
  }
  
  /**
   * Tests main method with '--help' argument.
   * 
   * @throws Exception if problem occurs.
   */
  @Test
  public void testHelpArguments() throws Exception {
    String[] args = new String[1];
    args[0] = "--help";
    MyIsern.main(args);
    assertNotNull("Main method should pass.", args);
  }
  
  /**
   * Tests web link validation method.
   * 
   * @throws Exception if problem occurs.
   */
  @Test
  public void testisLinkValidMethod() throws Exception {
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    assertTrue("known website: http://www.hawaii.edu", 
    		   mixl.isLinkValid("http://www.hawaii.edu"));
    assertFalse("fake website: http://myfake.invalid.website", 
    		    mixl.isLinkValid("http://myfake.invalid.website"));
  }
  

}
