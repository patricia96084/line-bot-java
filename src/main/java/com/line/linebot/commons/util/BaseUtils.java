package com.line.linebot.commons.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseUtils {

	public static String object2JsonString(Object obj) {
		try {
			ObjectMapper m = new ObjectMapper();
			return m.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static Object jsonFile2Object(File jsonFile, Class<?> cls) {
		ObjectMapper m = new ObjectMapper();
		Object result = null;
		try {
			result = m.readValue(jsonFile, cls);
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Object jsonString2Object(String str, Class<?> cls) {
		try {
			ObjectMapper m = new ObjectMapper();
			return m.readValue(str, cls);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
