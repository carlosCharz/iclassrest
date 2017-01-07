package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.InstructorSchedule;

/**
 * Instructor Schedule Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorScheduleRepository extends CrudRepository<InstructorSchedule, Long> {

	/**
	 * Return the schedules having the passed instructorId or null if no schedules are found.
	 * 
	 * @param instructorId
	 * @return schedules
	 */
	public List<InstructorSchedule> findByInstructorIdOrderByClassDateAscStartTimeAsc(Long instructorId);
}
