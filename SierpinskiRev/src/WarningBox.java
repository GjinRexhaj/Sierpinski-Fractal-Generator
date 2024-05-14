import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class WarningBox extends ControlPanel {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WarningBox frame = new WarningBox();
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
  public WarningBox() {
    setResizable(false);
    setTitle("Render Error");
    setIconImage(Toolkit.getDefaultToolkit().getImage(WarningBox.class.getResource("/resources/fractalIcon.png")));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 349, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblWarning = new JLabel("Render Failed");
    lblWarning.setForeground(Color.RED);
    lblWarning.setFont(new Font("Tahoma", Font.BOLD, 22));
    lblWarning.setIcon(null);
    lblWarning.setHorizontalAlignment(SwingConstants.LEFT);
    lblWarning.setBounds(30, 30, 275, 27);
    contentPane.add(lblWarning);
    
    JLabel lblDescriptor1 = new JLabel("Make sure you're inputing integers");
    lblDescriptor1.setHorizontalAlignment(SwingConstants.LEFT);
    lblDescriptor1.setFont(new Font("Tahoma", Font.PLAIN, 17));
    lblDescriptor1.setBounds(30, 78, 275, 27);
    contentPane.add(lblDescriptor1);
    
    JLabel lblDescriptor2 = new JLabel("in both text fields.");
    lblDescriptor2.setHorizontalAlignment(SwingConstants.LEFT);
    lblDescriptor2.setFont(new Font("Tahoma", Font.PLAIN, 17));
    lblDescriptor2.setBounds(30, 101, 275, 27);
    contentPane.add(lblDescriptor2);
    
    JButton btnOkButton = new JButton("Ok");
    btnOkButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    btnOkButton.setBackground(Color.WHITE);
    btnOkButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
    btnOkButton.setBounds(30, 152, 112, 46);
    contentPane.add(btnOkButton);
    
    JLabel lblExceptionCaught = new JLabel("NumberFormatException caught");
    lblExceptionCaught.setBackground(Color.WHITE);
    lblExceptionCaught.setFont(new Font("Consolas", Font.PLAIN, 11));
    lblExceptionCaught.setBounds(30, 223, 177, 14);
    contentPane.add(lblExceptionCaught);
  }
}
