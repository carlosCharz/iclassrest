package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.CourseSuggestion;

/**
 * Course Suggestion Service Interface
 * 
 * @author charz
 *
 */
public interface CourseSuggestionService {

	List<CourseSuggestion> findAll();

	CourseSuggestion findById(Long courseSuggestionId);

	void create(CourseSuggestion courseSuggestion);

	void update(Long courseSuggestionId, CourseSuggestion courseSuggestion);

	void delete(Long courseSuggestionId);

}
