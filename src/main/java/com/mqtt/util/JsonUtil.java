package com.mqtt.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * JSON工具类
 * @author ch
 *
 */
public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	static{
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
//	
	/**
	 * JSON字符串转换成JavaBean
	 * @param json
	 * @param clz
	 * @return
	 */
	public static <T> T toJavaBean(String json, Class<T> clz){
		if(json == null) return null;
		if(clz == null) return null;
		try {
			json = URLDecoder.decode(json, "UTF-8");
			return (T) mapper.readValue(json, clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isJson(String str){
		try {
			JSONObject.fromObject(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
