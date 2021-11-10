package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.RoomReservation;

public interface RoomReservationRepository extends CrudRepository<RoomReservation, Integer>{
	
	public RoomReservation findRoomReservationByTimeslotId(int timeslotId);

}
