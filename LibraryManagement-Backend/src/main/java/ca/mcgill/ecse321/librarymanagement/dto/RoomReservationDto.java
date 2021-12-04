package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;
import java.sql.Time;

public class RoomReservationDto {
	private RoomDto room;
	private ClientDto client;
	private int roomReservationID;
	private Time startTime;
	private Time endTime;
	private Date date;

	public int getRoomReservationID() {
		return roomReservationID;
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

	public RoomReservationDto(RoomDto room, ClientDto client, int roomReservationID, Time startTime, Time endTime,
			Date date) {
		this.room = room;
		this.client = client;
		this.roomReservationID = roomReservationID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}

	public RoomDto getRoom() {
		return room;
	}

	public ClientDto getClient() {
		return client;
	}

}
