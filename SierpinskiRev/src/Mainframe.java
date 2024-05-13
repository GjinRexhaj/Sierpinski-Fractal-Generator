import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;

public class Mainframe extends JFrame {
  
  // default depth of recursion: same as degree
  private static int depth = 2;
  
  // iterator to iterate filenames
  private static int iterator = 1;
  
  //Default size of window
  private static int canvasWidth = 600;
  private static int canvasHeight = 600;
  
  // setDepth of sierpinski triangle method
  public static void setDepth(int input) {
    depth = input;
  }
  
  // setResolution of window method
  public static void setResolution(int resolution) {
    canvasWidth = resolution;
    canvasHeight = resolution;
  }
  
  //getResolution of window method
  public static int getResolution() {
    return canvasWidth;
  }
  
  // construct future drawcanvas object
  private DrawCanvas canvas;
  
  // GUI setup
  public Mainframe(int depth, int rez) {
    
    //add savebutton
    JButton btnSaveButton = new JButton("SAVE");
    btnSaveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // hide button while screenshot processes
        btnSaveButton.setVisible(false);

        // save image
        saveImage("fractal_" + iterator, "png", getResolution(), getResolution());
        iterator++;
        
        // change button to textbox that says "Saved!"
        btnSaveButton.setText("SAVED");
        
        btnSaveButton.setEnabled(false);
        btnSaveButton.setBackground(Color.CYAN);
        
        btnSaveButton.setVisible(true);
      }
    });
    
    // Create save button
    btnSaveButton.setToolTipText("Click to save the latest generated fractal as a .png image.");
    btnSaveButton.setForeground(Color.BLACK);
    btnSaveButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
    btnSaveButton.setBackground(Color.CYAN);
    btnSaveButton.setBounds(25, 25, 80, 30);
    add(btnSaveButton);
    
    // set order to user specified
    setDepth(depth);
    
    // set resolution to user specified
    setResolution(rez);
    
    // create canvas with user input
    canvas = new DrawCanvas();
    canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    
    // set drawingcanvas as JFrame content pane
    Container cp = getContentPane();
    cp.add(canvas);
    
    // more setup
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setTitle(canvasWidth + "x" + canvasHeight + " | " + depth +"th order Sierpinski Fractal");
    setResizable(false);
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
      
      // create initial triangle: use Point objects
      Point p1 = new Point(canvasWidth / 2, 25); // 25: hardcoded margin offset var
      Point p2 = new Point(25, canvasHeight - 25); 
      Point p3 = new Point(canvasWidth - 25, canvasWidth - 25);
      
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
  
  // method to save canvas as image
  public void saveImage(String name, String type, int width, int height) {
    // configure new buffered image object
    BufferedImage image = new BufferedImage(width+16, height+25, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    printAll(g2);
    
    try {
      ImageIO.write(image, type, new File(name + "." + type));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  

  /* main method to test stuff
   public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
       @Override
       public void run() {
        
         command-line app
         int inputRez;
          
         Scanner s1 = new Scanner(System.in);
         System.out.println("WARNING: Entering too high of an 'n' value will make the program crash!");
         System.out.print("Enter n^th degree of Triangle to be generated: ");
         setDepth(s1.nextInt());
          
         System.out.print("Specify canvas resolution (INPUTxINPUT): ");
         inputRez = s1.nextInt();
         setResolution(inputRez);
          
         test
         Mainframe m1 = new Mainframe(5,600);
         m1.saveImage("fractal", "png");
         System.out.println("main method end");
       }
     });           
   }                  */
}
