/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;


import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;

import java.sql.Date;

// line 55 "model.ump"
// line 149 "model.ump"

@Entity
public class TimeSlot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TimeSlot Attributes
  private Time startTime;
  private Time endTime;
  private int dayOfWeek;
  private Date date;
  private Librarian librarian = new Librarian();
  private Library library = new Library();
  private Room room = new Room();
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int timeSlotId;

  //TimeSlot Associations
  @ManyToOne(targetEntity = Schedule.class)
  private Schedule schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected TimeSlot() {}

  public TimeSlot(Time aStartTime, Time aEndTime, int aDayOfWeek, Date aDate, Schedule aSchedule)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    dayOfWeek = aDayOfWeek;
    date = aDate;
    //timeSlotId = aTimeSlotId;
    boolean didAddSchedule = setSchedule(aSchedule);
    if (!didAddSchedule)
    {
      throw new RuntimeException("Unable to create timeSlot due to schedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    
    if (aSchedule instanceof StaffSchedule) {
    	room = null;
    	library = null;
    }
    
    else if (aSchedule instanceof RoomSchedule) {
    	library= null;
    	librarian = null;
    }
    
    else if (aSchedule instanceof LibrarySchedule) {
    	room = null;
    	librarian = null;
    }
    
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
  
  public void setLibrarian(Librarian librarian) {
	  if (librarian != null) {
		  this.librarian = librarian;
	  }
  }
  public void setLibrary(Library library) {
	  if (library != null) {
		  this.library = library;
	  }
  }
  public void setRoom(Room room) {
	  if (room != null) {
		  this.room = room;
	  }
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDayOfWeek(int aDayOfWeek)
  {
    boolean wasSet = false;
    dayOfWeek = aDayOfWeek;
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

//  public boolean setTimeSlotId(int aTimeSlotId)
//  {
//    boolean wasSet = false;
//    timeSlotId = aTimeSlotId;
//    wasSet = true;
//    return wasSet;
//  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public int getDayOfWeek()
  {
    return dayOfWeek;
  }

  public Date getDate()
  {
    return date;
  }

  public int getTimeSlotId()
  {
    return timeSlotId;
  }
  /* Code from template association_GetOne */
  public Schedule getSchedule()
  {
    return schedule;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSchedule(Schedule aSchedule)
  {
    boolean wasSet = false;
    if (aSchedule == null)
    {
      return wasSet;
    }

    Schedule existingSchedule = schedule;
    schedule = aSchedule;
    if (existingSchedule != null && !existingSchedule.equals(aSchedule))
    {
      existingSchedule.removeTimeSlot(this);
    }
    schedule.addTimeSlot(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Schedule placeholderSchedule = schedule;
    this.schedule = null;
    if(placeholderSchedule != null)
    {
      placeholderSchedule.removeTimeSlot(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "dayOfWeek" + ":" + getDayOfWeek()+ "," +
            "timeSlotId" + ":" + getTimeSlotId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "schedule = "+(getSchedule()!=null?Integer.toHexString(System.identityHashCode(getSchedule())):"null");
  }
}