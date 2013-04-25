package RosterGenerator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Create duration objects
 * also there is some get and set methods for variable access
 * @author nathantilsley
 */

import java.util.Date;
import java.util.GregorianCalendar;
public class Duration {
    
    private int startTime;
    
    private int endTime;
    
    private int duration;
    
    private int index;
    
    private int busNo;
    
    private String[]
    monthNoValue = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"};
    
    
    // note all time is stored in minutes!
    // so 405 mins for example is 6hrs 45mins
    // so represents time 6:45
    // bus number is a template to uniquely define which services abus takes and
    // should be replaced with a bus ID at some point
    // index represents how many services a bus has done
    public Duration(int requiredStartTime, int requiredEndTime,
                    int requiredBusNo, int requiredIndex)
    {
        startTime = requiredStartTime;
        endTime = requiredEndTime;
        duration = requiredEndTime - requiredStartTime;
        busNo = requiredBusNo;
        index = requiredIndex;
    }
    
    // method returns an easier representation of the time
    public GregorianCalendar convertDuration(int minutes, Date date)
    {
        String dateString = date.toString();
        String dateStringArray[] = dateString.split(" ", 6);
        int newDay = Integer.parseInt(dateStringArray[2]);
        int newYear = Integer.parseInt(dateStringArray[5]);
        newYear -= 1900;
        int newMonth = 0;
        for(int i = 0; i < monthNoValue.length; i++)
        {
          if(monthNoValue[i].equals(dateStringArray[1]))
          {
              newMonth = i;
          }
        }
        int timeHours = (int)Math.floor(minutes / 60);
        int timeMinutes = minutes % 60;
        
        
        return new GregorianCalendar(newYear, newMonth, 
                                     newDay, timeHours, timeMinutes, 0);
        
        
    }
    
    public int getHours(int time)
    {
        return (int)Math.floor(time / 60);
    }
    
    public int getMinutes(int time)
    {
        return time % 60;
    }
    
    public int getStartTime()
    {
        return startTime;
    }
    
    public int getEndTime()
    {
        return endTime;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public int getBusNo()
    {
        return busNo;
    }
    
    public int getDuration()
    {
        return duration;
    }
    
    public void setBusNo(int requiredbusNo)
    {
        busNo = requiredbusNo;
    }
    
    public void setIndex(int requiredIndex)
    {
         index = requiredIndex;
    }
            
        
    
}
