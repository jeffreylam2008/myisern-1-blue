package edu.hawaii.myisern.action;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

import static org.junit.Assert.assertEquals;

import org.junit.Test; 

/**
 * Tests operation of the StackStripes Push command.
 *
 * @author Jitender Miglani
 * @author Philip Johnson
 */
public class TestMyIsernActionBean  {

  /** The page title. */
  private String pageTitle = "MyIsern (version 1.3)";

  /** Get the test host. */
  private String testHost = System.getProperty("test.host");

  /** String literal for tableStack */
  private final String STACK_TABLE = "stackTable";
  
  /** String literal for popForm */
  private final String POP_FORM = "PopForm";
  
  /**
   * Tests the Stripe Stack push operation under normal situations.
   *
   * @throws Exception If problems occur
   */
  @Test
  public void testPush() throws Exception {
    WebConversation conversation = new WebConversation();

    // Get welcome.jsp page and check for successful retrieval
    String Url = testHost + "myisern-blue";
    WebResponse response = conversation.getResponse(Url);
    assertEquals("Checking index.jsp retrieval", pageTitle, response.getTitle());

    // Push the default value (1) onto the stack.
    WebForm loginForm = response.getFormWithID("LoginForm");
    WebRequest loginRequest = loginForm.getRequest();
    response = conversation.getResponse(loginRequest);
    
    WebForm pushForm = response.getFormWithID("orgLink");
    WebRequest pushRequest = pushForm.getRequest();
    response = conversation.getResponse(pushRequest);
/*
    // Check that the stack contains a single "1"
    WebTable stackTable = response.getTableWithID(STACK_TABLE);
    assertEquals("Checking stack size ", 1, stackTable.getRowCount());
    assertEquals("Checking stack contents", "1", stackTable.getTableCell(0, 0).getText());

    // Now push a second, explicit value (2) onto stack.
    pushForm = response.getFormWithID("PushForm");
    pushRequest = pushForm.getRequest();
    pushRequest.setParameter("numToPush", "2");
    response = conversation.getResponse(pushRequest);

    // Check that the stack contains two elements and that top of stack is "2".
    stackTable = response.getTableWithID(STACK_TABLE);
    assertEquals("Checking stack size (2)", 2, stackTable.getRowCount());
    assertEquals("Checking stack contents (2)", "2", stackTable.getTableCell(1, 0).getText());
  */
  }

  /**
   * Tests the Stripe Stack pop operation under normal situations.
   *
   * @throws Exception If problems occur
   */
  @Test
  public void testLogin() throws Exception {
    WebConversation conversation = new WebConversation();
    
    // Get welcome.jsp page and check for successful retrieval
    String Url = testHost + "myisern-blue";
    WebResponse response = conversation.getResponse(Url);
    assertEquals("Checking index.jsp retrieval", pageTitle, response.getTitle());
    
    // Pop the default value onto the stack.
    WebForm loginForm = response.getFormWithID("LoginForm");
    WebRequest loginRequest = loginForm.getRequest();
    response = conversation.getResponse(loginRequest);
    
    // Check that the stack is pop value.
    WebTable loginTable = response.getTableWithID("loginTable");
    
    // Check that the stack is pop value.
    loginTable = response.getTableWithID("ErrorMessageTable");
    assertEquals("Checking stack contents empty", "Error: Incorrect username or password entered."
    		, loginTable.getTableCell(0, 0).getText());
  }
}
