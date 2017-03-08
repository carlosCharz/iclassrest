package com.wedevol.iclass.core.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wedevol.iclass.core.annotation.Authorize;
import com.wedevol.iclass.core.entity.AccessToken;
import com.wedevol.iclass.core.service.AccessTokenService;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Access Token Controller (iClass)
 * 
 * @author charz
 *
 */
@RestController
@RequestMapping("/tokens")
public class AccessTokenController {

	protected static final Logger logger = LoggerFactory.getLogger(AccessTokenController.class);

	@Autowired
	private AccessTokenService accessTokenService;

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<AccessToken> findAll() {
		return accessTokenService.findAll();
	}

	@ApiIgnore
	@Authorize(basic = true)
	@RequestMapping(value = "/{accessTokenId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccessToken findById(@PathVariable Long accessTokenId) {
		return accessTokenService.findById(accessTokenId);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AccessToken create(@Valid @RequestBody AccessToken accessToken) {
		return accessTokenService.create(accessToken);
	}

	@Authorize(basic = true)
	@RequestMapping(value = "/{accessTokenId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void update(@PathVariable Long accessTokenId, @Valid @RequestBody AccessToken accessToken) {
		accessTokenService.update(accessTokenId, accessToken);
	}

	@Authorize(basic = true)
	@ApiIgnore
	@RequestMapping(value = "/{accessTokenId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable Long accessTokenId) {
		accessTokenService.delete(accessTokenId);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public AccessToken findAccessTokenByUserIdByUserType(
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "userType", required = true) String userType
			) {
		// TODO: remove this method, this is just a quick access to get the tokenss
		return accessTokenService.findAccessTokenByUserIdByUserType(userId, userType);
	}

}
