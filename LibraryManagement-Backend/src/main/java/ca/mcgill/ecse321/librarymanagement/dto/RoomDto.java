package ca.mcgill.ecse321.librarymanagement.dto;

import ca.mcgill.ecse321.librarymanagement.model.Room.RoomType;

public class RoomDto {
	private int roomId;
	private int capacity;
	private boolean isAvailable;
	private RoomType roomType;

	public RoomDto(int roomId, int capacity, boolean isAvailable, RoomType roomType) {
		this.roomId = roomId;
		this.capacity = capacity;
		this.isAvailable = isAvailable;
		this.roomType = roomType;
	}

	public int getRoomId() {
		return roomId;
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public RoomType getRoomType() {
		return roomType;
	}

}
