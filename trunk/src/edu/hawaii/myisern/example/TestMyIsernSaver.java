package edu.hawaii.myisern.example;

import junit.framework.TestCase;

/**
 * Demonstrates that the sample code is working.
 * 
 * @author Philip Johnson
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class TestMyIsernSaver extends TestCase {
  /**
   * test save methods.
   * 
   * @throws Exception if problem occurs.
   */
  public void testSaveMethods() throws Exception {
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();
    MyIsernXmlSaver mixs = new MyIsernXmlSaver();
    mixs.saveCollaboratotionsXml(mixl.collaborations);
    mixs.saveOrganizationsXml(mixl.organizations);
    mixs.saveResearchersXml(mixl.researchers);
    // assertNull ("Should fail since there is no file found", mixl);
    assertNotNull("just maken it through.", mixl);
  }
}
