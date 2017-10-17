package com.wedevol.iclass.core.jwt;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * JWT implementation: https://github.com/auth0/java-jwt
 * Generate keys: https://stackoverflow.com/questions/11410770/load-rsa-public-key-from-file/19387517#19387517
 * 
 */
public class JWTServiceImpl implements JWTService {

	private static final Logger log = LoggerFactory.getLogger(JWTServiceImpl.class);
	
	private final static int SECOND_IN_MILLISECONDS = 1000;
	private static String JWT_AUDIENCE;
	private static String JWT_ISSUER;
	private static Integer EXPIRATION_MILLISECONDS;
	private static Algorithm ALGORITHM;
	private static Map<String, Object> HEADER_CLAIMS;
	static
    {
		HEADER_CLAIMS = new HashMap<String, Object>();
		HEADER_CLAIMS.put("alg", "HS256");
		HEADER_CLAIMS.put("typ", "JWT");
    }
	
	public JWTServiceImpl(String audience, String issuer, String secret, Integer expiration, TemporalUnit expirationUnit) throws IllegalArgumentException, UnsupportedEncodingException {
		initParameters(audience, issuer);
		ALGORITHM = Algorithm.HMAC256(secret);
		EXPIRATION_MILLISECONDS = (int) Duration.of(expiration, expirationUnit).get(ChronoUnit.SECONDS) * SECOND_IN_MILLISECONDS;
	}
	
	private void initParameters(String audience, String issuer) {
		JWT_AUDIENCE = audience;
		JWT_ISSUER = issuer;
	}

	@Override
	public String signToken(String userId) {
		String token = null;
		final Date nowDate = new Date();
		final Date expirationDate = new Date(nowDate.getTime() + EXPIRATION_MILLISECONDS);
		try {
		    token = JWT.create()
		    			.withHeader(HEADER_CLAIMS)
		    			.withAudience(JWT_AUDIENCE)
		    			.withIssuer(JWT_ISSUER)
		    			.withIssuedAt(nowDate)
		    			.withExpiresAt(expirationDate)
		    			.withClaim("uid", userId)
		    			.sign(ALGORITHM);
		} catch (JWTCreationException exception){
			log.error("Invalid Signing configuration when signing jwt token. Error: {}", exception.getMessage());
		}
		return token;
	}

	@Override
	public String verifyTokenBasic(String token) {
		if (token == null || token.isEmpty()) {
			return null;
		}
		try {
		    JWTVerifier verifier = JWT.require(ALGORITHM)
	    								.withAudience(JWT_AUDIENCE)
	    								.withIssuer(JWT_ISSUER)
	    								.build();
		    DecodedJWT jwt = verifier.verify(token);
		    final Claim uidClaim = jwt.getClaim("uid");
		    if (!uidClaim.isNull()) {
		    	return uidClaim.asString();
		    }
		} catch (JWTVerificationException exception){
			log.warn("Invalid signature claims when verifying jwt token. Error: {}", exception.getMessage());
		}
		return null;
	}

	@Override
	public boolean isTokenValid(String token, String userId) {
		if (token == null || token.isEmpty() || userId == null || userId.isEmpty()) {
			return false;
		}
		final String uId = verifyTokenBasic(token);
		if (uId != null) {
			return uId.equals(userId);
		} else {
			return false;
		}
	}
	
}
