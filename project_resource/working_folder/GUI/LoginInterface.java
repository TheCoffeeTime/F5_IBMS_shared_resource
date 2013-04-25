package GUI;


import RequestHoliday.DriverHolidayRegistrationInterface;
import database.DriverInfo;
import database.database;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 * @version Last modified: 21/02/13
 */
public class LoginInterface {

  public static void createGUI() {

    //Border loweredetched;
    //loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


    final JFrame f = new JFrame("User Authentication");
    Container c = f.getContentPane();
    //c.setLayout(new FlowLayout());
    c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

    JPanel titlePanel = new JPanel();
    JLabel titleLabel = new JLabel();
    titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
    titleLabel.setText("User Authentication");
    titlePanel.add(titleLabel);
    c.add(titlePanel);

    JPanel loginPanel = new JPanel();
    loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 2 ));
    JLabel loginLabel = new JLabel();
    loginLabel.setFont(new Font("Serif", Font.BOLD, 12));
    loginLabel.setText("Driver Number: ");
    loginPanel.add(loginLabel);

    final JTextField loginTextField = new JTextField(10);
    loginPanel.add(loginTextField);
    c.add(loginPanel);

    final JPanel errorPanel = new JPanel();
    JLabel errorLabel = new JLabel();
    errorLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
    errorLabel.setText("Incorrect number!");
    errorLabel.setForeground(Color.RED);
    errorPanel.add(errorLabel);
    errorPanel.setVisible(false);
    c.add(errorPanel);


    JPanel bottomButtons = new JPanel();
    bottomButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2 ));

    JButton Exit = new javax.swing.JButton("Exit");
    Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               f.setVisible(false);
               f.dispose(); //Destroy the JFrame object
            }
    });
    bottomButtons.add(Exit);

    JButton Login = new javax.swing.JButton("Login");
    Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              int dirverID = DriverInfo.findDriver(loginTextField.getText());
              //2869 - driver ID to check
              if (dirverID!=0)
              {
               f.setVisible(false);
               DriverHolidayRegistrationInterface.createGUI(dirverID);
               f.dispose(); //Destroy the JFrame object
              }
              else
              {
                errorPanel.setVisible(true);
                f.pack();
              }
            }
    });
    bottomButtons.add(Login);

    c.add(bottomButtons);

    f.pack();
    f.setVisible(true);
  }

  public static void main(String[] av)
  {
    database.openBusDatabase();
    createGUI();
  }
}