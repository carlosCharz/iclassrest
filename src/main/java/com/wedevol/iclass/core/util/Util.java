package com.wedevol.iclass.core.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

/**
 * Util Class
 * 
 * @author charz
 * 
 */
public class Util {

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	public static String hashSHA256(String element) {
		return Hashing.sha256()
						.hashString("your input", StandardCharsets.UTF_8)
						.toString();
	}

}
