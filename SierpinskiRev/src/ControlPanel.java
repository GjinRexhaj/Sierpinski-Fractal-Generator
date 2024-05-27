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

public class ControlPanel extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField fldDepthField;
  private JTextField fldResolutionField;

  private static int depthFieldInput;
  private static int rezFieldInput;
  
  // getter methods for depth and rezfield input
  public static int getDepthFieldInput() {
    return depthFieldInput;
  }
  
  public static int getRezFieldInput() {
    return rezFieldInput;
  }
  //
  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ControlPanel frame = new ControlPanel();
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
  public ControlPanel() {
    setIconImage(Toolkit.getDefaultToolkit().getImage(ControlPanel.class.getResource("/resources/fractalIcon.png")));
    setAlwaysOnTop(false);
    setTitle("Java Fractal Generator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 523, 453);
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
    
    JLabel lblInfo = new JLabel("This program generates an \"nth\" order Sierpinski gasket on a seperate window. \r\n");
    lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblInfo.setBounds(25, 103, 485, 39);
    contentPane.add(lblInfo);
    
    JLabel lblMoreInfo = new JLabel("The 'n' value and resolution of the square drawing canvas are user specifiable.\r\n");
    lblMoreInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblMoreInfo.setBounds(25, 122, 461, 39);
    contentPane.add(lblMoreInfo);
    
    JLabel lblVersion = new JLabel("Version 1.2,   github.com/gjinrexhaj,   implemented with Java Swing and AWT");
    lblVersion.setBounds(10, 389, 541, 14);
    contentPane.add(lblVersion);
    
    JButton btnGenerateButton = new JButton("RENDER");
    btnGenerateButton.setToolTipText("Click to generate fractal with specified parameters, this will render the fractal on a new window independent of the program.");
    btnGenerateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        try {
    
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
        }
        
      }
    });
    btnGenerateButton.setForeground(Color.BLACK);
    btnGenerateButton.setBackground(Color.GREEN);
    btnGenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnGenerateButton.setBounds(226, 176, 130, 68);
    contentPane.add(btnGenerateButton);
    
    fldDepthField = new JTextField();
    fldDepthField.setToolTipText("Input a single integer value (any positive number under 10 is recommended)");
    fldDepthField.setBounds(130, 215, 86, 29);
    contentPane.add(fldDepthField);
    fldDepthField.setColumns(10);
    
    fldResolutionField = new JTextField();
    fldResolutionField.setToolTipText("Input a single integer value (600 is recommended)");
    fldResolutionField.setColumns(10);
    fldResolutionField.setBounds(130, 176, 86, 29);
    contentPane.add(fldResolutionField);
    
    JLabel lblDegLabel = new JLabel("Degree:");
    lblDegLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblDegLabel.setBounds(53, 210, 82, 38);
    contentPane.add(lblDegLabel);
    
    JLabel lblResolution = new JLabel("Resolution:");
    lblResolution.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblResolution.setBounds(25, 177, 110, 29);
    contentPane.add(lblResolution);
    
  }
}
