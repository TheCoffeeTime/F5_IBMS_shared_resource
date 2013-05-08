package RosterGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathantilsley
 */
public class RoasterFiles {
    
  private enum roasterVariables 
  {
      month, year, shift, Next_Day, Endshift, dateFrom, dateTo;
  }
    
  public static Roaster readFile(String fileName)
  {
    BufferedReader input = null;
    Roaster roaster = null;
    try
    {
      input = new BufferedReader(new FileReader(fileName));
   
      // input line for the string of text
      String inputLine;  
      
      int month = 0;
      int year = 0;
      ArrayList<ArrayList<Shift>> shiftsInAWeek = new ArrayList<ArrayList<Shift>>();
      GregorianCalendar dateFrom = null;
      GregorianCalendar dateTo = null;
         
      while((inputLine = input.readLine()) != null)
      {
        // Divide the line into fields using tab as a delimiter.
        String[] fields = inputLine.split(" ");
        System.out.println(fields[0]);
        switch(roasterVariables.valueOf(fields[0]))
        {
            
            case month : 
                month = Integer.parseInt(input.readLine());
                System.out.println("month");
                break;
            case year:
                year = Integer.parseInt(input.readLine());
                System.out.println("year");
                break;
            case shift:
                while(!((inputLine = input.readLine()).equals("EndShift")))
                {
                  ArrayList<Shift> newShifts = new ArrayList<Shift>();
                  System.out.println("shift");
                  while(!((inputLine = input.readLine()).equals("Next_Day")))
                  {
                      fields = inputLine.split(" ");
                      int driverID = Integer.parseInt(fields[0]);
                      int busID = Integer.parseInt(fields[1]);
                      int timeFrom = Integer.parseInt(fields[2]);
                      int timeTo = Integer.parseInt(fields[3]);
                      int routeID = Integer.parseInt(fields[5]);
                      Shift newShift = new Shift(driverID, busID, timeFrom, timeTo, routeID);
                      newShifts.add(newShift);
                  }
                  shiftsInAWeek.add(newShifts);
                }
                break;
            case dateFrom:
                inputLine = input.readLine();
                fields = inputLine.split(" ");
                dateFrom = new GregorianCalendar(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
                break;
            case dateTo:
                inputLine = input.readLine();
                fields = inputLine.split(" ");
                dateTo = new GregorianCalendar(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
                break;
            default:
                break;
        }
      }
      roaster = new Roaster(month, year, shiftsInAWeek, dateFrom, dateTo);
      System.out.println("month" + roaster.month);
      
        
              
    } //try
    
    catch(Exception exception)
    {
      // We report DeleteFieldExceptions to the standard output
        System.err.println("exception");
      System.out.println(exception.getMessage());
    } // catch
    finally
    {
      // attempt to close the input
      try { if (input != null) input.close(); }
      catch (IOException exception)
       { System.err.println("Could not close input " + exception); }
      // close the output stream
    } // finally
    return roaster;
  } // main


public static void saveFile(String fileName, Roaster roaster)
  {
    // input an output initally null
    BufferedReader input = null;
    PrintWriter output = null; 
      
    System.out.println(roaster.dateFrom.get(Calendar.DATE));
    System.out.println(roaster.shift.size());
    for(int i = 0; i < roaster.shift.size(); i++)
    {
        for(int j = 0; j < roaster.shift.get(i).size(); j++)
        {
            System.out.print("shiftID " + roaster.shift.get(i).get(j).getRouteID());
            System.out.println(" driverID " + roaster.shift.get(i).get(j).getDriverID());
        }
    }
      
    try
    {
      if (new File(fileName).exists())
	  throw new Exception("Output field "
	                                 + fileName + " already exists");
      else
        output = new PrintWriter(new FileWriter(fileName));
      
      System.out.println("made");
      
      // output the month header
      output.println("month");
      // output the month
      output.println(roaster.month);
      // output the year header
      output.println("year");
      // output the year
      output.println(roaster.year);
      
      //output that shift header
      output.println("shift");
      ArrayList<Shift> shifts = new ArrayList<Shift>();
      
      for(int i = 0; i < roaster.shift.size(); i++)
      {
          shifts = roaster.shift.get(i);
          
          for(int j = 0; j < shifts.size(); j++)
          {
              output.print(shifts.get(j).getDriverID() + " " + shifts.get(j).getBusID() + " ");
              output.print(shifts.get(j).getTimeFrom() + " " + shifts.get(j).getTimeTo() + " ");
              output.println(shifts.get(j).getDuration() + " " + shifts.get(j).getRouteID() + " ");
          }
          // header so we know to make a new ArrayList<shift>
          output.println("Next_Day");
      }
      
      output.println("EndShift");
      // output dateFrom header
      output.println("dateFrom");
      
      // print the dateFrom
      output.print(roaster.dateFrom.get(Calendar.YEAR) + " " + roaster.dateFrom.get(Calendar.MONTH)); 
      output.println(" " + roaster.dateFrom.get(Calendar.DATE));
      
      // print out dateTo header
      output.println("dateTo");
      
      // print dateTo
      output.print(roaster.dateTo.get(Calendar.YEAR) + " " + roaster.dateTo.get(Calendar.MONTH)); 
      output.println(" " + roaster.dateTo
              .get(Calendar.DATE));
      
      System.out.println("made");
         
    } //try
    catch(Exception exception)
    {
        System.err.println(exception);
    }
    finally
    {
      // close the output stream
	output.close();
        System.out.println("close");
	if (output.checkError())
	  System.err.println("Something went wrong with the output");
    } // finally
  } // main

  public static void main(String[] args)
  {
      Roaster roaster = readFile("roaster5.txt");
      
      saveFile("roaster6.txt", roaster);
  }
    
}
