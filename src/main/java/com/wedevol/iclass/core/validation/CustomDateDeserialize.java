package com.wedevol.iclass.core.validation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wedevol.iclass.core.enums.BadRequestErrorType;
import com.wedevol.iclass.core.exception.BadRequestException;

/**
 * Custom Date Deserialize
 *
 * @author charz
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		final String str = paramJsonParser.getText()
									.trim();
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			throw new BadRequestException(BadRequestErrorType.WRONG_DESERIALIZATION);
		}
	}
}
