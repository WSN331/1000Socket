package com.lab.sockettest.websocket.response;

import com.lab.sockettest.websocket.WSResponse;

import net.sf.json.JSONObject;

public class BaseWSResponse implements WSResponse {

	@Override
	public String response() {
		return JSONObject.fromObject(this).toString();
	}

}
