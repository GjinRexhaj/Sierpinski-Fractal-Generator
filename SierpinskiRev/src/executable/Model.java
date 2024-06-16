/**
 * Model.java
 * 
 * Contains all backend methods used for rendering fractals
 * 
 * @author Gjin Rexhaj
 */

package executable;
import java.awt.*;

import javax.swing.*;



@SuppressWarnings("serial")
public class Model extends JFrame {
  

  // method to draw line between two points
  private static void drawLine(Graphics g, Point p1, Point p2) {
    g.drawLine(p1.x, p1.y, p2.x, p2.y);
  }
  
  //method to return midpoint between two points
  private static Point midpoint(Point p1, Point p2) {
    return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
  }
  
  /** 
   * Recursive method used to render Sierpinski triangles
   * 
   * @param g Graphics component
   * @param localDepth Depth of the Sierpinski fractal
   * @param p1 Top-most point
   * @param p2 Bottom-left point
   * @param p3 Bottom-right point
   */
  public static void renderTriangles(Graphics g, int localDepth, Point p1, Point p2, Point p3) {
    // base case
    if (localDepth == 0) {
      // draw degree 0 triangle
      drawLine(g, p1, p2);
      drawLine(g, p2, p3);
      drawLine(g, p3, p1);
      return;
    }

    // recursive case, 3 layers of recursion
    Point midOneTwo = midpoint(p1,p2);
    Point midTwoThr = midpoint(p2,p3);
    Point midThrOne = midpoint(p3,p1);
    renderTriangles(g, localDepth - 1, p1, midOneTwo, midThrOne);
    renderTriangles(g, localDepth - 1, midOneTwo, p2, midTwoThr);
    renderTriangles(g, localDepth - 1, midThrOne, midTwoThr, p3);
  }                
}
