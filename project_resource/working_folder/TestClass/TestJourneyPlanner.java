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
// Method to test Journey planer
public class TestJourneyPlanner {
   
  public static void main(String args[]) {
    
     // Open database connection
    database.openBusDatabase();
    
    // Input arguments
    
    // For direct journey
    String departureAreaName = "Stockport";
    String departureBusStopName = "Bus Station";
    
    String arrivalAreaName = "Marple";
    String arrivalBusStopName = "Offerton Fold";
    
    /*
    // For journey through Bus Station 
    String departureAreaName = "Strines";
    String departureBusStopName = "Royal Oak";
    
    String arrivalAreaName = "Romiley";
    String arrivalBusStopName = "Corcoran Drive";
     */
    
    // Date including time
    GregorianCalendar date =  new GregorianCalendar(2013, 05, 4, 14, 47);
    
    // If is true time in date is considered as "arrive by this time to the arrival bus stop".
    // arriveBy = false means "depart from departue bus stop from this time".
    boolean arriveBy = true;
    
    // Get 2d Array List [Option_number][Journey(s)] of journeys
    ArrayList<ArrayList<Journey>> myJourneys = JourneyPlanner.JourneyPlanner(departureAreaName, departureBusStopName,
                                                              arrivalAreaName, arrivalBusStopName, 
                                                              date, arriveBy);
    // Print Journey Planer in System output in a user friendly way.
    JourneyPlanner.printJourneyPlanner(myJourneys);
  } 
}
