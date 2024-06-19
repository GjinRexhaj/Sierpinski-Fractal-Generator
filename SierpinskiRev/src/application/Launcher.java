package application;
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
import javax.swing.JColorChooser;
import javax.swing.UIManager;

/**
 * The launching point of the program- The very first window which opens up and allows
 * the user to generate a fractal in acocrdance with specifiable parameters.
 */

@SuppressWarnings("serial")
public class Launcher extends JFrame {

  private JPanel contentPane;
  
  // create fractal and backgorund color objects for later use
  // black and white are the default color options
  Color fractalColor = new Color(0, 0, 0);
  Color backgroundColor = new Color(255, 255, 255);
  
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
    setBounds(100, 100, 529, 492);
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

    JSpinner degreeSpinner = new JSpinner();
    degreeSpinner.setToolTipText("Select the degree of the fractal");
    degreeSpinner.setModel(new SpinnerNumberModel(5, 1, 12, 1));
    degreeSpinner.setBounds(143, 227, 57, 68);
    contentPane.add(degreeSpinner);
    
    JSpinner resolutionSpinner = new JSpinner();
    resolutionSpinner.setToolTipText("Select the resolution of the drawing canvas");
    resolutionSpinner.setModel(new SpinnerListModel(new String[] {"1024x576", "1152x648", "1280x720", "1366x768", "1600x900", "1920x1080"}));
    resolutionSpinner.setBounds(26, 227, 107, 68);
    contentPane.add(resolutionSpinner);
    
    JLabel lblInfo = new JLabel("This program generates an \"nth\" order Sierpinski gasket on a seperate window. \r\n");
    lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblInfo.setBounds(25, 103, 485, 39);
    contentPane.add(lblInfo);
    
    JLabel lblMoreInfo = new JLabel("The 'n' value and resolution of the rendered canvas are user specifiable.\r\n");
    lblMoreInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblMoreInfo.setBounds(25, 122, 461, 39);
    contentPane.add(lblMoreInfo);
    
    JLabel lblVersion = new JLabel("Version 2.2,   github.com/gjinrexhaj,   implemented with Java Swing and AWT");
    lblVersion.setBounds(10, 428, 541, 14);
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
        
        
        new Control(Integer.parseInt(splitString[0]), Integer.parseInt(splitString[1]), (Integer) degreeSpinner.getValue(), backgroundColor, fractalColor);
        
      }
    });
    btnGenerateButton.setForeground(Color.BLACK);
    btnGenerateButton.setBackground(Color.GREEN);
    btnGenerateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
    btnGenerateButton.setBounds(255, 227, 130, 68);
    contentPane.add(btnGenerateButton);
    
    JLabel lblResolution = new JLabel("Resolution and Degree");
    lblResolution.setFont(new Font("Tahoma", Font.PLAIN, 20));
    lblResolution.setBounds(25, 187, 217, 29);
    contentPane.add(lblResolution);
    
    JButton btnBackgroundColor = new JButton("");
    btnBackgroundColor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new JColorChooser();
        
        backgroundColor = JColorChooser.showDialog(null, "Choose the background color", Color.BLACK);
        btnBackgroundColor.setBackground(backgroundColor);
      }
    });
    btnBackgroundColor.setToolTipText("Click to change the background color of the render canvas");
    btnBackgroundColor.setForeground(Color.BLACK);
    btnBackgroundColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
    btnBackgroundColor.setBackground(Color.WHITE);
    btnBackgroundColor.setOpaque(true);
    btnBackgroundColor.setBounds(25, 306, 29, 29);
    contentPane.add(btnBackgroundColor);
    
    JButton btnFractalColor = new JButton("");
    btnFractalColor.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new JColorChooser();
        
        fractalColor = JColorChooser.showDialog(null, "Choose the fractal color", Color.BLACK);
        btnFractalColor.setBackground(fractalColor);
        
      }
    });
    btnFractalColor.setToolTipText("Click to change the color of the rendered fractal");
    btnFractalColor.setForeground(Color.BLACK);
    btnFractalColor.setFont(new Font("Tahoma", Font.PLAIN, 16));
    btnFractalColor.setBackground(Color.BLACK);
    btnFractalColor.setBounds(25, 341, 29, 29);
    contentPane.add(btnFractalColor);
    
    JLabel lblBackgroundColor = new JLabel("Background Color");
    lblBackgroundColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblBackgroundColor.setBounds(64, 306, 107, 29);
    contentPane.add(lblBackgroundColor);
    
    JLabel lblFractalColor = new JLabel("Fractal Color");
    lblFractalColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblFractalColor.setBounds(64, 341, 107, 29);
    contentPane.add(lblFractalColor);
    
    JButton btnSwapColors = new JButton("");
    btnSwapColors.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        
        
      }
    });
    btnSwapColors.setToolTipText("Click to swap Background color with Fractal color and vice versa");
    btnSwapColors.setForeground(Color.BLACK);
    btnSwapColors.setFont(new Font("Tahoma", Font.PLAIN, 12));
    btnSwapColors.setBackground(UIManager.getColor("Button.background"));
    btnSwapColors.setBounds(25, 376, 29, 29);
    contentPane.add(btnSwapColors);
    
    JLabel lblSwapColors = new JLabel("Swap Colors");
    lblSwapColors.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblSwapColors.setBounds(63, 377, 107, 29);
    contentPane.add(lblSwapColors);
    
    
  }
}