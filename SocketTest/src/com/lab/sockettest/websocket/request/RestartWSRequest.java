package com.lab.sockettest.websocket.request;

import javax.websocket.Session;

import com.lab.sockettest.socket.SocketStarterListener;
import com.lab.sockettest.socket.send.RestartRequest;

//{"method":"restart","body":{"sessionKey":"20180314120008771", "deviceId": "dlkz000111111111"}}
public class RestartWSRequest extends BaseWSRequest {
	
	private String sessionKey;
	
	private String deviceId;
	
	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	

	@Override
	public void action(Session session) {
		RestartRequest request = new RestartRequest();
		SocketStarterListener.getTerminalServer().sendPack(deviceId, request);
	}

}
