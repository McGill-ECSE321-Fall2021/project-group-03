package ca.mcgill.ecse321.librarymanagement.dto;


public class RoomReservationDto {
	private RoomDto room;
	private ClientDto client;

	public RoomReservationDto(RoomDto room, ClientDto client) {
		this.room = room;
		this.client = client;
	}
	
	public RoomDto getRoom() {
		return room;
	}

	public ClientDto getClient() {
		return client;
	}


}
