package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer>{
	
	Room findBookByRoomId(int roomId);

}