package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.Admin;

/**
 * Admin Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface AdminRepository extends CrudRepository<Admin, Long> {

	/**
	 * Return the admin having the passed email or null if no admin is found.
	 * 
	 * @param email
	 * @return admin
	 */
	public Admin findByEmail(String email);

}
