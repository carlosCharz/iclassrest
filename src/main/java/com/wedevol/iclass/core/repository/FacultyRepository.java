package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Faculty;

/**
 * Faculty Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface FacultyRepository extends CrudRepository<Faculty, Long> {

	/**
	 * Return the faculty having the passed faculty name or null if no faculty is found.
	 * 
	 * @param facultyName
	 * @return faculty
	 */
	public Faculty findByName(String name);
	
	/**
	 * Return the faculties of a university
	 * 
	 * @param universityId
	 * @return list of faculties
	 */
	@Query("SELECT fac FROM Faculty fac WHERE fac.universityId = :universityId")
	public List<Faculty> findFacultiesWithUniversityId(@Param("universityId") Long universityId);


}
