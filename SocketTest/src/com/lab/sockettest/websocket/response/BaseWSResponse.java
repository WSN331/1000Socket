package com.lab.sockettest.websocket.response;

import com.lab.sockettest.websocket.WSResponse;

import net.sf.json.JSONObject;

public class BaseWSResponse implements WSResponse {

	private String method;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public BaseWSResponse(String method) {
		super();
		this.method = method;
	}

	@Override
	public String response() {
		return JSONObject.fromObject(this).toString();
	}

}
