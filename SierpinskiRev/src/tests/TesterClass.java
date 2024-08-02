/**
 * @author Gjin Rexhaj
 */

package tests;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Scanner;

import application.Launcher;

/**
 * Class containing methods that execute automated testing.
 */
public class TesterClass {
  
  /**
   * Delay in miliseconds which controls how fast the bot operates.
   */
  public static int delay = 10;
  
  /**
   * Method that tells the robot to click the "Render" button.
   * 
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void clickRender(Robot robot) throws InterruptedException {
    robot.mouseMove(425, 390);
    Thread.sleep(delay);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseMove(968, 548);
  }
  
  /**
   * Method that tells the robot to Zoom in and out of the fractal.
   * 
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void zoom(Robot robot) throws InterruptedException {
    for (int i = 0; i < 20; i++) {
      robot.mouseWheel(-1 );
      Thread.sleep(delay);
    }
    for (int i = 0; i < 20; i++) {
      robot.mouseWheel(1);
      Thread.sleep(delay);
    }
  }
  
  /**
   * Method that tells the robot to pan left, right, up, and down on the renderCanvas.
   * 
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void pan(Robot robot) throws InterruptedException {
    // pan left
    for (int i = 0; i < 500; ) {
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      Thread.sleep(delay);
      robot.mouseMove(968 - i, 548);
      i += 50;
    }
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    
    // pan right
    for (int i = 0; i < 500; ) {
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      Thread.sleep(delay);
      robot.mouseMove(968 + i, 548);
      i += 50;
    }
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    
    // pan up
    for (int i = 0; i < 250; ) {
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      Thread.sleep(delay);
      robot.mouseMove(968, 548 - i);
      i += 50;
    }
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    
    // pan down
    for (int i = 0; i < 250; ) {
      robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
      Thread.sleep(delay);
      robot.mouseMove(968, 548 + i);
      i += 50;
    }
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  /**
   * Method that tells the robot to save the fractal as an image.
   * {@code Please note, this function is currently resolution-specific
   * and only implemented for the lowest resolution}.
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void saveImage(Robot robot) throws InterruptedException {
    robot.mouseMove(494, 268);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  /**
   * Method that tells the robot to close the render canvas.
   * {@code Please note, this function is currently resolution-specific 
   * and only implemented for the lowest resolution}.
   * @param robot Robot object which will execute this action
   * @throws InterruptedException
   */
  public static void closeRenderCanvas(Robot robot) throws InterruptedException {
    robot.mouseMove(1440, 250);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  /**
   * Method that tells the robot to increase the resolution by one
   * JSpinner increment.
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void increaseResolution(Robot robot) throws InterruptedException {
    robot.mouseMove(230, 375);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  /**
   * Method that tells the robot to increase the degree by one
   * JSpinner increment.
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void increaseDegree(Robot robot) throws InterruptedException {
    robot.mouseMove(300, 375);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
  
  /**
   * Method that tells the robot to decrease the degree by one
   * JSpinner increment.
   * @param robot Robot object which will execute this action.
   * @throws InterruptedException
   */
  public static void decreaseDegree(Robot robot) throws InterruptedException {
    robot.mouseMove(300, 400);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    Thread.sleep(delay);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }

  /**
   * Method that tells robot to run a full test.
   * {@code Please note, this function is currently resolution-specific 
   * and only implemented for the lowest resolution, as it uses saveImage() and
   * closeRenderCanvas(), which are also resolution specific}.
   * @param robot Robot object which will execute this action
   * @throws InterruptedException@param robot
   * @throws InterruptedException
   */
  public static void lowRezTestRun(Robot robot) throws InterruptedException {
    for (int i = 0; i < 12; i ++) {
      clickRender(robot);
      zoom(robot);
      pan(robot);
      saveImage(robot);
      closeRenderCanvas(robot);
      increaseDegree(robot);
    }
    Thread.sleep(250);
    System.out.println("");
    System.out.println("-- TESTING HAS ENDED --");
    System.out.println("It's safe to use your mouse now");
  }
  
  /**
   * Launch the application.
   * @throws AWTException 
   * @throws InterruptedException 
   */
  public static void main(String[] args) throws AWTException, InterruptedException {
    Robot testerRobot = new Robot();
    
    boolean decisionState = true;
    boolean decision = false;
    String option;
    Scanner s1 = new Scanner(System.in);
    
    System.out.println("TesterClass.java, Version 1.0");
    System.out.println("Written by Gjin Rexhaj");
    System.out.println();
    
    System.out.println("Tester has been executed, your cursor will be controlled for a brief period of time.");
    System.out.println("DO NOT MOVE YOUR MOUSE WHILE THE TEST IS RUNNING!");
    System.out.println();
    
    
    while(decisionState) {
      System.out.println("Would you like to continue? (y/n)");
      
      option = s1.nextLine();
      
      if (option.equals("y")) {
        decisionState = false;
        decision = true;
        System.out.println("");
        System.out.println("-- TESTING HAS BEGUN --");
      } else if (option.equals("n")) {
        decisionState = false;
        decision = false;
        System.out.println("-- PROGRAM TERMINATED --");
      } else {
        System.out.println("Invalid answer, type 'y' or 'n'");
        System.out.println();
      }
    }
    
    s1.close();
    
    Thread.sleep(250);
    
    if (decision) {
    
    
      EventQueue.invokeLater(new Runnable() {
        public void run() {
          try {
            Launcher frame = new Launcher();
            frame.setVisible(true);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });
    
      Thread.sleep(500);
    
      // set resolution to zero
      decreaseDegree(testerRobot);
      decreaseDegree(testerRobot);
      decreaseDegree(testerRobot);
      decreaseDegree(testerRobot);
      Thread.sleep(delay);
    
      // run tests
      lowRezTestRun(testerRobot);
    
      //while(true) {
      //  Thread.sleep(5000);
      //  System.out.println(MouseInfo.getPointerInfo().getLocation());
      //}
    }
  }
}
