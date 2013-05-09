/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import database.BusStopInfo;
import database.TimetableInfo;
import database.TimetableInfo.timetableKind;
import database.database;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anthony
 */
public class UpdateSimulationTest {
    
    public UpdateSimulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        database.openBusDatabase();
        System.out.println("\n ==== UpdateSimulation class test ==== \n");
    }
    
    
    @Before
    public void setUp() {
        System.out.println(" ------------------------------------");
    }
    
    @After
    public void tearDown() {
        System.out.println(" Method Done Testing");
    }

    //test whether the route 65 visits the correct bustops
     @Test
    public void initialiseArrayListForRoute65(){
        System.out.println(" initialiseArrayListForRoute when the route is 65 ");
        int route = 65;
        GregorianCalendar date = new GregorianCalendar(2013, 5, 7, 0, 0, 0);

        ArrayList<Simulation> ex = UpdateSimulation.initialiseArrayListForRoute(route, date);
          
        // 3 stops are not visted stop ID 771,774,778
        assertEquals(770, ex.get(0).getBusStopID());
        assertEquals(772, ex.get(1).getBusStopID());
        assertEquals(773, ex.get(2).getBusStopID());
        assertEquals(775, ex.get(3).getBusStopID());
        assertEquals(776, ex.get(4).getBusStopID());
        assertEquals(777, ex.get(5).getBusStopID());
        assertEquals(779, ex.get(6).getBusStopID());
        assertEquals(780, ex.get(7).getBusStopID());
    }
    
    //test whether the route 66 visits the correct bustops
    @Test
    public void testInitialiseArrayListForRoute66() {
        System.out.println(" initialiseArrayListForRoute when the route is 66 ");
        int route = 66;
        GregorianCalendar date = new GregorianCalendar(2013, 5, 7, 0, 0, 0);
        ArrayList<Simulation> ex = UpdateSimulation.initialiseArrayListForRoute(route, date);
        
        //all stops should be visited on the route
        assertEquals(781, ex.get(0).getBusStopID());
        assertEquals(782, ex.get(1).getBusStopID());
        assertEquals(783, ex.get(2).getBusStopID());
        assertEquals(784, ex.get(3).getBusStopID());
        assertEquals(785, ex.get(4).getBusStopID());
        assertEquals(786, ex.get(5).getBusStopID());
        assertEquals(787, ex.get(6).getBusStopID());
        assertEquals(788, ex.get(7).getBusStopID());
        assertEquals(789, ex.get(8).getBusStopID());
    }
   
    //test whether the route 67 visits the correct bustops
    @Test
    public void initialiseArrayListForRoute67(){
        System.out.println(" initialiseArrayListForRoute when the route is 67 ");
        int route = 67;
        GregorianCalendar date = new GregorianCalendar(2013, 5, 7, 0, 0, 0);
        ArrayList<Simulation> ex = UpdateSimulation.initialiseArrayListForRoute(route, date);
        
        //ignore printers arm, so the third stop should be ID 796 instead of 795
        assertEquals(793, ex.get(0).getBusStopID());
        assertEquals(794, ex.get(1).getBusStopID());
        assertEquals(796, ex.get(2).getBusStopID());
        assertEquals(797, ex.get(3).getBusStopID());
        assertEquals(798, ex.get(4).getBusStopID());
        assertEquals(799, ex.get(5).getBusStopID());
        assertEquals(800, ex.get(6).getBusStopID());
        assertEquals(801, ex.get(7).getBusStopID());
        assertEquals(802, ex.get(8).getBusStopID());
    }
    
    //test whether the route 68 visits the correct bustops
   /* @Test
    public void initialiseArrayListForRoute68(){
        System.out.println(" initialiseArrayListForRoute when the route is 68 ");
        int route = 68;
        GregorianCalendar date = new GregorianCalendar(2013, 5, 7, 0, 0, 0);
        ArrayList<Simulation> ex = UpdateSimulation.initialiseArrayListForRoute(route, date);
      
        assertEquals(804, ex.get(0).getBusStopID());
        assertEquals(805, ex.get(1).getBusStopID());
        assertEquals(806, ex.get(2).getBusStopID());
        assertEquals(807, ex.get(3).getBusStopID());
        assertEquals(808, ex.get(4).getBusStopID());
        assertEquals(809, ex.get(5).getBusStopID());
    }*/
    
    @Test
    public void setCancel(){
        System.out.println(" setCancel ");
        int route = 66;
        GregorianCalendar date = new GregorianCalendar(2013, 5, 7, 0, 0, 0);
        Date newDate = new Date((long)date.getTimeInMillis());
        ArrayList<Simulation> ex = UpdateSimulation.initialiseArrayListForRoute(route, date);
        
        UpdateSimulation.setCancel(ex,1,route,TimetableInfo.timetableKind(newDate));
        System.out.println(ex.get(2).getMessage());
        
    }
    
}
