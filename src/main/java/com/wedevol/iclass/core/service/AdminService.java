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
public interface AdminService extends BaseService<Admin> {

	List<Admin> findAll();

	Admin findByEmail(String email);

	Admin findByDeviceId(String deviceId);

	AdminFull getAdminByIdWithFullInfo(Long userId);

	Admin getAdminByEmail(String email);

}
