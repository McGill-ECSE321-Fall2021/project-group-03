package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 10 "model.ump"
// line 94 "model.ump"
@Entity
public class Room
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum RoomType { Study, Event }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Room Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int roomId;
  private int capacity;
  private boolean isAvailable;
  private RoomType roomType;

  //Room Associations
  @ManyToOne(targetEntity = Library.class)
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Room() {}

  public Room(int aCapacity, boolean aIsAvailable, RoomType aRoomType)
  {
    capacity = aCapacity;
    isAvailable = aIsAvailable;
    roomType = aRoomType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCapacity(int aCapacity)
  {
    boolean wasSet = false;
    capacity = aCapacity;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setRoomType(RoomType aRoomType)
  {
    boolean wasSet = false;
    roomType = aRoomType;
    wasSet = true;
    return wasSet;
  }

  public int getRoomId()
  {
    return roomId;
  }

  public int getCapacity()
  {
    return capacity;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public RoomType getRoomType()
  {
    return roomType;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeRoom(this);
    }
    library.addRoom(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
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
            "roomId" + ":" + getRoomId()+ "," +
            "capacity" + ":" + getCapacity()+ "," +
            "isAvailable" + ":" + getIsAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "roomType" + "=" + (getRoomType() != null ? !getRoomType().equals(this)  ? getRoomType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}