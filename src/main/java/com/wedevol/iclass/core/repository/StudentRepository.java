package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Student;

/**
 * Student Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {

	/**
	 * Return the student having the passed email or null if no student is found.
	 * 
	 * @param email
	 * @return student
	 */
	public Student findByEmail(String email);

}
