import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HighDegreeWarningBox extends ControlPanel {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          HighDegreeWarningBox frame = new HighDegreeWarningBox();
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
  /**
   * Create the frame.
   */
  public HighDegreeWarningBox() {
    setResizable(false);
    setTitle("Render Warning");
    setIconImage(Toolkit.getDefaultToolkit().getImage(HighDegreeWarningBox.class.getResource("/resources/fractalIcon.png")));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 349, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblWarning = new JLabel("High Degree Detected");
    lblWarning.setForeground(Color.BLACK);
    lblWarning.setFont(new Font("Tahoma", Font.BOLD, 22));
    lblWarning.setIcon(null);
    lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
    lblWarning.setBounds(30, 30, 275, 27);
    contentPane.add(lblWarning);
    
    JLabel lblDescriptor1 = new JLabel("Higher degree values will typically");
    lblDescriptor1.setHorizontalAlignment(SwingConstants.LEFT);
    lblDescriptor1.setFont(new Font("Tahoma", Font.PLAIN, 17));
    lblDescriptor1.setBounds(30, 76, 275, 27);
    contentPane.add(lblDescriptor1);
    
    JLabel lblDescriptor2 = new JLabel("cause the program to crash on");
    lblDescriptor2.setHorizontalAlignment(SwingConstants.LEFT);
    lblDescriptor2.setFont(new Font("Tahoma", Font.PLAIN, 17));
    lblDescriptor2.setBounds(30, 99, 275, 27);
    contentPane.add(lblDescriptor2);
    
    JButton btnCancelButton = new JButton("Cancel");
    btnCancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    btnCancelButton.setBackground(Color.WHITE);
    btnCancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnCancelButton.setBounds(30, 158, 112, 46);
    contentPane.add(btnCancelButton);
    
    JLabel lblExceptionCaught = new JLabel("DepthTextField > 9");
    lblExceptionCaught.setBackground(Color.WHITE);
    lblExceptionCaught.setFont(new Font("Consolas", Font.PLAIN, 11));
    lblExceptionCaught.setBounds(30, 223, 177, 14);
    contentPane.add(lblExceptionCaught);
    
    JLabel lblWeakerHardware = new JLabel("weaker hardware.");
    lblWeakerHardware.setHorizontalAlignment(SwingConstants.LEFT);
    lblWeakerHardware.setFont(new Font("Tahoma", Font.PLAIN, 17));
    lblWeakerHardware.setBounds(30, 122, 275, 27);
    contentPane.add(lblWeakerHardware);
    
    
    
    // Render Anyways button
    JButton btnRenderAnyways = new JButton("Render Anyways");
    btnRenderAnyways.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("render anyways button clicked");
        Mainframe renderWindow = new Mainframe(getDepthFieldInput(), getRezFieldInput());
      }
    });
    btnRenderAnyways.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnRenderAnyways.setBackground(Color.WHITE);
    btnRenderAnyways.setBounds(152, 158, 140, 46);
    contentPane.add(btnRenderAnyways);
  }
}
