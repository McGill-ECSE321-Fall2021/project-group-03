package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Timeslot;

public interface TimeslotRepository extends CrudRepository<Timeslot, Integer>{
	
	public Timeslot findTimeslotByTimeslotId(int timeslotId);

}
