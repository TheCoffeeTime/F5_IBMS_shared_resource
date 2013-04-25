package RosterGenerator;


import RosterGenerator.Roaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    
  public static void saveFile(String fileName, Roaster roaster)
  {
    // input an output initally null
    BufferedReader input = null;
    PrintWriter output = null; 
    
    System.out.println(roaster.dateFrom.get(Calendar.DATE));
    for(int i = 0; i < roaster.shift.size(); i++)
    {
        for(int j = 0; j < roaster.shift.get(i).size(); j++)
        {
            System.out.println("shiftID" + roaster.shift.get(i).get(j).getRouteID());
        }
    }
    // Nathan....please fix this line....thank you.....return;
      
    try
    {
      
	input = new BufferedReader(new FileReader(fileName));
   

      // input line for the string of text
      String inputLine;  
      
      int fieldToDelete = 0;
         
      while((inputLine = input.readLine()) != null)
      {
        // Divide the line into fields using tab as a delimiter.
        String[] fields = inputLine.split("\t");
        String editedLine = ""; 
        if (fields.length < fieldToDelete)
          editedLine = inputLine;
        else 
        {
	  // We build the new line in parts
   	  // Add the fields before the one to be deleted
	  for (int index = 0; index < fieldToDelete - 1; index++)
	    if(editedLine.equals(""))
	      editedLine = fields[index];
	    else
	      editedLine += "\t" + fields[index];
	
	  // Add the fields after the one on to be deleted
	  for(int index = fieldToDelete; index < fields.length; index++)	  
	    if (editedLine.equals(""))
	      editedLine = fields[index];
	    else
	      editedLine += "\t" + fields[index];	    	  
        } // else
        output.println(editedLine);
      } // while
    } //try
    catch(Exception exception)
    {
      // We report DeleteFieldExceptions to the standard output
      System.out.println(exception.getMessage());
    } // catch
    finally
    {
      // attempt to close the input
      try { if (input != null) input.close(); }
      catch (IOException exception)
       { System.err.println("Could not close input " + exception); }
      // close the output stream
      if (output != null)
      {
	output.close();
	if (output.checkError())
	  System.err.println("Something went wrong with the output");
      }// if
    } // finally
  } // main


public static void readFile(String fileName)
  {
    // input an output initally null
    BufferedReader input = null;
    PrintWriter output = null; 
      
    try
    {
      if (new File(fileName).exists())
	  throw new Exception("Output field "
	                                 + fileName + " already exists");
      else
        output = new PrintWriter(new FileWriter(fileName));
      
      // input line for the string of text
      String inputLine;   
         
      while((inputLine = input.readLine()) != null)
      {
        // Divide the line into fields using tab as a delimiter.
        String[] fields = inputLine.split("\t");
        String editedLine = ""; 
        int fieldToDelete = 0;;
        if (fields.length < fieldToDelete)
          editedLine = inputLine;
        else 
        {
	  // We build the new line in parts
   	  // Add the fields before the one to be deleted
	  for (int index = 0; index < fieldToDelete - 1; index++)
	    if(editedLine.equals(""))
	      editedLine = fields[index];
	    else
	      editedLine += "\t" + fields[index];
	
	  // Add the fields after the one on to be deleted
	  for(int index = fieldToDelete; index < fields.length; index++)	  
	    if (editedLine.equals(""))
	      editedLine = fields[index];
	    else
	      editedLine += "\t" + fields[index];	    	  
        } // else
        output.println(editedLine);
      } // while
    } //try
    catch(Exception exception)
    {
        System.err.println(exception);
    }
    finally
    {
      // attempt to close the input
      try { if (input != null) input.close(); }
      catch (IOException exception)
       { System.err.println("Could not close input " + exception); }
      // close the output stream
      if (output != null)
      {
	output.close();
	if (output.checkError())
	  System.err.println("Something went wrong with the output");
      }// if
    } // finally
  } // main

    
}
