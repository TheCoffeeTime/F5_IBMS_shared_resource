package TestClass;


import RosterGenerator.GenerateDuration;
import RosterGenerator.GenerateArrayOfShift;
import RosterGenerator.Duration;
import RosterGenerator.Shift;
import RosterGenerator.AvailableBuses;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Class to test GenerateArrayOfShift
 * So test to see that each shift is under 5 hours
 * @author nathantilsley
 */
public class TestGenerateArrayOfShift {
   
    public static void main(String[] args)
    {
        // generate some days to test
        GregorianCalendar weekdayDate =  new GregorianCalendar(2013, 02, 13);
        GregorianCalendar saturdayDate = new GregorianCalendar(2013, 02, 15);
        GregorianCalendar sundayDate = new GregorianCalendar(2013, 02, 16);
        
        // SO GENERATE DURATIONS FOR EACH A GIVEN DATE AND FOR EACH ROUTE, THE GENERATE AN ARRAY OF SHIFTS FOR EACH CORRESPONDING DURATION
        // WE DO THIS 3 TIMES, ONE FOR EACH ROUTE
        // THEN THE WHOLE PROCESS IN TOTOAL 3 TIMES
        
        ArrayList<Duration> duration358Weekday = GenerateDuration.generateDuration(weekdayDate, 67, 68);
        ArrayList<Duration> duration383Weekday = GenerateDuration.generateDuration(weekdayDate, 65);
        ArrayList<Duration> duration384Weekday = GenerateDuration.generateDuration(weekdayDate, 66);
        
        AvailableBuses buses = new AvailableBuses();
        ArrayList<Shift> shift358Weekday = GenerateArrayOfShift.generateDuration(duration358Weekday, buses, 358);
        ArrayList<Shift> shift383Weekday = GenerateArrayOfShift.generateDuration(duration383Weekday, buses, 383);
        ArrayList<Shift> shift384Weekday = GenerateArrayOfShift.generateDuration(duration384Weekday, buses, 384);
        
        ArrayList<Duration> duration358Saturday = GenerateDuration.generateDuration(saturdayDate, 67, 68);
        ArrayList<Duration> duration383Saturday = GenerateDuration.generateDuration(saturdayDate, 65);
        ArrayList<Duration> duration384Saturday = GenerateDuration.generateDuration(saturdayDate, 66);
        
        buses = new AvailableBuses();
        ArrayList<Shift> shift358Saturday = GenerateArrayOfShift.generateDuration(duration358Saturday, buses, 358);
        ArrayList<Shift> shift383Saturday = GenerateArrayOfShift.generateDuration(duration383Saturday, buses, 383);
        ArrayList<Shift> shift384Saturday = GenerateArrayOfShift.generateDuration(duration384Saturday, buses, 384);
        
        ArrayList<Duration> duration358Sunday = GenerateDuration.generateDuration(sundayDate, 67, 68);
        ArrayList<Duration> duration383Sunday = GenerateDuration.generateDuration(sundayDate, 65);
        ArrayList<Duration> duration384Sunday = GenerateDuration.generateDuration(sundayDate, 66);
        
        buses = new AvailableBuses();
        ArrayList<Shift> shift358Sunday = GenerateArrayOfShift.generateDuration(duration358Sunday, buses, 358);
        ArrayList<Shift> shift383Sunday = GenerateArrayOfShift.generateDuration(duration383Sunday, buses, 383);
        ArrayList<Shift> shift384Sunday = GenerateArrayOfShift.generateDuration(duration384Sunday, buses, 384);
        
        
        // loop though each array list and check that there no shift exceeds 5 hours
        boolean shiftToLong = false;
        for(int i = 0; i < shift358Weekday.size(); i++)
        {
            if(shift358Weekday.get(i).getTimeTo() - shift358Weekday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this weekday fro route 358");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this weekday for route 358");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift358Saturday.size(); i++)
        {
            if(shift358Saturday.get(i).getTimeTo() - shift358Saturday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this saturday for route 358");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this saturday for route 358");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift358Sunday.size(); i++)
        {
            if(shift358Sunday.get(i).getTimeTo() - shift358Sunday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this sunday for route 358");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this sunday for route 358");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift383Weekday.size(); i++)
        {
            if(shift383Weekday.get(i).getTimeTo() - shift383Weekday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this weekday fro route 383");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this weekday for route 383");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift383Saturday.size(); i++)
        {
            if(shift383Saturday.get(i).getTimeTo() - shift383Saturday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this saturday for route 383");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this saturday for route 383");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift383Sunday.size(); i++)
        {
            if(shift383Sunday.get(i).getTimeTo() - shift383Sunday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this sunday for route 383");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this sunday for route 383");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift384Weekday.size(); i++)
        {
            if(shift384Weekday.get(i).getTimeTo() - shift384Weekday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this weekday fro route 384");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this weekday for route 384");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift384Saturday.size(); i++)
        {
            if(shift384Saturday.get(i).getTimeTo() - shift384Saturday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this saturday for route 383");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this saturday for route 383");
        }
        
        shiftToLong = false;
        for(int i = 0; i < shift384Sunday.size(); i++)
        {
            if(shift384Sunday.get(i).getTimeTo() - shift384Sunday.get(i).getTimeFrom() > 300)
            {
                shiftToLong = true;
            }
        }
        
        if(shiftToLong)
        {
            System.out.println("There is one or more shifts in on this date that exceed 5 hours on this sunday for route 383");
        }
        else
        {
            System.out.println("There are no shifts that exceed 5 hours on this sunday for route 383");
        }
        
    }
}
