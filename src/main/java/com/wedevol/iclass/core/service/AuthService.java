package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.view.response.AdminView;
import com.wedevol.iclass.core.view.response.InstructorView;
import com.wedevol.iclass.core.view.response.StudentView;

/**
 * Auth Service Interface
 * 
 * @author charz
 *
 */
public interface AuthService {

	StudentView loginStudent(String email, String password);

	InstructorView loginInstructor(String email, String password);
	
	AdminView loginAdmin(String email, String password);

}
