package TestClass;


import RosterGenerator.RoasterGenerator;
import database.database;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanakorn
 */
public class TestRoasterGenerator {
    public static void main(String[] args)
    {
        database.openBusDatabase();
        GregorianCalendar dStart = new GregorianCalendar(2013, 2, 21, 0, 0, 0);
        GregorianCalendar dEnd = new GregorianCalendar(2013, 2, 27, 0, 0, 0);      
        RoasterGenerator.GenerateRoaster(dStart, dEnd);
        /*
        for(int i = 0; i < r.shift.get(0).size(); i++)
        {
            System.out.println("---------------------------------------------");
            System.out.println("Driver ID = " + r.shift.get(0).get(i).getDriverID());
            System.out.println("Bus ID = " + r.shift.get(0).get(i).getBusID());
            System.out.println("From = " + r.shift.get(0).get(i).getTimeFrom() + 
                               "To " + r.shift.get(0).get(i).getTimeTo());
        }//for
        System.out.println(RoasterGenerator.systemMsg.message);
        */
    }//main
}
