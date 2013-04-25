package TestClass;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathantilsley
 */
import RosterGenerator.DriverPrioritising;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestPrioritiesGroup {
    
    public static void main(String[] args)
    {
        // get an array for rach day
        int[] mondayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 10));
        int[] tuesdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 12));
        int[] wednesdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 12));
        int[] thursdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 13));
        int[] fridayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 14));
        int[] saturdayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 15));
        int[] sundayGroupArray = DriverPrioritising.getGroupsInAWeekday(new GregorianCalendar(2013, 02, 16));
        
        System.out.println("Now check that there is 5 groups per day");
        testGroupSize(mondayGroupArray);
        testGroupSize(tuesdayGroupArray);
        testGroupSize(wednesdayGroupArray);
        testGroupSize(thursdayGroupArray);
        testGroupSize(fridayGroupArray);
        testGroupSize(saturdayGroupArray);
        testGroupSize(sundayGroupArray);
        
        //Some test numbers
        int numberOfDriversPerGroupTest1 = 1;
        int numberOfDriversPerGroupTest2 = 2;
        int numberOfDriversPerGroupTest3 = 3;
        int numberOfDriversPerGroupTest4 = 4;
        int numberOfDriversPerGroupTest5 = 5;
        int numberOfDriversPerGroupTest6 = 6;
        int numberOfDriversPerGroupTest7 = 7;
        
        // get every group and all the drivers in that group
        ArrayList<ArrayList<Integer>> groups = DriverPrioritising.groupDrivers();
        
        // print off every driver in their corresponding group
        for(int i = 0; i < groups.size(); i++)
        {
            ArrayList<Integer> groupRow = groups.get(i);
            if(groupRow.size() == 10)
                System.out.println("The size of group " + (i + 1) + " is as expected");
            else
                System.out.println("The size of group " + (i + 1) + " is not as expected");
       
        }
        
        
        // make arraylist of all drivers working in on a day, after they ahve been prioritised
        // so only drivers who have worked the least horus should be working
        ArrayList<Integer> group1 = DriverPrioritising.prioritiseDriversInGroup(groups.get(0), numberOfDriversPerGroupTest1, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group2 = DriverPrioritising.prioritiseDriversInGroup(groups.get(1), numberOfDriversPerGroupTest2, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group3 = DriverPrioritising.prioritiseDriversInGroup(groups.get(2), numberOfDriversPerGroupTest3, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group4 = DriverPrioritising.prioritiseDriversInGroup(groups.get(3), numberOfDriversPerGroupTest4, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group5 = DriverPrioritising.prioritiseDriversInGroup(groups.get(4), numberOfDriversPerGroupTest5, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group6 = DriverPrioritising.prioritiseDriversInGroup(groups.get(5), numberOfDriversPerGroupTest6, new GregorianCalendar(2013, 02, 10));
        ArrayList<Integer> group7 = DriverPrioritising.prioritiseDriversInGroup(groups.get(6), numberOfDriversPerGroupTest7, new GregorianCalendar(2013, 02, 10));
        
        
        // print off all the drivers that are working in each group
        
        groupNumber = 1;
        
        System.out.print("check that the number of drivers working in that group");
        System.out.println(" corresponds to the number of drivers that should ");       
        
        testWorkingDrivers(group1, numberOfDriversPerGroupTest1);
        testWorkingDrivers(group2, numberOfDriversPerGroupTest2);
        testWorkingDrivers(group3, numberOfDriversPerGroupTest3);
        testWorkingDrivers(group4, numberOfDriversPerGroupTest4);
        testWorkingDrivers(group5, numberOfDriversPerGroupTest5);
        testWorkingDrivers(group6, numberOfDriversPerGroupTest6);
        testWorkingDrivers(group7, numberOfDriversPerGroupTest7);
    }
    
    private static int groupNumber = 1;
    public static void testGroupSize(int[] group)
    {
        if(group.length == 5)
            System.out.println("There are 5 drivers in working on day " + groupNumber);
        else
            System.out.println("There are more than 5 drivers working on day " + groupNumber);
        
        groupNumber++;
    }
    
    public static void testWorkingDrivers(ArrayList<Integer> group, int numberOfDriversWorking)
    {
       if(group.size() == numberOfDriversWorking) 
           System.out.println("The number of drivers working on a day is as expected for group "+ groupNumber);
       else
           System.out.println("The number of drivers working on a day is as expected for group "+ groupNumber);  
       
       groupNumber++;
    }
    
}
