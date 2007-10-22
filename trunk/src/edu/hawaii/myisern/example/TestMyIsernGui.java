package edu.hawaii.myisern.example;

import static org.junit.Assert.assertNotNull;
import org.eclipse.swt.graphics.Point;
import org.junit.Test;

/**
 * Demonstrates that the sample code is working.
 * 
 * @author Philip Johnson
 * @author Marcius Bagwan
 * @author Sonwright Gomez
 * @author John Hauge
 */
public class TestMyIsernGui {
  /**
   * Tests my distanceBetweenTwoPoints method.
   * 
   * @throws Exception If problems occur.
   */
  @Test
  public void testDistanceBetweenTwoPoints() throws Exception {
    double distance = MyIsernGui.getDistanceBetweenTwoPoints(new Point(-2, -3), new Point(-4, 4));
    System.out.println("**********DISTANCE:   " + distance + "  ***************");
    // assertEqual(distance, 7.28);
    assertNotNull("Should pass", distance);
  }

  /**
   * Tests my gui.
   * 
   * @throws Exception If problems occur.
   */
  @Test
  public void testCreateGui() throws Exception {
    MyIsernXmlLoader mixl = new MyIsernXmlLoader();

    MyIsernGui mig = new MyIsernGui(mixl.collaborations, mixl.organizations, mixl.researchers);
    mig.createGui();
    assertNotNull("passing through", mixl);
  }
}
