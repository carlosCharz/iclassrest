package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.view.response.AdminView;

/**
 * Admin Service Interface
 * 
 * @author charz
 *
 */
public interface AdminService {

	List<Admin> findAll();

	Admin findByEmail(String email);

	Admin findById(Long userId);

	Admin create(Admin admin);

	void update(Long userId, Admin admin);

	void delete(Long userId);
	
	AdminView getAdminByIdWithFullInfo(Long userId);
	
	Admin getAdminByEmail(String email);

}
