package RosterGenerator;


import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
  ROaster Object. An Object containing combination information of drivers, 
  buses, route, and shift.  
*/
public class Roaster
{
  int roasterID; //In case we are going to generate many roaster. Rarely 
  int month;
  int year;
  GregorianCalendar dateFrom;
  GregorianCalendar dateTo;

  ArrayList<ArrayList<Shift>> shift;
  
  //Constructor. RoasterID is being generated by itself.
  public Roaster(int m, int y, ArrayList<ArrayList<Shift>> s, GregorianCalendar dF, GregorianCalendar dT)
  {
    month = m;
    year = y;
    shift = s;
    dateFrom = dF;
    dateTo = dT;
  }//Constructor
  
}//Roaster Object
