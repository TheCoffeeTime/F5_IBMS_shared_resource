package JourneyPlanner;

import database.BusStopInfo;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * @author Nikita
 */
public class JourneyPlanner {
  
  /* RouteServicesTimes is a 2d ArrayList [ServiceSequenceNumber][BusStop]
   * It uses departure or arrival bus stuo INDEX (This is not bus stop ID like 770 or 781,
   * it's an index (0,1,2,3) and you can find it useing Timetable.findBusStopIndex)
   * to loop through all services to find a sequence number of service which 
   * departure/arrival time is the closest one to a given time.
  */
  public static int findThreshold(int departureBusStopIndex, int arrivalBusStopIndex, int time, boolean arriveBy,
                            ArrayList<ArrayList<Integer>> RouteServicesTimes)
  {
    // Set threshold (ServiceSequenceNumber) to impossible value.
    // If -1 is returned, it means that there are no bus services for this time.
    int threshold = -1;
    
    // Loop through all bus services
    for (int i = 0; i < RouteServicesTimes.size(); i++)
    {
        
      // Check that bus really goes through both bus stops... thank you the bus which DOESN'T
     if (departureBusStopIndex < RouteServicesTimes.get(i).size() && 
          arrivalBusStopIndex < RouteServicesTimes.get(i).size())
     {
        if (arriveBy == false) // Departure after option
        {
          // If we find service which departure time is greater than given time
          //(Example: Condition - Departure after 12:00, we found bus service 
          // which departs at 12:05)
          if(RouteServicesTimes.get(i).get(departureBusStopIndex) > time)
          {
            // Remember its sequence number & stop the loop
            threshold = i;
            break;
          }
        }
        else // ArriveBy option
        {
          // If we find service wich arrival time is greater than given time
          // (Example: Condition - Arrive by 12:00, we found a bus service
          // which arrives at 12:05)
          if(RouteServicesTimes.get(i).get(arrivalBusStopIndex) > time)
          {
            // Remeber sequence number of the previous service and stop the loop
            threshold = i - 1;
            break;
          }                
        }                           
      }             
    }
    // Return sequence number of a bus service which best matches given conditions
    return threshold;
  }
  
