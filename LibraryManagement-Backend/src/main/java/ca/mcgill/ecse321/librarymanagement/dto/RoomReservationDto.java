package ca.mcgill.ecse321.librarymanagement.dto;


public class RoomReservationDto {
	private RoomDto room;
	private ClientDto client;
	private int roomReservationID;

	public RoomReservationDto(RoomDto room, ClientDto client, int roomReservationID) {
		this.room = room;
		this.client = client;
		this.roomReservationID = roomReservationID;
	}
	
	public RoomDto getRoom() {
		return room;
	}

	public ClientDto getClient() {
		return client;
	}


}
