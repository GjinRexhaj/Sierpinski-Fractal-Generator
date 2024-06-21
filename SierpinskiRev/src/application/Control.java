/**
 * @author Gjin Rexhaj
 */

package application;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.MouseWheelEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Class which creates a canvas containing the rendered fractal.
 */
@SuppressWarnings("serial")
public class Control extends JFrame implements MouseListener, MouseWheelListener, MouseMotionListener {
  
  /**
   * {@code backgroundColor} is an object containing the color of the background.
   */
  Color backgroundColor;
  
  /**
   * {@code fractalColor} is an object containing the color of the fractal.
   */
  Color fractalColor;
  
  /**
   * {@code iterator} is a static variable which increments each time an image is saved,
   * used in saved image naming scheme.
   */
  public static int iterator = 1;
  
  
  /**
   * {@code panSpeed} is an object-dependent variable which controls the speed at which dragging
   * pans the fractal.
   */
  public double panSpeed = 1;
  
  /**
   * {@code lblImageSaved} is an isntance JLabel object which displays info about a saved image.
   * upon saving
   */
  JLabel lblImageSaved = new JLabel();
  
  /**
   * {@code scrollFactor} is a variable which influences how "zoomed in" a fractal is .
   */
  public int scrollFactor;
  
  /**
   * {@code zoomSpeed} is a variable which controls the speed at which the image zooms in/out.
   */
  public double zoomSpeed = 1.1232;
  
  /**
   * {@code fractalDepth} is a variable which stores the degree of the fractal.
   */
  public int fractalDepth;
  
  /**
   * {@code xResolution} is a variable which stores the horizontal resolution of the window.
   */
  public int xResolution; 
  
  /**
   * {@code yResolution} is a variable which stores the vertical resolution of the window.
   */
  public int yResolution; 
  
  /**
   * {@code deflectionX} is a variable which specifies horizontal movement of the fractal.
   */
  public double deflectionX = 0;
  
  /**
   * {@code deflectionY} is a variable which specifies vertical movement of the fractal.
   */
  public double deflectionY = 0;
  
  /**
   * {@code RenderBounds} is a box which bounds an equilateral triangle.
   */
  public RenderBounds box = new RenderBounds();
  
  // create canvas
  private DrawCanvas canvas;
 
  /**
   * {@code p1} is the topmost point.
   */
  Point p1 = new Point();
  
  /**
   * {@code p2} is the bottom-left point.
   */
  Point p2 = new Point();
  
  /**
   * {@code p3} is the bottom-right point.
   */
  Point p3 = new Point();
  
  /**
   * {@code originalX} Used to know which direction fractal should be panned.
   */
  public int originalX;
  
  /**
   * {@code originalY} Used to know which direction fractal should be panned.
   */
  public int originalY;
  
  //create a label which displays useful debug information
  //JLabel textArea = new JLabel();
  
