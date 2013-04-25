package RosterGenerator;

/**
 *
 * @author Thanakorn, nathantilsley, Nikita, Henryka
 */

import RequestHoliday.ValidateHolidayRequest;
import globalMessage.SystemMsg;
import RosterGenerator.AvailableBuses;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * A Roaster generator class used to create a roaster object. This is the
 * main class of generator a roaster call many other classes including
 * GenerateDuration, GenerateArrayOfShit, and DriverPrioritising. 
 * @author Thanakorn
 */
public class RoasterGenerator
{
  public static SystemMsg systemMsg = new SystemMsg();
  public static Roaster roster;
  
   
  /*
    A method that used to generate a roaster given DateFrom and DateTo. 
    A Roaster Object will be created from the dateFrom and dateTo given
  */ 
  public static void GenerateRoaster(GregorianCalendar dateFrom, GregorianCalendar dateTo)
  {
    int interval = ValidateHolidayRequest.calculateInterval(dateFrom, dateTo);
    ArrayList<ArrayList<Shift>> shift = new ArrayList<ArrayList<Shift>>(7);
    
    //Use calendar to increase the date
    GregorianCalendar currentCal = new GregorianCalendar
      (dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), 
      dateFrom.get(Calendar.DATE), 0, 0, 0);    
    
    //A date object to be used in the for loop below. It get the value from 
    //the calendar. 
    GregorianCalendar currentDate = new GregorianCalendar(dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), 
                                dateFrom.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
       
