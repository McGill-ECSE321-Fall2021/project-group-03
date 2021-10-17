/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

// line 11 "model.ump"
// line 113 "model.ump"

@Entity
public class Room
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  @Id
  //Room Attributes
  private int roomId;

  //Room Associations
  @ManyToOne(targetEntity = RoomSchedule.class)

  private RoomSchedule roomSchedule;
  
  @ManyToOne(targetEntity = Library.class)
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Room() {}

  public Room(int aRoomId, RoomSchedule aRoomSchedule, Library aLibrary)
  {
    roomId = aRoomId;
    boolean didAddRoomSchedule = setRoomSchedule(aRoomSchedule);
    if (!didAddRoomSchedule)
    {
      throw new RuntimeException("Unable to create room due to roomSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create room due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRoomId(int aRoomId)
  {
    boolean wasSet = false;
    roomId = aRoomId;
    wasSet = true;
    return wasSet;
  }

  public int getRoomId()
  {
    return roomId;
  }
  /* Code from template association_GetOne */
  public RoomSchedule getRoomSchedule()
  {
    return roomSchedule;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRoomSchedule(RoomSchedule aRoomSchedule)
  {
    boolean wasSet = false;
    if (aRoomSchedule == null)
    {
      return wasSet;
    }

    RoomSchedule existingRoomSchedule = roomSchedule;
    roomSchedule = aRoomSchedule;
    if (existingRoomSchedule != null && !existingRoomSchedule.equals(aRoomSchedule))
    {
      existingRoomSchedule.removeRoom(this);
    }
    roomSchedule.addRoom(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    //Must provide library to room
    if (aLibrary == null)
    {
      return wasSet;
    }

    //library already at maximum (4)
    if (aLibrary.numberOfRooms() >= Library.maximumNumberOfRooms())
    {
      return wasSet;
    }
    
    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      boolean didRemove = existingLibrary.removeRoom(this);
      if (!didRemove)
      {
        library = existingLibrary;
        return wasSet;
      }
    }
    library.addRoom(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    RoomSchedule placeholderRoomSchedule = roomSchedule;
    this.roomSchedule = null;
    if(placeholderRoomSchedule != null)
    {
      placeholderRoomSchedule.removeRoom(this);
    }
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeRoom(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "roomId" + ":" + getRoomId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "roomSchedule = "+(getRoomSchedule()!=null?Integer.toHexString(System.identityHashCode(getRoomSchedule())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}