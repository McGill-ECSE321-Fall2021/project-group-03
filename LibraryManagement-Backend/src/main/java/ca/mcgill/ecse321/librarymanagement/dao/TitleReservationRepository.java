package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.TitleReservation;

public interface TitleReservationRepository extends CrudRepository<TitleReservation, Integer>{
	
	TitleReservation findTitleReservationByTitleReservationId(int titleReservationId);

}
