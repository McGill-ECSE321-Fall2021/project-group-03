package ca.mcgill.ecse321.librarymanagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;
import java.sql.Time;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

// line 73 "model.ump"
// line 136 "model.ump"

@Entity
@DiscriminatorValue(value = "Room_Reservation")
public class RoomReservation extends Timeslot
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RoomReservation Associations
	
  @ManyToOne(targetEntity = Room.class)
  private Room room;
  
  @ManyToOne(targetEntity = Client.class)
  private Client client;
  

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected RoomReservation() {}

  public RoomReservation(Time aStartTime, Time aEndTime, Date aDate, Room aRoom, Client aClient)
  {
    super(aStartTime, aEndTime, aDate);
    if (!setRoom(aRoom))
    {
      throw new RuntimeException("Unable to create RoomReservation due to aRoom. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setClient(aClient))
    {
      throw new RuntimeException("Unable to create RoomReservation due to aClient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }
  /* Code from template association_GetOne */
  public Client getClient()
  {
    return client;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setRoom(Room aNewRoom)
  {
    boolean wasSet = false;
    if (aNewRoom != null)
    {
      room = aNewRoom;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setClient(Client aNewClient)
  {
    boolean wasSet = false;
    if (aNewClient != null)
    {
      client = aNewClient;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    room = null;
    client = null;
    super.delete();
  }

}