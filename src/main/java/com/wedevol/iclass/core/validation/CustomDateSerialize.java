package com.wedevol.iclass.core.validation;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Custom Date Serialize
 *
 * @author charz
 */
public class CustomDateSerialize extends JsonSerializer<Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		if (value == null) {
			gen.writeNull();
		} else {
			gen.writeString(dateFormat.format(value.getTime()));
		}
	}
}
