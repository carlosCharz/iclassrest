package com.wedevol.iclass.core.service.impl;

import static com.wedevol.iclass.core.util.CommonUtil.hashSHA256;
import static com.wedevol.iclass.core.util.CommonUtil.isNullOrEmpty;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.Admin;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.AdminRepository;
import com.wedevol.iclass.core.service.AdminService;

/**
 * Admin Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class AdminServiceImpl implements AdminService {

	protected static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public List<Admin> findAll() {
		final Iterable<Admin> adminsIterator = adminRepository.findAll();
		return Lists.newArrayList(adminsIterator);
	}

	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public Admin findById(Long userId) {
		final Optional<Admin> adminObj = Optional.ofNullable(adminRepository.findOne(userId));
		return adminObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.ADMIN_NOT_FOUND));
	}

	@Override
	public Admin create(Admin admin) {
		// Fields missing validation
		if (admin.getFirstName() == null || admin.getLastName() == null || admin.getPassword() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The admin should not exist
		final Optional<Admin> adminObj = Optional.ofNullable(findByEmail(admin.getEmail()));
		if (adminObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.USER_ALREADY_EXISTS);
		}
		admin.setPassword(hashSHA256(admin.getPassword()));
		admin.setActive(true);
		return adminRepository.save(admin);
	}

	@Override
	public void update(Long userId, Admin admin) {
		// The admin should exist
		Admin existingAdmin = findById(userId);
		if (!isNullOrEmpty(admin.getFirstName())) {
			existingAdmin.setFirstName(admin.getFirstName());
		}
		if (!isNullOrEmpty(admin.getLastName())) {
			existingAdmin.setLastName(admin.getLastName());
		}
		if (!isNullOrEmpty(admin.getPassword())) {
			existingAdmin.setPassword(hashSHA256(admin.getPassword()));
		}
		if (!isNullOrEmpty(admin.getUniversity())) {
			existingAdmin.setUniversity(admin.getUniversity());
		}
		if (!isNullOrEmpty(admin.getFcmToken())) {
			existingAdmin.setFcmToken(admin.getFcmToken());
		}
		// Save
		adminRepository.save(existingAdmin);
	}

	@Override
	public void delete(Long userId) {
		// The admin should exist
		findById(userId);
		adminRepository.delete(userId);
	}

}