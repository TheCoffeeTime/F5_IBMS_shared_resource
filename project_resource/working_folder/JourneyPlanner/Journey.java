/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JourneyPlanner;

import database.BusStopInfo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class Journey {
  
  String routeString;
  String departureTimeString;
  String arrivalTimeString;
  
  int route;
  String departureBusStopString;
  int departureTime;
  String arrivalBusStopString;
  int arrivalTime;
  int serviceID;
  int departureBusStopIndex;
  int arrivalBusStopIndex;
  
  public Journey(int pRoute, String pDepartureBusStopString, int pDepartureTime,
                             String pArrivalBusStopString,   int pArrivalTime,
                 int pServiceID, int pDepartureBusStopIndex, int pArrivalBusStopIndex)
  {
    route = pRoute;
    departureBusStopString = pDepartureBusStopString;
    departureTime = pDepartureTime;
    arrivalBusStopString = pArrivalBusStopString;
    arrivalTime = pArrivalTime;
    serviceID = pServiceID;
    departureBusStopIndex = pDepartureBusStopIndex;
    arrivalBusStopIndex = pArrivalBusStopIndex;
    
    routeString = BusStopInfo.getRouteName(route);
    departureTimeString = minutesToTime(departureTime);
    arrivalTimeString = minutesToTime(arrivalTime);
  }
  
  // Converts time in minutes into 00:00 string time representation
  public static String minutesToTime(int minutes)
  {
    int m = minutes % 60;
    int h = (minutes - m) / 60;
    String time;
    if(m>10)
    {
      time = Integer.toString(h) + ":" + Integer.toString(m);
    }
    else
    {
      time = Integer.toString(h) + ":0" + Integer.toString(m);
    }
    return time;
  }
  
  // Methods to get string representation of journey data FOR INTERFACE
  public String getRouteString()
  {
    return routeString;
  }
  
  public String getDepartureBusStopString()
  {
    return departureBusStopString;
  }
  
  public String getDepartureTimeString()
  {
    return departureTimeString;
  }
  
  public String getArrivalBusStopString()
  {
    return arrivalBusStopString;
  }
  
  public String getArrivalTimeString()
  {
    return arrivalTimeString;
  }
  
  // Methods to get integer representation of journey data.
  // They are used to quickly find other possible journeys close to this one
  public int getRoute()
  {
    return route;
  }
  
  public int getDepartureTime()
  {
    return departureTime;
  }
  
  public int getArrivalTime()
  {
    return arrivalTime;
  }
  
  public int getServiceID()
  {
    return serviceID;
  }
  
  public int getDepartureBusStopIndex()
  {
    return departureBusStopIndex;
  }
  
  public int getArrivalBusStopIndex()
  {
    return arrivalBusStopIndex;
  }
  
  public void printJourney()
  {
    System.out.println("Route name: " + routeString + " ; "); 
    System.out.println("Departure from: " + departureBusStopString + 
                                   " at " + departureTimeString + " ;");
    System.out.println("Arrive to: " + arrivalBusStopString + 
                              " at " + arrivalTimeString + " ;"); 
  }
}
