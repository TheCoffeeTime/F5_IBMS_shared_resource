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
  
  public static final int NUMBER_OF_ROUTES = 4;
  
  ArrayList<ArrayList<ArrayList<Integer>>> timetable = new ArrayList<ArrayList<ArrayList<Integer>>>();
  ArrayList<int[]> busstops = new ArrayList<int[]>();
  
  int[] routes = BusStopInfo.getRoutes();
  
  private int[][]kostilj = {{804, 805, 806, 807, 808, 809, 811, 813, 814},
                            {793, 794, 796, 798, 799, 800, 801, 802, 803},
                            {770, 772, 773, 775, 776, 777, 779, 780},
                            {781, 782, 783, 784, 785, 786, 787, 788, 789}};
  
  // Creates local copy of the timetable
  public Timetable(GregorianCalendar date)
  {   
    for(int i = 0; i < NUMBER_OF_ROUTES; i++)
    {
      timetable.add(new ArrayList());
      busstops.add(kostilj[i]);

      int numberOfServices = TimetableInfo.getNumberOfServices(routes[i], date.getTime());
      
      for(int j = 0; j < numberOfServices; j++)
      {
        timetable.get(i).add(new ArrayList());
        int[] ServiceTimes = TimetableInfo.getServiceTimes(routes[i],date.getTime(),j);

        int k = 0;
        while(k < ServiceTimes.length && k < kostilj[i].length)
        {
          timetable.get(i).get(j).add(ServiceTimes[k]);
          k++;
        }        
      }      
    }    
  }
  
  private int findRouteIndex (int route)
  {
    int routeIndex = -1;
    // Find route index in the timetable
    for (int i = 0; i < routes.length; i++)
    {
      if (route == routes[i])
      {
        routeIndex = i;
      }
    }
    return routeIndex;
  }
  
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
  
  public ArrayList<ArrayList<Integer>> getRouteServicesTimes(int route)
  {
    return timetable.get(findRouteIndex(route));
  }
  
  public int[] getBusStops(int route)
  {
    return busstops.get(findRouteIndex(route));   
  }
}
