package com.wedevol.iclass.core.entity.constraint;

import static com.wedevol.iclass.core.util.CommonUtil.stringToDate;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.wedevol.iclass.core.exception.BadRequestException;
import com.wedevol.iclass.core.exception.enums.BadRequestErrorType;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Custom Datetime Deserialize
 *
 * @author charz
 */
public class CustomDatetimeDeserialize extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser paramJsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		final String str = paramJsonParser.getText().trim();
		try {
			return stringToDate(str, CommonUtil.DATETIME_FORMAT);
		} catch (ParseException e) {
			throw new BadRequestException(BadRequestErrorType.WRONG_DESERIALIZATION);
		}
	}
}
