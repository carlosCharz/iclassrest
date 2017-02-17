package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.AccessToken;

/**
 * Access Token Service Interface
 * 
 * @author charz
 *
 */
public interface AccessTokenService extends BaseService<AccessToken> {

	List<AccessToken> findAll();

	AccessToken findByToken(String token);

	AccessToken findByTokenAndUserType(String token, String userType);

}
