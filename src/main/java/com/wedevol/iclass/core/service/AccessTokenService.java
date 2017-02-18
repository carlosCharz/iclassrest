package com.wedevol.iclass.core.service;

import java.util.List;

import com.wedevol.iclass.core.entity.AccessToken;
import com.wedevol.iclass.core.entity.enums.UserType;

/**
 * Access Token Service Interface
 * 
 * @author charz
 *
 */
public interface AccessTokenService extends BaseService<AccessToken> {

	List<AccessToken> findAll();

	AccessToken findByToken(String token);

	AccessToken findByUserIdAndUserType(Long userId, String userType);

	String refreshAccessToken(Long userId, UserType userType);

}
