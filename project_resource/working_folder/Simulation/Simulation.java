/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import java.util.GregorianCalendar;

/**
 *
 * @author nathantilsley
 */
public class Simulation {
    
    private int nextArriveTime; // represented in minutes
    
    private int status; // 0 = ontime 1 = cancelled 2 = delayed
    
    private int currentTime; // represented in minutes
    
    private boolean changed; // 0 = not changed, 1 = changed
    
    private int busStopID;
    
    private int delay;
    
    private int cancel;
    
    private int serviceNumber;
    
    private GregorianCalendar date;
    
    private String message;
    
    private String stopName;
    
    private String time;
    
    public Simulation(int reqBusStopID, int reqNextArriveTime, int reqStatus, int reqCurrentTime, GregorianCalendar reqDate, int reqServiceNumber, String reqStopName)
    {
        busStopID = reqBusStopID;
        nextArriveTime = reqNextArriveTime;
        status = reqStatus;
        currentTime = reqCurrentTime;
        date = reqDate;
        changed = false;
        serviceNumber = reqServiceNumber;
        delay = 0;
        message = "-";
        cancel = 0;  
        stopName = reqStopName;
    }
    
    public int getStatus()
    {
        return status;
    }
    
    public int getCurrentTime()
    {
        return currentTime;
    }
    
    public boolean getChanged()
    {
        return changed;
    }
    
    public int getNextArriveTime()
    {
        return nextArriveTime;
    }
    
    public GregorianCalendar getDate()
    {
        return date;
    }
    
    public int getBusStopID()
    {
        return busStopID;
    }
    
    public int getServiceNumber()
    {
        return serviceNumber;
    }
    
    public String getMessage()
    {
      return message;
    }
    
    public int getDelay()
    {
      return delay;
    }
    
    public int getCancel()
    {
      return cancel;
    }
    
    public String getBusStopName()
    {
      return stopName;
    }
    
    public String getTime()
    {
      return time;
    }
   
    public void setStatus(int newStatus)
    {
        status = newStatus;
    }
    
    public void setNextArriveTime(int newArrivalTime)
    {
        nextArriveTime = newArrivalTime;
    }
    
    public void setCurrentTime(int newCurrentTime)
    {
        currentTime = newCurrentTime;
    }
    
    public void setChanged(boolean newChanged)
    {
        changed = newChanged;
    }
    
    public void setDate(GregorianCalendar newDate)
    {
        date = newDate;
    }
    
    public void setBusStopID(int newBusStopID)
    {
        busStopID = newBusStopID;
    }
    
    public void setServiceNumber(int newServiceNumber)
    {
        serviceNumber = newServiceNumber;
    }
    
    public void setDelay(int newDelay)
    {
      delay = newDelay;
    }
    
    public void setMessage(String newMessage)
    {
      message = newMessage;
    }
    
    public void setCancel(int newCancel)
    {
      cancel = newCancel;
    }
    
    public void setBusStopName(String reqName)
    {
      stopName = reqName;
    }
    
    public void setTime(int reqTime)
    {
      String minutes = getMinutes(reqTime) < 10? "0" + getMinutes(reqTime) : Integer.toString(getMinutes(reqTime));
      String newTime = Integer.toString(getHours(reqTime)) + ":" + (minutes);
      time = newTime;
    }
    
    public int getHours(int time)
    {
        return (int)Math.floor(time / 60);
    }
    
    public int getMinutes(int time)
    {
        return time % 60;
    }
    
    public String getRandomCancelMessage()
    {
      int rand = (int) (2 * Math.random());
      
      String message = "";
      
      switch(rand)
      {
        case 0:
          message = "D1";
          break;
        case 1:
          message = "D2";
          break;
        case 2:
          message = "D3";
          break;
        default: 
          message = "D4";
          break;
      }
      return message;
    }
    
    public String getRandomDelayMessage()
    {
      int rand = (int) (2 * Math.random());
      
      String message = "";
      
      switch(rand)
      {
        case 0:
          message = "D1";
          break;
        case 1:
          message = "D2";
          break;
        case 2:
          message = "D3";
          break;
        default: 
          message = "D4";
          break;
      }
      return message;
    }
}
