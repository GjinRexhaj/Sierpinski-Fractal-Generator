import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

public class Launcher extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  private static int depthFieldInput;
  private static int rezFieldInput;
  
  // getter methods for depth and rezfield input
  public static int getDepthFieldInput() {
    return depthFieldInput;
  }
  
  public static int getRezFieldInput() {
    return rezFieldInput;
  }
  
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
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
  }

  /**
   * Create the frame.
   */
  public Launcher() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Launcher.class.getResource("/resources/fractalIcon.png")));
    setAlwaysOnTop(false);
    setTitle("Java Fractal Generator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 529, 453);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setResizable(false);

    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblTitle = new JLabel("Sierpinski Fractal Generator");
    lblTitle.setForeground(new Color(0, 0, 0));
    lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
    lblTitle.setBounds(25, 25, 451, 39);
    contentPane.add(lblTitle);
    
    JLabel lblCredits = new JLabel("Developed by Gjin Rexhaj");
    lblCredits.setBounds(25, 65, 321, 14);
    contentPane.add(lblCredits);
    
    //
    JSpinner degreeSpinner = new JSpinner();
    degreeSpinner.setModel(new SpinnerNumberModel(5, 1, 12, 1));
    degreeSpinner.setBounds(143, 235, 57, 68);
    contentPane.add(degreeSpinner);
    
    JSpinner resolutionSpinner = new JSpinner();
    resolutionSpinner.setModel(new SpinnerListModel(new String[] {"1024x576", "1152x648", "1280x720", "1366x768", "1600x900", "1920x1080"}));
    resolutionSpinner.setBounds(26, 235, 107, 68);
    contentPane.add(resolutionSpinner);
    //
    
    JLabel lblInfo = new JLabel("This program generates an \"nth\" order Sierpinski gasket on a seperate window. \r\n");
    lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblInfo.setBounds(25, 103, 485, 39);
    contentPane.add(lblInfo);
    
    JLabel lblMoreInfo = new JLabel("The 'n' value and resolution of the rendered canvas are user specifiable.\r\n");
    lblMoreInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblMoreInfo.setBounds(25, 122, 461, 39);
    contentPane.add(lblMoreInfo);
    
    JLabel lblVersion = new JLabel("Version 2.0,   github.com/gjinrexhaj,   implemented with Java Swing and AWT");
    lblVersion.setBounds(10, 389, 541, 14);
    contentPane.add(lblVersion);
    
    JButton btnGenerateButton = new JButton("RENDER");
    btnGenerateButton.setToolTipText("Click to generate fractal with specified parameters, this will render the fractal on a new window independent of the program.");
    btnGenerateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String stringResolution = (String)resolutionSpinner.getValue();
        String[] splitString = stringResolution.split("x");
        
        System.out.println(stringResolution);
        System.out.println(splitString[0]);
        System.out.println(splitString[1]);
        
        
        //int xRes = Integer.parseInt(stringResolution.split("x"));
        //int yRes = 
        
        Control controlWindow = new Control(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), (Integer) degreeSpinner.getValue());
      
        
        /*  try {
    
          // store user input in int vars
          depthFieldInput = Integer.parseInt(fldDepthField.getText());
          rezFieldInput = Integer.parseInt(fldResolutionField.getText());
          
          
          // if fldDepthField > 9, create warningbox
          if (Integer.parseInt(fldDepthField.getText()) > 9) {
            // create warningbox
            HighDegreeWarningBox w2 = new HighDegreeWarningBox();
            w2.setVisible(true);
          } else {
            // create mainframe object
            new Mainframe(depthFieldInput, rezFieldInput);
            
          }
          
        } catch (NumberFormatException exception) {
          // if there isn't an int in textfields, display error box
          RenderFailedBox renderFailed = new RenderFailedBox();
          renderFailed.setVisible(true);
        } */
        
      }
    });
    btnGenerateButton.setForeground(Color.BLACK);
    btnGenerateButton.setBackground(Color.GREEN);
    btnGenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnGenerateButton.setBounds(255, 235, 130, 68);
    contentPane.add(btnGenerateButton);
    
    JLabel lblResolution = new JLabel("Resolution and Degree");
    lblResolution.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblResolution.setBounds(25, 195, 217, 29);
    contentPane.add(lblResolution);
    
    
  }
}