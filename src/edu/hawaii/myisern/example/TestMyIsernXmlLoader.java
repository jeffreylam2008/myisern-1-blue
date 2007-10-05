package edu.hawaii.myisern.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Demonstrates that the sample code is working. 
 * @author Philip Johnson
 */
public class TestMyIsernXmlLoader {

  /**
   * Tests the Collaboration XML to Java conversion. 
   * @throws Exception If problems occur.
   */
  @Test public void testLoader() throws Exception {
    MyIsernXmlLoader loader = new MyIsernXmlLoader();
    assertEquals("Check collaborations size", 1, loader.getNumCollaborations());
    assertEquals("Check organizations size", 2, loader.getNumOrganizations());
    assertEquals("Check researchers size", 3, loader.getNumResearchers());
    assertTrue("Check Collaborations", loader.getCollaborationsXml().contains("<Collaborations>"));
  }

}
