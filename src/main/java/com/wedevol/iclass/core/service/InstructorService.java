package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Instructor;

/**
 * Instructor Service Interface
 * 
 * @author charz
 *
 */
public interface InstructorService {

	List<Instructor> findAll();

	Instructor findByEmail(String email);

	Instructor findById(Long userId);

	void create(Instructor instructor);

	void update(Long userId, Instructor instructor);

	void delete(Long userId);

}
