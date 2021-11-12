package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

// line 10 "model.ump"
// line 94 "model.ump"
@Entity
public class Room {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	public enum RoomType {
		Study, Event
	}

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Room Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roomId;
	private int capacity;
	private boolean isAvailable;
	private RoomType roomType;

	// Room Associations

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	protected Room() {
	}

	public Room(int aCapacity, boolean aIsAvailable, RoomType aRoomType) {
		capacity = aCapacity;
		isAvailable = aIsAvailable;
		roomType = aRoomType;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setCapacity(int aCapacity) {
		boolean wasSet = false;
		capacity = aCapacity;
		wasSet = true;
		return wasSet;
	}

	public boolean setIsAvailable(boolean aIsAvailable) {
		boolean wasSet = false;
		isAvailable = aIsAvailable;
		wasSet = true;
		return wasSet;
	}

	public boolean setRoomType(RoomType aRoomType) {
		boolean wasSet = false;
		roomType = aRoomType;
		wasSet = true;
		return wasSet;
	}

	public int getRoomId() {
		return roomId;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public RoomType getRoomType() {
		return roomType;
	}
	/* Code from template association_GetOne */

	/* Code from template association_SetOneToMany */

	public String toString() {
		return super.toString() + "[" + "roomId" + ":" + getRoomId() + "," + "capacity" + ":" + getCapacity() + ","
				+ "isAvailable" + ":" + getIsAvailable() + "]" + System.getProperties().getProperty("line.separator")
				+ "  " + "roomType" + "="
				+ (getRoomType() != null
						? !getRoomType().equals(this) ? getRoomType().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator");
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;

	}
}