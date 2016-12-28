package com.wedevol.iclass.core.util;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;

/**
 * Common Util Class
 * 
 * @author charz
 * 
 */
public class CommonUtil {

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static String hashSHA256(String element) {
		return Hashing.sha256().hashString("your input", StandardCharsets.UTF_8).toString();
	}

	public static String toJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

}
