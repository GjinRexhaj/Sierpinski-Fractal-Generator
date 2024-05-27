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
  public double scrollRate;
  public int fractalDepth;
  
  public double scale = 1;
  public double focusX = 0;
  public double focusY = 0;
  
  private DrawCanvas canvas;
  
  
  // main method
  public static void main(String[] args) {
    new Control();
  }
  
  // constructor
  public Control() {
    // GUI setup
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setSize(500,500);
    this.setLayout(null);
    
    //test
    canvas = new DrawCanvas();
    canvas.setBounds(0, 0, 500, 500);
    canvas.setOpaque(true);
    canvas.addMouseWheelListener(this);
    this.add(canvas);
    this.setVisible(true);
  }
  
  private class DrawCanvas extends JPanel {
    
    
    @Override
    public void paintComponent(Graphics g) {
      
      // drawcanvas setup
      super.paintComponent(g);
      setBackground(Color.GRAY);
      
      // TO-DO: MAKE SCROLL-PANEL SCALE WITH WINDOW-MAKE WINDOW RESIZABLE
      // FOR EACH SCROLL, ZOOM IN ON WHERE MOUSE CURSOR IS POINTED, BY CHANGING
      // POINTS P1-P3 IN ACCORDANCE WITH ZOOM AND RE-RENDERING THE FRACTAL
      // ??-BASED ON ZOOM FACTOR, INCREASE DEGREE OF FRACTAL AND DISPOSE ANY NON-DISPLAYED POINTS
      
      
      // create initial triangle: use Point objects in zoom square
      /*
      int vertMargin = 100;
      int horiMargin = 100;
      
      Point p1 = new Point((int)RenderBox.getWidth() / 2, vertMargin); // 25: hardcoded margin offset var
      Point p2 = new Point(horiMargin, (int)RenderBox.getHeight() - vertMargin); 
      Point p3 = new Point((int)RenderBox.getWidth() - horiMargin, (int)RenderBox.getWidth() - vertMargin);
      
      //call to recursive method
      renderTriangles(g, 3, p1, p2, p3);
      */
    }
  }
  
  
  // scroll wheel method
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    // nested if statements to determine scroll wheel direction
    int notches = e.getWheelRotation();
    
    if (notches < 0) {
      System.out.println("Mouse Wheel Up " + -notches + " notch(es) \n");
    } else {
      System.out.println("Mouse Wheel Down " + notches + " notch(es) \n");
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    System.out.println("aaaa");

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
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    
  } 
}
  