  // Method to find a single journey
  public static Journey findJourney(int tempRoute, int time, boolean arriveBy, Timetable timetable,
                             List allDepartureAreaBusStopsList, List allArrivalAreaBusStopsList)
  {
    int departureBusStop = 0;
    int arrivalBusStop = 0;
    
    // Loop throug all departure bus stops for this route
    Iterator<Integer> iteratorDepartureBusStopsList = allDepartureAreaBusStopsList.iterator();
	  while (iteratorDepartureBusStopsList.hasNext()) 
    {
      int tempBusStop = iteratorDepartureBusStopsList.next();
      // Find the first possible BusStop
      if(isTimingPointOnRoute(tempBusStop,tempRoute,timetable))
      {
        departureBusStop = tempBusStop;
        break; // Thid break is important :D
      }
    }
        
    // Loop throug all arrival bus stops for this route   
    Iterator<Integer> iteratorArrivalBusStopList = allArrivalAreaBusStopsList.iterator();
	  while (iteratorArrivalBusStopList.hasNext()) 
    {
      int tempBusStop = iteratorArrivalBusStopList.next();
      // Find the last possible BusStop
      if(isTimingPointOnRoute(tempBusStop,tempRoute,timetable))
      {
        arrivalBusStop = tempBusStop;
      }
    }        
    
    /* Check bus stop order. This condition is used to find the shortest route.
     * Bus Stops are numbered in ascending order, so for example, if departure
     * BusStop ID is 777 and arrival BusStop ID is 776 it means that bus has to
     * go through the whole route. There always are better options, so this condition
     * ensures that such journeys are not taken into account.
     */
    if (departureBusStop <  arrivalBusStop)
    {
      // Find bus stops indicies (used to get time when bus stops on a bus stop
      // from 2d ArrayList of services.
      int departureBusStopIndex = timetable.findBusStopIndex(tempRoute, departureBusStop);
      int arrivalBusStopIndex = timetable.findBusStopIndex(tempRoute, arrivalBusStop);     
      
      // Get a 2d ArrayList[service number][time when bus stops on a busStop]
      ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(tempRoute);
      // Find service sequence number of the service which best matches time conditions
      int threshold = findThreshold(departureBusStopIndex, arrivalBusStopIndex, time, arriveBy, RouteServicesTimes);
      // If such service doesn't exist
      if (threshold == -1)
      {
        // No possible services are found so journey can't be create
        return null;
      }
      
      // Get Bus Stops numbers
      int[] tempBusStops = timetable.getBusStops(tempRoute);
      int route = tempRoute;
      // Get string representation of the departure bus stop
      String departureBusStopString = BusStopInfo.getFullName(tempBusStops[departureBusStopIndex]);
      // Get departure time of a service which best matches given time conditions
      int departureTime = RouteServicesTimes.get(threshold).get(departureBusStopIndex);
      // Get string representation of the departure bus stop
      String arrivalBusStopString = BusStopInfo.getFullName(tempBusStops[arrivalBusStopIndex]);
      // Get arrival time of a service which best matches given time conditions
      int arrivalTime = RouteServicesTimes.get(threshold).get(arrivalBusStopIndex);
      
      // Create Journey:
      Journey journey  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                    threshold, departureBusStopIndex, arrivalBusStopIndex);
      // Return journey which best satisfies given time conditions
      return journey;
    }
    // No possible services are found so journey can't be created
    return null;
  }
  
  // This method is used to find all BusStop IDs in a given array which have given bus stop name 
  public static List getAllRelevantBusStops(String areaName, String busStopName)
  {
    // Get ID of the area
    int area = BusStopInfo.findAreaByName(areaName);
    // Get all bus stops on the area
    int[] allAreaBusStops = BusStopInfo.getBusStopsInArea(area);
    // List to store relevant bus stop IDs
    List allAreaBusStopsList = new ArrayList();
    
    // Loop through all area bus stops
    for (int i = 0; i < allAreaBusStops.length; i++)
    {
      // if bus stop name is the same as a given one
      if (BusStopInfo.getFullName(allAreaBusStops[i]).equals(areaName + ", " + busStopName))
      {
        // add it to the list
        allAreaBusStopsList.add(allAreaBusStops[i]);
      }
    }
    // return list of all matching bus stops
    return allAreaBusStopsList;
  }
  
  // This method is used to chech if given busStop is a timing point on a given route
  public static boolean isTimingPointOnRoute(int busStop, int route, Timetable timetable)
  {
    // Get all bus stops for a given route
    int[] busStops = timetable.getBusStops(route);
    // Loop through all possible bus stops
    for (int i = 0; i < busStops.length; i++)
    {
      // If given bus stop is one of route timing points
      if(busStop == busStops[i])
      {
        return true;
      }
    }
    return false;
  }
  
  // This method is used to create journeys for not direct journeys.
  public static Journey findJourney2(ArrayList routes, int time, boolean arriveBy, Timetable timetable,
                             List allDepartureAreaBusStopsList, List allArrivalAreaBusStopsList)
  {
    Journey journey;
    // Loop through all routes, find and return first possible journey
    Iterator<Integer> iteratorRoutes = routes.iterator();
    while (iteratorRoutes.hasNext()) 
    { 
      int tempRoute = iteratorRoutes.next();
      journey = findJourney(tempRoute, time, arriveBy, timetable,
                            allDepartureAreaBusStopsList, allArrivalAreaBusStopsList);       
      if (journey != null)
      {
        return journey;
      }                  
    }
    return null;
  }
  
  // This method is used to find other possible journeys which are close to the best option
  public static ArrayList<Journey> findOtherOptions(Journey coreJourney, boolean arriveBy, ArrayList<ArrayList<Integer>> RouteServicesTimes)
  {
    // ArrayList to store all journey options
    ArrayList<Journey> journeys = new ArrayList<Journey>();
    // Best option is the first option
    journeys.add(coreJourney);
    
    // Rememver number of journeys in a list
    int numberOfJourneys = 1;
    
    // Variables which are used to create journey object, they are common for all
    // journey options apart from departure & arrival time and service ID.
    int route = coreJourney.getRoute();
    String departureBusStopString = coreJourney.getDepartureBusStopString();
    String arrivalBusStopString = coreJourney.getArrivalBusStopString();
    int serviceID = coreJourney.getServiceID();
    int departureBusStopIndex = coreJourney.getDepartureBusStopIndex();
    int arrivalBusStopIndex = coreJourney.getArrivalBusStopIndex();
    int departureTime = 0;
    int arrivalTime = 0;
    
    // Loop while we don't find 5 journey options and there are still other services which
    // matches gicen time conditions
    while(numberOfJourneys < 5 && serviceID > 0 && serviceID < RouteServicesTimes.size()-1)
    {      
      if (arriveBy == true) // If condition is Arrive by
      {
        // create journey from the time of a service before the previous one
        serviceID--; 
        departureTime = RouteServicesTimes.get(serviceID).get(departureBusStopIndex);
        arrivalTime = RouteServicesTimes.get(serviceID).get(arrivalBusStopIndex);
        Journey nextJourney  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                           serviceID, departureBusStopIndex, arrivalBusStopIndex);
        // Add journey to a list
        journeys.add(nextJourney);
      }
      else // If condition is Depart from
      {
        // create a journey from the time of a next service
        serviceID++;
        departureTime = RouteServicesTimes.get(serviceID).get(departureBusStopIndex);
        arrivalTime = RouteServicesTimes.get(serviceID).get(arrivalBusStopIndex);
        Journey nextJourney  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                           serviceID, departureBusStopIndex, arrivalBusStopIndex);
        journeys.add(nextJourney);
        
      }
      // Add journey to a list
      numberOfJourneys++;
    }
    // Return at most 5 journey options
    return journeys;
  }
  
  // Method to print all journeys options in a friendly way
  public static void printJourneyPlanner(ArrayList<ArrayList<Journey>> Journeys)
  {
    // If there are no journey options
    if (Journeys == null)
    {
      // inform user about this.
      System.out.println("There are no journey options for your query.");
    }
    else // if there are journey options
    {
      System.out.println("%%%%%%%%%%%%%%%% Journey Planner %%%%%%%%%%%%%%%%");
      // Loop through all journey options
      for (int i = 0; i < Journeys.size(); i++)
      {
        System.out.println("Option " + (i+1) + ":");
        // Loop through all journeys in a journey options
        // Indirect journeys consist of two journeys.
        for (int j = 0; j < Journeys.get(i).size(); j++)
        {
          Journeys.get(i).get(j).printJourney();
        }
        System.out.println("------------------------------------------------");
      }
      System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
  }
  
  // Method to create 2d ArrayList of possible journeys options
  public static ArrayList<ArrayList<Journey>> JourneyPlanner(String departureAreaName, String departureBusStopName,
                                                              String arrivalAreaName, String arrivalBusStopName, 
                                                              GregorianCalendar date, boolean arriveBy)
  {
    // Create local copy of database timetable
    Timetable timetable = new Timetable(date);
    // 2d ArrayList to store possible journeys options
    ArrayList<ArrayList<Journey>> possibleJourneys = new ArrayList<ArrayList<Journey>>();
    
    // Convert time from 01:30 -> 90 minutes
    int time = date.get(GregorianCalendar.HOUR_OF_DAY) * 60 + 
               date.get(GregorianCalendar.MINUTE);
    
    // Lists to store all possible departure and arrival bus stops
    List allDepartureAreaBusStopsList = getAllRelevantBusStops(departureAreaName,departureBusStopName);
    List allArrivalAreaBusStopsList = getAllRelevantBusStops(arrivalAreaName, arrivalBusStopName);
    
    // Get all routes
    int[] routes = BusStopInfo.getRoutes();
    // Lists to store departure & arrival routes
    ArrayList departureRoutes = new ArrayList();
    ArrayList arrivalRoutes = new ArrayList();
    
    // Loop through all routes
    for (int i = 0; i < routes.length; i++)
    {    
      // Loop throug all departure bus stops and find all routes which go through them
      Iterator<Integer> iteratorDepartureBusStopsList = allDepartureAreaBusStopsList.iterator();
	    while (iteratorDepartureBusStopsList.hasNext()) 
      {
        int tempBusStop = iteratorDepartureBusStopsList.next();
        if(isTimingPointOnRoute(tempBusStop, routes[i], timetable) && !departureRoutes.contains(routes[i]))
        {        
          departureRoutes.add(routes[i]);
        }
      }
      
      // Loop throug all arrival bus stops and find all routes which go through them     
      Iterator<Integer> iteratorArrivalBusStopList = allArrivalAreaBusStopsList.iterator();
	    while (iteratorArrivalBusStopList.hasNext()) 
      {
        int tempBusStop = iteratorArrivalBusStopList.next();
        if(isTimingPointOnRoute(tempBusStop, routes[i], timetable) && !arrivalRoutes.contains(routes[i]))
        {
          arrivalRoutes.add(routes[i]);
        }
      }
    }
    
    // List to store routes which go directly from departure to arrival bus stop
    ArrayList departureAndArrivalRoutes = new ArrayList();
    
    // Find routes which goes though both departure & arrival bus stops
    for(int i = 0; i < departureRoutes.size(); i++)
    {
      if(arrivalRoutes.contains(departureRoutes.get(i)))
      {
        departureAndArrivalRoutes.add(departureRoutes.get(i));
      }
    }
    
    // If it is possible to drive from start to end bus stop by one route
    if(!departureAndArrivalRoutes.isEmpty())
    {
      System.out.println("Direct journeys are possible!");
      
      // Loop throug all possible routes:
      Iterator<Integer> iteratordepartureAndArrivalRoutes = departureAndArrivalRoutes.iterator();
      while (iteratordepartureAndArrivalRoutes.hasNext()) 
      {       
        int tempRoute = iteratordepartureAndArrivalRoutes.next();
        // Get service times for a current route
        ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(tempRoute);
        
        // Find best journey option
        Journey journey = findJourney(tempRoute, time, arriveBy, timetable,
                             allDepartureAreaBusStopsList, allArrivalAreaBusStopsList);
        // If best journey option exists
        if (journey != null)
        {
          // Find other journey options
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          // Add all posible journey options to the final journey options list
          for (int i = 0; i < journeys.size(); i++)
          {
            possibleJourneys.add(new ArrayList());
            possibleJourneys.get(i).add(journeys.get(i));
          }
          // Return all possible journeys
          return possibleJourneys;         
        }
      }
      // No possible journeys for this combination of arrival & departure bus stop, date * time
      return null;
    }
    else // There are no direct routes
    {
      System.out.println("Direct journeys are NOT possible!");
      // All buses go to the Bus Station, so it is possible to go to any bus stop
      // from any other bus stop by changing bus on the Bus Station.
      String busStationAreaName = "Stockport";
      String busStationBusStopName = "Bus Station";
      List allBusStationBusStopsList = getAllRelevantBusStops(busStationAreaName,busStationBusStopName);
      
      if (arriveBy == false) // DepartForm
      {
        
        // Find best journey option Start Bus Stop -> Bus Station
        Journey journey = findJourney2(departureRoutes, time, arriveBy, timetable,
                               allDepartureAreaBusStopsList, allBusStationBusStopsList);    
        // If best journey option exists
        if (journey != null)
        {
          // Find other journey options
          ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          
          // For each of the journeys START BUS STOP -> BUS STATION
          for (int i = 0; i < journeys.size(); i++)
          {
            // Find journey BUS STATION -> END BUS STOP
            int newTime = journeys.get(i).getArrivalTime();
            Journey journey1 = findJourney2(arrivalRoutes, newTime, false, timetable,
                             allBusStationBusStopsList, allArrivalAreaBusStopsList);
            // If both journeys exist, add them as ONE journey option to final list
            if (journey1 != null)
            {
              possibleJourneys.add(new ArrayList());
              possibleJourneys.get(i).add(journeys.get(i));
              possibleJourneys.get(i).add(journey1);
            }
          }
          // If there is at least one journey option in a list, return it
          if (possibleJourneys.size() > 1 && possibleJourneys.get(0).size() > 1)
          {
            return possibleJourneys;
          }
          else
          {
            // No possible journeys for this combination of arrival & departure bus stop, date * time
            return null;
          }    
        }
        // No possible journeys for this combination of arrival & departure bus stop, date * time
        return null;
      }
      else // ArriveBy
      {
        // Find best journey option BUS STATION -> END BUS STOP
        Journey journey = findJourney2(arrivalRoutes, time, arriveBy, timetable,
                           allBusStationBusStopsList, allArrivalAreaBusStopsList);
        if (journey != null)
        {
          // Find other journey options
          ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          
          // For each of the journeys BUS STATION -> END BUS STOP
          for (int i = 0; i < journeys.size(); i++)
          {
            // Find journey START BUS STOP -> BUS STATION
            int newTime = journeys.get(i).getDepartureTime();
            Journey journey1 = findJourney2(departureRoutes, newTime, true, timetable,
                               allDepartureAreaBusStopsList, allBusStationBusStopsList);
            
            // If both journeys exist, add them as ONE journey option to final list
            if (journey1 != null)
            {
              possibleJourneys.add(new ArrayList());
              possibleJourneys.get(i).add(journey1);
              possibleJourneys.get(i).add(journeys.get(i));          
            }
          }
          // If there is at least one journey option in a list, return it
          if (possibleJourneys.size() > 1 && possibleJourneys.get(0).size() > 1)
          {
            return possibleJourneys;
          }
          else
          {
            // No possible journeys for this combination of arrival & departure bus stop, date * time
            return null;
          }
        }
        // No possible journeys for this combination of arrival & departure bus stop, date * time
        return null;
      }      
    }
  }
}