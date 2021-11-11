package ca.mcgill.ecse321.librarymanagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/

/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

// line 2 "model.ump"
// line 84 "model.ump"
@Entity
public class Library {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Library Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int libraryId;

	// Library Associations

//  @OneToOne(targetEntity = Schedule.class)
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule librarySchedule;

	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Title> titles;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Room> rooms;

	@OneToMany(cascade = CascadeType.ALL)
	private List<RoomReservation> roomReservations;

	@OneToMany(cascade = CascadeType.ALL)
	private List<TitleReservation> titleReservations;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Library() {
		librarySchedule = new Schedule();
		users = new ArrayList<User>();
		titles = new ArrayList<Title>();
		rooms = new ArrayList<Room>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	public boolean addTitleReservation(TitleReservation aTitleReservation) {
		titleReservations.add(aTitleReservation);
		return true;
	}

	public List<TitleReservation> getTitleReservations() {
		return titleReservations;
	}
	
	public void removeTitleReservation(TitleReservation aTitleReservation) {
		titleReservations.remove(aTitleReservation);
	}
	
	public void setLibrarySchedule(Schedule librarySchedule) {
		this.librarySchedule = librarySchedule;
	}

	public boolean setLibraryId(int aLibraryId) {
		boolean wasSet = false;
		libraryId = aLibraryId;
		wasSet = true;
		return wasSet;
	}

	public int getLibraryId() {
		return libraryId;
	}

	/* Code from template association_GetOne */
	public Schedule getLibrarySchedule() {
		return librarySchedule;
	}

	public boolean hasLibrarySchedule() {
		boolean has = librarySchedule != null;
		return has;
	}

	/* Code from template association_GetMany */
	public User getUser(int index) {
		User aUser = users.get(index);
		return aUser;
	}

	public List<User> getUsers() {
		List<User> newUsers = Collections.unmodifiableList(users);
		return newUsers;
	}

	public int numberOfUsers() {
		int number = users.size();
		return number;
	}

	public boolean hasUsers() {
		boolean has = users.size() > 0;
		return has;
	}

	public int indexOfUser(User aUser) {
		int index = users.indexOf(aUser);
		return index;
	}

	/* Code from template association_GetMany */
	public Title getTitle(int index) {
		Title aTitle = titles.get(index);
		return aTitle;
	}

	public List<Title> getTitles() {
		List<Title> newTitles = Collections.unmodifiableList(titles);
		return newTitles;
	}

	public int numberOfTitles() {
		int number = titles.size();
		return number;
	}

	public boolean hasTitles() {
		boolean has = titles.size() > 0;
		return has;
	}

	public int indexOfTitle(Title aTitle) {
		int index = titles.indexOf(aTitle);
		return index;
	}

	/* Code from template association_GetMany */
	public Room getRoom(int index) {
		Room aRoom = rooms.get(index);
		return aRoom;
	}

	public List<Room> getRooms() {
		List<Room> newRooms = Collections.unmodifiableList(rooms);
		return newRooms;
	}

	public int numberOfRooms() {
		int number = rooms.size();
		return number;
	}

	public boolean hasRooms() {
		boolean has = rooms.size() > 0;
		return has;
	}

	public int indexOfRoom(Room aRoom) {
		int index = rooms.indexOf(aRoom);
		return index;
	}


	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfUsers() {
		return 0;
	}
	/* Code from template association_AddManyToOne */

	public boolean addUser(User aUser) {
		boolean wasAdded = false;
		if (users.contains(aUser)) {
			return false;
		} else {
			users.add(aUser);
			return true;
		}
		
	}

	public boolean removeUser(User aUser) {
		if (users.contains(aUser)) {
			users.remove(aUser);
			return true;
		} else {
			return false;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addUserAt(User aUser, int index) {
		boolean wasAdded = false;
		if (addUser(aUser)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(aUser);
			users.add(index, aUser);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveUserAt(User aUser, int index) {
		boolean wasAdded = false;
		if (users.contains(aUser)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(aUser);
			users.add(index, aUser);
			wasAdded = true;
		} else {
			wasAdded = addUserAt(aUser, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfTitles() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Title addTitle(String aName, int aTitleId, String aDescription, String aGenre, boolean aIsAvailable,
			Title.TitleType aTitleType) {
		return new Title(aName, aDescription, aGenre, aIsAvailable, aTitleType);
	}

	public boolean addTitle(Title aTitle) {
		boolean wasAdded = false;
		if (titles.contains(aTitle)) {
			return false;
		} else {
			titles.add(aTitle);
			return true;
		}
	}

	public boolean removeTitle(Title aTitle) {
		if (titles.contains(aTitle)) {
			titles.remove(aTitle);
			return true;
		} else {
			return false;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addTitleAt(Title aTitle, int index) {
		boolean wasAdded = false;
		if (addTitle(aTitle)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfTitles()) {
				index = numberOfTitles() - 1;
			}
			titles.remove(aTitle);
			titles.add(index, aTitle);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveTitleAt(Title aTitle, int index) {
		boolean wasAdded = false;
		if (titles.contains(aTitle)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfTitles()) {
				index = numberOfTitles() - 1;
			}
			titles.remove(aTitle);
			titles.add(index, aTitle);
			wasAdded = true;
		} else {
			wasAdded = addTitleAt(aTitle, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfRooms() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Room addRoom(int aRoomId, int aCapacity, boolean aIsAvailable, Room.RoomType aRoomType) {
		return new Room(aCapacity, aIsAvailable, aRoomType);
	}

	public boolean addRoom(Room aRoom) {
		boolean wasAdded = false;
		if (rooms.contains(aRoom)) {
			return false;
		} else {
			rooms.add(aRoom);
			wasAdded = true;
			return wasAdded;
		}
	
	}

	public boolean removeRoom(Room aRoom) {
		boolean wasRemoved = false;
		if (rooms.contains(aRoom)) {
			rooms.remove(aRoom);
			return true;
		} else {
			return false;
		}
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addRoomAt(Room aRoom, int index) {
		boolean wasAdded = false;
		if (addRoom(aRoom)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRooms()) {
				index = numberOfRooms() - 1;
			}
			rooms.remove(aRoom);
			rooms.add(index, aRoom);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveRoomAt(Room aRoom, int index) {
		boolean wasAdded = false;
		if (rooms.contains(aRoom)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRooms()) {
				index = numberOfRooms() - 1;
			}
			rooms.remove(aRoom);
			rooms.add(index, aRoom);
			wasAdded = true;
		} else {
			wasAdded = addRoomAt(aRoom, index);
		}
		return wasAdded;
	}

	public void delete() {
		while (users.size() > 0) {
			User aUser = users.get(users.size() - 1);
			users.remove(aUser);
		}

		while (titles.size() > 0) {
			Title aTitle = titles.get(titles.size() - 1);
			titles.remove(aTitle);
		}

		while (rooms.size() > 0) {
			Room aRoom = rooms.get(rooms.size() - 1);
			rooms.remove(aRoom);
		}
		
		while (roomReservations.size() > 0) {
			RoomReservation aRoomReservation = roomReservations.get(roomReservations.size() - 1);
			roomReservations.remove(aRoomReservation);
		}
		
		while (titleReservations.size() > 0) {
			TitleReservation aTitleReservation = titleReservations.get(titleReservations.size() - 1);
			titleReservations.remove(aTitleReservation);
		}
		
		this.librarySchedule = null;

	}

	public String toString() {
		return super.toString() + "[" + "libraryId" + ":" + getLibraryId() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "librarySchedule = "
				+ (getLibrarySchedule() != null ? Integer.toHexString(System.identityHashCode(getLibrarySchedule()))
						: "null");
	}

	public void addRoomReservation(RoomReservation roomReservation) {
		roomReservations.add(roomReservation);
	}

	public List<RoomReservation> getRoomReservations() {
		return roomReservations;
	}
}