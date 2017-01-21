package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.entity.Instructor;
import com.wedevol.iclass.core.entity.Student;

/**
 * Auth Service Interface
 * 
 * @author charz
 *
 */
public interface AuthService {

	Student loginStudent(String email, String password);

	Instructor loginInstructor(String email, String password);

}
