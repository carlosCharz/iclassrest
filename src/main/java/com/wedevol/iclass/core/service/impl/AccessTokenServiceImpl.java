package com.wedevol.iclass.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wedevol.iclass.core.entity.AccessToken;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.ResourceNotFoundException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.enums.NotFoundErrorType;
import com.wedevol.iclass.core.repository.AccessTokenRepository;
import com.wedevol.iclass.core.service.AccessTokenService;

/**
 * Access Token Service Implementation
 * 
 * @author charz
 *
 */
@Transactional
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

	protected static final Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

	@Autowired
	private AccessTokenRepository accessTokenRepository;

	@Override
	public List<AccessToken> findAll() {
		final Iterable<AccessToken> tokensIterator = accessTokenRepository.findAll();
		return Lists.newArrayList(tokensIterator);
	}

	@Override
	public AccessToken findById(Long accessTokenId) {
		final Optional<AccessToken> tokenObj = Optional.ofNullable(accessTokenRepository.findOne(accessTokenId));
		return tokenObj.orElseThrow(() -> new ResourceNotFoundException(NotFoundErrorType.ACCESS_TOKEN_NOT_FOUND));
	}

	@Override
	public AccessToken create(AccessToken accessToken) {
		// Fields missing validation
		if (accessToken.getUserType() == null || accessToken.getUserId() == null || accessToken.getToken() == null) {
			throw new BadRequestException(BadRequestErrorType.FIELDS_MISSING);
		}
		// The access token should not exist
		final Optional<AccessToken> tokenObj = Optional.ofNullable(this.findByTokenAndUserType(accessToken.getToken(),
				accessToken.getUserType()));
		if (tokenObj.isPresent()) {
			throw new BadRequestException(BadRequestErrorType.ACCESS_TOKEN_ALREADY_EXISTS);
		}
		// Save
		return accessTokenRepository.save(accessToken);
	}

	@Override
	public void update(Long accessTokenId, AccessToken accessToken) {
		// The access token should exist
		AccessToken existingToken = this.findById(accessTokenId);
		if (!isNullOrEmpty(accessToken.getUserType())) {
			existingToken.setUserType(accessToken.getUserType());
		}
		// TODO: validate the existing userType and userId
		if (accessToken.getUserId() != null) {
			existingToken.setUserId(accessToken.getUserId());
		}
		if (accessToken.getToken() != null) {
			existingToken.setToken(accessToken.getToken());
		}
		// Update
		accessTokenRepository.save(existingToken);
	}

	@Override
	public void delete(Long accessTokenId) {
		// The access token should exist
		this.findById(accessTokenId);
		accessTokenRepository.delete(accessTokenId);
	}

	@Override
	public AccessToken findByToken(String token) {
		return accessTokenRepository.findByToken(token);
	}

	@Override
	public AccessToken findByTokenAndUserType(String token, String userType) {
		return accessTokenRepository.findByTokenAndUserType(token, userType);
	}

}
