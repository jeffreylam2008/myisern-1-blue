package edu.hawaii.myisern.example;

import junit.framework.TestCase;
import org.junit.Test; //import com.meterware.httpunit.WebConversation;
//import com.meterware.httpunit.WebLink;
//import com.meterware.httpunit.WebResponse;
import edu.hawaii.myisern.example.MyIsernXmlLoader;

//import edu.hawaii.myisern.example.MyIsernXmlLoader;

/**
 * Demonstrates that the sample code is working.
 * 
 * @author Philip Johnson
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
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
   * Tests web link validation method.
   * 
   * @throws Exception if problem occurs.
   */
  @Test
  public void testisLinkValidMethod() throws Exception {
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    assertTrue("known website: http://www.hawaii.edu", mixl.isLinkValid("http://www.hawaii.edu"));
    assertFalse("fake website: http://myfake.invalid.website", mixl
        .isLinkValid("http://myfake.invalid.website"));
    assertFalse("no website entered", mixl.isLinkValid(""));
  }

}
