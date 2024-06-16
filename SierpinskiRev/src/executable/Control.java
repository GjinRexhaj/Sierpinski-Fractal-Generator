/**
 * @author Gjin Rexhaj
 */

package executable;
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

@SuppressWarnings("serial")
/**
 * Constructor for Control window
 */
public class Control extends JFrame implements MouseListener, MouseWheelListener, MouseMotionListener {
  
  // color objects
  Color backgroundColor;
  Color fractalColor;
  
  // static iterator variable for save image name incrementing
  public static int iterator = 1;
  
  // Instance object "image saved" JLabel creation
  JLabel lblImageSaved = new JLabel();
  
  // how zoomed in the user is
  public int scrollFactor;
  
  // the speed at which scrolling zooms into the fractal
  public double zoomSpeed = 1.1232;
  
  // TO-DO, implement and "infinite depth" setting which increments fractal
  // depth when zoom level increases, this is the defualt value for testing purposes
  public int fractalDepth;
  
  // Declare resolutions
  public int xResolution; 
  public int yResolution; 
  
  // TO-DO, add click/drag functionality
  public double deflectionX = 0;
  public double deflectionY = 0;
  
  // box where triangle will render inside of
  public RenderBounds box = new RenderBounds();
  
  // create canvas
  private DrawCanvas canvas;
 
  // intialize first points
  Point p1 = new Point();
  Point p2 = new Point();
  Point p3 = new Point();
  
  //create a label which displays useful debug information
  //JLabel textArea = new JLabel();
  
  //default color value for testing

  
  // constructor
  public Control(int xRez, int yRez, int degree, Color inputBackgroundColor, Color inputFractalColor) {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Launcher.class.getResource("/resources/fractalIcon.png")));
    
    
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

  
  // variables so the program knows which direction mouse is being dragged
  public int originalX;
  public int originalY;
  
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
      deflectionX++;
      originalX = e.getX();
    } else if (e.getX() < originalX) {
      deflectionX--;
      originalX = e.getX();
    }
    
    // increment deflectionY if dragged up
    // decrement deflectionY if dragged down
    // NOTE Y AXIS IS INVERTED IN JAVA!
    if (e.getY() > originalY) {
      deflectionY++;
      originalY = e.getY();
    } else if (e.getY() < originalY) {
      deflectionY--;
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