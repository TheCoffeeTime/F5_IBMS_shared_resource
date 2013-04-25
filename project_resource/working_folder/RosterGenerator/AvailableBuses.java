package RosterGenerator;


import database.BusInfo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nikita
 */
public class AvailableBuses {
    boolean availableBuses[];
    int busesID[];
    
    public AvailableBuses()
    {       
        busesID = BusInfo.getBuses();
        availableBuses = new boolean[busesID.length];
        for (int i = 0; i < busesID.length; i++)
        {
            availableBuses[i] = true;
        }
    }
    
    public boolean[] getBuses()
    {
        return availableBuses;
    }
    
    public void setBusUnavailable(int busIndex)
    {
        availableBuses[busIndex] = false;
    }
    
    public void setBusAvailable(int busIndex)
    {
        availableBuses[busIndex] = true;
    }
    
    public int getBusID(int busIndex)
    {
        return  busesID[busIndex];
    }
    
    public void setAllAvailable()
    {
        for (int i = 0; i < busesID.length; i++)
        {
            availableBuses[i] = true;
        }
    }
}