  //default color value for testing

  
  /**
   * Constructor of Control window.
   * 
   * @param xRez The horizontal resolution of the window.
   * @param yRez The vertical resolution of the window.
   * @param degree The degree of the fractal to be rendered.
   * @param inputBackgroundColor The background color.
   * @param inputFractalColor The fractal color.
   */
  public Control(int xRez, int yRez, int degree, Color inputBackgroundColor, Color inputFractalColor) {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Launcher.class.getResource("/resources/fractalIcon.png")));
    
    // make panspeed scale (slightly) exponentially with degree
    panSpeed = (degree * 1.09) - (degree - 1);
    
    backgroundColor = inputBackgroundColor;
    fractalColor = inputFractalColor;
    
    // instance variables that facilitate communication between
    // control and launcher
    xResolution = xRez;
    yResolution = yRez;
    fractalDepth = degree;
    
    // set size of RenderBounds box for triangle
    box.setWidth(yResolution);
    
    // default zoom points which adapt to the selected resolution
    p1.setLocation(xResolution/2, 0 - scrollFactor);
    p2.setLocation(box.calculateHorizontalMargin(xResolution) - scrollFactor, box.getHeight() + scrollFactor);
    p3.setLocation(xResolution - box.calculateHorizontalMargin(xResolution) + scrollFactor, box.getHeight() + scrollFactor);
    
    // textArea contains useful debug text (mouse drag position, scroll state, etc.)
    //textArea.setLocation(0, yResolution - 75);
    
    // GUI setup
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(xResolution,yResolution);
    this.setLayout(null);
    setResizable(false);
    setTitle("Fractal Viewer");
    setLocationRelativeTo(null);
    canvas = new DrawCanvas();
    canvas.setBounds(0, 30, xResolution, yResolution);
    canvas.setOpaque(true);
    canvas.addMouseWheelListener(this);
    canvas.addMouseMotionListener(this);
    canvas.addMouseListener(this);
    //canvas.add(textArea);
    this.add(canvas);
    this.setVisible(true);
    
    // TO-DO: add analysis button which opens seperate dialog box
    // which displays details about what's being viewed
    /*
    //add analysis button
    JButton btnAnalysisButton = new JButton("ANALYSIS");
    btnAnalysisButton.setToolTipText("Click to open fractal analysis window.");
    btnAnalysisButton.setForeground(Color.BLACK);
    btnAnalysisButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
    btnAnalysisButton.setBackground(Color.LIGHT_GRAY);
    btnAnalysisButton.setBounds(80, yResolution - 75, 100, 36);
    add(btnAnalysisButton);
    btnAnalysisButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
      }
    });
    */
    
    // label which notifies user that image has been saved
    File file = new File("");
    String path = file.getAbsolutePath();
    lblImageSaved.setForeground(Color.BLACK);
    lblImageSaved.setBackground(Color.WHITE);
    lblImageSaved.setFont(new Font("Tahoma", Font.PLAIN, 9));
    lblImageSaved.setLocation(80, 0);
    lblImageSaved.setBounds(100, 0, 700, 30);
    add(lblImageSaved);
    lblImageSaved.setVisible(true);
    
    
    
    //add savebutton
    JButton btnSaveButton = new JButton("SAVE");
    btnSaveButton.setToolTipText("Click to save the latest generated fractal as a .png image.");
    btnSaveButton.setForeground(Color.BLACK);
    btnSaveButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
    btnSaveButton.setBackground(Color.CYAN);
    btnSaveButton.setBounds(0, 0, 80, 30);
    add(btnSaveButton);
    btnSaveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // hide button while screenshot processes
        btnSaveButton.setEnabled(false);
        btnSaveButton.setVisible(false);
        
        System.out.println("save button hit");

        // save image
        saveImage("fractal_" + iterator, "png", xResolution, yResolution);
        
        // TO-DO, add subtext that briefly mentions "image saved to <directory>
        btnSaveButton.setEnabled(true);
        btnSaveButton.setVisible(true);
        lblImageSaved.setText("fractal_" + iterator + ".png saved to " + path);
        iterator++;
      }
    });
  }
  
  private class DrawCanvas extends JPanel {
    
    @Override
    public void paintComponent(Graphics g) {
      
      // drawcanvas setup
      super.paintComponent(g);
      setBackground(backgroundColor);
      g.setColor(fractalColor);
      lblImageSaved.setText("");
      
      //call to recursive method which belongs in Model class
      Model.renderTriangles(g, fractalDepth, p1, p2, p3);
      System.out.println("paintComponent method run...");
      
      
    }
  }
  
  
  // scroll wheel method
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    // nested if statements to determine scroll wheel direction
    int notches = e.getWheelRotation();
    System.out.println("scrollFactor: " + scrollFactor);
    
    if (notches < 0) {
      //textArea.setText("Mouse Wheel Up");
      scrollFactor += 8;
      scrollFactor *= zoomSpeed;
      
    } else {
      //textArea.setText("Mouse Wheel Down");
      scrollFactor -= 8;
      scrollFactor /= zoomSpeed;
    }
    
    // adjust three points then run recursive method to
    // draw new "zoomed in" triangle
    p1.setLocation(xResolution/2 + deflectionX, 0 - scrollFactor + deflectionY);
    p2.setLocation(box.calculateHorizontalMargin(xResolution) - scrollFactor + deflectionX, box.getHeight() + scrollFactor + deflectionY);
    p3.setLocation(xResolution - box.calculateHorizontalMargin(xResolution) + scrollFactor + deflectionX, box.getHeight() + scrollFactor + deflectionY);
    
    System.out.println(box.getWidth());
    System.out.println(box.getHeight());
    
    repaint();
    
  }


  // debug and unused overriden mouse event methods
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Mouse clicked: (" + e.getX() + "), (" + e.getY() + ")");

  }
  
  @Override
  public void mousePressed(MouseEvent e) {
    originalX = e.getX();
    originalY = e.getY();
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    //textArea.setText("Mouse entered");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    //textArea.setText("Mouse exited");
    
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // increment deflectionX if dragged right
    // decrement deflectionX if dragged left
    if (e.getX() > originalX) {
      deflectionX+=panSpeed;
      originalX = e.getX();
    } else if (e.getX() < originalX) {
      deflectionX-=panSpeed;
      originalX = e.getX();
    }
    
    // increment deflectionY if dragged up
    // decrement deflectionY if dragged down
    // NOTE Y AXIS IS INVERTED IN JAVA!
    if (e.getY() > originalY) {
      deflectionY+=panSpeed;
      originalY = e.getY();
    } else if (e.getY() < originalY) {
      deflectionY-=panSpeed;
      originalY = e.getY();
    }
    
    p1.setLocation(xResolution/2 + deflectionX, 0 - scrollFactor + deflectionY);
    p2.setLocation(box.calculateHorizontalMargin(xResolution) - scrollFactor + deflectionX, box.getHeight() + scrollFactor + deflectionY);
    p3.setLocation(xResolution - box.calculateHorizontalMargin(xResolution) + scrollFactor + deflectionX, box.getHeight() + scrollFactor + deflectionY);
    repaint();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    // mouseTracker("Mouse moved", e); // commented out as it keeps running paintComponent
    //int mousePosX = e.getX();
    //int mousePosY = e.getY();
    
  } 
  
  // method to display crucial mouse info
  //private void mouseTracker(String description, MouseEvent e) {
  //  System.out.println(description + " (" + e.getX() + ", " + e.getY() + ")" + 
  //  " detected on " + e.getComponent().getClass().getName() + "\n");
  //}  

  // method to save canvas as image
  public void saveImage(String name, String type, int width, int height) {
    // configure new buffered image object
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2 = (Graphics2D) image.getGraphics();
    printAll(g2);
    
    try {
      ImageIO.write(image, type, new File(name + "." + type));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
