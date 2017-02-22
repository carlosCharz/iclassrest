package com.wedevol.iclass.core.service;

import com.wedevol.iclass.core.util.DefaultInterface;
import com.wedevol.iclass.core.view.response.AdminFull;
import com.wedevol.iclass.core.view.response.InstructorFull;
import com.wedevol.iclass.core.view.response.StudentFull;

/**
 * Auth Service Interface
 * 
 * @author charz
 *
 */
public interface AuthService extends DefaultInterface {

	StudentFull loginStudent(String email, String password);

	InstructorFull loginInstructor(String email, String password);
	
	AdminFull loginAdmin(String email, String password);
	
	void refreshFCMToken(String fcmToken, String deviceId);

}
