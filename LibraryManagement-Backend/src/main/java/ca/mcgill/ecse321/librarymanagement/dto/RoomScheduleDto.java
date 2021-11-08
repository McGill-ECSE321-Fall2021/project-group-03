package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.TimeSlot;

public class RoomScheduleDto {
	private int scheduleId;
	private List<Room> rooms;
	private List<TimeSlot> timeSlots;
	
	public RoomScheduleDto() {
		
	}
	
	public List<Room> getRoomSchedule(){
		return this.rooms;
	}
	
	public List<TimeSlot> getTimeSlots() {
		return this.timeSlots;
	}
	
	public int getScheduleId() {
		return this.scheduleId;
	}
}
