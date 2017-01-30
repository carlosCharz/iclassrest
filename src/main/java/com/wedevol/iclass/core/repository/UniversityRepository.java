package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.University;

/**
 * University Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface UniversityRepository extends CrudRepository<University, Long> {

	/**
	 * Return the university having the passed university name or null if no university is found.
	 * 
	 * @param universityName
	 * @return university
	 */
	public University findByName(String name);

	/**
	 * Return all the universities order by name asc
	 * 
	 * @return university list
	 */
	public List<University> findAllByOrderByNameAsc();

}
