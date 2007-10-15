package edu.hawaii.myisern.example;

import junit.framework.TestCase;
import org.junit.Test;

//import edu.hawaii.myisern.example.MyIsernXmlLoader;

/**
 * Demonstrates that the sample code is working.
 * 
 * @author Philip Johnson
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
    assertEquals("Check collaborations size", 1, loader.getNumCollaborations());
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
  
  /*
  public void testisLinkValidMethod() throws Exception {
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    assertTrue(mixl.isLinkValid("http://www.hawaii.edu"));
    assertFalse(mixl.isLinkValid("http://myfake.invalid.website"));
  }
  */

}