package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeslotDto {

	private Time startTime;
	private Time endTime;
	private Date date;
	private int timeslotId;

	public TimeslotDto(Time startTime, Time endTime, Date date, int timeslotId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
		this.timeslotId = timeslotId;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Date getDate() {
		return date;
	}

	public int getTimeslotId() {
		return timeslotId;
	}

}
