package com.wedevol.iclass.core.util;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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

	public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_PRETTY = "dd/MM/yyyy";
	public static final String DATE_FORMAT_QUERY_DB = "yyyyMMdd";
	public static final SimpleDateFormat dateFormatPretty = new SimpleDateFormat(DATE_FORMAT_PRETTY);
	public static final long HOUR = 3600*1000; // in milli-seconds

	public static String hashSHA256(String element) {
		return Hashing.sha256().hashString(element, StandardCharsets.UTF_8).toString();
	}

	public static String toJsonString(Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

	public static String dateToString(Date date) {
		return dateFormatPretty.format(date.getTime());
	}

	public static String dateToString(Date date, String dateFormatStr) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.format(date.getTime());
	}

	public static Date stringToDate(String dateStr) throws ParseException {
		return dateFormatPretty.parse(dateStr);
	}
	
	public static Date stringToDate(String dateStr, String dateFormatStr) throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.parse(dateStr);
	}

	public static boolean isNullOrEmpty(String element) {
		// TODO: replace with the base functional interface
		return element == null || element.isEmpty();
	}
	
	public static LocalDateTime now(){
		return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
	}

}
