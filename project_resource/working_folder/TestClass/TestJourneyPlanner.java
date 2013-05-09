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
    String departureAreaName = "Romiley";
    String departureBusStopName = "Train Station";
    
    String arrivalAreaName = "Stockport";
    String arrivalBusStopName = "Bus Station";
    */
    
    String departureAreaName = "Romiley";
    String departureBusStopName = "Corcoran Drive";
    
    String arrivalAreaName = "Strines";
    String arrivalBusStopName = "Royal Oak";
    
    
    GregorianCalendar date =  new GregorianCalendar(2013, 04, 22, 15, 47);
    
    boolean arriveBy = true;
    
    ArrayList<ArrayList<Journey>> myJourneys = JourneyPlanner.JourneyPlanner(departureAreaName, departureBusStopName,
                                                              arrivalAreaName, arrivalBusStopName, 
                                                              date, arriveBy);
    JourneyPlanner.printJourneyPlanner(myJourneys);
  } 
}
