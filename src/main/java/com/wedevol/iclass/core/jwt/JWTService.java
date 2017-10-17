package com.wedevol.iclass.core.jwt;

public interface JWTService {
	
	String signToken(String userId);
	
	String verifyTokenBasic(String token);
	
	boolean isTokenValid(String token, String userId);

}
