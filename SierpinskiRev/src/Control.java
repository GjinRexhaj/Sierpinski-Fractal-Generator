import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class Control extends JFrame implements MouseListener, MouseWheelListener, MouseMotionListener {
  // instance variables of important scroller factors
  public int scrollFactor;
  public int fractalDepth = 5;
  
  public int xResolution = 1280 + 17; //19 px taken up by unknown
  public int yResolution = 720 + 40; //40 px taken up by window header
  
  public double deflectionX = 0;
  public double deflectionY = 0;
  
  private DrawCanvas canvas;
  
  
  Point p1 = new Point(xResolution / 2, 100); // 25: hardcoded margin offset var
  Point p2 = new Point(320, yResolution - 100); 
  Point p3 = new Point(xResolution - 320, yResolution - 100);
  
  
  JLabel textArea = new JLabel(); //create a label
  
  // main method
  public static void main(String[] args) {
    new Control();
  }
  
  // constructor
  public Control() {
    // GUI setup
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(xResolution,yResolution);
    this.setLayout(null);
    setResizable(false);

    textArea.setText("TEXT AREA INITIALIZED"); //set text of label
    
    setTitle("Fractal Viewer");
    setLocationRelativeTo(null);
    
    canvas = new DrawCanvas();
    canvas.setBounds(0, 0, xResolution, yResolution);
    canvas.setOpaque(true);
    canvas.addMouseWheelListener(this);
    canvas.addMouseMotionListener(this);
    canvas.addMouseListener(this);
    canvas.add(textArea);
    
    this.add(canvas);
    this.setVisible(true);
  }
  
  private class DrawCanvas extends JPanel {
    
    
    @Override
    public void paintComponent(Graphics g) {
      
      // drawcanvas setup
      super.paintComponent(g);
      setBackground(Color.WHITE);
      
      // TO-DO: MAKE SCROLL-PANEL SCALE WITH WINDOW-MAKE WINDOW RESIZABLE
      // FOR EACH SCROLL, ZOOM IN ON WHERE MOUSE CURSOR IS POINTED, BY CHANGING
      // POINTS P1-P3 IN ACCORDANCE WITH ZOOM AND RE-RENDERING THE FRACTAL
      // ??-BASED ON ZOOM FACTOR, INCREASE DEGREE OF FRACTAL AND DISPOSE ANY NON-DISPLAYED POINTS
      
      
      // create initial triangle: use Point objects in zoom square
      
      //call to recursive method which belongs in Model class
      Model.renderSierpinskiTriangle(g, fractalDepth, p1, p2, p3);
      System.out.println("paintComponent method run...");
      
    }
  }
  
  
  // scroll wheel method
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    // nested if statements to determine scroll wheel direction
    int notches = e.getWheelRotation();
    System.out.println("scrollFactor: " + scrollFactor);
    
    // mousePosX, and mousePosY
    
    if (notches < 0) {
      textArea.setText("Mouse Wheel Up");
      scrollFactor += 1;
      scrollFactor *= 1.1232;
      
    } else {
      textArea.setText("Mouse Wheel Down");
      scrollFactor -= 1;
      scrollFactor /= 1.1232;
    }
    
    p1.setLocation(xResolution / 2, 100 - scrollFactor);
    p2.setLocation(320 - scrollFactor, yResolution - 100 + scrollFactor);
    p3.setLocation(xResolution - 320 + scrollFactor, yResolution - 100 + scrollFactor);
    
    
    repaint();
    
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    System.out.println("Mouse clicked: (" + e.getX() + "), (" + e.getY() + ")");

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    textArea.setText("Mouse entered");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    textArea.setText("Mouse exited");
    
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    mouseTracker("Mouse dragged", e);
    
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    // mouseTracker("Mouse moved", e); // commented out as it keeps running paintComponent
    int mousePosX = e.getX();
    int mousePosY = e.getY();
    
  } 
  
  // method to display crucial mouse info
  private void mouseTracker(String description, MouseEvent e) {
    textArea.setText(description + " (" + e.getX() + ", " + e.getY() + ")" /* + 
    " detected on " + e.getComponent().getClass().getName() + "\n"*/);
  }
}
  
