package TestClass;

import RequestHoliday.ValidateHolidayRequest;
import database.database;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class OaksCalendarTest
{                 
  public static void main(String[] args)
  {
    //GregorianCalendar currentCal 
      //= new GregorianCalendar(2012, 10, 30, 0, 0, 0);
    
    //Test. From 30 Nov to 1st Dec
    //System.out.println(currentCal.get(Calendar.MONTH));
    //currentCal.add(Calendar.DATE, 1);
    //System.out.println(currentCal.get(Calendar.MONTH));
    GregorianCalendar a = new GregorianCalendar(2012, 11, 30, 0, 0, 0);
    GregorianCalendar b = new GregorianCalendar(2013, 0, 10, 0, 0, 0);
    database.openBusDatabase();
    ValidateHolidayRequest.validateRequest(a, b, 2013);
    //System.out.println(ValidateRequest.HREM.dateExceptionMsg);
  }//main
}//class
