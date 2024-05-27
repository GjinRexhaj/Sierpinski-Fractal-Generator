import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.Canvas;

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
    
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 566, 471);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBackground(Color.RED);
    panel.setBounds(0, 0, 550, 94);
    contentPane.add(panel);
    panel.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("FRACTAL ENGINE");
    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 31));
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setBounds(22, 0, 400, 94);
    panel.add(lblNewLabel);
    
    JLabel lblVersion = new JLabel("Version 1.0.0");
    lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
    lblVersion.setForeground(Color.WHITE);
    lblVersion.setFont(new Font("Tahoma", Font.BOLD, 11));
    lblVersion.setBounds(300, 73, 244, 21);
    panel.add(lblVersion);
  }
}
