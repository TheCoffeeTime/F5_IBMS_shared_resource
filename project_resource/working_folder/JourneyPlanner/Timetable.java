/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JourneyPlanner;

import database.BusStopInfo;
import database.TimetableInfo;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class Timetable {
  
  // Number of routes in the database
  public static final int NUMBER_OF_ROUTES = 4;
  
  // 3d ArrayList to store local copy of the timetable information from the database
  ArrayList<ArrayList<ArrayList<Integer>>> timetable = new ArrayList<ArrayList<ArrayList<Integer>>>();
  ArrayList<int[]> busstops = new ArrayList<int[]>();
  
  // Get all routes IDs
  int[] routes = BusStopInfo.getRoutes();
  
  // All Bus Stops which are used for building a timetable
  private int[][]mostCommonBusStops = {{804, 805, 806, 807, 808, 809, 811, 813, 814},
                                       {793, 794, 796, 798, 799, 800, 801, 802, 803},
                                       {770, 772, 773, 775, 776, 777, 779, 780},
                                       {781, 782, 783, 784, 785, 786, 787, 788, 789}};
  
  // Creates local copy of the timetable
  public Timetable(GregorianCalendar date)
  { 
    // Loop through all routes
    for(int i = 0; i < NUMBER_OF_ROUTES; i++)
    {
      timetable.add(new ArrayList());
      // Store Bus Stop IDs sequence
      busstops.add(mostCommonBusStops[i]);
      int numberOfServices = TimetableInfo.getNumberOfServices(routes[i], date.getTime());
      // Loop through all route services
      for(int j = 0; j < numberOfServices; j++)
      {
        timetable.get(i).add(new ArrayList());
        int[] ServiceTimes = TimetableInfo.getServiceTimes(routes[i],date.getTime(),j);
        int k = 0;
        // Loop through all timing points
        while(k < ServiceTimes.length && k < mostCommonBusStops[i].length)
        {
          // Store time on the bus stop
          timetable.get(i).get(j).add(ServiceTimes[k]);
          k++;
        }        
      }      
    }    
  }
  
  // Find route index in the timetable, return -1 if route doesn't exist
  private int findRouteIndex (int route)
  {
    int routeIndex = -1;
    
    for (int i = 0; i < routes.length; i++)
    {
      if (route == routes[i])
      {
        routeIndex = i;
      }
    }
    return routeIndex;
  }
  
  // Find bus stop index in the timetable, return -1 if bus stop doesn't exist
  public int findBusStopIndex(int route, int busStop)
  {
    int[] routeBusStops = busstops.get(findRouteIndex(route));
    int busStopIndex = -1;
    for (int i = 0; i < routeBusStops.length; i++)
    {
      if (routeBusStops[i] == busStop)
      {
        busStopIndex = i;
      }
    }
    return busStopIndex;
  }
  
  // Returns timetable as 3d array list [route][service][times]
  public ArrayList<ArrayList<ArrayList<Integer>>> getTimetable()
  {
    return timetable;
  }
  
  // Returns 2d ArrayList of all services times on all bus stops for a given route
  public ArrayList<ArrayList<Integer>> getRouteServicesTimes(int route)
  {
    return timetable.get(findRouteIndex(route));
  }
  
  // Returns all Bus stops IDs for a given route
  public int[] getBusStops(int route)
  {
    return busstops.get(findRouteIndex(route));   
  }
}
