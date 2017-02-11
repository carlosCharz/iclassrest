package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.view.response.AdminFull;

/**
 * Admin Service Interface
 * 
 * @author charz
 *
 */
public interface AdminService {

	List<Admin> findAll();

	Admin findByEmail(String email);
	
	Admin findByDeviceId(String deviceId);

	Admin findById(Long userId);

	Admin create(Admin admin);

	void update(Long userId, Admin admin);

	void delete(Long userId);
	
	AdminFull getAdminByIdWithFullInfo(Long userId);
	
	Admin getAdminByEmail(String email);

}
