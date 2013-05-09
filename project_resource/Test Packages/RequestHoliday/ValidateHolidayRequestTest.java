/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestHoliday;

import java.util.GregorianCalendar;
import org.joda.time.DateTime;
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
public class ValidateHolidayRequestTest {
    
    public ValidateHolidayRequestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("==== Junit testing ValidateHolidayRequest class ====\n");
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        System.out.println("-------------------------------------------------");
    }

    /**
     * Test of validateRequest method, of class ValidateHolidayRequest.
     */
    /*@Test
    public void testValidateRequest() {
        System.out.println("validateRequest");
        GregorianCalendar dateFrom = new GregorianCalendar(2001,9,21,0,0,0);
        GregorianCalendar dateTo = new GregorianCalendar(2001,9,21,0,0,0);
        int driverID = 2014;
        boolean expResult = true;
        boolean result = ValidateHolidayRequest.validateRequest(dateFrom, dateTo, driverID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }*/

    /**
     * Test of checkDateInterval method, of class ValidateHolidayRequest.
     */
   /* @Test
    public void testCheckDateInterval() {
        System.out.println("checkDateInterval");
        GregorianCalendar dateFrom = null;
        GregorianCalendar dateTo = null;
        int driverID = 0;
        boolean expResult = false;
        boolean result = ValidateHolidayRequest.checkDateInterval(dateFrom, dateTo, driverID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of dateAvailable method, of class ValidateHolidayRequest.
     */
   /* @Test
    public void testDateAvailable() {
        System.out.println("dateAvailable");
        GregorianCalendar givenDate = null;
        int driverID = 0;
        int expResult = 0;
        int result = ValidateHolidayRequest.dateAvailable(givenDate, driverID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of validateHolidayLength method, of class ValidateHolidayRequest.
     */
    @Test
    public void testValidateHolidayLength() {
        System.out.println("validateHolidayLength");
        GregorianCalendar dateFrom = new GregorianCalendar(2001,9,25,0,0,0);
        GregorianCalendar dateTo = new GregorianCalendar(2001,9,21,0,0,0);
        int driverID = 2014;
        boolean overTheYear = false;
        boolean expResult = false;
        boolean result = ValidateHolidayRequest.validateHolidayLength(dateFrom, dateTo, driverID, overTheYear);
        assertEquals(expResult, result);
        
    }

    
    /**
     * Test of calculateInterval method, of class ValidateHolidayRequest.
     */
   @Test
    public void testCalculateInterval() {
        System.out.println("validateRequest");
        GregorianCalendar dateFrom = new GregorianCalendar(2001,9,21,0,0,0);
        GregorianCalendar dateTo = new GregorianCalendar(2001,9,21,0,0,0);
       
        GregorianCalendar dateFrom1 = new GregorianCalendar(2001,9,21,0,0,0);
        GregorianCalendar dateTo1 = new GregorianCalendar(2001,9,25,0,0,0);
        //int driverID = 2014;
        int expResult = 1;
        int result = ValidateHolidayRequest.calculateInterval(dateFrom, dateTo);
        assertEquals(expResult, result);
        
        int result1 = ValidateHolidayRequest.calculateInterval(dateFrom1, dateTo1);
        int expResult1 = 5;
        assertEquals(expResult1, result1);
    }
}
