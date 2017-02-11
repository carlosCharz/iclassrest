package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.CourseSuggestion;
import com.wedevol.iclass.core.view.response.CourseSuggFull;

/**
 * Course Suggestion Service Interface
 * 
 * @author charz
 *
 */
public interface CourseSuggestionService {

	List<CourseSuggFull> findAllFullInfo();

	CourseSuggestion findById(Long courseSuggestionId);

	CourseSuggestion create(CourseSuggestion courseSuggestion);

	void update(Long courseSuggestionId, CourseSuggestion courseSuggestion);

	void delete(Long courseSuggestionId);

}
