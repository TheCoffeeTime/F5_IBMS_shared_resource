package RosterGenerator;

import java.util.Date;
/*
  An object which is the 'main' part of a roaster object. 
*/
public class Shift
{
  int driverID, busID, timeFrom, timeTo, routeID, duration;
  Date date;
  
  public Shift(int dID, int bID, int TF, int TT, int r) //, int r, Date d
  {
    driverID = dID;
    busID = bID;
    timeFrom = TF;
    timeTo = TT;
    duration = TT-TF;
    routeID = r; // Date & route are used to generete ArrayOfDurations and are not stored in it,
    //date = d;    // I've commented them as I've used only ArrayOfDurations to generate ArrayOfShifts
  }//Shift
  
  public int getRouteID()
  {
    return routeID;
  }//getDriverID
    
  public int getDuration()
  {
      return duration;
  }
  
  public int getDriverID()
  {
    return driverID;
  }//getDriverID
  
  public void setDriverID(int dID)
  {
    driverID = dID;
  }//setDriverID
  
  public int getTimeFrom()
  {
      return timeFrom;
  }
  
  public int getTimeTo()
  {
      return timeTo;
  }
  
  public int getBusID()
  {
      return busID;
  }
}//Shift
