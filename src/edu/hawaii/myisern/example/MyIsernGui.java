package edu.hawaii.myisern.example;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.organizations.jaxb.Organization;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.researchers.jaxb.Researcher;
import edu.hawaii.myisern.researchers.jaxb.Researchers;


/**
 * Graphical User Interface for myISERN. (Currently experimental).
 * @author John Hauge
 *
 */
public class MyIsernGui {
/**
  * Creates a Gui representation of the organizations, researchers
  *   and their collaborations.
  * @param collaborations list of.
  * @param organizations list of.
  * @param researchers list of.
  */
  public static void createGui(Collaborations collaborations, 
                               Organizations organizations,
                               Researchers researchers) {
    
    testDisplay();
    
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("myIsern-1-blue baby, cool like the ocean breeze.");
    shell.setSize(640, 480);
    //shell.setLayout(new FillLayout());
    int startX = 0;
    int startY = 50;
    int curX = startX;
    int curY = startY;
    curX += 100;
    // start with organizations
    for (Organization org : organizations.getOrganization()) {
      // create organization text box representation
      addTextBoxToGui(org.getName(), shell, curX, curY);
      curX += 250;
    }
    //process each control in the shell to find organization text boxes
    for (Control ctrl : shell.getChildren()) {
      //check to see if control is a Text 
      if ("Text {}".equals(ctrl.toString())) {
        Text textBox = (Text) ctrl;
        //spawn child researcher text boxes under organization
        curX = textBox.getLocation().x;
        curY = textBox.getLocation().y;
        curX += 20;
        for (Researcher researcher : researchers.getResearcher()) {
          if (textBox.getText().equals(researcher.getOrganization())) {
            curY += textBox.getSize().y + 10;
            addTextBoxToGui(researcher.getName(), shell, curX, curY);
            //draw tree links from organization text box to each 
            //researcher text box 
          }
        }
      }
    }
    // process each collaboration
    //shell.pack ();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
/**
 * Calculates the distance between two points.
 * @param pt1 The first point.
 * @param pt2 The second point.
 * @return The distance between them.
 */
  public static double getDistanceBetweenTwoPoints(Point pt1, Point pt2) {
    double distance = 0.0;
    distance = Math.sqrt(Math.pow(pt2.x - pt1.x, 2) + Math.pow(pt2.y - pt1.y, 2));
    return distance;
  }
/**
 * Draws a line between two text boxes.
 * @param shell Area to draw in.
 * @param firstTextbox First text box.
 * @param secondTextbox Second text box.
 */
  public static void drawLineBetweenTwoTextboxes(Shell shell, 
    Text firstTextbox, 
    Text secondTextbox) {
    //get location of first text box
    Rectangle firstTextBoxBounds = firstTextbox.getBounds();
    List<Point> firstTextBoxPointList = new ArrayList<Point>();
    //upper left corner
    firstTextBoxPointList.add(new Point(firstTextBoxBounds.x,
                                        firstTextBoxBounds.y));  
    //upper right corner
    firstTextBoxPointList.add(new Point(firstTextBoxBounds.x + firstTextBoxBounds.width,
                                        firstTextBoxBounds.y));  
    //lower left corner
    firstTextBoxPointList.add(new Point(firstTextBoxBounds.x,
                                        firstTextBoxBounds.y + firstTextBoxBounds.height));  
    //lower right corner
    firstTextBoxPointList.add(new Point(firstTextBoxBounds.x + firstTextBoxBounds.width,
                                        firstTextBoxBounds.y + firstTextBoxBounds.height));  
    //get location of second text box
    Rectangle secondTextBoxBounds = secondTextbox.getBounds();
    List<Point> secondTextBoxPointList = new ArrayList<Point>();
    //upper left corner
    secondTextBoxPointList.add(new Point(secondTextBoxBounds.x,
                                         secondTextBoxBounds.y));  
    //upper right corner
    secondTextBoxPointList.add(new Point(secondTextBoxBounds.x + secondTextBoxBounds.width,
                                         secondTextBoxBounds.y));  
    //lower left corner
    secondTextBoxPointList.add(new Point(secondTextBoxBounds.x,
                                         secondTextBoxBounds.y + secondTextBoxBounds.height));  
    //lower right corner
    secondTextBoxPointList.add(new Point(secondTextBoxBounds.x + secondTextBoxBounds.width,
                                         secondTextBoxBounds.y + secondTextBoxBounds.height));  

    Point firstTextBoxPoint;
    Point secondTextBoxPoint;
    double shortestDistance = 99999999.99999;
    //find closest point
    //shortest distance variable
    
    //for each corner on 1st text box
    for (Point first_pt : firstTextBoxPointList) {
      //compare to each corner of 2nd text box
      for (Point second_pt : secondTextBoxPointList) {
        double distance = getDistanceBetweenTwoPoints(first_pt, second_pt);
        //if distance shorter than shortest distance variable
        if (distance < shortestDistance) {
          //then set shortest distance variable to new shorter distance
          shortestDistance = distance;
          //and store two points as having shortest distance
          firstTextBoxPoint = first_pt;
          secondTextBoxPoint = second_pt;
        }
      }
    }
    //a listener method to handle each redraw.
    shell.addPaintListener(new PaintListener() {
                             public void paintControl (PaintEvent e) {
                               e.gc.setLineWidth(4);
                               e.gc.drawLine(0, 0, 19, 19);
                               e.gc.drawLine(19, 0, 0, 19);
                             }
                           } );
  }
  /** Allows testing of widgets without implementation in our code.
   * 
   */
  public static void testDisplay() {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.open();
    GC gc = new GC(shell);
    gc.drawLine(0, 0, 19, 19);
    gc.drawLine(19, 0, 0, 19);
    gc.dispose();
    drawLineBetweenTwoTextboxes(shell, 
    addTextBoxToGui("test1", shell, 50, 50), 
    addTextBoxToGui("test2", shell, 60, 100));
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    display.dispose();
  }
/**
  * Adds a text box to the Gui.
  * @param text to fill in the text box.
  * @param shell to put the text box in.
  * @param x location in the shell.
  * @param y location in the shell.
  * @return textBox the Text added to the Gui.
  */
  public static Text addTextBoxToGui(String text, Shell shell, int x, int y) {
    //Code from eclipse documentaion archive 
    //http://dev.eclipse.org/viewcvs/index.cgi/~checkout~/org.
    //  eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet55.java
    //with some modification.
    Text textBox = new Text(shell, SWT.BORDER);
    GC gc = new GC(textBox);
    FontMetrics fm = gc.getFontMetrics();
    int width = text.length() * fm.getAverageCharWidth();
    int height = fm.getHeight();
    gc.dispose();
    textBox.setSize(textBox.computeSize(width, height));
    textBox.setLocation(x, y);
    textBox.insert(text);
    return textBox;
  }
}