    for(int i = 0; i < interval; i++)
    {
      System.out.println("--------------------------------------------------");
      System.out.println("Generating a roster for day " + (i+1));
      shift.add(GenerateADayRoaster(currentDate));
      currentCal.add(Calendar.DATE, 1);
      currentDate.set(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH), currentCal.get(Calendar.DATE));
    }//for
    
    //Create a roaster object. 
    roster = new Roaster
            (dateFrom.get(Calendar.MONTH), dateFrom.get(Calendar.YEAR), 
            shift, dateFrom, dateTo);
    
    RoasterFiles.saveFile("roaster", roster);
    
    
  }//GenerateRoaster
  
  /*
    A method that generate an array of shifts. This represents a roaster for 
    one day. 
  */
  public static ArrayList<Shift> GenerateADayRoaster(GregorianCalendar date)
  {
    System.out.println("Creating array of durations...");
    ArrayList<Duration> duration358Weekday = GenerateDuration.generateDuration(date, 67, 68);
    ArrayList<Duration> duration383Weekday = GenerateDuration.generateDuration(date, 65);
    ArrayList<Duration> duration384Weekday = GenerateDuration.generateDuration(date, 66);
    
    AvailableBuses buses = new AvailableBuses();
    System.out.println("Creating array of shift...");
    ArrayList<Shift> shift358 = 
            GenerateArrayOfShift.generateDuration(duration358Weekday, buses, 358);
    ArrayList<Shift> shift383 = 
            GenerateArrayOfShift.generateDuration(duration383Weekday, buses, 383);
    ArrayList<Shift> shift384 = 
            GenerateArrayOfShift.generateDuration(duration384Weekday, buses, 384);
    
    System.out.println("Combining array of shift into one...");
    shift358.addAll(shift383);
    shift358.addAll(shift384);
    
    System.out.println("Assigning drivers to shifts...");
    AssignDriverToShift(shift358, date);
   
    //Combine all the shift for different route into one. 
    return shift358;
  }//GenerateADayRoaster
  
  
  /*A Method to assigne drivers to shifts.
   * @author Henryka Reszka
   */
  public static ArrayList<Shift> AssignDriverToShift(ArrayList<Shift> driverShift, GregorianCalendar date)
  {
    AssignDummyDriverToShift(driverShift);
    //create an array of drivers  
    int[] drivers = new int[70];
    int noOfDriver=0; 
    
    //in this loop going through driverShift array to
    //calculate the numbers of drivers needed for a given date
    for (int i=0; i<driverShift.size(); i++)
    {
       if (drivers[driverShift.get(i).getDriverID()]==0);
       {
          drivers[driverShift.get(i).getDriverID()]++;
          noOfDriver++;
       } 
    }  
    
    //get drivers'ID
    System.out.println(systemMsg.message);
    ArrayList<Integer> driverIDs = DriverPrioritising.getDrivers(date, noOfDriver);
    
    //assign drivers'ID to a driverShift
    for (int i=0; i<driverShift.size(); i++)
    {
        driverShift.get(i).setDriverID(driverIDs.get(driverShift.get(i).getDriverID()));
    }//for
    
    return driverShift;       
  } //AssignDriverToShift  
  
  /* Assign dummy driver to a bus to be used in the AssignDriverToShift method
   * This create an array of workingDriver(so far) and keep extending it.
   * @author Henryka Reszka
   */
  public static void AssignDummyDriverToShift(ArrayList<Shift> driverShift)
  {
      ArrayList<WorkingDriver> workingDriver = new ArrayList<WorkingDriver>();
      //For each shift, assign a driver to it. 
      int driverFakeID = 0;
      System.out.println("Total number of shifts = " + driverShift.size());
      for(int i = 0; i < driverShift.size(); i++)
      {
          boolean shiftAssign = false;
          //For each driver in the workingDriver array
          for(int j = 0; j < workingDriver.size(); j++)
          {
            //If driver can still work (< 10 hours) & have a break
            if(workingDriver.get(j).totalHr + driverShift.get(i).getDuration() <= 600
               && driverShift.get(i).timeFrom - workingDriver.get(j).currentEndTime >= 60)
            {
                shiftAssign = true; //So that no new driver needed. 
                //Re-calculate the total number of hours work so far by this driver
                workingDriver.get(j).totalHr += driverShift.get(i).getDuration();
                //Re-calculate the currentEndTime of the selected working driver
                workingDriver.get(j).currentEndTime = driverShift.get(i).getTimeTo();
                //Set that driver shift to contain a fake driver id
                driverShift.get(i).setDriverID(workingDriver.get(j).id);
                break; //Break out if found a driver for this shift. 
            }//if
          }//for
          //If shift not assigned. If no driver assigned from the above for loop.
          if(!shiftAssign)
          {
              //Create a new working driver
              WorkingDriver newDriver = new WorkingDriver();
              //Initialise the new working driverID, totalHR, and currentEndTime
              newDriver.id = driverFakeID;
              newDriver.totalHr = driverShift.get(i).getDuration();
              newDriver.currentEndTime = driverShift.get(i).getTimeTo();
              //Set that driver shift to contain a fake driver id
              driverShift.get(i).setDriverID(newDriver.id);
              driverFakeID++;
              //Add the newly created workingDriver to the workingDriver array. 
              workingDriver.add(newDriver);
          }//if
      }//for
      systemMsg.message = "Number of driver = " + (driverFakeID+1);
  }
  
    public static String[][] dayGUI(int dayID, Roaster roster)
    {

        String[][] rosterGUI = new String[3][21];
        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 3; j++)
            {   
                rosterGUI[j][i] = "";
            }//for
        }
        GregorianCalendar dateFrom = roster.dateFrom;
        
        //Create a new calendar for current date
        GregorianCalendar currentCal = new GregorianCalendar
             (dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), 
              dateFrom.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

        int dayIncreasedBy = 0;
        GregorianCalendar requiredDate = new GregorianCalendar
                                 (dateFrom.get(Calendar.YEAR), dateFrom.get(Calendar.MONTH), 
              dateFrom.get(Calendar.DAY_OF_MONTH));

        while(true) 
        { 
            if(currentCal.get(GregorianCalendar.DAY_OF_WEEK) != dayID)
            {
                currentCal.add(Calendar.DATE, 1);
                dayIncreasedBy++;
            }//if
            else
                break;
        }//while
        requiredDate.set(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH),currentCal.get(Calendar.DATE));
        System.out.println("Match date = " + requiredDate.get(Calendar.DATE) + "/" + requiredDate.get(Calendar.MONTH) + "/" + requiredDate.get(Calendar.YEAR));

        //For day. 
        int starting = 300; //Start at 5:00 am -> 300/60 = 5:00
        int minsInHr = 60;
        System.out.println(roster.shift.get(dayIncreasedBy).size());
        for(int i = 0; i < roster.shift.get(dayIncreasedBy).size(); i++)
        {    
            int service = 0;
            switch (roster.shift.get(dayIncreasedBy).get(i).getRouteID())
            {
                  case 358: service = 0;
                            break;
                  case 383: service = 1;
                            break;
                  case 384: service = 2;
                            break;
            }
            int index = 1;
            Shift currentShift = roster.shift.get(dayIncreasedBy).get(i);
            index = ((currentShift.getTimeFrom() - starting) / 60) +1;
            do
            {
              rosterGUI[service][index] += 
                      ("DriverID = " + currentShift.getDriverID() 
                      + ": BusID = " + currentShift.getBusID() + "\n");
              index++;
            }while(index * minsInHr < currentShift.getTimeTo() - starting);
        }//for
    
    return rosterGUI;
    }//dayGUI
}//class
