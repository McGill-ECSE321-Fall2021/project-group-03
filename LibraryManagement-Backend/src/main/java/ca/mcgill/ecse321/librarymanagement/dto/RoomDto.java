package ca.mcgill.ecse321.librarymanagement.dto;

import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;

public class RoomDto {

	private RoomSchedule aRoomSchedule;
	private Library aLibrary;
	private int roomId;	//is this necessary??
	
	public RoomDto() {
		
	}
	
	public RoomDto(RoomSchedule aRoomSchedule, Library aLibrary) {
		this.aLibrary = aLibrary;
		this.aRoomSchedule = aRoomSchedule;
		}
	
	public Library getLibrary() {
		return this.aLibrary;
	}
	
	public RoomSchedule getRoomSchedule() {
		return this.aRoomSchedule;
	}
}
