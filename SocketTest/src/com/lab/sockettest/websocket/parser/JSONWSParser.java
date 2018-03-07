package com.lab.sockettest.websocket.parser;

import java.util.HashMap;
import java.util.Map;

import com.lab.sockettest.websocket.WSParser;
import com.lab.sockettest.websocket.WSRequest;
import com.lab.sockettest.websocket.request.LoginWSRequest;

import net.sf.json.JSONObject;

/**
 * 统一前端和后台的请求格式 json
 * {
 * 		method: ''
 * 		body: {
 * 
 * 		}
 * }
 * @author GongTengPangYi
 *
 */
public class JSONWSParser implements WSParser {
	
	public static Map<String, Class<? extends WSRequest>> requestClassMap = new HashMap<>();
	
	static {
		requestClassMap.put("login", LoginWSRequest.class);
	}

	@Override
	public WSRequest parse(String message) {
		JSONObject jobj = JSONObject.fromObject(message);
		Class<? extends WSRequest> clazz = requestClassMap.get(jobj.get("method"));
		JSONObject body = jobj.getJSONObject("body");
		return (WSRequest) JSONObject.toBean(body, clazz);
	}
	

}
