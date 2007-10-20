package edu.hawaii.myisern.example;


//import java.util.HashSet;
//import java.util.Set;

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
  //private String organization = "-organization";
 // private String researcher = "-researcher";
 // private String uOfH = "University_of_Hawaii";
 // private String pJohnson = "Philip_Johnson";
  
  /**
   * Tests MyIsern.
   */
    @Test
    public void testMyIsern() {
      String[] args = new String[3];
      args[0] = "test";
      args[1] = "one";
      args[2] = "two";
      MyIsern myisern = new MyIsern(args);
      assertNotNull ("Main method should pass", args);
    }
}
