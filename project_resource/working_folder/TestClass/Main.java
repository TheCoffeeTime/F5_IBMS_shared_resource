package TestClass;

/*
 * A very simple application illustrating how to use the interface.
 * Prints the names of all the drivers in the database.
 * @author John Sargeant
 */
import database.database;
import java.util.Date;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Connect to the database
        database.openBusDatabase();
        //-------------------------------------------------------------------
        //Body
        
        //Get all the id of drivers in the database
        //int[] driverIDs = DriverInfo.getDrivers();
        
        //For each of those ID, get the name and print them
        //for (int i=0; i<driverIDs.length; i++)
            //System.out.println(DriverInfo.getName(driverIDs[i]));
        
        //Date date1 = new Date(2011, 12, 31);
        //Date date2 = new Date(2011, 12, 31);
     
        //int daysBetween = Dates.calculateInterval(date1, date2);
        System.out.println();     
        
        //-------------------------------------------------------------------
    }
    
}
