package com.wedevol.iclass.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wedevol.iclass.core.entity.AccessToken;

/**
 * Access Token Repository
 * 
 * @author charz
 *
 */
@Repository
@Transactional
public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

	/**
	 * Return the access token having the passed userId and userType or null if no access token is found.
	 * 
	 * @param userId
	 * @param userType
	 * @return accessToken
	 */
	public AccessToken findByUserIdAndUserType(Long userId, String userType);

	/**
	 * Return the access token having the passed token or null if no access token is found.
	 * 
	 * @param token
	 * @return accessToken
	 */
	public AccessToken findByToken(String token);

}
