package executable;
import java.awt.*;

import javax.swing.*;



@SuppressWarnings("serial")
public class Model extends JFrame {
  
  // CLASS WHICH CONTAINS ALL FRACTAL ALGORITHMS,
  // IN PROCESS OF BEING MIGRATED, HAS A LOT OF UNUSED CODE
  
  // temp test placement
  public static void renderSierpinskiTriangle(Graphics g, int localDepth, Point p1, Point p2, Point p3) {
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
    renderSierpinskiTriangle(g, localDepth - 1, p1, midOneTwo, midThrOne);
    renderSierpinskiTriangle(g, localDepth - 1, midOneTwo, p2, midTwoThr);
    renderSierpinskiTriangle(g, localDepth - 1, midThrOne, midTwoThr, p3);
  }
  
  // DEFAULT VALUES FOR TESTING PURPOSES
  private static int depth;;

  
  //Default size of window
  public static Dimension size = new Dimension(800,600);
  
  
  
  // where triangle renders
  public static Dimension RenderBox = new Dimension(500, 500);
  private DrawCanvas canvas;
  
  
  
  // GUI setup
  public Model() {
    // create canvas with user input
    canvas = new DrawCanvas();
    canvas.setPreferredSize(new Dimension(800, 600));
    
    // set drawingcanvas as JFrame content pane
    Container cp = getContentPane();
    cp.add(canvas);
    
    // more setup
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setTitle(800 + "x" + 600 + " | " + depth +"th order Sierpinski Fractal");
    setResizable(true);
    setAlwaysOnTop(false);
    setVisible(true);
  }
  
  // inner class with polymorphic JPanel used for custom rendering
  private class DrawCanvas extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
      
      // drawcanvas setup
      super.paintComponent(g);
      setBackground(Color.WHITE);
      
      // create initial triangle: use Point objects in zoom square
      
      int vertMargin = 100;
      int horiMargin = 100;
      
      Point p1 = new Point((int)RenderBox.getWidth() / 2, vertMargin); // 25: hardcoded margin offset var
      Point p2 = new Point(horiMargin, (int)RenderBox.getHeight() - vertMargin); 
      Point p3 = new Point((int)RenderBox.getWidth() - horiMargin, (int)RenderBox.getWidth() - vertMargin);
      
      // call to recursive method
      renderTriangles(g, depth, p1, p2, p3);
    }
  }
  
  // method to draw line between two points
  private static void drawLine(Graphics g, Point p1, Point p2) {
    g.drawLine(p1.x, p1.y, p2.x, p2.y);
  }
  
  //method to return midpoint between two points
  private static Point midpoint(Point p1, Point p2) {
    return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
  }
  
  // recursive method to render triangles
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

  // main method to test stuff
   public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
       @Override
       public void run() {
         new Model();
         System.out.println("main method end");
       }
     });           
   }                  
}
