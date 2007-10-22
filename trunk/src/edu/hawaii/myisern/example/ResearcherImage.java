package edu.hawaii.myisern.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import edu.hawaii.myisern.researchers.jaxb.Researcher;


/** 
 * Custom widget. Stores an Image and a Researcher Object.
 * @author John Hauge
 *
 */
public class ResearcherImage {

/** Location of the Image.*/
  public Point location;
/** Size of the image.*/
  public Point size;
/** Image object.*/
  public Image image;
/** Researcher object.*/
  public Researcher researcher;
  
  /**
   * Constructor.
   * @param parent Parent object to draw on.
   */
  public ResearcherImage(Composite parent) {
    location = new Point(0,0);
    size = new Point(50,50);
    final Display display = parent.getDisplay();
    FormLayout layout = new FormLayout();
    parent.setLayout(layout);
    this.image = new Image(display, 
                 "C:/projects/svn-google/myisern-1-blue/images/image1.jpg");
    final Canvas canvas = new Canvas(parent, SWT.NO_BACKGROUND);
    final Rectangle rect = this.image.getBounds();
    canvas.setSize(size);
    canvas.addListener(SWT.Paint, 
      new Listener() {
        public void handleEvent(Event e) {
          canvas.setSize(size);
          e.gc.drawImage(image, 0, 0, rect.width, rect.height, 0, 0, size.x, size.y);
          canvas.setLocation(location);
        }
      }
    );
    canvas.addListener(SWT.MouseDown, 
      new Listener() {
        public void handleEvent(Event e) {
          //to do... display bubble window with info
        }
      }
    );
    display.asyncExec(
      new Runnable() {
        public void run() {
          if (canvas.isDisposed()) {
            return;
          } 
          else {
            canvas.redraw();
            display.timerExec(150, this);
          }
        }
      }
    );
  } 
  /**
   * Garbage collection.
   */
  public void dispose() {
    freeResources();
  }
  /**
   * Garbage collection.
   */
  void freeResources() {
    if (image != null) {
      image.dispose();
      image = null;
    }
  }
  /**
   * Image access method.
   * @return Image object.
   */
  public Image getImage() {
    return this.image;
  }
  /**
   * Set image method.
   * @param image image to set.
   */
  public void setImage(Image image) {
    this.image = image;
  }
  /**
   * Researcher access method.
   * @return Researcher object.
   */
  public Researcher getResearcher() {
    return this.researcher;
  }
  /**
   * Set researcher method.
   * @param researcher researcher to set.
   */
  public void setResearcher(Researcher researcher) {
   this.researcher = researcher;
  }
}
