package ca.mcgill.ecse321.librarymanagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.sql.Time;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

// line 66 "model.ump"
// line 131 "model.ump"

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Timeslot_Type")
@DiscriminatorValue(value = "Timeslot")
public class Timeslot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Timeslot Attributes
  private Time startTime;
  private Time endTime;
  private Date date;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int timeslotId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  protected Timeslot() {}
  
  public Timeslot(Time aStartTime, Time aEndTime, Date aDate)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    date = aDate;
    
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeslotId(int aTimeSlotId)
  {
    boolean wasSet = false;
    timeslotId = aTimeSlotId;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public Date getDate()
  {
    return date;
  }

  public int getTimeSlotId()
  {
    return timeslotId;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "timeSlotId" + ":" + getTimeSlotId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}