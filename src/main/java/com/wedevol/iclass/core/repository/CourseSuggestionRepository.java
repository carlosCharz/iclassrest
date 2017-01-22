package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.CourseSuggestion;

/**
 * Course Suggestion Repository
 * 
 * @author Charz++
 *
 */
@Repository
@Transactional
public interface CourseSuggestionRepository extends CrudRepository<CourseSuggestion, Long> {

}
