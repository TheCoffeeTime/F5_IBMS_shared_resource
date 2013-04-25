package RosterGenerator;


import RosterGenerator.Duration;
import RosterGenerator.Shift;
import RosterGenerator.AvailableBuses;
import database.database;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class GenerateArrayOfShift {
    
    public static ArrayList<Shift> generateDuration(ArrayList<Duration> Durations, AvailableBuses buses, int route)
    {       
        ArrayList<Shift> shiftArray = new ArrayList<Shift>();
        
        int numberOfBuses = 1;
        int nextAvailableBus =0;
                
        // Find required number of buses (maximum bus number in the List of Durations)
        for (int i = 0; i < Durations.size(); i++)
        {
            if (Durations.get(i).getBusNo() > numberOfBuses)
            {
                numberOfBuses = Durations.get(i).getBusNo();
            }
        }
        
        // Variables which store information about shift
        int startTime;
        int endTime;
        
        // Loop through all buses
        for (int j = 1; j <= numberOfBuses; j++)
        {
            // Reset shift information
            startTime = 0;
            endTime = 0;

            // Loop through all durations            
            for (int i = 0; i < Durations.size(); i++)
            {
              // If current duration is current bus duration
              if (Durations.get(i).getBusNo() == j)
              {
                  // If it's first shift duration
                  if (startTime == 0)
                  {
                      // Set start time
                      startTime =  Durations.get(i).getStartTime();
                  }
                  // If we can add current duration to current shift (shift max time = 5h = 300m) 
                  if ((Durations.get(i).getEndTime() - startTime) < 300)                  
                  {
                      // Update end Time
                      endTime = Durations.get(i).getEndTime();
                  }
                  else
                  {
                      // Shift is finished -> create object & store it in the Array
                      boolean availableBuses[] = buses.getBuses();
                      for (int k = 0; k < availableBuses.length; k++)
                      {
                          if (availableBuses[k] == true)
                          {
                              nextAvailableBus = buses.getBusID(k);
                              break;
                          }
                      }
                      //buses.getBuses()
                      Shift tempShift = new Shift(0, nextAvailableBus,
                                                  startTime, endTime, route);
                      shiftArray.add(tempShift);
                      // Set start & end time of the next shift
                      startTime = Durations.get(i).getStartTime();
                      endTime = Durations.get(i).getEndTime();
                  }
              }
              
              // If it is this bus last duration - finish shift
              if ((i == Durations.size() - 1) && endTime != 0)
              {
                      boolean availableBuses[] = buses.getBuses();
                      for (int k = 0; k < availableBuses.length; k++)
                      {
                          if (availableBuses[k] == true)
                          {
                              nextAvailableBus = buses.getBusID(k);
                              break;
                          }
                      }
                      
                      Shift tempShift = new Shift(0, nextAvailableBus,
                                                  startTime, endTime, route);
                      shiftArray.add(tempShift);
              }
            }
            
            boolean availableBuses[] = buses.getBuses();
            for (int k = 0; k < availableBuses.length; k++)
            {
              if (availableBuses[k] == true)
              {
                buses.setBusUnavailable(k);
                break;
              }
            }           
        }
        return shiftArray;
    }
    
    // Test GenerateArrayOfShift
    public static void main(String[] args)
    {
        database.openBusDatabase();
        // To Nathan - there might be a bug in duration calculation in your code,
        // have a look at the last three lines of 358 bus durations output
        // in 2 of them duration is longer than 1400 m.
        
        // Nathan's test code from main in GenerateDuration
        GregorianCalendar date =  new GregorianCalendar(2013, 02, 13);
        ArrayList<Duration> duration358 = GenerateDuration.generateDuration(date, 67, 68);
        ArrayList<Duration> duration383 = GenerateDuration.generateDuration(date, 65);
       
        System.out.println("358 bus durations:");
        
        for(int i = 0; i < duration358.size(); i++)
        {
              System.out.println("Start Time " + duration358.get(i).getHours(duration358.get(i).getStartTime()) + 
                                  duration358.get(i).getMinutes(duration358.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration358.get(i).getHours(duration358.get(i).getEndTime()) + 
                                  duration358.get(i).getMinutes(duration358.get(i).getEndTime()) + " : "
                                 + "Duration " + duration358.get(i).getDuration() + " : " +
                                 "Index "      + duration358.get(i).getIndex() + " : " + 
                                 "Bus No " + duration358.get(i).getBusNo());
   
        }
        
        System.out.println();
        
        // Nikita's test code
        AvailableBuses buses = new AvailableBuses();
        ArrayList<Shift> shiftArray = generateDuration(duration358, buses, 358);
        
        for (int i = 0; i < shiftArray.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shiftArray.get(i).getTimeFrom() + " | " + 
                               "End time: " + shiftArray.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shiftArray.get(i).getDriverID() + " | " +
                               "Bus ID: " + shiftArray.get(i).getBusID() + " | " +
                               "Shift duration: " + (shiftArray.get(i).getTimeTo() - 
                                                     shiftArray.get(i).getTimeFrom()) + " | " +
                               "Route ID: " + shiftArray.get(i).getRouteID());
        }
        
        shiftArray = generateDuration(duration383, buses, 383);
        
        for (int i = 0; i < shiftArray.size(); i++)
        {
            System.out.println("Shift number: " + i + " | " +
                               "Start time: " + shiftArray.get(i).getTimeFrom() + " | " + 
                               "End time: " + shiftArray.get(i).getTimeTo() + " | " +
                               "Driver ID: " + shiftArray.get(i).getDriverID() + " | " +
                               "Bus ID: " + shiftArray.get(i).getBusID() + " | " +
                               "Shift duration: " + (shiftArray.get(i).getTimeTo() - 
                                                     shiftArray.get(i).getTimeFrom()) + " | " +
                               "Route ID: " + shiftArray.get(i).getRouteID());
        }       
        
        
       
    }
}
