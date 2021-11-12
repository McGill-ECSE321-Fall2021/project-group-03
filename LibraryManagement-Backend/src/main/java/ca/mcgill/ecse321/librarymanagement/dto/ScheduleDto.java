package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

public class ScheduleDto {

	private int scheduleId;
	private List<TimeslotDto> timeslots;

	public ScheduleDto(int scheduleId, List<TimeslotDto> timeslots) {

		this.scheduleId = scheduleId;
		this.timeslots = timeslots;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public List<TimeslotDto> getTimeslots() {
		return timeslots;
	}

}
