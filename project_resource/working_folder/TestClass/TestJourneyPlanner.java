/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TestClass;

import JourneyPlanner.Journey;
import JourneyPlanner.JourneyPlanner;
import JourneyPlanner.Timetable;
import database.database;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Nikita
 */
public class TestJourneyPlanner {
   public static void main(String args[]) {
    // Open database connection
    database.openBusDatabase();
    
    // Input arguments
    /*
    String departureAreaName = "Stockport";
    String departureBusStopName = "Bus Station";
    
    String arrivalAreaName = "Marple";
    String arrivalBusStopName = "Offerton Fold";
    
    
    String departureAreaName = "Strines";
    String departureBusStopName = "Royal Oak";
    
    String arrivalAreaName = "Romiley";
    String arrivalBusStopName = "Corcoran Drive";
     */
    
    String departureAreaName = "Strines";
    String departureBusStopName = "Royal Oak";
    
    String arrivalAreaName = "Stockport";
    String arrivalBusStopName = "Bus Station";  
    
    GregorianCalendar date =  new GregorianCalendar(2013, 05, 4, 14, 47);
    
    boolean arriveBy = true;
    
    ArrayList<ArrayList<Journey>> myJourneys = JourneyPlanner.JourneyPlanner(departureAreaName, departureBusStopName,
                                                              arrivalAreaName, arrivalBusStopName, 
                                                              date, arriveBy);
    JourneyPlanner.printJourneyPlanner(myJourneys);
  } 
}
