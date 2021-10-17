package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.TimeSlot;


public interface TimeSlotRepository extends CrudRepository<Book, String>{
	
	TimeSlot findBookByTimeSlot(String titleID);

}
