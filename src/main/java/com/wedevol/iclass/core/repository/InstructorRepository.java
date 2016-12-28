package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Instructor;

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
	 * Return the instructor having the passed email or null if no instructor is found.
	 * 
	 * @param email
	 * @return instructor
	 */
	public Instructor findByEmail(String email);

}
