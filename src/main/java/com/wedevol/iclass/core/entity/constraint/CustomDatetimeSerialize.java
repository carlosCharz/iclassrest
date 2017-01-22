package com.wedevol.iclass.core.entity.constraint;

import static com.wedevol.iclass.core.util.CommonUtil.dateToString;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wedevol.iclass.core.util.CommonUtil;

/**
 * Custom Datetime Serialize
 *
 * @author charz
 */
public class CustomDatetimeSerialize extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeNull();
		} else {
			gen.writeString(dateToString(value, CommonUtil.DATETIME_FORMAT));
		}
	}
}
