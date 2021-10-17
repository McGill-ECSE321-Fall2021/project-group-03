package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;


public interface StaffScheduleRepository extends CrudRepository<StaffSchedule, Integer>{
	
	StaffSchedule findStaffScheduleByScheduleId(int staffScheduleId);

}