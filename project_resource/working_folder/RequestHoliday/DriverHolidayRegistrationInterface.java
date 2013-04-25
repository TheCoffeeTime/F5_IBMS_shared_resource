package RequestHoliday;

//import working_folder.database;
import RequestHoliday.Update;
import RequestHoliday.ValidateHolidayRequest;
import database.DriverInfo;
import database.database;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 * @version Last modified: 21/02/13
 */
public class DriverHolidayRegistrationInterface {

    //private static JLabel SystemMessageLabel;
    private static JTextPane SystemMessageTextPane;

     /** A Calendar object used throughout */
    Calendar calendar = new GregorianCalendar();
            
    public static void createGUI(final int driverID) {
    database.openBusDatabase();

    Border loweredetched;
    loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);


    final JFrame f = new JFrame("Driver: Holiday Request");
    //JPanel p = new JPanel();
    Container c = f.getContentPane();
    //c.setLayout(new FlowLayout());
    c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

    // Add title
    JPanel titlePanel = new JPanel();
    JLabel titleLabel = new JLabel();
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    titleLabel.setText("Vacation Registration");
    titlePanel.add(titleLabel);
    c.add(titlePanel);

    // Add System Message
    JPanel SystemMessagePanel = new JPanel();
    SystemMessageTextPane = new JTextPane();
    SystemMessageTextPane.setBounds( 10, 10, 700, 100 );
    SystemMessageTextPane.setEditable(false);
    SystemMessageTextPane.setBackground(c.getBackground());
    SystemMessagePanel.setLayout( null );
    SystemMessagePanel.add(SystemMessageTextPane);
    SystemMessagePanel.setPreferredSize( new Dimension( 100, 120 ) );   
    c.add( SystemMessagePanel, BorderLayout.CENTER );
    
    
    
    SystemMessageTextPane.setFont(new Font("Serif", Font.BOLD, 16));
    SystemMessageTextPane.setText("Welcome " + DriverInfo.getName(driverID) + "!"
            + " Please select start and end date of your holiday. Green fields indicates dates which you "
            + "can pick for a holiday, whereas red dates are the ones which are already reserved by other drivers."
            + " Dates when you have a holiday are highlighted with yellow. "
            + "All dates in your holiday period MUST be available (green).");
    
    // Days left
    JPanel daysLeftPanel = new JPanel();
    daysLeftPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2 ));
    final JLabel daysLeftLabel = new JLabel();
    daysLeftLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
    daysLeftLabel.setForeground(new Color(0, 102,0));
    daysLeftLabel.setText("Days left: " + (25 - DriverInfo.getHolidaysTaken(driverID)));
    daysLeftPanel.add(daysLeftLabel);
    c.add(daysLeftPanel);


    // Create panel for two Date Pickers
    JPanel calendarsPanel = new JPanel();
    calendarsPanel.setLayout(new GridLayout(1,0));
    c.add(calendarsPanel);


    // Create Date From Calendar

    JPanel dateFromPanel = new JPanel();
    TitledBorder dateFromTitled;
    dateFromTitled = BorderFactory.createTitledBorder(loweredetched, "Date From");
    dateFromTitled.setTitleFont(new Font("Serif", Font.BOLD, 16));
    dateFromPanel.setBorder(dateFromTitled);
    calendarsPanel.add(dateFromPanel);
    final Cal DateFrom = new Cal(driverID);
    dateFromPanel.add(DateFrom);

    // Create Date To Calendar

    JPanel dateToPanel = new JPanel();
    TitledBorder dateToTitled;
    dateToTitled = BorderFactory.createTitledBorder(loweredetched, "Date To");
    dateToTitled.setTitleFont(new Font("Serif", Font.BOLD, 16));
    dateToPanel.setBorder(dateToTitled);
    calendarsPanel.add(dateToPanel);
    final Cal DateTo = new Cal(driverID);
    dateToPanel.add(DateTo);


    // Create Buttons Submit & Cancel
    JPanel bottomButtons = new JPanel();
    bottomButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 2 ));

    JButton Cancel = new javax.swing.JButton("Cancel");
    Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               f.setVisible(false);
               f.dispose(); //Destroy the JFrame object
            }
    });
    bottomButtons.add(Cancel);

    JButton Submit = new javax.swing.JButton("Submit");
    Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              if (ValidateHolidayRequest.validateRequest(DateFrom.getDate(), DateTo.getDate(), driverID) == true)
              {
                Update.updateHolidayRequest(DateFrom.getDate(), DateTo.getDate(), driverID);
                daysLeftLabel.setText("Days left: " + (25 - DriverInfo.getHolidaysTaken(driverID)));
                SystemMessageTextPane.setText(Update.systemMsg.message);
                DateFrom.recompute(driverID);
                DateTo.recompute(driverID);
              }
              else
              {
                SystemMessageTextPane.setText(ValidateHolidayRequest.systemMsg.message);
              }
            }
    });
    bottomButtons.add(Submit);

    c.add(bottomButtons);
    f.pack();
    f.setVisible(true);
  }


  public static void main(String[] av)
  {
    //createGUI(2013);
  }
}