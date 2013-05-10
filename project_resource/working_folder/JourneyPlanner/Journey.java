package JourneyPlanner;

import database.BusStopInfo;

/**
 * @author Nikita
 */
public class Journey {
  
  // Variables to store string representation of information about a journey
  // Used for Interfaces and output.
  String routeString;
  String departureTimeString;
  String arrivalTimeString;
  String departureBusStopString;
  String arrivalBusStopString;
  
  // Variables to store integer representations of information about a journey
  // Used for finding duration of the journey, other similar journeys etc.
  int route;
  int departureTime;
  int arrivalTime;
  int serviceID;
  int departureBusStopIndex;
  int arrivalBusStopIndex;
  
  // Constructor
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
  
  // Converts time in minutes to 00:00 string time representation
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
  // They are used to quickly find other possible journeys close to this best option
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
  
  // Method to print information about a journey in a friendly way (used for testing)
  public void printJourney()
  {
    System.out.println("Route name: " + routeString + " ; "); 
    System.out.println("Departure from: " + departureBusStopString + 
                                   " at " + departureTimeString + " ;");
    System.out.println("Arrive to: " + arrivalBusStopString + 
                              " at " + arrivalTimeString + " ;"); 
  }
}
