package TestClass;

import RequestHoliday.Update;
import database.database;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextPane;

/*
 * Third part of the algorithm. 
 * Updates the database with when the driver has sucessfully requested 
 * time off for holiday. All variables are hard coded snubs and will recieve
 * actual values from other classes.
 */

/**
 *
 * @author Anthony
 */
public class AntUpdate 
{
    public static void main(String[] args)
    {
        //boolen value state if holiday request is valid or not
        boolean x = true;
        //number of requested days off
        //Driver ID given when driver logs in to system. 
        int driverID = 2012;
        database.openBusDatabase();
        GregorianCalendar dateTo = new GregorianCalendar(2013, 1,10,0,0,0);
        GregorianCalendar dateFrom = new GregorianCalendar(2013,1,5,0,0,0);
        Update.updateHolidayRequest(dateFrom, dateTo, driverID);
    }
    


      
}

