package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

	Schedule findScheduleByScheduleId(int scheduleId);

}
