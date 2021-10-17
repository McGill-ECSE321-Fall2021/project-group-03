package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;

public interface RoomScheduleRepository extends CrudRepository<RoomSchedule, Integer>{
	
	RoomSchedule findRoomScheduleByScheduleId(int roomScheduleId);
	
}