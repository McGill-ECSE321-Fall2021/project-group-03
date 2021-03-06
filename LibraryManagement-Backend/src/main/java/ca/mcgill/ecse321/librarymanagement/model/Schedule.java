package ca.mcgill.ecse321.librarymanagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// line 53 "model.ump"
// line 125 "model.ump"
@Entity
//@Table(name="pseudoSchedule")
public class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int scheduleId;

  //Schedule Associations
  
  @OneToMany (targetEntity = Timeslot.class)
  private List<Timeslot> timeslots;
  

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public Schedule()
  {
    timeslots = new ArrayList<Timeslot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setScheduleId(int aScheduleId)
  {
    boolean wasSet = false;
    scheduleId = aScheduleId;
    wasSet = true;
    return wasSet;
  }

  public int getScheduleId()
  {
    return scheduleId;
  }
  /* Code from template association_GetMany */
  public Timeslot getTimeslot(int index)
  {
    Timeslot aTimeslot = timeslots.get(index);
    return aTimeslot;
  }

  public List<Timeslot> getTimeslots()
  {
    List<Timeslot> newTimeslots = Collections.unmodifiableList(timeslots);
    return newTimeslots;
  }

  public int numberOfTimeslots()
  {
    int number = timeslots.size();
    return number;
  }

  public boolean hasTimeslots()
  {
    boolean has = timeslots.size() > 0;
    return has;
  }

  public int indexOfTimeslot(Timeslot aTimeslot)
  {
    int index = timeslots.indexOf(aTimeslot);
    return index;
  }

  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeslots()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTimeslot(Timeslot aTimeslot)
  {
    boolean wasAdded = false;
    if (timeslots.contains(aTimeslot)) { return false; }
    timeslots.add(aTimeslot);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeslot(Timeslot aTimeslot)
  {
    boolean wasRemoved = false;
    if (timeslots.contains(aTimeslot))
    {
      timeslots.remove(aTimeslot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeslotAt(Timeslot aTimeslot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeslot(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslots()) { index = numberOfTimeslots() - 1; }
      timeslots.remove(aTimeslot);
      timeslots.add(index, aTimeslot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeslotAt(Timeslot aTimeslot, int index)
  {
    boolean wasAdded = false;
    if(timeslots.contains(aTimeslot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeslots()) { index = numberOfTimeslots() - 1; }
      timeslots.remove(aTimeslot);
      timeslots.add(index, aTimeslot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeslotAt(aTimeslot, index);
    }
    return wasAdded;
  }




  public String toString()
  {
    return super.toString() + "["+
            "scheduleID" + ":" + getScheduleId()+ "]" + System.getProperties().getProperty("line.separator");
  }
}