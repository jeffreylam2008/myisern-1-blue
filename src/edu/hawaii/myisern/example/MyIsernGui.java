package edu.hawaii.myisern.example;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


import edu.hawaii.myisern.collaborations.jaxb.Collaborations;
import edu.hawaii.myisern.organizations.jaxb.Organizations;
import edu.hawaii.myisern.researchers.jaxb.Researchers;


/**
 * Graphical User Interface for myISERN. (Currently experimental).
 * Inspired by 
 * http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/SWTBrowserExample.htm
 * @author John Hauge
 *
 */
public class MyIsernGui {

  private Display display;
  private Image image1;
  private Image image2;
  private Image image3;
  
  private Collaborations collaborations; 
  private Organizations organizations;
  private Researchers researchers;
  
  /** 
   * Constructor.
   * @param collaborations List of Collaborations.
   * @param organizations List of Organizations.
   * @param researchers List of researchers.
   */
  public MyIsernGui(Collaborations collaborations, 
            Organizations organizations,
            Researchers researchers) {
    String currentWorkingDirectory = System.getProperty("user.dir");
    this.collaborations = collaborations;
    this.organizations = organizations;
    this.researchers = researchers;
  }
  /**
   * Method to initiate GUI creation.
   */
  public void createGui() {
    this.image1 = new Image(this.display, 
                  "C:/projects/svn-google/myisern-1-blue/images/image1.jpg");
    this.image2 = new Image(this.display, 
                  "C:/projects/svn-google/myisern-1-blue/images/image2.jpg");
    this.image3 = new Image(this.display, 
                  "C:/projects/svn-google/myisern-1-blue/images/image2.jpg");
    Shell shell = new Shell();
    shell.setText("myIsern-1-blue baby, cool like the ocean breeze.");
    shell.setSize(640, 480);
    
    ResearcherImage ri1 = new ResearcherImage(shell);
    ri1.setResearcher(researchers.getResearcher().get(0));
    ri1.setImage(image1);
    ri1.location = new Point(50,50);
    
    ResearcherImage ri2 = new ResearcherImage(shell);
    ri2.setImage(image2);
    ri2.setResearcher(researchers.getResearcher().get(1));
    ri2.location = new Point(300,300);
    
    ResearcherImage ri3 = new ResearcherImage(shell);
    ri3.setImage(image3);
    ri3.setResearcher(researchers.getResearcher().get(2));
    ri3.location = new Point(400,50);
    
    
    CollaborationLine line1 = new CollaborationLine(shell);
    Point ri1LowerRight = new Point(ri1.location.x + ri1.size.x, 
                                    ri1.location.y + ri1.size.y);
    Point ri2UpperLeft = ri2.location;
    line1.startPoint = ri1LowerRight;
    line1.endPoint = ri2UpperLeft;
      
    
    CollaborationLine line2 = new CollaborationLine(shell);
    Point ri1UpperRight = new Point(ri1.location.x + ri1.size.x, ri1.location.y);
    Point ri3UpperLeft = ri3.location;
    line2.startPoint = ri1UpperRight;
    line2.endPoint = ri3UpperLeft;
    
    CollaborationLine line3 = new CollaborationLine(shell);
    Point ri3LowerLeft = new Point(ri3.location.x, ri3.location.y + ri3.size.y);
    Point ri2UpperRight = new Point(ri2.location.x + ri2.size.x, ri2.location.y);
    line3.startPoint = ri3LowerLeft;
    line3.endPoint = ri2UpperRight;
    shell.open();
    Display display = shell.getDisplay();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
    //line1.dispose();
    //line2.dispose();
    //line3.dispose();
    ri1.dispose();
    ri2.dispose();
    ri3.dispose();
    display.dispose();
  }
   
  /**
   * Method for getting points at each of the four corners of an image object.
   * @param img Image to get points from.
   * @return List<Point> List of points.
   */
  private static List<Point> getFourCorners(Image img) {
    List<Point> corners = new ArrayList<Point>();
    Rectangle bounds = img.getBounds();
    
    //upper left corner
      corners.add(new Point(bounds.x,  bounds.y));  
      //upper right corner
      corners.add(new Point(bounds.x + bounds.width, bounds.y));  
      //lower left corner
      corners.add(new Point(bounds.x, bounds.y + bounds.height));  
      //lower right corner
      corners.add(new Point(bounds.x + bounds.width, bounds.y + bounds.height));  
      for (Point p : corners) {
        System.out.println("corners: " + p.x + "," + p.y);
      }
      return corners;
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

}
