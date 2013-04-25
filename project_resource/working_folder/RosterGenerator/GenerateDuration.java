package RosterGenerator;

import database.TimetableInfo;
import RosterGenerator.Duration;
import database.database;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class will generate an array of durations objects, each array will be relative to a route
 * There is two generate durations methods, one for the circular routes 383 and 384
 * which takes two parameters one for the date and one for the route.
 * There is a method specifically for the 358 routes, which takes 3 parameters
 * the date, and the 358out route, and the 358back route. This will combine the two routes into one duration
 * 
 * @author nathantilsley
 */
public class GenerateDuration {   
    
    // this method creates an array of durations, 
    // see duration class for attributes of object, is used for 383 adn 384 services to return durations
    // also used for 358out and 358back to return durations for them
    public static ArrayList<Duration> generateDuration(GregorianCalendar gregorianDate, int route)
    {
        //database.openBusDatabase();
        Date date = new Date((long)gregorianDate.getTimeInMillis());
        int noOfServices = TimetableInfo.getNumberOfServices(route, date);
        int[] serviceTimes;
        int[] services = TimetableInfo.getServices(route, TimetableInfo.timetableKind(date));
        ArrayList<Duration> durationArray = new ArrayList<Duration>();
        
        // this represents the index of ech bus so how many services a bus has done
        // each position index is accessed usign a bus number
        // so everytime a bus number occurs the value is incremented at the busNo index
        // this is done when making new durations
        ArrayList<Integer> busIndex = new ArrayList<Integer>();
        
        // want to initalize all this to 0 so can access the index
        for(int i = 0; i < 25; i++)
            busIndex.add(0);
        
        // used when incrementing the bus index to temporarily hold the value
        int tempBusIndex;

        int noOfBuses = 0;
        
        // index represents how many services or journeys the bus has made

        int[] serviceTimes2 = TimetableInfo.getServiceTimes(route, TimetableInfo.timetableKind(date), 0);
        
        // cretae a temporary duration to compare with other durations to calculate whether start times over run
        Duration tempDuration = new Duration(serviceTimes2[0], serviceTimes2[serviceTimes2.length - 1], noOfBuses, busIndex.get(noOfBuses));
        for(int i = 0; i < noOfServices; i++)
        {
            serviceTimes = TimetableInfo.getServiceTimes(route, TimetableInfo.timetableKind(date), i);
            Duration tempDuration2 = new Duration(serviceTimes[0], serviceTimes[serviceTimes.length - 1], noOfBuses, busIndex.get(noOfBuses));                    
            
            // when a bus does not start from the bus depot, add how long it would take to reach it starting destination to the duration
            if(serviceTimes.length < 4)
               tempDuration2 = new Duration(serviceTimes[0] - 42, serviceTimes[serviceTimes.length - 1], noOfBuses, busIndex.get(noOfBuses));
            
            // when the end time of a service runs into the start time of another service we need another bus
            if(tempDuration.getEndTime() > tempDuration2.getStartTime())
            {
                noOfBuses++;
                tempBusIndex = busIndex.get(noOfBuses);
                tempBusIndex++;
                busIndex.remove(noOfBuses);
                busIndex.add(noOfBuses, tempBusIndex);
            }
            // otherwise we dont need another bus so reset bus number and change the comparing duration
            else 
            {
                noOfBuses = 1;
                tempBusIndex = busIndex.get(noOfBuses);
                tempBusIndex++;
                busIndex.remove(noOfBuses);
                busIndex.add(noOfBuses, tempBusIndex);
                tempDuration = tempDuration2;
                    
            }
            
            // there is certain cases where the the time for service goes over midnight, the wrapper class sorts these wrong
            // this if statement deals with these exceptions
            if(tempDuration2.getDuration() > 1000)
            {  
                if(i == 19)
                  tempDuration2 = tempDuration2 = new Duration(serviceTimes[serviceTimes.length - 1], serviceTimes[0] + 1478, noOfBuses, busIndex.get(noOfBuses));
                else if(i == 18 || i == 15)
                   tempDuration2 = tempDuration2 = new Duration((serviceTimes[2]), serviceTimes[1] + 1440, noOfBuses, busIndex.get(noOfBuses));
                else if(i == 60 || i == 52 || i == 25)
                   tempDuration2 = tempDuration2 = new Duration((serviceTimes[1]), serviceTimes[0] + 1440, noOfBuses, busIndex.get(noOfBuses));
                else if(i == 16)
                   tempDuration2 = tempDuration2 = new Duration((serviceTimes[serviceTimes.length - 1]), (serviceTimes[serviceTimes.length - 2]) + 1440, noOfBuses, busIndex.get(noOfBuses));
            }
            Duration newDuration = tempDuration2;
            durationArray.add(newDuration);
     
            
        }
        return durationArray;
        
    }
    
