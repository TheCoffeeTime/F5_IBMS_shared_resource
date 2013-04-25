package RequestHoliday;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
public class Dates
{  
    
  private static String[]
    monthNoValue = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
  
  public static DateTime dateToDateTime(Date date)
  {
      String dateString = date.toString();
      System.out.println(dateString);
      String dateStringArray[] = dateString.split(" ", 6);
      System.out.println(dateStringArray[1] + dateStringArray[2] + dateStringArray[5]);
      int newMonth = 0;
      for(int i = 0; i < monthNoValue.length; i++)
      {
          if(monthNoValue[i].equals(dateStringArray[1]))
              newMonth = i + 1;
      }
      int newDay = Integer.parseInt(dateStringArray[2]);
      int newYear = Integer.parseInt(dateStringArray[5]);
      newYear -= 1900;
      return new DateTime(newYear, newMonth, newDay, 0, 0);
  }
  
  public static int calculateInterval(Date dateFrom, Date dateTo)
  {
      if(dateFrom.equals(dateTo))
          return 1;
      
      DateTime dateTimeFrom = dateToDateTime(dateFrom);
      DateTime dateTimeTo = dateToDateTime(dateTo);
      // return days between + 1 becasue want to include the dateFrom and dateTo
      // in the interval
      return Days.daysBetween(dateTimeFrom, dateTimeTo).getDays() + 1;
      
  }

} // class Dates