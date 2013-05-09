/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import RequestHoliday.Cal;
import RequestHoliday.DriverHolidayRegistrationInterface;
import RequestHoliday.Update;
import RequestHoliday.ValidateHolidayRequest;
import RosterGenerator.RoasterGenerator;
import RosterGenerator.cellRenderer;
import Simulation.Simulation;
import database.DriverInfo;
import database.database;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import Simulation.*;



//Array of components contianing tabs. 

/**
 *
 * @author Thanakorn
 */
public class InternalUser extends javax.swing.JFrame {

    private int tabSize = 5;
    private int noOfBusStop = 10;
    private int noOfRoute = 4;
    private JLabel[][] BusStop;    //383
    private JLabel[][] NextArrival;    //384
    private JLabel[][] Status;   //358 out
    private JLabel[][] Note;   //358 back
     
    private Component[] tabs; 
    /**
     * Tabs representations are: 0 = Login, 1 = Request holiday, 2 = Controller UI, 3 = Roster. 
     * @author Thanakorn Tuanwachat
     * 
     */
    public InternalUser() {
        initComponents();
        //Error pane in the login page
        errorPanel.setVisible(false);
        //Text area in the request holiday page (status text area)
        jTextArea1.setLineWrap(true);
        jTextArea1.setEditable(false);
        noteMessage.setLineWrap(true);
        noteMessage.setEditable(false);
        noteMessage.setText("We apoigise for the inconvenience\n"
                + "C1: The Driver is ill, this service has be cancelled.\n"
                + "C2: The Bus has broke down, this service has be cancelled.\n"
                + "C3: There has been a flood, this service has be cancelled.\n"
                + "C4: There has been an earthquake, this service has be cancelled.\n"
                + "D1: The Driver is ill, this service has be delayed. We apoigise for the inconvenience\n"
                + "D2: The Bus has broke down, this service has be delayed\n"
                + "D3: There has been a flood, this service has be delayed\n"
                + "D4: There has been an earthquake, this service has be delayed");
        //To wrap the text in each cell in the Roster tab
        jTable3.setDefaultRenderer(Object.class, new cellRenderer());
        jTable3.setAutoCreateRowSorter(true);
        //Set the column size and cell renender for each column in the Roster tab
        for(int i = 0; i < 21; i++)
        {
            jTable3.getColumnModel().getColumn(i).setMinWidth(120);
            jTable3.getColumnModel().getColumn(i).setCellRenderer(new cellRenderer());
        }
        //Create an array of tabs
        tabs = new Component[tabSize];
        //Initialise the array of tabs. 
        for(int i = 0; i < tabSize; i++)
        {
            tabs[i] = InternalUserTab.getComponent(i);
        }
        //Remove all the tabs in the interface and only add one which is login.
        InternalUserTab.removeAll();
        InternalUserTab.add(tabs[0]);
        
        //Initialise array of simulation
        BusStop = new JLabel[noOfRoute][noOfBusStop];
        NextArrival = new JLabel[noOfRoute][noOfBusStop];
        Status = new JLabel[noOfRoute][noOfBusStop];
        Note = new JLabel[noOfRoute][noOfBusStop];
        initialiseRouteArray();
        
        ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            //If user has set runsimulation to be on, then start running it. 
            if(runSimulation)
              updateSimulation();
        }
        };
        new Timer(500, taskPerformer).start();
    }
    /*
     * @author Thanakorn Tuanwachat
     */
    private void initialiseRouteArray() {
        //For every lable in the simulator (that needed to be update)
        BusStop[0][0] = B1_383;
        BusStop[0][1] = B2_383;
        BusStop[0][2] = B3_383;
        BusStop[0][3] = B4_383;
        BusStop[0][4] = B5_383;
        BusStop[0][5] = B6_383;
        BusStop[0][6] = B7_383;
        BusStop[0][7] = B8_383;    
        BusStop[0][8] = B9_383;
        BusStop[0][9] = B10_383;    
        
        BusStop[1][0] = B1_384;
        BusStop[1][1] = B2_384;
        BusStop[1][2] = B3_384;
        BusStop[1][3] = B4_384;
        BusStop[1][4] = B5_384;
        BusStop[1][5] = B6_384;
        BusStop[1][6] = B7_384;
        BusStop[1][7] = B8_384;    
        BusStop[1][8] = B9_384;
        BusStop[1][9] = B10_384; 
        
        BusStop[2][0] = B1_358O;
        BusStop[2][1] = B2_358O;
        BusStop[2][2] = B3_358O;
        BusStop[2][3] = B4_358O;
        BusStop[2][4] = B5_358O;
        BusStop[2][5] = B6_358O;
        BusStop[2][6] = B7_358O;
        BusStop[2][7] = B8_358O;  
        BusStop[2][8] = B9_358O;
        BusStop[2][9] = B10_358O;
        
        BusStop[3][0] = B1_358B;
        BusStop[3][1] = B2_358B;
        BusStop[3][2] = B3_358B;
        BusStop[3][3] = B4_358B;
        BusStop[3][4] = B5_358B;
        BusStop[3][5] = B6_358B;
        BusStop[3][6] = B7_358B;
        BusStop[3][7] = B8_358B;    
        BusStop[3][8] = B9_358B;
        BusStop[3][9] = B10_358B; 
        //-------------------------------------------------------------------
        NextArrival[0][0] = N1_383;
        NextArrival[0][1] = N2_383;
        NextArrival[0][2] = N3_383;
        NextArrival[0][3] = N4_383;
        NextArrival[0][4] = N5_383;
        NextArrival[0][5] = N6_383;
        NextArrival[0][6] = N7_383;
        NextArrival[0][7] = N8_383;
        NextArrival[0][8] = N9_383;
        NextArrival[0][9] = N10_383;
        
        NextArrival[1][0] = N1_384;
        NextArrival[1][1] = N2_384;
        NextArrival[1][2] = N3_384;
        NextArrival[1][3] = N4_384;
        NextArrival[1][4] = N5_384;
        NextArrival[1][5] = N6_384;
        NextArrival[1][6] = N7_384;
        NextArrival[1][7] = N8_384;
        NextArrival[1][8] = N9_384;
        NextArrival[1][9] = N10_384;
               
        NextArrival[2][0] = N1_358O;
        NextArrival[2][1] = N2_358O;
        NextArrival[2][2] = N3_358O;
        NextArrival[2][3] = N4_358O;
        NextArrival[2][4] = N5_358O;
        NextArrival[2][5] = N6_358O;
        NextArrival[2][6] = N7_358O;
        NextArrival[2][7] = N8_358O;
        NextArrival[2][8] = N9_358O;
        NextArrival[2][9] = N10_358O;
        
        NextArrival[3][0] = N1_358B;
        NextArrival[3][1] = N2_358B;
        NextArrival[3][2] = N3_358B;
        NextArrival[3][3] = N4_358B;
        NextArrival[3][4] = N5_358B;
        NextArrival[3][5] = N6_358B;
        NextArrival[3][6] = N7_358B;
        NextArrival[3][7] = N8_358B;
        NextArrival[3][8] = N9_358B;
        NextArrival[3][9] = N10_358B;
        
        //-------------------------------------------------------------------
        
        Status[0][0] = S1_383;
        Status[0][1] = S2_383;
        Status[0][2] = S3_383;
        Status[0][3] = S4_383;
        Status[0][4] = S5_383;
        Status[0][5] = S6_383;
        Status[0][6] = S7_383;
        Status[0][7] = S8_383;
        Status[0][8] = S9_383;
        Status[0][9] = S10_383;
        
        Status[1][0] = S1_384;
        Status[1][1] = S2_384;
        Status[1][2] = S3_384;
        Status[1][3] = S4_384;
        Status[1][4] = S5_384;
        Status[1][5] = S6_384;
        Status[1][6] = S7_384;
        Status[1][7] = S8_384;
        Status[1][8] = S9_384;
        Status[1][9] = S10_384;
        
        Status[2][0] = S1_358O;
        Status[2][1] = S2_358O;
        Status[2][2] = S3_358O;
        Status[2][3] = S4_358O;
        Status[2][4] = S5_358O;
        Status[2][5] = S6_358O;
        Status[2][6] = S7_358O;
        Status[2][7] = S8_358O;
        Status[2][8] = S9_358O;
        Status[2][9] = S10_358O;
        
        Status[3][0] = S1_358B;
        Status[3][1] = S2_358B;
        Status[3][2] = S3_358B;
        Status[3][3] = S4_358B;
        Status[3][4] = S5_358B;
        Status[3][5] = S6_358B;
        Status[3][6] = S7_358B;
        Status[3][7] = S8_358B;
        Status[3][8] = S9_358B;
        Status[3][9] = S10_358B;
        //-------------------------------------------------------------------
        
        Note[0][0] = NT1_383;
        Note[0][1] = NT2_383;
        Note[0][2] = NT3_383;
        Note[0][3] = NT4_383;
        Note[0][4] = NT5_383;
        Note[0][5] = NT6_383;
        Note[0][6] = NT7_383;
        Note[0][7] = NT8_383;
        Note[0][8] = NT9_383;
        Note[0][9] = NT10_383;
        
        Note[1][0] = NT1_384;
        Note[1][1] = NT2_384;
        Note[1][2] = NT3_384;
        Note[1][3] = NT4_384;
        Note[1][4] = NT5_384;
        Note[1][5] = NT6_384;
        Note[1][6] = NT7_384;
        Note[1][7] = NT8_384;
        Note[1][8] = NT9_384;
        Note[1][9] = NT10_384;
        
        Note[2][0] = NT1_358O;
        Note[2][1] = NT2_358O;
        Note[2][2] = NT3_358O;
        Note[2][3] = NT4_358O;
        Note[2][4] = NT5_358O;
        Note[2][5] = NT6_358O;
        Note[2][6] = NT7_358O;
        Note[2][7] = NT8_358O;
        Note[2][8] = NT9_358O;
        Note[2][9] = NT10_358O;
        
        Note[3][0] = NT1_358B;
        Note[3][1] = NT2_358B;
        Note[3][2] = NT3_358B;
        Note[3][3] = NT4_358B;
        Note[3][4] = NT5_358B;
        Note[3][5] = NT6_358B;
        Note[3][6] = NT7_358B;
        Note[3][7] = NT8_358B;
        Note[3][8] = NT9_358B;
        Note[3][9] = NT10_358B;
        
    }
    
    /*
     * @Authod Thanakorn Tuanwachat
     */
    String textTime;
    private void updateSimulation()
    {
        
        UpdateSimulation.updateSim(sim383, 65);
        UpdateSimulation.updateSim(sim384, 66);
        UpdateSimulation.updateSim(sim358O, 67);
        UpdateSimulation.updateSim(sim358B, 68);
        int hr, min;
        hr = date383.get(Calendar.HOUR);
        min = date383.get(Calendar.MINUTE);
        textTime = hr < 10? "0" + Integer.toString(hr): Integer.toString(hr);
        textTime += ":" + (min < 10? "0" + Integer.toString(min): Integer.toString(min));
        clock383.setText(textTime);
        
        hr = date384.get(Calendar.HOUR);
        min = date384.get(Calendar.MINUTE);
        textTime = hr < 10? "0" + Integer.toString(hr): Integer.toString(hr);
        textTime += ":" + (min < 10? "0" + Integer.toString(min): Integer.toString(min));
        clock384.setText(textTime);
        
        hr = date358O.get(Calendar.HOUR);
        min = date358O.get(Calendar.MINUTE);
        textTime = hr < 10? "0" + Integer.toString(hr): Integer.toString(hr);
        textTime += ":" + (min < 10? "0" + Integer.toString(min): Integer.toString(min));
        clock358O.setText(textTime);
        
        hr = date358B.get(Calendar.HOUR);
        min = date358B.get(Calendar.MINUTE);
        textTime = hr < 10? "0" + Integer.toString(hr): Integer.toString(hr);
        textTime += ":" + (min < 10? "0" + Integer.toString(min): Integer.toString(min));
        clock358B.setText(textTime);
            
        for(int i = 0; i < sim383.size(); i++)
        {
            if(sim383.get(i).getChanged())
            {
                BusStop[0][i].setText(sim383.get(i).getBusStopName());
                NextArrival[0][i].setText(sim383.get(i).getTime());
                if(sim383.get(i).getStatus() == 0)
                  Status[0][i].setText("On time");
                else if(sim383.get(i).getStatus() == 1)
                  Status[0][i].setText("Cancelled");
                else
                  Status[0][i].setText("Delayed");
                if(sim383.get(i).getStatus() == 1)
                {
                  Note[0][i].setText(sim383.get(i).getRandomCancelMessage());
                }//if
                else if (sim383.get(i).getStatus() == 2)
                {
                    Note[0][i].setText(sim383.get(i).getRandomDelayMessage());
                }//else
                else
                {
                    Note[0][i].setText("-");
                }
            }//if
        }//for
        
        for(int i = 0; i < sim384.size(); i++)
        {
            if(sim384.get(i).getChanged())
            {
                BusStop[1][i].setText(sim384.get(i).getBusStopName());
                NextArrival[1][i].setText(sim384.get(i).getTime());
                if(sim384.get(i).getStatus() == 0)
                  Status[1][i].setText("On time");
                else if(sim384.get(i).getStatus() == 1)
                  Status[1][i].setText("Cancelled");
                else
                  Status[1][i].setText("Delayed");
                if(sim384.get(i).getStatus() == 1)
                {
                  Note[1][i].setText(sim384.get(i).getRandomCancelMessage());
                }//if
                else if(sim384.get(i).getStatus() == 2)
                {
                    Note[1][i].setText(sim384.get(i).getRandomDelayMessage());
                }//else
                else
                {
                  Note[1][i].setText("-");
                }
            }//if
        }//for
        
         for(int i = 0; i < sim358O.size(); i++)
        {
            if(sim358O.get(i).getChanged())
            {
                BusStop[2][i].setText(sim358O.get(i).getBusStopName());
                NextArrival[2][i].setText(sim358O.get(i).getTime());
                
                if(sim358O.get(i).getStatus() == 0)
                  Status[2][i].setText("On time");
                else if(sim358O.get(i).getStatus() == 1)
                  Status[2][i].setText("Cancelled");
                else
                  Status[2][i].setText("Delayed");
                if(sim358O.get(i).getStatus() == 1)
                {
                  Note[2][i].setText(sim358O.get(i).getRandomCancelMessage());
                }//if
                else if (sim358O.get(i).getStatus() == 2)
                {
                    Note[2][i].setText(sim358O.get(i).getRandomDelayMessage());
                }//else
                else
                {
                    Note[2][i].setText("-");
                }//else
            }//if
        }//for
         
        for(int i = 0; i < sim358B.size(); i++)
        {
            if(sim358B.get(i).getChanged())
            {
                BusStop[3][i].setText(sim358B.get(i).getBusStopName());
                NextArrival[3][i].setText(sim358B.get(i).getTime());
                if(sim358B.get(i).getStatus() == 0)
                  Status[3][i].setText("On time");
                else if(sim358B.get(i).getStatus() == 1)
                  Status[3][i].setText("Cancelled");
                else
                  Status[3][i].setText("Delayed");
                if(sim358B.get(i).getStatus() == 1)
                {
                  Note[3][i].setText(sim358B.get(i).getRandomCancelMessage());
                }//if
                else if (sim358B.get(i).getStatus() == 2)
                {
                    Note[3][i].setText(sim358B.get(i).getRandomDelayMessage());
                }//else
                else
                {
                    Note[3][i].setText("-");
                }//else
            }//if
        }//for
    }//updateSimulation
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel5 = new javax.swing.JPanel();
        jTable1 = new javax.swing.JTable();
        jTable2 = new javax.swing.JTable();
        InternalUserTab = new javax.swing.JTabbedPane();
        LoginPanel = new javax.swing.JPanel();
        InsideLoginPanel = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        errorPanel = new javax.swing.JPanel();
        errorLable = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        loginTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        dateFromPanel = new javax.swing.JPanel();
        dateToPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        dayLeftLable = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        simBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        combobox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        Status358B = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        BStop383 = new javax.swing.JPanel();
        B2_383 = new javax.swing.JLabel();
        B1_383 = new javax.swing.JLabel();
        B3_383 = new javax.swing.JLabel();
        B4_383 = new javax.swing.JLabel();
        B5_383 = new javax.swing.JLabel();
        B6_383 = new javax.swing.JLabel();
        B7_383 = new javax.swing.JLabel();
        B8_383 = new javax.swing.JLabel();
        B9_383 = new javax.swing.JLabel();
        B10_383 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        noteMessage = new javax.swing.JTextArea();
        jPanel23 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        NA383 = new javax.swing.JPanel();
        N2_383 = new javax.swing.JLabel();
        N1_383 = new javax.swing.JLabel();
        N3_383 = new javax.swing.JLabel();
        N4_383 = new javax.swing.JLabel();
        N5_383 = new javax.swing.JLabel();
        N6_383 = new javax.swing.JLabel();
        N7_383 = new javax.swing.JLabel();
        N8_383 = new javax.swing.JLabel();
        N9_383 = new javax.swing.JLabel();
        N10_383 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Status383 = new javax.swing.JPanel();
        S2_383 = new javax.swing.JLabel();
        S1_383 = new javax.swing.JLabel();
        S3_383 = new javax.swing.JLabel();
        S4_383 = new javax.swing.JLabel();
        S5_383 = new javax.swing.JLabel();
        S6_383 = new javax.swing.JLabel();
        S7_383 = new javax.swing.JLabel();
        S8_383 = new javax.swing.JLabel();
        S9_383 = new javax.swing.JLabel();
        S10_383 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        Note383 = new javax.swing.JPanel();
        NT2_383 = new javax.swing.JLabel();
        NT1_383 = new javax.swing.JLabel();
        NT3_383 = new javax.swing.JLabel();
        NT4_383 = new javax.swing.JLabel();
        NT5_383 = new javax.swing.JLabel();
        NT6_383 = new javax.swing.JLabel();
        NT7_383 = new javax.swing.JLabel();
        NT8_383 = new javax.swing.JLabel();
        NT9_383 = new javax.swing.JLabel();
        NT10_383 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        clock383 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        BStop384 = new javax.swing.JPanel();
        B2_384 = new javax.swing.JLabel();
        B1_384 = new javax.swing.JLabel();
        B3_384 = new javax.swing.JLabel();
        B4_384 = new javax.swing.JLabel();
        B5_384 = new javax.swing.JLabel();
        B6_384 = new javax.swing.JLabel();
        B7_384 = new javax.swing.JLabel();
        B8_384 = new javax.swing.JLabel();
        B9_384 = new javax.swing.JLabel();
        B10_384 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel34 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        NA384 = new javax.swing.JPanel();
        N2_384 = new javax.swing.JLabel();
        N1_384 = new javax.swing.JLabel();
        N3_384 = new javax.swing.JLabel();
        N4_384 = new javax.swing.JLabel();
        N5_384 = new javax.swing.JLabel();
        N6_384 = new javax.swing.JLabel();
        N7_384 = new javax.swing.JLabel();
        N8_384 = new javax.swing.JLabel();
        N9_384 = new javax.swing.JLabel();
        N10_384 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        Status384 = new javax.swing.JPanel();
        S2_384 = new javax.swing.JLabel();
        S1_384 = new javax.swing.JLabel();
        S3_384 = new javax.swing.JLabel();
        S4_384 = new javax.swing.JLabel();
        S5_384 = new javax.swing.JLabel();
        S6_384 = new javax.swing.JLabel();
        S7_384 = new javax.swing.JLabel();
        S8_384 = new javax.swing.JLabel();
        S9_384 = new javax.swing.JLabel();
        S10_384 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        Note384 = new javax.swing.JPanel();
        NT2_384 = new javax.swing.JLabel();
        NT1_384 = new javax.swing.JLabel();
        NT3_384 = new javax.swing.JLabel();
        NT4_384 = new javax.swing.JLabel();
        NT5_384 = new javax.swing.JLabel();
        NT6_384 = new javax.swing.JLabel();
        NT7_384 = new javax.swing.JLabel();
        NT8_384 = new javax.swing.JLabel();
        NT9_384 = new javax.swing.JLabel();
        NT10_384 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        clock384 = new javax.swing.JLabel();
        jLabel193 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        BStop358O = new javax.swing.JPanel();
        B2_358O = new javax.swing.JLabel();
        B1_358O = new javax.swing.JLabel();
        B3_358O = new javax.swing.JLabel();
        B4_358O = new javax.swing.JLabel();
        B5_358O = new javax.swing.JLabel();
        B6_358O = new javax.swing.JLabel();
        B7_358O = new javax.swing.JLabel();
        B8_358O = new javax.swing.JLabel();
        B9_358O = new javax.swing.JLabel();
        B10_358O = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel44 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        NA358O = new javax.swing.JPanel();
        N2_358O = new javax.swing.JLabel();
        N1_358O = new javax.swing.JLabel();
        N3_358O = new javax.swing.JLabel();
        N4_358O = new javax.swing.JLabel();
        N5_358O = new javax.swing.JLabel();
        N6_358O = new javax.swing.JLabel();
        N7_358O = new javax.swing.JLabel();
        N8_358O = new javax.swing.JLabel();
        N9_358O = new javax.swing.JLabel();
        N10_358O = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        Status358O = new javax.swing.JPanel();
        S2_358O = new javax.swing.JLabel();
        S1_358O = new javax.swing.JLabel();
        S3_358O = new javax.swing.JLabel();
        S4_358O = new javax.swing.JLabel();
        S5_358O = new javax.swing.JLabel();
        S6_358O = new javax.swing.JLabel();
        S7_358O = new javax.swing.JLabel();
        S8_358O = new javax.swing.JLabel();
        S9_358O = new javax.swing.JLabel();
        S10_358O = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        Note358O = new javax.swing.JPanel();
        NT2_358O = new javax.swing.JLabel();
        NT1_358O = new javax.swing.JLabel();
        NT3_358O = new javax.swing.JLabel();
        NT4_358O = new javax.swing.JLabel();
        NT5_358O = new javax.swing.JLabel();
        NT6_358O = new javax.swing.JLabel();
        NT7_358O = new javax.swing.JLabel();
        NT8_358O = new javax.swing.JLabel();
        NT9_358O = new javax.swing.JLabel();
        NT10_358O = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        clock358O = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel142 = new javax.swing.JLabel();
        BStop358B = new javax.swing.JPanel();
        B2_358B = new javax.swing.JLabel();
        B1_358B = new javax.swing.JLabel();
        B3_358B = new javax.swing.JLabel();
        B4_358B = new javax.swing.JLabel();
        B5_358B = new javax.swing.JLabel();
        B6_358B = new javax.swing.JLabel();
        B7_358B = new javax.swing.JLabel();
        B8_358B = new javax.swing.JLabel();
        B9_358B = new javax.swing.JLabel();
        B10_358B = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jPanel54 = new javax.swing.JPanel();
        jLabel153 = new javax.swing.JLabel();
        NA358B = new javax.swing.JPanel();
        N2_358B = new javax.swing.JLabel();
        N1_358B = new javax.swing.JLabel();
        N3_358B = new javax.swing.JLabel();
        N4_358B = new javax.swing.JLabel();
        N5_358B = new javax.swing.JLabel();
        N6_358B = new javax.swing.JLabel();
        N7_358B = new javax.swing.JLabel();
        N8_358B = new javax.swing.JLabel();
        N9_358B = new javax.swing.JLabel();
        N10_358B = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        S2_358B = new javax.swing.JLabel();
        S1_358B = new javax.swing.JLabel();
        S3_358B = new javax.swing.JLabel();
        S4_358B = new javax.swing.JLabel();
        S5_358B = new javax.swing.JLabel();
        S6_358B = new javax.swing.JLabel();
        S7_358B = new javax.swing.JLabel();
        S8_358B = new javax.swing.JLabel();
        S9_358B = new javax.swing.JLabel();
        S10_358B = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        jLabel175 = new javax.swing.JLabel();
        Note358B = new javax.swing.JPanel();
        NT2_358B = new javax.swing.JLabel();
        NT1_358B = new javax.swing.JLabel();
        NT3_358B = new javax.swing.JLabel();
        NT4_358B = new javax.swing.JLabel();
        NT5_358B = new javax.swing.JLabel();
        NT6_358B = new javax.swing.JLabel();
        NT7_358B = new javax.swing.JLabel();
        NT8_358B = new javax.swing.JLabel();
        NT9_358B = new javax.swing.JLabel();
        NT10_358B = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        clock358B = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        jPanel61 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        startSim = new javax.swing.JButton();
        stopSim = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel37 = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel39 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Service", "5:00-6:00", "6:00-7:00", "7:00-8:00", "8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00", "21:00-22:00", "22:00-23:00", "23:00-0:00", "0:00-1:00"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Service", "5:00-6:00", "6:00-7:00", "7:00-8:00", "8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00", "21:00-22:00", "22:00-23:00", "23:00-0:00", "0:00-1:00"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        InternalUserTab.setBackground(new java.awt.Color(51, 51, 51));
        InternalUserTab.setPreferredSize(new java.awt.Dimension(910, 600));

        LoginPanel.setMinimumSize(new java.awt.Dimension(0, 0));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), LoginPanel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        InsideLoginPanel.setBackground(new java.awt.Color(204, 204, 204));
        InsideLoginPanel.setForeground(new java.awt.Color(153, 153, 153));
        InsideLoginPanel.setMinimumSize(new java.awt.Dimension(910, 600));
        InsideLoginPanel.setPreferredSize(new java.awt.Dimension(910, 600));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, LoginPanel, org.jdesktop.beansbinding.ELProperty.create("${alignmentX}"), InsideLoginPanel, org.jdesktop.beansbinding.BeanProperty.create("alignmentX"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, LoginPanel, org.jdesktop.beansbinding.ELProperty.create("${alignmentY}"), InsideLoginPanel, org.jdesktop.beansbinding.BeanProperty.create("alignmentY"));
        bindingGroup.addBinding(binding);

        InsideLoginPanel.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel13, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), errorPanel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        errorLable.setForeground(new java.awt.Color(255, 0, 0));
        errorLable.setText("Incorrect Driver number");

        javax.swing.GroupLayout errorPanelLayout = new javax.swing.GroupLayout(errorPanel);
        errorPanel.setLayout(errorPanelLayout);
        errorPanelLayout.setHorizontalGroup(
            errorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorPanelLayout.createSequentialGroup()
                .addComponent(errorLable, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 59, Short.MAX_VALUE))
        );
        errorPanelLayout.setVerticalGroup(
            errorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(errorLable, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Driver ID");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Internal User Authentication");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(loginButton)
                .addGap(7, 7, 7)
                .addComponent(errorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        InsideLoginPanel.add(jPanel13, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InsideLoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InsideLoginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Login", LoginPanel);

        jPanel6.setPreferredSize(new java.awt.Dimension(0, 0));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel6, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel7.setPreferredSize(new java.awt.Dimension(910, 600));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel7, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel14, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel8, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Date from");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel9, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Date to");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        dateFromPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), dateFromPanel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        dateFromPanel.setLayout(new java.awt.BorderLayout());

        dateToPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), dateToPanel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        dateToPanel.setLayout(new java.awt.BorderLayout());

        jButton1.setText("submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel14Layout.createSequentialGroup()
                            .addComponent(dateFromPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dateToPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateFromPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(dateToPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(33, 25, 216, 46);
        jPanel7.add(jPanel14, gridBagConstraints);

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel15, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel10, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        dayLeftLable.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dayLeftLable.setForeground(new java.awt.Color(51, 51, 51));
        dayLeftLable.setText("000");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayLeftLable, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dayLeftLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Status Message:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel9.setBackground(new java.awt.Color(51, 51, 51));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Request a holiday form");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(44, 25, 0, 46);
        jPanel7.add(jPanel15, gridBagConstraints);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Request Holiday form", jPanel6);

        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(910, 600));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel1, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel3.setPreferredSize(new java.awt.Dimension(910, 600));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel3, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel12, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        simBtn.setText("Real-time Simulation");
        simBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simBtnActionPerformed(evt);
            }
        });
        jPanel12.add(simBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 191, 32));

        jButton2.setText("Generate Roster");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 191, 34));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Controller Interface");
        jPanel12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 103;
        gridBagConstraints.ipady = 52;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(183, 180, 215, 180);
        jPanel3.add(jPanel12, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Controller Interface", jPanel1);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel2, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel4.setPreferredSize(new java.awt.Dimension(910, 600));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel4, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Roster");

        combobox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        combobox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 255));
        jLabel5.setText("Day of week");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14", "Title 15", "Title 16", "Title 17", "Title 18", "Title 19", "Title 20", "Title 21"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setAutoscrolls(false);
        jTable3.setMinimumSize(new java.awt.Dimension(2520, 470));
        jTable3.setName(""); // NOI18N
        jTable3.setPreferredSize(new java.awt.Dimension(2520, 470));
        jTable3.setRowHeight(150);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/23.png"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jButton4, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Generate Roster");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jButton5, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Roster", jPanel2);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel16, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel16.setLayout(new java.awt.GridBagLayout());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), Status358B, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel17, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel21, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Bus Stop ");

        B2_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B2_383.setOpaque(true);

        B1_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B1_383.setOpaque(true);

        B3_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B3_383.setOpaque(true);

        B4_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B4_383.setOpaque(true);

        B5_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B5_383.setOpaque(true);

        B6_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B6_383.setOpaque(true);

        B7_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B7_383.setOpaque(true);

        B8_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B8_383.setOpaque(true);

        B9_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B9_383.setOpaque(true);

        B10_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B10_383.setOpaque(true);

        javax.swing.GroupLayout BStop383Layout = new javax.swing.GroupLayout(BStop383);
        BStop383.setLayout(BStop383Layout);
        BStop383Layout.setHorizontalGroup(
            BStop383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop383Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BStop383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B2_383, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(B3_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B4_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B6_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B7_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B9_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B10_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(BStop383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop383Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(B1_383, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        BStop383Layout.setVerticalGroup(
            BStop383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop383Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(B2_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B3_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B4_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B5_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B6_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B7_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B8_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B9_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B10_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BStop383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop383Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(B1_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(BStop383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BStop383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        noteMessage.setColumns(20);
        noteMessage.setRows(5);
        jScrollPane3.setViewportView(noteMessage);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Next Arrival");

        N2_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N2_383.setOpaque(true);

        N1_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N1_383.setOpaque(true);

        N3_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N3_383.setOpaque(true);

        N4_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N4_383.setOpaque(true);

        N5_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N5_383.setOpaque(true);

        N6_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N6_383.setOpaque(true);

        N7_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N7_383.setOpaque(true);

        N8_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N8_383.setOpaque(true);

        N9_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N9_383.setOpaque(true);

        N10_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N10_383.setOpaque(true);

        javax.swing.GroupLayout NA383Layout = new javax.swing.GroupLayout(NA383);
        NA383.setLayout(NA383Layout);
        NA383Layout.setHorizontalGroup(
            NA383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA383Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NA383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(N2_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N3_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N4_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N5_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N6_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N7_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N8_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N9_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N10_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(NA383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA383Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(N1_383, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        NA383Layout.setVerticalGroup(
            NA383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA383Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(N2_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N3_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N4_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N5_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N6_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N7_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N8_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N9_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N10_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(NA383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA383Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(N1_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NA383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(NA383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Status");

        S2_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S2_383.setOpaque(true);

        S1_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S1_383.setOpaque(true);

        S3_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S3_383.setOpaque(true);

        S4_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S4_383.setOpaque(true);

        S5_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S5_383.setOpaque(true);

        S6_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S6_383.setOpaque(true);

        S7_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S7_383.setOpaque(true);

        S8_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S8_383.setOpaque(true);

        S9_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S9_383.setOpaque(true);

        S10_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S10_383.setOpaque(true);

        javax.swing.GroupLayout Status383Layout = new javax.swing.GroupLayout(Status383);
        Status383.setLayout(Status383Layout);
        Status383Layout.setHorizontalGroup(
            Status383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status383Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Status383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S2_383, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(S3_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S4_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S5_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S6_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S7_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S8_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S9_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S10_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Status383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status383Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(S1_383, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Status383Layout.setVerticalGroup(
            Status383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status383Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(S2_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S3_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S4_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S5_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S6_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S7_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S8_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S9_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S10_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Status383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status383Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(S1_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Status383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Note");

        NT2_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT2_383.setOpaque(true);

        NT1_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT1_383.setOpaque(true);

        NT3_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT3_383.setOpaque(true);

        NT4_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT4_383.setOpaque(true);

        NT5_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT5_383.setOpaque(true);

        NT6_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT6_383.setOpaque(true);

        NT7_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT7_383.setOpaque(true);

        NT8_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT8_383.setOpaque(true);

        NT9_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT9_383.setOpaque(true);

        NT10_383.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT10_383.setOpaque(true);

        javax.swing.GroupLayout Note383Layout = new javax.swing.GroupLayout(Note383);
        Note383.setLayout(Note383Layout);
        Note383Layout.setHorizontalGroup(
            Note383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note383Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Note383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NT2_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT3_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT4_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT5_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT6_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT7_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT8_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT9_383, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT10_383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Note383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note383Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NT1_383, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Note383Layout.setVerticalGroup(
            Note383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note383Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(NT2_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT3_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT4_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT5_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT6_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT7_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT8_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT9_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT10_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(Note383Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note383Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(NT1_383, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(Note383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Note383, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        clock383.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        clock383.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clock383.setText("00:00");

        jLabel191.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel191.setText("Clock");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel191)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(clock383)
                    .addGap(0, 21, Short.MAX_VALUE)))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel191)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(clock383)
                    .addGap(0, 26, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Status358B.addTab("          383          ", jPanel17);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel18, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel31, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setText("Bus Stop ");

        B2_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B2_384.setOpaque(true);

        B1_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B1_384.setOpaque(true);

        B3_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B3_384.setOpaque(true);

        B4_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B4_384.setOpaque(true);

        B5_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B5_384.setOpaque(true);

        B6_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B6_384.setOpaque(true);

        B7_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B7_384.setOpaque(true);

        B8_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B8_384.setOpaque(true);

        B9_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B9_384.setOpaque(true);

        B10_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B10_384.setOpaque(true);

        javax.swing.GroupLayout BStop384Layout = new javax.swing.GroupLayout(BStop384);
        BStop384.setLayout(BStop384Layout);
        BStop384Layout.setHorizontalGroup(
            BStop384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop384Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BStop384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B2_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B3_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B4_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B6_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B7_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B9_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B10_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(BStop384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop384Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(B1_384, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        BStop384Layout.setVerticalGroup(
            BStop384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop384Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(B2_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B3_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B4_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B5_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B6_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B7_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B8_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B9_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B10_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BStop384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop384Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(B1_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(BStop384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BStop384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, noteMessage, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextArea3, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane4.setViewportView(jTextArea3);

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Next Arrival");

        N2_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N2_384.setOpaque(true);

        N1_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N1_384.setOpaque(true);

        N3_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N3_384.setOpaque(true);

        N4_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N4_384.setOpaque(true);

        N5_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N5_384.setOpaque(true);

        N6_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N6_384.setOpaque(true);

        N7_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N7_384.setOpaque(true);

        N8_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N8_384.setOpaque(true);

        N9_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N9_384.setOpaque(true);

        N10_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N10_384.setOpaque(true);

        javax.swing.GroupLayout NA384Layout = new javax.swing.GroupLayout(NA384);
        NA384.setLayout(NA384Layout);
        NA384Layout.setHorizontalGroup(
            NA384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA384Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NA384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(N2_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N3_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N4_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N5_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N6_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N7_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N8_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N9_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N10_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(NA384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA384Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(N1_384, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        NA384Layout.setVerticalGroup(
            NA384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA384Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(N2_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N3_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N4_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N5_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N6_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N7_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N8_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N9_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N10_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(NA384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA384Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(N1_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NA384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(NA384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel76.setText("Status");

        S2_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S2_384.setOpaque(true);

        S1_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S1_384.setOpaque(true);

        S3_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S3_384.setOpaque(true);

        S4_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S4_384.setOpaque(true);

        S5_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S5_384.setOpaque(true);

        S6_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S6_384.setOpaque(true);

        S7_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S7_384.setOpaque(true);

        S8_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S8_384.setOpaque(true);

        S9_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S9_384.setOpaque(true);

        S10_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S10_384.setOpaque(true);

        javax.swing.GroupLayout Status384Layout = new javax.swing.GroupLayout(Status384);
        Status384.setLayout(Status384Layout);
        Status384Layout.setHorizontalGroup(
            Status384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status384Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Status384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S2_384, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(S3_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S4_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S5_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S6_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S7_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S8_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S9_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S10_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Status384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status384Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(S1_384, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Status384Layout.setVerticalGroup(
            Status384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status384Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(S2_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S3_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S4_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S5_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S6_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S7_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S8_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S9_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S10_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Status384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status384Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(S1_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Status384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jLabel87.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel87.setText("Note");

        NT2_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT2_384.setOpaque(true);

        NT1_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT1_384.setOpaque(true);

        NT3_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT3_384.setOpaque(true);

        NT4_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT4_384.setOpaque(true);

        NT5_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT5_384.setOpaque(true);

        NT6_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT6_384.setOpaque(true);

        NT7_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT7_384.setOpaque(true);

        NT8_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT8_384.setOpaque(true);

        NT9_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT9_384.setOpaque(true);

        NT10_384.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT10_384.setOpaque(true);

        javax.swing.GroupLayout Note384Layout = new javax.swing.GroupLayout(Note384);
        Note384.setLayout(Note384Layout);
        Note384Layout.setHorizontalGroup(
            Note384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note384Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Note384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NT2_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT3_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT4_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT5_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT6_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT7_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT8_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT9_384, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT10_384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Note384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note384Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NT1_384, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Note384Layout.setVerticalGroup(
            Note384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note384Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(NT2_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT3_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT4_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT5_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT6_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT7_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT8_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT9_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT10_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(Note384Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note384Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(NT1_384, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(Note384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Note384, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        clock384.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        clock384.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel193.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel193.setText("Clock");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel193)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(clock384)
                    .addGap(0, 21, Short.MAX_VALUE)))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel193)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(clock384)
                    .addGap(0, 26, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Status358B.addTab("         384         ", jPanel18);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel19, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel41.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel41, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel98.setText("Bus Stop ");

        B2_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B2_358O.setOpaque(true);

        B1_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B1_358O.setOpaque(true);

        B3_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B3_358O.setOpaque(true);

        B4_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B4_358O.setOpaque(true);

        B5_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B5_358O.setOpaque(true);

        B6_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B6_358O.setOpaque(true);

        B7_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B7_358O.setOpaque(true);

        B8_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B8_358O.setOpaque(true);

        B9_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B9_358O.setOpaque(true);

        B10_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B10_358O.setOpaque(true);

        javax.swing.GroupLayout BStop358OLayout = new javax.swing.GroupLayout(BStop358O);
        BStop358O.setLayout(BStop358OLayout);
        BStop358OLayout.setHorizontalGroup(
            BStop358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop358OLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BStop358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B2_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B3_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B4_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B6_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B7_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B9_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B10_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(BStop358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop358OLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(B1_358O, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        BStop358OLayout.setVerticalGroup(
            BStop358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop358OLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(B2_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B3_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B4_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B5_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B6_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B7_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B8_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B9_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B10_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BStop358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop358OLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(B1_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(BStop358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BStop358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, noteMessage, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextArea4, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane5.setViewportView(jTextArea4);

        jLabel109.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel109.setText("Next Arrival");

        N2_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N2_358O.setOpaque(true);

        N1_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N1_358O.setOpaque(true);

        N3_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N3_358O.setOpaque(true);

        N4_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N4_358O.setOpaque(true);

        N5_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N5_358O.setOpaque(true);

        N6_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N6_358O.setOpaque(true);

        N7_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N7_358O.setOpaque(true);

        N8_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N8_358O.setOpaque(true);

        N9_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N9_358O.setOpaque(true);

        N10_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N10_358O.setOpaque(true);

        javax.swing.GroupLayout NA358OLayout = new javax.swing.GroupLayout(NA358O);
        NA358O.setLayout(NA358OLayout);
        NA358OLayout.setHorizontalGroup(
            NA358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA358OLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NA358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(N2_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N3_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N4_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N5_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N6_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N7_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N8_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N9_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N10_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(NA358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA358OLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(N1_358O, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        NA358OLayout.setVerticalGroup(
            NA358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA358OLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(N2_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N3_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N4_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N5_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N6_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N7_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N8_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N9_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N10_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(NA358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA358OLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(N1_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NA358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(NA358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel120.setText("Status");

        S2_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S2_358O.setOpaque(true);

        S1_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S1_358O.setOpaque(true);

        S3_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S3_358O.setOpaque(true);

        S4_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S4_358O.setOpaque(true);

        S5_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S5_358O.setOpaque(true);

        S6_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S6_358O.setOpaque(true);

        S7_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S7_358O.setOpaque(true);

        S8_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S8_358O.setOpaque(true);

        S9_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S9_358O.setOpaque(true);

        S10_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S10_358O.setOpaque(true);

        javax.swing.GroupLayout Status358OLayout = new javax.swing.GroupLayout(Status358O);
        Status358O.setLayout(Status358OLayout);
        Status358OLayout.setHorizontalGroup(
            Status358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status358OLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Status358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S2_358O, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(S3_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S4_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S5_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S6_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S7_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S8_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S9_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S10_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Status358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status358OLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(S1_358O, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Status358OLayout.setVerticalGroup(
            Status358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Status358OLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(S2_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S3_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S4_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S5_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S6_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S7_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S8_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S9_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S10_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Status358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Status358OLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(S1_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Status358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel131.setText("Note");

        NT2_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT2_358O.setOpaque(true);

        NT1_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT1_358O.setOpaque(true);

        NT3_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT3_358O.setOpaque(true);

        NT4_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT4_358O.setOpaque(true);

        NT5_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT5_358O.setOpaque(true);

        NT6_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT6_358O.setOpaque(true);

        NT7_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT7_358O.setOpaque(true);

        NT8_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT8_358O.setOpaque(true);

        NT9_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT9_358O.setOpaque(true);

        NT10_358O.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT10_358O.setOpaque(true);

        javax.swing.GroupLayout Note358OLayout = new javax.swing.GroupLayout(Note358O);
        Note358O.setLayout(Note358OLayout);
        Note358OLayout.setHorizontalGroup(
            Note358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note358OLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Note358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NT2_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT3_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT4_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT5_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT6_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT7_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT8_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT9_358O, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT10_358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Note358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note358OLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NT1_358O, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Note358OLayout.setVerticalGroup(
            Note358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note358OLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(NT2_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT3_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT4_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT5_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT6_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT7_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT8_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT9_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT10_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(Note358OLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note358OLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(NT1_358O, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(Note358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Note358O, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        clock358O.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        clock358O.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel195.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel195.setText("Clock");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel195)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(clock358O)
                    .addGap(0, 21, Short.MAX_VALUE)))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel195)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel50Layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(clock358O)
                    .addGap(0, 26, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Status358B.addTab("      358_out    ", jPanel19);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel20, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel51, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel142.setText("Bus Stop ");

        B2_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B2_358B.setOpaque(true);

        B1_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B1_358B.setOpaque(true);

        B3_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B3_358B.setOpaque(true);

        B4_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B4_358B.setOpaque(true);

        B5_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B5_358B.setOpaque(true);

        B6_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B6_358B.setOpaque(true);

        B7_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B7_358B.setOpaque(true);

        B8_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B8_358B.setOpaque(true);

        B9_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B9_358B.setOpaque(true);

        B10_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        B10_358B.setOpaque(true);

        javax.swing.GroupLayout BStop358BLayout = new javax.swing.GroupLayout(BStop358B);
        BStop358B.setLayout(BStop358BLayout);
        BStop358BLayout.setHorizontalGroup(
            BStop358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop358BLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BStop358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(B2_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B3_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B4_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B5_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B6_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B7_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B8_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B9_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(B10_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(BStop358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop358BLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(B1_358B, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        BStop358BLayout.setVerticalGroup(
            BStop358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BStop358BLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(B2_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B3_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B4_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B5_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B6_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B7_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B8_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B9_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B10_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BStop358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(BStop358BLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(B1_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(BStop358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BStop358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, noteMessage, org.jdesktop.beansbinding.ELProperty.create("${text}"), jTextArea5, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane6.setViewportView(jTextArea5);

        jLabel153.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel153.setText("Next Arrival");

        N2_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N2_358B.setOpaque(true);

        N1_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N1_358B.setOpaque(true);

        N3_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N3_358B.setOpaque(true);

        N4_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N4_358B.setOpaque(true);

        N5_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N5_358B.setOpaque(true);

        N6_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N6_358B.setOpaque(true);

        N7_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N7_358B.setOpaque(true);

        N8_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N8_358B.setOpaque(true);

        N9_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N9_358B.setOpaque(true);

        N10_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        N10_358B.setOpaque(true);

        javax.swing.GroupLayout NA358BLayout = new javax.swing.GroupLayout(NA358B);
        NA358B.setLayout(NA358BLayout);
        NA358BLayout.setHorizontalGroup(
            NA358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA358BLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NA358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(N2_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N3_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N4_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N5_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N6_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N7_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N8_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N9_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(N10_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(NA358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA358BLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(N1_358B, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        NA358BLayout.setVerticalGroup(
            NA358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NA358BLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(N2_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N3_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N4_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N5_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N6_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N7_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N8_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N9_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N10_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(NA358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(NA358BLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(N1_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NA358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(NA358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel164.setText("Status");

        S2_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S2_358B.setOpaque(true);

        S1_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S1_358B.setOpaque(true);

        S3_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S3_358B.setOpaque(true);

        S4_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S4_358B.setOpaque(true);

        S5_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S5_358B.setOpaque(true);

        S6_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S6_358B.setOpaque(true);

        S7_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S7_358B.setOpaque(true);

        S8_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S8_358B.setOpaque(true);

        S9_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S9_358B.setOpaque(true);

        S10_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        S10_358B.setOpaque(true);

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(S2_358B, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(S3_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S4_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S5_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S6_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S7_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S8_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S9_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(S10_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(S1_358B, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(S2_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S3_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S4_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S5_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S6_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S7_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S8_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S9_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(S10_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel57Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(S1_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel164, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jLabel175.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel175.setText("Note");

        NT2_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT2_358B.setOpaque(true);

        NT1_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT1_358B.setOpaque(true);

        NT3_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT3_358B.setOpaque(true);

        NT4_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT4_358B.setOpaque(true);

        NT5_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT5_358B.setOpaque(true);

        NT6_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT6_358B.setOpaque(true);

        NT7_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT7_358B.setOpaque(true);

        NT8_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT8_358B.setOpaque(true);

        NT9_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT9_358B.setOpaque(true);

        NT10_358B.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        NT10_358B.setOpaque(true);

        javax.swing.GroupLayout Note358BLayout = new javax.swing.GroupLayout(Note358B);
        Note358B.setLayout(Note358BLayout);
        Note358BLayout.setHorizontalGroup(
            Note358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note358BLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Note358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NT2_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT3_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT4_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT5_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT6_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT7_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT8_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT9_358B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NT10_358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(Note358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note358BLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(NT1_358B, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        Note358BLayout.setVerticalGroup(
            Note358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Note358BLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(NT2_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT3_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT4_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT5_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT6_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT7_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT8_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT9_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NT10_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(Note358BLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Note358BLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(NT1_358B, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(Note358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addComponent(jLabel175, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Note358B, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        clock358B.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        clock358B.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel197.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel197.setText("Clock");

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel197)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel60Layout.createSequentialGroup()
                    .addGap(0, 21, Short.MAX_VALUE)
                    .addComponent(clock358B)
                    .addGap(0, 21, Short.MAX_VALUE)))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel197)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel60Layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(clock358B)
                    .addGap(0, 26, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel51Layout.createSequentialGroup()
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Status358B.addTab("     358_back     ", jPanel20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = -14;
        gridBagConstraints.ipady = -39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 11, 10);
        jPanel16.add(Status358B, gridBagConstraints);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel61, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 102, 0));
        jLabel14.setText("Real-time Simulation");

        startSim.setBackground(new java.awt.Color(153, 153, 153));
        startSim.setText("Start");
        startSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSimActionPerformed(evt);
            }
        });

        stopSim.setBackground(new java.awt.Color(153, 153, 153));
        stopSim.setText("Stop");
        stopSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(startSim, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stopSim, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startSim)
                .addComponent(stopSim))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 106;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 0, 10);
        jPanel16.add(jPanel61, gridBagConstraints);

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/23.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 0);
        jPanel16.add(jButton3, gridBagConstraints);

        InternalUserTab.addTab("Simulation", jPanel16);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel27, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel28, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jPanel33.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel33, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jCheckBox1)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(37, 37, 37))
        );

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Journey Planner", jPanel27);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InternalUserTab, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel29, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, InsideLoginPanel, org.jdesktop.beansbinding.ELProperty.create("${background}"), jPanel30, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        InternalUserTab.addTab("Journey results", jPanel29);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(InternalUserTab, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InternalUserTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //After the user has selected the Day of week, it will print out the result
    //in the table. 
    private void combobox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox1ActionPerformed
        
        String selDay = combobox1.getSelectedItem().toString();
        String[][] data = new String[][] {
                            {"358 ", null, null, null, null, null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, null},
                            {"383", null, null, null, null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, null, null},
                            {"384", null, null, null, null, null, null, null,null, null, null, null, null, null, null, null, null, null, null, null, null}};
        String[]  columnName = new String [] {"Service", "5:00 - 6:00" , "6:00 - 7:00", "7:00 - 8:00", "8:00 - 9:00", "9:00 - 10:00", 
                        "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", 
                        "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00", 
                        "22:00 - 23:00", "23:00 - 00:00", "00:00 - 1:00"};
        int dayID = 1;
        if (selDay.compareTo("Monday") == 0)
            dayID = 2;
        else if(selDay.compareTo("Tuesday") == 0)
            dayID = 3;
        else if(selDay.compareTo("Wednesday") == 0)
            dayID = 4;
        else if(selDay.compareTo("Thursday") == 0)
            dayID = 5;
        else if(selDay.compareTo("Friday") == 0)
            dayID = 6;
        else if(selDay.compareTo("Saturday") == 0)
            dayID = 7;
        else if (selDay.compareTo("Sunday") == 0)
            dayID = 1;
        System.out.println("Date you have selected is "+selDay);
        String[][] toBePrinted = RoasterGenerator.dayGUI(dayID, RoasterGenerator.roster);
        for(int column = 1; column < 21; column++)
                    {
                        for(int row = 0; row < 3; row++)
                        {
                            data[row][column] = toBePrinted[row][column];
                        }
                    }
                    
                    jTable3.setModel(new DefaultTableModel(data,columnName));
    }//GEN-LAST:event_combobox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //Get the dateFrom calendar
        if (ValidateHolidayRequest.validateRequest(DateFrom.getDate(), DateTo.getDate(), driverID) == true)
        {   System.out.println("TRUE");
            Update.updateHolidayRequest(DateFrom.getDate(), DateTo.getDate(), driverID);
            System.out.println("finish update");
            dayLeftLable.setText("Days left: " + (25 - DriverInfo.getHolidaysTaken(driverID)));
            jTextArea1.setText(Update.systemMsg.message);
            DateFrom.recompute(driverID);
            DateTo.recompute(driverID);
            System.out.println("FINSIHs");
        }
        else
        {
            jTextArea1.setText(ValidateHolidayRequest.systemMsg.message);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    //Actions perform when login button is pressed
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        driverID = DriverInfo.findDriver(loginTextField.getText());
        //2869 - driver ID to check
        //Check if the driver ID exist. 
        if (driverID!=0)
        {
            //DriverHolidayRegistrationInterface.createGUI(dirverID);
            //Remove the current tab
            InternalUserTab.remove(CURRENT_TAB);
            //Add the reuqest holiday_tab
            InternalUserTab.add(tabs[REQUEST_H]);
            //Create a new dateFrom calendar
            DateFrom = new Cal(driverID);
            //Add this dateFrom calendar to the left panel
            dateFromPanel.add(DateFrom);
            //Create a new dateTo calendar
            DateTo = new Cal(driverID);
            //Add this dateTo calendat to the right panel
            dateToPanel.add(DateTo);
            //
            dayLeftLable.setText("Days left: " + (25 - DriverInfo.getHolidaysTaken(driverID)));
            jTextArea1.setText("Welcome " + DriverInfo.getName(driverID) + "!"
                + " Please select start and end date of your holiday. Green fields indicates dates which you "
                + "can pick for a holiday, whereas red dates are the ones which are already reserved by other drivers."
                + " Dates when you have a holiday are highlighted with yellow. "
                + "All dates in your holiday period MUST be available (green).");
        }
        else if (loginTextField.getText().compareTo("Controller") == 0)
        {
            //Remove the current tab
            InternalUserTab.remove(CURRENT_TAB);
            //Add the reuqest holiday_tab
            InternalUserTab.add(tabs[CONTROLLER_UI]);
        }
        else
        {
            errorPanel.setVisible(true);
        }   // TODO add your handling code here:
    }//GEN-LAST:event_loginButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Remove the current tab
        InternalUserTab.remove(CURRENT_TAB);
        //Add the reuqest holiday_tab
        InternalUserTab.add(tabs[ROSTER]);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void simBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simBtnActionPerformed
        //Remove the current tab
        InternalUserTab.remove(CURRENT_TAB);
        //Add the reuqest holiday_tab
        InternalUserTab.add(tabs[SIM]);
    }//GEN-LAST:event_simBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Remove the current tab
        InternalUserTab.remove(CURRENT_TAB);
        //Add the reuqest holiday_tab
        InternalUserTab.add(tabs[CONTROLLER_UI]);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //Remove the current tab
        InternalUserTab.remove(CURRENT_TAB);
        //Add the reuqest holiday_tab
        InternalUserTab.add(tabs[CONTROLLER_UI]);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        GregorianCalendar dateFrom = new GregorianCalendar();
        dateFrom.set(dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), dateFrom.get(Calendar.DATE), 0, 0, 0);
        
        GregorianCalendar dateTo = new GregorianCalendar();
        dateTo.set(dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), dateFrom.get(Calendar.DATE), 0, 0, 0);
        dateTo.add(Calendar.DATE, 6);
        RoasterGenerator.GenerateRoaster(dateFrom, dateTo);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void startSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSimActionPerformed
        // TODO add your handling code here:
        //Set run simulation to be on
        runSimulation = true;
    }//GEN-LAST:event_startSimActionPerformed

    private void stopSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSimActionPerformed
        // TODO add your handling code here:
        //Set run simulation to be off
        runSimulation = false;
    }//GEN-LAST:event_stopSimActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private Cal DateFrom;
    private Cal DateTo;
    private int driverID;
    private static boolean runSimulation; 
    private static ArrayList<Simulation> sim383;
    private static ArrayList<Simulation> sim384;
    private static ArrayList<Simulation> sim358O;
    private static ArrayList<Simulation> sim358B;
    private static GregorianCalendar date383, date384, date358O, date358B;
    
    /**
     * @param args the command line arguments
     */
    private static int LOGIN, REQUEST_H, CONTROLLER_UI, ROSTER, CURRENT_TAB, SIM;
    public static void main(String args[]) {    
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        database.openBusDatabase();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InternalUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InternalUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InternalUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InternalUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        //Initialise variables
        LOGIN = 0;
        REQUEST_H = 1;
        CONTROLLER_UI = 2;
        ROSTER = 3;
        SIM = 4;
        CURRENT_TAB = 0;
        runSimulation = false;
        //Connect to the database
        date383 = new GregorianCalendar();
        date383.set(date383.get(Calendar.YEAR), date383.get(Calendar.MONTH), date383.get(Calendar.DATE), 0, 0, 0);
        date384 = new GregorianCalendar();
        date384.set(date384.get(Calendar.YEAR), date384.get(Calendar.MONTH), date384.get(Calendar.DATE), 0, 0, 0);
        date358O = new GregorianCalendar();
        date358O.set(date358O.get(Calendar.YEAR), date358O.get(Calendar.MONTH), date358O.get(Calendar.DATE), 0, 0, 0);
        date358B = new GregorianCalendar();
        date358B.set(date358B.get(Calendar.YEAR), date358B.get(Calendar.MONTH), date358B.get(Calendar.DATE), 0, 0, 0);
        
        sim383 = UpdateSimulation.initialiseArrayListForRoute(65, date383);
        sim384 = UpdateSimulation.initialiseArrayListForRoute(66, date384);
        sim358O = UpdateSimulation.initialiseArrayListForRoute(67, date358O);
        sim358B = UpdateSimulation.initialiseArrayListForRoute(68, date358B);
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InternalUser().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel B10_358B;
    private javax.swing.JLabel B10_358O;
    private javax.swing.JLabel B10_383;
    private javax.swing.JLabel B10_384;
    private javax.swing.JLabel B1_358B;
    private javax.swing.JLabel B1_358O;
    private javax.swing.JLabel B1_383;
    private javax.swing.JLabel B1_384;
    private javax.swing.JLabel B2_358B;
    private javax.swing.JLabel B2_358O;
    private javax.swing.JLabel B2_383;
    private javax.swing.JLabel B2_384;
    private javax.swing.JLabel B3_358B;
    private javax.swing.JLabel B3_358O;
    private javax.swing.JLabel B3_383;
    private javax.swing.JLabel B3_384;
    private javax.swing.JLabel B4_358B;
    private javax.swing.JLabel B4_358O;
    private javax.swing.JLabel B4_383;
    private javax.swing.JLabel B4_384;
    private javax.swing.JLabel B5_358B;
    private javax.swing.JLabel B5_358O;
    private javax.swing.JLabel B5_383;
    private javax.swing.JLabel B5_384;
    private javax.swing.JLabel B6_358B;
    private javax.swing.JLabel B6_358O;
    private javax.swing.JLabel B6_383;
    private javax.swing.JLabel B6_384;
    private javax.swing.JLabel B7_358B;
    private javax.swing.JLabel B7_358O;
    private javax.swing.JLabel B7_383;
    private javax.swing.JLabel B7_384;
    private javax.swing.JLabel B8_358B;
    private javax.swing.JLabel B8_358O;
    private javax.swing.JLabel B8_383;
    private javax.swing.JLabel B8_384;
    private javax.swing.JLabel B9_358B;
    private javax.swing.JLabel B9_358O;
    private javax.swing.JLabel B9_383;
    private javax.swing.JLabel B9_384;
    private javax.swing.JPanel BStop358B;
    private javax.swing.JPanel BStop358O;
    private javax.swing.JPanel BStop383;
    private javax.swing.JPanel BStop384;
    private javax.swing.JPanel InsideLoginPanel;
    private javax.swing.JTabbedPane InternalUserTab;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JLabel N10_358B;
    private javax.swing.JLabel N10_358O;
    private javax.swing.JLabel N10_383;
    private javax.swing.JLabel N10_384;
    private javax.swing.JLabel N1_358B;
    private javax.swing.JLabel N1_358O;
    private javax.swing.JLabel N1_383;
    private javax.swing.JLabel N1_384;
    private javax.swing.JLabel N2_358B;
    private javax.swing.JLabel N2_358O;
    private javax.swing.JLabel N2_383;
    private javax.swing.JLabel N2_384;
    private javax.swing.JLabel N3_358B;
    private javax.swing.JLabel N3_358O;
    private javax.swing.JLabel N3_383;
    private javax.swing.JLabel N3_384;
    private javax.swing.JLabel N4_358B;
    private javax.swing.JLabel N4_358O;
    private javax.swing.JLabel N4_383;
    private javax.swing.JLabel N4_384;
    private javax.swing.JLabel N5_358B;
    private javax.swing.JLabel N5_358O;
    private javax.swing.JLabel N5_383;
    private javax.swing.JLabel N5_384;
    private javax.swing.JLabel N6_358B;
    private javax.swing.JLabel N6_358O;
    private javax.swing.JLabel N6_383;
    private javax.swing.JLabel N6_384;
    private javax.swing.JLabel N7_358B;
    private javax.swing.JLabel N7_358O;
    private javax.swing.JLabel N7_383;
    private javax.swing.JLabel N7_384;
    private javax.swing.JLabel N8_358B;
    private javax.swing.JLabel N8_358O;
    private javax.swing.JLabel N8_383;
    private javax.swing.JLabel N8_384;
    private javax.swing.JLabel N9_358B;
    private javax.swing.JLabel N9_358O;
    private javax.swing.JLabel N9_383;
    private javax.swing.JLabel N9_384;
    private javax.swing.JPanel NA358B;
    private javax.swing.JPanel NA358O;
    private javax.swing.JPanel NA383;
    private javax.swing.JPanel NA384;
    private javax.swing.JLabel NT10_358B;
    private javax.swing.JLabel NT10_358O;
    private javax.swing.JLabel NT10_383;
    private javax.swing.JLabel NT10_384;
    private javax.swing.JLabel NT1_358B;
    private javax.swing.JLabel NT1_358O;
    private javax.swing.JLabel NT1_383;
    private javax.swing.JLabel NT1_384;
    private javax.swing.JLabel NT2_358B;
    private javax.swing.JLabel NT2_358O;
    private javax.swing.JLabel NT2_383;
    private javax.swing.JLabel NT2_384;
    private javax.swing.JLabel NT3_358B;
    private javax.swing.JLabel NT3_358O;
    private javax.swing.JLabel NT3_383;
    private javax.swing.JLabel NT3_384;
    private javax.swing.JLabel NT4_358B;
    private javax.swing.JLabel NT4_358O;
    private javax.swing.JLabel NT4_383;
    private javax.swing.JLabel NT4_384;
    private javax.swing.JLabel NT5_358B;
    private javax.swing.JLabel NT5_358O;
    private javax.swing.JLabel NT5_383;
    private javax.swing.JLabel NT5_384;
    private javax.swing.JLabel NT6_358B;
    private javax.swing.JLabel NT6_358O;
    private javax.swing.JLabel NT6_383;
    private javax.swing.JLabel NT6_384;
    private javax.swing.JLabel NT7_358B;
    private javax.swing.JLabel NT7_358O;
    private javax.swing.JLabel NT7_383;
    private javax.swing.JLabel NT7_384;
    private javax.swing.JLabel NT8_358B;
    private javax.swing.JLabel NT8_358O;
    private javax.swing.JLabel NT8_383;
    private javax.swing.JLabel NT8_384;
    private javax.swing.JLabel NT9_358B;
    private javax.swing.JLabel NT9_358O;
    private javax.swing.JLabel NT9_383;
    private javax.swing.JLabel NT9_384;
    private javax.swing.JPanel Note358B;
    private javax.swing.JPanel Note358O;
    private javax.swing.JPanel Note383;
    private javax.swing.JPanel Note384;
    private javax.swing.JLabel S10_358B;
    private javax.swing.JLabel S10_358O;
    private javax.swing.JLabel S10_383;
    private javax.swing.JLabel S10_384;
    private javax.swing.JLabel S1_358B;
    private javax.swing.JLabel S1_358O;
    private javax.swing.JLabel S1_383;
    private javax.swing.JLabel S1_384;
    private javax.swing.JLabel S2_358B;
    private javax.swing.JLabel S2_358O;
    private javax.swing.JLabel S2_383;
    private javax.swing.JLabel S2_384;
    private javax.swing.JLabel S3_358B;
    private javax.swing.JLabel S3_358O;
    private javax.swing.JLabel S3_383;
    private javax.swing.JLabel S3_384;
    private javax.swing.JLabel S4_358B;
    private javax.swing.JLabel S4_358O;
    private javax.swing.JLabel S4_383;
    private javax.swing.JLabel S4_384;
    private javax.swing.JLabel S5_358B;
    private javax.swing.JLabel S5_358O;
    private javax.swing.JLabel S5_383;
    private javax.swing.JLabel S5_384;
    private javax.swing.JLabel S6_358B;
    private javax.swing.JLabel S6_358O;
    private javax.swing.JLabel S6_383;
    private javax.swing.JLabel S6_384;
    private javax.swing.JLabel S7_358B;
    private javax.swing.JLabel S7_358O;
    private javax.swing.JLabel S7_383;
    private javax.swing.JLabel S7_384;
    private javax.swing.JLabel S8_358B;
    private javax.swing.JLabel S8_358O;
    private javax.swing.JLabel S8_383;
    private javax.swing.JLabel S8_384;
    private javax.swing.JLabel S9_358B;
    private javax.swing.JLabel S9_358O;
    private javax.swing.JLabel S9_383;
    private javax.swing.JLabel S9_384;
    private javax.swing.JTabbedPane Status358B;
    private javax.swing.JPanel Status358O;
    private javax.swing.JPanel Status383;
    private javax.swing.JPanel Status384;
    private javax.swing.JLabel clock358B;
    private javax.swing.JLabel clock358O;
    private javax.swing.JLabel clock383;
    private javax.swing.JLabel clock384;
    private javax.swing.JComboBox combobox1;
    private javax.swing.JPanel dateFromPanel;
    private javax.swing.JPanel dateToPanel;
    private javax.swing.JLabel dayLeftLable;
    private javax.swing.JLabel errorLable;
    private javax.swing.JPanel errorPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JTextArea noteMessage;
    private javax.swing.JButton simBtn;
    private javax.swing.JButton startSim;
    private javax.swing.JButton stopSim;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
