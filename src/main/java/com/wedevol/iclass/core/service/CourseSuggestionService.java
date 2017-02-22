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
public interface CourseSuggestionService extends BaseService<CourseSuggestion> {

	List<CourseSuggFull> findAllFullInfo();

}
