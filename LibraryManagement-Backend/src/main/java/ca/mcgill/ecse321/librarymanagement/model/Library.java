/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.sql.Date;

// line 2 "model.ump"
// line 105 "model.ump"

@Entity
public class Library
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

	//Library Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int libraryId;

  //Library Associations
  @OneToMany(targetEntity = Room.class, mappedBy = "library")
  private List<Room> rooms;
  
  @OneToMany(targetEntity = Title.class)
  private List<Title> titles;
  
  @OneToOne(targetEntity = LibrarySchedule.class)
  private LibrarySchedule librarySchedule;
  
  @OneToMany(targetEntity = User.class, mappedBy = "library")
  private List<User> users;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Library() {}

  public Library(LibrarySchedule aLibrarySchedule)
  {
    rooms = new ArrayList<Room>();
    titles = new ArrayList<Title>();
    if (!setLibrarySchedule(aLibrarySchedule))
    {
      throw new RuntimeException("Unable to create Library due to aLibrarySchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    users = new ArrayList<User>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLibraryId(int aLibraryId)
  {
    boolean wasSet = false;
    libraryId = aLibraryId;
    wasSet = true;
    return wasSet;
  }

  public int getLibraryId()
  {
    return libraryId;
  }
  /* Code from template association_GetMany */
  public Room getRoom(int index)
  {
    Room aRoom = rooms.get(index);
    return aRoom;
  }

  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(Room aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_GetMany */
  public Title getTitle(int index)
  {
    Title aTitle = titles.get(index);
    return aTitle;
  }

  public List<Title> getTitles()
  {
    List<Title> newTitles = Collections.unmodifiableList(titles);
    return newTitles;
  }

  public int numberOfTitles()
  {
    int number = titles.size();
    return number;
  }

  public boolean hasTitles()
  {
    boolean has = titles.size() > 0;
    return has;
  }

  public int indexOfTitle(Title aTitle)
  {
    int index = titles.indexOf(aTitle);
    return index;
  }
  /* Code from template association_GetOne */
  public LibrarySchedule getLibrarySchedule()
  {
    return librarySchedule;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRoomsValid()
  {
    boolean isValid = numberOfRooms() >= minimumNumberOfRooms() && numberOfRooms() <= maximumNumberOfRooms();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfRooms()
  {
    return 4;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 4;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRooms()
  {
    return 4;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Room addRoom(int aRoomId, RoomSchedule aRoomSchedule)
  {
    if (numberOfRooms() >= maximumNumberOfRooms())
    {
      return null;
    }
    else
    {
      return new Room(aRoomSchedule, this);
    }
  }

  public boolean addRoom(Room aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    if (numberOfRooms() >= maximumNumberOfRooms())
    {
      return wasAdded;
    }

    Library existingLibrary = aRoom.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);

    if (isNewLibrary && existingLibrary.numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasAdded;
    }

    if (isNewLibrary)
    {
      aRoom.setLibrary(this);
    }
    else
    {
      rooms.add(aRoom);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoom(Room aRoom)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoom, as it must always have a library
    if (this.equals(aRoom.getLibrary()))
    {
      return wasRemoved;
    }

    //library already at minimum (4)
    if (numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasRemoved;
    }
    rooms.remove(aRoom);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTitles()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addTitle(Title aTitle)
  {
    boolean wasAdded = false;
    if (titles.contains(aTitle)) { return false; }
    titles.add(aTitle);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTitle(Title aTitle)
  {
    boolean wasRemoved = false;
    if (titles.contains(aTitle))
    {
      titles.remove(aTitle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTitleAt(Title aTitle, int index)
  {  
    boolean wasAdded = false;
    if(addTitle(aTitle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTitles()) { index = numberOfTitles() - 1; }
      titles.remove(aTitle);
      titles.add(index, aTitle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTitleAt(Title aTitle, int index)
  {
    boolean wasAdded = false;
    if(titles.contains(aTitle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTitles()) { index = numberOfTitles() - 1; }
      titles.remove(aTitle);
      titles.add(index, aTitle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTitleAt(aTitle, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setLibrarySchedule(LibrarySchedule aNewLibrarySchedule)
  {
    boolean wasSet = false;
    if (aNewLibrarySchedule != null)
    {
      librarySchedule = aNewLibrarySchedule;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, boolean aIsResident, int aUserId)
  {
    return new User(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aIsResident, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    Library existingLibrary = aUser.getLibrary();
    boolean isNewLibrary = existingLibrary != null && !this.equals(existingLibrary);
    if (isNewLibrary)
    {
      aUser.setLibrary(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a library
    if (!this.equals(aUser.getLibrary()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (rooms.size() > 0)
    {
      Room aRoom = rooms.get(rooms.size() - 1);
      aRoom.delete();
      rooms.remove(aRoom);
    }
    
    titles.clear();
    librarySchedule = null;
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "libraryId" + ":" + getLibraryId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "librarySchedule = "+(getLibrarySchedule()!=null?Integer.toHexString(System.identityHashCode(getLibrarySchedule())):"null");
  }
}