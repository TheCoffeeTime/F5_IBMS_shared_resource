/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JourneyPlanner;

import database.BusStopInfo;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Q'ketske
 */
public class JourneyPlanner {
  
  private static int findThreshold(int departureBusStopIndex, int arrivalBusStopIndex, int time, boolean arriveBy,
                            ArrayList<ArrayList<Integer>> RouteServicesTimes)
  {
    int threshold = -1;

    for (int i = 0; i < RouteServicesTimes.size(); i++)
    {
      // Check that bus really goes through both bus stops... thank you the bus which DOESN'T
     if (departureBusStopIndex < RouteServicesTimes.get(i).size() && 
          arrivalBusStopIndex < RouteServicesTimes.get(i).size())
     {
        if (arriveBy == false) // Departure after option
        {
          if(RouteServicesTimes.get(i).get(departureBusStopIndex) > time)
          {
            threshold = i;
            System.out.println("Closest departure time: " + RouteServicesTimes.get(threshold).get(departureBusStopIndex));
            break;
          }
        }
        else // ArriveBy option
        {
          if(RouteServicesTimes.get(i).get(arrivalBusStopIndex) > time)
          {
            threshold = i - 1;
            System.out.println("Closest arrival time: " + RouteServicesTimes.get(threshold).get(arrivalBusStopIndex));
            break;
          }                
        }                           
      }             
    }
    return threshold;
  }
  
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
      if(isTimingPointOnRoute(tempBusStop,tempRoute,timetable))
      {
        arrivalBusStop = tempBusStop;
      }
    }        
    
    // Check bus stop order
    if (departureBusStop <  arrivalBusStop)
    {
      System.out.println("Departure Route: " + tempRoute + "; Bus Stop: " + departureBusStop);
      System.out.println("Arrival Route: " + tempRoute + "; Bus Stop: " + arrivalBusStop);
      int departureBusStopIndex = timetable.findBusStopIndex(tempRoute, departureBusStop);
      int arrivalBusStopIndex = timetable.findBusStopIndex(tempRoute, arrivalBusStop);
      System.out.println("Departure bus stop index: " + departureBusStopIndex);
      System.out.println("Arrival bus stop index: " + arrivalBusStopIndex);          
          
      ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(tempRoute);
      int threshold = findThreshold(departureBusStopIndex, arrivalBusStopIndex, time, arriveBy, RouteServicesTimes);
      if (threshold == -1)
      {
        System.out.println("No services =(");
        return null;
      }
      // Create Journey:
      int[] tempBusStops = timetable.getBusStops(tempRoute);
      int route = tempRoute;
      String departureBusStopString = BusStopInfo.getFullName(tempBusStops[departureBusStopIndex]);
      int departureTime = RouteServicesTimes.get(threshold).get(departureBusStopIndex);
      String arrivalBusStopString = BusStopInfo.getFullName(tempBusStops[arrivalBusStopIndex]);
      int arrivalTime = RouteServicesTimes.get(threshold).get(arrivalBusStopIndex);
      
      Journey journey  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                    threshold, departureBusStopIndex, arrivalBusStopIndex);
      return journey;
    }   
    return null;
  }
  
  private static List getAllRelevantBusStops(String areaName, String busStopName)
  {
    int area = BusStopInfo.findAreaByName(areaName);
    int[] allAreaBusStops = BusStopInfo.getBusStopsInArea(area);
    List allAreaBusStopsList = new ArrayList();
    
    // Remove not acceptable bus stops
    for (int i = 0; i < allAreaBusStops.length; i++)
    {
      if (BusStopInfo.getFullName(allAreaBusStops[i]).equals(areaName + ", " + busStopName))
      {
        allAreaBusStopsList.add(allAreaBusStops[i]);
      }
    }
    return allAreaBusStopsList;
  }
  
  private static boolean isTimingPointOnRoute(int busStop, int route, Timetable timetable)
  {
    int[] busStops = timetable.getBusStops(route);
    for (int i = 0; i < busStops.length; i++)
    {
      if(busStop == busStops[i])
      {
        return true;
      }
    }
    return false;
  }
  
  private static Journey findJourney2(ArrayList routes, int time, boolean arriveBy, Timetable timetable,
                             List allDepartureAreaBusStopsList, List allArrivalAreaBusStopsList)
  {
    Journey journey;
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
  
  private static ArrayList<Journey> findOtherOptions(Journey coreJourney, boolean arriveBy, ArrayList<ArrayList<Integer>> RouteServicesTimes)
  {
    ArrayList<Journey> journeys = new ArrayList<Journey>();
    journeys.add(coreJourney);
    
    int numberOfJourneys = 1;
    
    
    int route = coreJourney.getRoute();
    String departureBusStopString = coreJourney.getDepartureBusStopString();
    String arrivalBusStopString = coreJourney.getArrivalBusStopString();
    int serviceID = coreJourney.getServiceID();
    int departureBusStopIndex = coreJourney.getDepartureBusStopIndex();
    int arrivalBusStopIndex = coreJourney.getArrivalBusStopIndex();
    int departureTime = 0;
    int arrivalTime = 0;
      
    while(numberOfJourneys < 5 && serviceID > 0 && serviceID < RouteServicesTimes.size())
    {      
      if (arriveBy == true)
      {
        serviceID--; 
        departureTime = RouteServicesTimes.get(serviceID).get(departureBusStopIndex);
        arrivalTime = RouteServicesTimes.get(serviceID).get(arrivalBusStopIndex);
        Journey nextJourney  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                           serviceID, departureBusStopIndex, arrivalBusStopIndex);
        journeys.add(nextJourney);
      }
      else
      {
        serviceID++;
        departureTime = RouteServicesTimes.get(serviceID).get(departureBusStopIndex);
        arrivalTime = RouteServicesTimes.get(serviceID).get(arrivalBusStopIndex);
        Journey nextJourney  = new Journey(route, departureBusStopString, departureTime,
                                           arrivalBusStopString,   arrivalTime,
                                           serviceID, departureBusStopIndex, arrivalBusStopIndex);
        journeys.add(nextJourney);
        
      }
      numberOfJourneys++;
    }
    return journeys;
  }
  
  public static void printJourneyPlanner(ArrayList<ArrayList<Journey>> Journeys)
  {
    System.out.println("%%%%%%%% Journey Planner %%%%%%%%");
    for (int i = 0; i < Journeys.get(0).size(); i++)
    {
      System.out.println("Option " + (i+1) + ":");
      for (int j = 0; j < Journeys.size(); j++)
      {
        Journeys.get(j).get(i).printJourney();
      }
      System.out.println("-------------------------------");
    }
    System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
  }
  
  public static ArrayList<ArrayList<Journey>> JourneyPlanner(String departureAreaName, String departureBusStopName,
                                                              String arrivalAreaName, String arrivalBusStopName, 
                                                              GregorianCalendar date, boolean arriveBy,
                                                              Timetable timetable)
  {
    ArrayList<ArrayList<Journey>> possibleJourneys = new ArrayList<ArrayList<Journey>>();
            
    int time = date.get(GregorianCalendar.HOUR_OF_DAY) * 60 + 
               date.get(GregorianCalendar.MINUTE);
    System.out.println("Time: " + Journey.minutesToTime(time));
    
    
    List allDepartureAreaBusStopsList = getAllRelevantBusStops(departureAreaName,departureBusStopName);
    List allArrivalAreaBusStopsList = getAllRelevantBusStops(arrivalAreaName, arrivalBusStopName);
    
    int[] routes = BusStopInfo.getRoutes();
    ArrayList departureRoutes = new ArrayList();
    ArrayList departureBusStops = new ArrayList();
    ArrayList arrivalRoutes = new ArrayList();
    ArrayList arrivalBusStops = new ArrayList();
    
    for (int i = 0; i < routes.length; i++)
    {    
      // Loop throug all departure bus stops
      Iterator<Integer> iteratorDepartureBusStopsList = allDepartureAreaBusStopsList.iterator();
	    while (iteratorDepartureBusStopsList.hasNext()) 
      {
        
        int tempBusStop = iteratorDepartureBusStopsList.next();
        if(isTimingPointOnRoute(tempBusStop, routes[i], timetable) && !departureRoutes.contains(routes[i]))
        {        
          departureRoutes.add(routes[i]);
          departureBusStops.add(tempBusStop);
        }
      }
           
      Iterator<Integer> iteratorArrivalBusStopList = allArrivalAreaBusStopsList.iterator();
	    while (iteratorArrivalBusStopList.hasNext()) 
      {
        int tempBusStop = iteratorArrivalBusStopList.next();
        if(isTimingPointOnRoute(tempBusStop, routes[i], timetable) && !arrivalRoutes.contains(routes[i]))
        {
          arrivalRoutes.add(routes[i]);
          arrivalBusStops.add(tempBusStop);
        }
      }
    }
      
    // Intermidiate journey planner results
    System.out.println("Departure Routes:");
    for (int i = 0; i < departureRoutes.size(); i++) {
		System.out.println(departureRoutes.get(i));
	  }
    
    // Intermidiate journey planner results
    System.out.println("Arrival Routes");
    for (int i = 0; i < arrivalRoutes.size(); i++) {
		System.out.println(arrivalRoutes.get(i));
	  }
    
    ArrayList departureAndArrivalRoutes = new ArrayList();
    
    for(int i = 0; i < departureRoutes.size(); i++)
    {
      if(arrivalRoutes.contains(departureRoutes.get(i)))
      {
        departureAndArrivalRoutes.add(departureRoutes.get(i));
      }
    }
    
    // Intermidiate journey planner results
    System.out.println("Departure & Arrival Routes");
    for (int i = 0; i < departureAndArrivalRoutes.size(); i++) {
		System.out.println(departureAndArrivalRoutes.get(i));
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
        ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(tempRoute);
        
        Journey journey = findJourney(tempRoute, time, arriveBy, timetable,
                             allDepartureAreaBusStopsList, allArrivalAreaBusStopsList);       
        if (journey != null)
        {
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          possibleJourneys.add(journeys);
          return possibleJourneys;
        }
        else
        {
          return null;
        }
      }
    }
    else
    {
      System.out.println("Direct journeys are NOT possible!");
      
      String busStationAreaName = "Stockport";
      String busStationBusStopName = "Bus Station";
      List allBusStationBusStopsList = getAllRelevantBusStops(busStationAreaName,busStationBusStopName);
      
      if (arriveBy == false) // DepartForm
      {
        Journey journey = findJourney2(departureRoutes, time, arriveBy, timetable,
                               allDepartureAreaBusStopsList, allBusStationBusStopsList);
        if (journey != null)
        {
          int newTime = journey.getArrivalTime();
          Journey journey1 = findJourney2(arrivalRoutes, newTime, false, timetable,
                             allBusStationBusStopsList, allArrivalAreaBusStopsList);
          
          
          ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          possibleJourneys.add(journeys);
          
          RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys1 = findOtherOptions(journey1, arriveBy, RouteServicesTimes);
          possibleJourneys.add(journeys1);
          
          return possibleJourneys;
          /*
          System.out.println("--------------------------------------------------");
          journey.printJourney();
          journey1.printJourney();
          */         
        }
        return null;
      }
      else // ArriveBy
      {
        Journey journey = findJourney2(arrivalRoutes, time, arriveBy, timetable,
                           allBusStationBusStopsList, allArrivalAreaBusStopsList);
        if (journey != null)
        {
          int newTime = journey.getDepartureTime();
          Journey journey1 = findJourney2(departureRoutes, newTime, true, timetable,
                             allDepartureAreaBusStopsList, allBusStationBusStopsList);
          
          ArrayList<ArrayList<Integer>> RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys1 = findOtherOptions(journey1, arriveBy, RouteServicesTimes);
          possibleJourneys.add(journeys1);
          
          RouteServicesTimes = timetable.getRouteServicesTimes(journey.getRoute());
          ArrayList<Journey> journeys = findOtherOptions(journey, arriveBy, RouteServicesTimes);
          possibleJourneys.add(journeys);
          
          return possibleJourneys;
          /*
          System.out.println("--------------------------------------------------");
          journey1.printJourney();
          journey.printJourney();
          */
        }
        return null;
      }      
    }
    return null;
  }
}