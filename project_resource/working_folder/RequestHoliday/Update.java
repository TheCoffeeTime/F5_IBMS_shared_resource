package RequestHoliday;

import RequestHoliday.ValidateHolidayRequest;
import globalMessage.SystemMsg;
import database.DriverInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTextPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony 21/02/13
 */
public class Update {
     
    //By Anthony 21/02/13
    public static SystemMsg systemMsg 
                  = new SystemMsg();
    
    public static void updateHolidayRequest
            (GregorianCalendar dateFrom, GregorianCalendar dateTo, int driverID)
    {
        int holidayLength = 
            ValidateHolidayRequest.calculateInterval(dateFrom, dateTo);
        int oldLength = DriverInfo.getHolidaysTaken(driverID);
        System.out.println("Holiday length update:" + holidayLength+oldLength);
        DriverInfo.setHolidaysTaken(driverID, holidayLength+oldLength);
        
        GregorianCalendar currentCal = new GregorianCalendar
            (dateFrom.YEAR, dateFrom.MONTH, 
             dateFrom.DATE, 0, 0, 0);
         
        GregorianCalendar currentDate = new GregorianCalendar(dateFrom.YEAR, dateFrom.MONTH,
                                    dateFrom.DATE, 0, 0, 0);
 
        do
        {
            Date copyOfCurrentDate = new Date(currentDate.getTimeInMillis());
            //System.out.println("Current date:" + currentDate.getDate());
            DriverInfo.setAvailable(driverID, copyOfCurrentDate, false);
            currentCal.add(Calendar.DATE, 1);
            currentDate.set(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH), currentCal.get(Calendar.DATE));
            //System.out.println("UPDATED!");
            

        }while(!currentDate.after(dateTo));
        systemMsg .message =("Your vacation was successfully registred in our system."
                + " Our company wishes you a nice holiday time.");
        // Print message in interface text field
    }//UpdateHolidayRequest
   
}
