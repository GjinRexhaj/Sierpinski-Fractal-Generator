import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

public class Launcher extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  
  
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
    degreeSpinner.setToolTipText("Select the degree of the fractal");
    degreeSpinner.setModel(new SpinnerNumberModel(5, 1, 12, 1));
    degreeSpinner.setBounds(143, 235, 57, 68);
    contentPane.add(degreeSpinner);
    
    JSpinner resolutionSpinner = new JSpinner();
    resolutionSpinner.setToolTipText("Select the resolution of the drawing canvas");
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
    
    JLabel lblVersion = new JLabel("Version 2.1,   github.com/gjinrexhaj,   implemented with Java Swing and AWT");
    lblVersion.setBounds(10, 389, 541, 14);
    contentPane.add(lblVersion);
    
    JButton btnGenerateButton = new JButton("RENDER");
    btnGenerateButton.setToolTipText("Click to generate fractal with specified parameters, this will render the fractal on a new window independent of the program.");
    btnGenerateButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // split <resolution>x<resolution> from JSpinner into
        // two seperate strings, parse strings as int and
        // pass them into the control/view constructor
        String stringResolution = (String)resolutionSpinner.getValue();
        String[] splitString = stringResolution.split("x");
        
        
        new Control(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), (Integer) degreeSpinner.getValue());
        
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