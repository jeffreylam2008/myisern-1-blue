package edu.hawaii.myisern.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Custom widget that draws a line between two points and stores a collaboration object.
 * 
 * @author John Hauge
 * 
 */
public class CollaborationLine {
  /** Point to start drawing from. */
  public Point startPoint;
  /** Point to end drawing. */
  public Point endPoint;

  /**
   * Constructor
   * 
   * @param parent Parent object to draw onto.
   */

  public CollaborationLine(Composite parent) {
    startPoint = new Point(0, 0);
    endPoint = new Point(0, 0);
    // adding line to shell
    FormLayout layout = new FormLayout();
    parent.setLayout(layout);
    Shell shell = parent.getShell();
    shell.addListener(SWT.Paint, new Listener() {
      public void handleEvent(Event e) {
        e.gc.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
      }
    });
  }
  /** Garbage collection */
  /*
   * public void dispose() { freeResources(); }
   */
  /** Garbage collection */
  /*
   * void freeResources() { }
   */

}
