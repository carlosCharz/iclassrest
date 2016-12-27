package com.wedevol.iclass.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.InstructorBasic;

/**
 * Instructor Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface InstructorRepository extends CrudRepository<Instructor, Long> {

	/**
	 * Return the instructor having the passed email or null if no instructor is
	 * found.
	 * 
	 * @param email
	 * @return instructor
	 */
	public Instructor findByEmail(String email);

	/**
	 * Return the instructors of a course for an specific date
	 * 
	 * @param courseId
	 * @return list of courses
	 */
	//TODO: finish this method, add the status = available
	@Query("SELECT new com.wedevol.iclass.core.entity.InstructorBasic(ins.id, ins.firstName, ins.lastName, ins.rating, ins.level, 0, 'S/.') FROM Instructor ins")
	public List<InstructorBasic> findInstructorsWithCourseWithDate(@Param("courseId") Long courseId,
			@Param("classDate") Date classDate);

}
