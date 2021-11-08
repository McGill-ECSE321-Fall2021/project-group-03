package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.TimeSlot;

public class LibraryScheduleDto {
	private int scheduleId;
	private List<TimeSlot> timeSlots;
	
	public LibraryScheduleDto(int scheduleId, List<TimeSlot> timeSlots) {
		this.scheduleId = scheduleId;
		this.timeSlots = timeSlots;
	}
	
	public int getScheduleId() {
		return this.scheduleId;
	}
	
	public List<TimeSlot> getTimeSlots() {
		return this.timeSlots;
	}
	

}