    // this method specificaly for 358 service, combines the 358 out journey with the 358 back journey
    // along with correct bus numbers for each journey
    public static ArrayList<Duration> generateDuration(GregorianCalendar date, int route358Out, int route358Back)
    {
        //database.openBusDatabase();
        
        ArrayList<Duration> duration358Out = generateDuration(date, route358Out);
        ArrayList<Duration> duration358Back = generateDuration(date, route358Back);
        
        // this represensts the index of ech bus so how many services a bus has done
        // each position index is accessed usign a bus number
        // so everytime a bus number occurs the value is incremented at the busNo index
        // this is done when making new durations
        ArrayList<Integer> busIndex = new ArrayList<Integer>();
        
        // want to initalize all this to 0 so can access the index
        for(int i = 0; i < 25; i++)
            busIndex.add(0);
        

        ArrayList<Duration> durationArray = new ArrayList<Duration>();
        
        int noOfBuses = 0;
        
        // used when incrementing the bus index to temporarily hold the value
        int tempBusIndex;
        
        for(int i = 0; i < duration358Out.size(); i++)
        {      
            durationArray.add(duration358Out.get(i));
            if(i < 19)
            durationArray.add(duration358Back.get(i));
             
        }
        
        Duration tempDuration = durationArray.get(0);
        
        // same principle as in other generateDuration method,
        // when endtime of service over laps we need more buses
        for(int i = 0; i < durationArray.size(); i++)
        {
            Duration tempDuration2 = durationArray.get(i);
            
            if(tempDuration.getEndTime() > tempDuration2.getStartTime())
            {
                 noOfBuses++;
                 tempBusIndex = busIndex.get(noOfBuses);
                 tempBusIndex++;
                 busIndex.remove(noOfBuses);
                 busIndex.add(noOfBuses, tempBusIndex);
            }
            else 
            {
                noOfBuses = 1;
                int b = busIndex.get(noOfBuses);
                b++;
                busIndex.remove(noOfBuses);
                busIndex.add(noOfBuses, b);
                tempDuration = durationArray.get(i);
                    
            }
            durationArray.get(i).setIndex(busIndex.get(noOfBuses));
            durationArray.get(i).setBusNo(noOfBuses);
        }
           return durationArray; 
       }
        
    
    
     public static void main(String[] args)
     {
        database.openBusDatabase();
        GregorianCalendar date =  new GregorianCalendar(2013, 02, 15);
        ArrayList<Duration> duration358 = generateDuration(date, 67, 68);
        ArrayList<Duration> duration383 = generateDuration(date, 65);
        ArrayList<Duration> duration384 = generateDuration(date, 66);
       
        System.out.println("358 bus durations:");
        
        for(int i = 0; i < duration358.size(); i++)
        {
              System.out.println("Start Time " + 
                                  duration358.get(i).getHours(duration358.get(i).getStartTime())  + 
                                  duration358.get(i).getMinutes(duration358.get(i).getStartTime()) + " : " + " End Time " +
                                 + duration358.get(i).getHours(duration358.get(i).getEndTime()) + 
                                  duration358.get(i).getMinutes(duration358.get(i).getEndTime()) + " : "
                                 + "Duration " + duration358.get(i).getDuration() + " : " +
                                 "Index "      + duration358.get(i).getIndex() + " : " + 
                                 "Bus No " + duration358.get(i).getBusNo());
   
        }
        
        System.out.println();
        System.out.println("383 bus durations:");
        
        for(int i = 0; i < duration383.size(); i++)
        {
              System.out.println("Start Time " + duration383.get(i).getHours(duration383.get(i).getStartTime()) + 
                                  duration383.get(i).getMinutes(duration383.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration383.get(i).getHours(duration383.get(i).getEndTime()) + 
                                  duration383.get(i).getMinutes(duration383.get(i).getEndTime()) + " : "
                                 + "Duration " + duration383.get(i).getDuration() + " : " +
                                 "Index "      + duration383.get(i).getIndex() + " : " + 
                                 "Bus No " + duration383.get(i).getBusNo());
   
        }
        
        System.out.println();
        System.out.println("384 bus durations:");
        
        for(int i = 0; i < duration384.size(); i++)
        {
              System.out.println("Start Time " + duration384.get(i).getHours(duration384.get(i).getStartTime()) + 
                                  duration384.get(i).getMinutes(duration384.get(i).getStartTime()) +" : " + "End Time " +
                                 + duration384.get(i).getHours(duration384.get(i).getEndTime()) + 
                                  duration384.get(i).getMinutes(duration384.get(i).getEndTime()) + " : "
                                 + "Duration " + duration384.get(i).getDuration() + " : " +
                                 "Index "      + duration384.get(i).getIndex() + " : " + 
                                 "Bus No " + duration384.get(i).getBusNo());
   
        }
    }
}
