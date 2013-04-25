package TestClass;


import RosterGenerator.GenerateDuration;
import RosterGenerator.Duration;
import database.database;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Q'ketske
 */
public class TestGenerateDuration {
    
    public static void main(String[] args)
    {
      database.openBusDatabase();
      GregorianCalendar dateWeek =  new GregorianCalendar(2013, 02, 13);
      GregorianCalendar dateSat =  new GregorianCalendar(2013, 02, 15);
      GregorianCalendar dateSun =  new GregorianCalendar(2013, 02, 16);
      
      ArrayList<Duration> duration358Week = GenerateDuration.generateDuration(dateWeek, 67, 68);
      ArrayList<Duration> duration383Week = GenerateDuration.generateDuration(dateWeek, 65);
      ArrayList<Duration> duration384Week = GenerateDuration.generateDuration(dateWeek, 66);
      
      ArrayList<Duration> duration358Sat = GenerateDuration.generateDuration(dateSat, 67, 68);
      ArrayList<Duration> duration383Sat = GenerateDuration.generateDuration(dateSat, 65);
      ArrayList<Duration> duration384Sat = GenerateDuration.generateDuration(dateSat, 66);
      
      ArrayList<Duration> duration358Sun = GenerateDuration.generateDuration(dateSun, 67, 68);
      ArrayList<Duration> duration383Sun = GenerateDuration.generateDuration(dateSun, 65);
      ArrayList<Duration> duration384Sun = GenerateDuration.generateDuration(dateSun, 66);
      
      int totalDurationLenght = 0;
      int minDuration = 99999;
      int maxDuration = 0;
        System.out.println("358 bus weekday durations:");
        
        for(int i = 0; i < duration358Week.size(); i++)
        {
              System.out.println("Start Time " + duration358Week.get(i).getHours(duration358Week.get(i).getStartTime()) + ":" +
                                                 duration358Week.get(i).getMinutes(duration358Week.get(i).getStartTime()) +
                                 " | End Time "+ duration358Week.get(i).getHours(duration358Week.get(i).getEndTime()) + ":" +
                                                 duration358Week.get(i).getMinutes(duration358Week.get(i).getEndTime()) +
                                 " | Duration "+ duration358Week.get(i).getDuration() + 
                                 " | Index "   + duration358Week.get(i).getIndex() +
                                 " | Bus No "  + duration358Week.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration358Week.get(i).getDuration();
              if (duration358Week.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration358Week.get(i).getDuration();
              }
              
              if (duration358Week.get(i).getDuration() < minDuration)
              {
                  minDuration = duration358Week.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration358Week.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("383 bus weekday durations:");
        
        for(int i = 0; i < duration383Week.size(); i++)
        {
              System.out.println("Start Time " + duration383Week.get(i).getHours(duration383Week.get(i).getStartTime()) + ":" +
                                                 duration383Week.get(i).getMinutes(duration383Week.get(i).getStartTime()) +
                                 " | End Time "+ duration383Week.get(i).getHours(duration383Week.get(i).getEndTime()) + ":" +
                                                 duration383Week.get(i).getMinutes(duration383Week.get(i).getEndTime()) +
                                 " | Duration "+ duration383Week.get(i).getDuration() + 
                                 " | Index "   + duration383Week.get(i).getIndex() +
                                 " | Bus No "  + duration383Week.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration383Week.get(i).getDuration();
              if (duration383Week.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration383Week.get(i).getDuration();
              }
              
              if (duration383Week.get(i).getDuration() < minDuration)
              {
                  minDuration = duration383Week.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration383Week.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("384 bus weekday durations:");
        
        for(int i = 0; i < duration384Week.size(); i++)
        {
              System.out.println("Start Time " + duration384Week.get(i).getHours(duration384Week.get(i).getStartTime()) + ":" +
                                                 duration384Week.get(i).getMinutes(duration384Week.get(i).getStartTime()) +
                                 " | End Time "+ duration384Week.get(i).getHours(duration384Week.get(i).getEndTime()) + ":" +
                                                 duration384Week.get(i).getMinutes(duration384Week.get(i).getEndTime()) +
                                 " | Duration "+ duration384Week.get(i).getDuration() + 
                                 " | Index "   + duration384Week.get(i).getIndex() +
                                 " | Bus No "  + duration384Week.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration384Week.get(i).getDuration();
              if (duration384Week.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration384Week.get(i).getDuration();
              }
              
              if (duration384Week.get(i).getDuration() < minDuration)
              {
                  minDuration = duration384Week.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration384Week.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        
        

        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("358 bus Saturday durations:");
        
        for(int i = 0; i < duration358Sat.size(); i++)
        {
              System.out.println("Start Time " + duration358Sat.get(i).getHours(duration358Sat.get(i).getStartTime()) + ":" +
                                                 duration358Sat.get(i).getMinutes(duration358Sat.get(i).getStartTime()) +
                                 " | End Time "+ duration358Sat.get(i).getHours(duration358Sat.get(i).getEndTime()) + ":" +
                                                 duration358Sat.get(i).getMinutes(duration358Sat.get(i).getEndTime()) +
                                 " | Duration "+ duration358Sat.get(i).getDuration() + 
                                 " | Index "   + duration358Sat.get(i).getIndex() +
                                 " | Bus No "  + duration358Sat.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration358Sat.get(i).getDuration();
              if (duration358Sat.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration358Sat.get(i).getDuration();
              }
              
              if (duration358Sat.get(i).getDuration() < minDuration)
              {
                  minDuration = duration358Sat.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration358Sat.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("383 bus Saturday durations:");
        
        for(int i = 0; i < duration383Sat.size(); i++)
        {
              System.out.println("Start Time " + duration383Sat.get(i).getHours(duration383Sat.get(i).getStartTime()) + ":" +
                                                 duration383Sat.get(i).getMinutes(duration383Sat.get(i).getStartTime()) +
                                 " | End Time "+ duration383Sat.get(i).getHours(duration383Sat.get(i).getEndTime()) + ":" +
                                                 duration383Sat.get(i).getMinutes(duration383Sat.get(i).getEndTime()) +
                                 " | Duration "+ duration383Sat.get(i).getDuration() + 
                                 " | Index "   + duration383Sat.get(i).getIndex() +
                                 " | Bus No "  + duration383Sat.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration383Sat.get(i).getDuration();
              if (duration383Sat.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration383Sat.get(i).getDuration();
              }
              
              if (duration383Sat.get(i).getDuration() < minDuration)
              {
                  minDuration = duration383Sat.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration383Sat.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("384 bus Saturday durations:");
        
        for(int i = 0; i < duration384Sat.size(); i++)
        {
              System.out.println("Start Time " + duration384Sat.get(i).getHours(duration384Sat.get(i).getStartTime()) + ":" +
                                                 duration384Sat.get(i).getMinutes(duration384Sat.get(i).getStartTime()) +
                                 " | End Time "+ duration384Sat.get(i).getHours(duration384Sat.get(i).getEndTime()) + ":" +
                                                 duration384Sat.get(i).getMinutes(duration384Sat.get(i).getEndTime()) +
                                 " | Duration "+ duration384Sat.get(i).getDuration() + 
                                 " | Index "   + duration384Sat.get(i).getIndex() +
                                 " | Bus No "  + duration384Sat.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration384Sat.get(i).getDuration();
              if (duration384Sat.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration384Sat.get(i).getDuration();
              }
              
              if (duration384Sat.get(i).getDuration() < minDuration)
              {
                  minDuration = duration384Sat.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration384Sat.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("358 bus Sunday durations:");
        
        for(int i = 0; i < duration358Sun.size(); i++)
        {
              System.out.println("Start Time " + duration358Sun.get(i).getHours(duration358Sun.get(i).getStartTime()) + ":" +
                                                 duration358Sun.get(i).getMinutes(duration358Sun.get(i).getStartTime()) +
                                 " | End Time "+ duration358Sun.get(i).getHours(duration358Sun.get(i).getEndTime()) + ":" +
                                                 duration358Sun.get(i).getMinutes(duration358Sun.get(i).getEndTime()) +
                                 " | Duration "+ duration358Sun.get(i).getDuration() + 
                                 " | Index "   + duration358Sun.get(i).getIndex() +
                                 " | Bus No "  + duration358Sun.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration358Sun.get(i).getDuration();
              if (duration358Sun.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration358Sun.get(i).getDuration();
              }
              
              if (duration358Sun.get(i).getDuration() < minDuration)
              {
                  minDuration = duration358Sun.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration358Sun.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("383 bus Sunday durations:");
        
        for(int i = 0; i < duration383Sun.size(); i++)
        {
              System.out.println("Start Time " + duration383Sun.get(i).getHours(duration383Sun.get(i).getStartTime()) + ":" +
                                                 duration383Sun.get(i).getMinutes(duration383Sun.get(i).getStartTime()) +
                                 " | End Time "+ duration383Sun.get(i).getHours(duration383Sun.get(i).getEndTime()) + ":" +
                                                 duration383Sun.get(i).getMinutes(duration383Sun.get(i).getEndTime()) +
                                 " | Duration "+ duration383Sun.get(i).getDuration() + 
                                 " | Index "   + duration383Sun.get(i).getIndex() +
                                 " | Bus No "  + duration383Sun.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration383Sun.get(i).getDuration();
              if (duration383Sun.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration383Sun.get(i).getDuration();
              }
              
              if (duration383Sun.get(i).getDuration() < minDuration)
              {
                  minDuration = duration383Sun.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration383Sun.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
        
        
        totalDurationLenght = 0;
        minDuration = 99999;
        maxDuration = 0;
        System.out.println("384 bus Sunday durations:");
        
        for(int i = 0; i < duration384Sun.size(); i++)
        {
              System.out.println("Start Time " + duration384Sun.get(i).getHours(duration384Sun.get(i).getStartTime()) + ":" +
                                                 duration384Sun.get(i).getMinutes(duration384Sun.get(i).getStartTime()) +
                                 " | End Time "+ duration384Sun.get(i).getHours(duration384Sun.get(i).getEndTime()) + ":" +
                                                 duration384Sun.get(i).getMinutes(duration384Sun.get(i).getEndTime()) +
                                 " | Duration "+ duration384Sun.get(i).getDuration() + 
                                 " | Index "   + duration384Sun.get(i).getIndex() +
                                 " | Bus No "  + duration384Sun.get(i).getBusNo());
              totalDurationLenght = totalDurationLenght + duration384Sun.get(i).getDuration();
              if (duration384Sun.get(i).getDuration() > maxDuration)
              {
                 maxDuration = duration384Sun.get(i).getDuration();
              }
              
              if (duration384Sun.get(i).getDuration() < minDuration)
              {
                  minDuration = duration384Sun.get(i).getDuration();
              }
        }
        System.out.println("Total Duration = " + totalDurationLenght);
        System.out.println("Average Duration = " + totalDurationLenght / duration384Sun.size());
        System.out.println("Longest Duration = " + maxDuration);
        System.out.println("Shortest Duration = " + minDuration);
    }
}