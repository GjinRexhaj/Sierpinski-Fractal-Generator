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
          int depthFieldInput = Integer.parseInt(fldDepthField.getText());
          int rezFieldInput = Integer.parseInt(fldResolutionField.getText());
        
          // create mainframe object
          Mainframe renderWindow = new Mainframe(depthFieldInput, rezFieldInput);
          lblInfo.setVisible(true);
        } catch (NumberFormatException exception) {
          // if there isn't an int in textfields, display error box
          WarningBox warning = new WarningBox();
          warning.setVisible(true);
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
    
    JLabel lblWarningLimitDegree = new JLabel("\r\nTry to limit degree values to");
    lblWarningLimitDegree.setForeground(Color.RED);
    lblWarningLimitDegree.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblWarningLimitDegree.setBounds(25, 282, 198, 39);
    contentPane.add(lblWarningLimitDegree);
    
    JLabel lblWarning = new JLabel("WARNING!");
    lblWarning.setForeground(Color.RED);
    lblWarning.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblWarning.setBounds(25, 259, 100, 39);
    contentPane.add(lblWarning);
    
    JLabel lblAsTheRequired = new JLabel("at which the program crashes");
    lblAsTheRequired.setForeground(Color.RED);
    lblAsTheRequired.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblAsTheRequired.setBounds(25, 314, 198, 39);
    contentPane.add(lblAsTheRequired);
    
    JLabel lblSingleDigitNumbers = new JLabel("single digit numbers, the value");
    lblSingleDigitNumbers.setForeground(Color.RED);
    lblSingleDigitNumbers.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblSingleDigitNumbers.setBounds(25, 298, 191, 39);
    contentPane.add(lblSingleDigitNumbers);
    
    JLabel lblVariesOnHardware = new JLabel("varies on hardware.");
    lblVariesOnHardware.setForeground(Color.RED);
    lblVariesOnHardware.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblVariesOnHardware.setBounds(25, 330, 191, 39);
    contentPane.add(lblVariesOnHardware);
    
    JLabel lblNotRender = new JLabel("Not Rendering?");
    lblNotRender.setForeground(Color.BLACK);
    lblNotRender.setFont(new Font("Tahoma", Font.BOLD, 15));
    lblNotRender.setBounds(236, 256, 120, 39);
    contentPane.add(lblNotRender);
    
    JLabel lblMakeSureYoure = new JLabel("Make sure you're inputting");
    lblMakeSureYoure.setForeground(Color.BLACK);
    lblMakeSureYoure.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblMakeSureYoure.setBounds(236, 280, 336, 39);
    contentPane.add(lblMakeSureYoure);
    
    JLabel lblIntegerValuesIn = new JLabel("integer values in both text");
    lblIntegerValuesIn.setForeground(Color.BLACK);
    lblIntegerValuesIn.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblIntegerValuesIn.setBounds(236, 297, 336, 39);
    contentPane.add(lblIntegerValuesIn);
    
    JLabel lblFields = new JLabel("fields (EX: 600, 4)");
    lblFields.setForeground(Color.BLACK);
    lblFields.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblFields.setBounds(236, 314, 336, 39);
    contentPane.add(lblFields);
    
  }
}
