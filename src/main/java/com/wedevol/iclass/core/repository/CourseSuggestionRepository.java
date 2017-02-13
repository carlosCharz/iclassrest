package com.wedevol.iclass.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.CourseSuggestion;
import com.wedevol.iclass.core.view.response.CourseSuggFull;

/**
 * Course Suggestion Repository
 * 
 * @author Charz++
 *
 */
@Repository
@Transactional
public interface CourseSuggestionRepository extends CrudRepository<CourseSuggestion, Long> {

	/**
	 * Return the course suggestion with full info
	 * 
	 * @return list of suggested courses
	 */
	@Query("SELECT new com.wedevol.iclass.core.view.response.CourseSuggFull(cou.id, cou.userType, cou.userId, cou.name, cou.description, fac.id, fac.shortName, uni.id, uni.shortName, cou.requestedAt, cou.status) "
			+ "FROM CourseSuggestion cou, Faculty fac, University uni "
			+ "WHERE fac.id = cou.facultyId AND uni.id=cou.universityId")
	public List<CourseSuggFull> findAllFullInfo();

}
