package com.wedevol.iclass.core.util;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	public static String hashSHA256(String element) {
		return Hashing.sha256().hashString(element, StandardCharsets.UTF_8).toString();
	}

	public static String toJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

	public static String dateToString(Date date) {
		return CommonUtil.dateFormat.format(date.getTime());
	}

	public static Date stringToDate(String dateStr) throws ParseException {
		return CommonUtil.dateFormat.parse(dateStr);
	}

	public static boolean isNullOrEmpty(String element) {
		return element == null || element.isEmpty();
	}

}
