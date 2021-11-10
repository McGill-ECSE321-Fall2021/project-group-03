package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.ScheduleDto;

public interface ScheduleRepository extends CrudRepository<ScheduleDto, Integer>{
	
	ScheduleDto findScheduleByScheduleId(int scheduleId);

}
