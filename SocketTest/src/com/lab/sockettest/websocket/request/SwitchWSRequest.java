package com.lab.sockettest.websocket.request;

import javax.websocket.Session;

import com.lab.sockettest.socket.SocketStarterListener;
import com.lab.sockettest.socket.send.SwitchControlRequest;

//{"method":"switch","body":{"sessionKey":"20180314120008771", "deviceId": "dlkz000111111111", "switch1": 1, "switch2": 2}}
public class SwitchWSRequest extends BaseWSRequest {
	
	private String sessionKey;
	
	private String deviceId;
	
	private Integer switch1;
	
	private Integer switch2;
	
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
	
	public Integer getSwitch1() {
		return switch1;
	}

	public void setSwitch1(Integer switch1) {
		this.switch1 = switch1;
	}

	public Integer getSwitch2() {
		return switch2;
	}

	public void setSwitch2(Integer switch2) {
		this.switch2 = switch2;
	}

	@Override
	public void action(Session session) {
		SwitchControlRequest request = new SwitchControlRequest();
		request.setSwitch1(switch1);
		request.setSwitch2(switch2);
		request.setSwitch3(0);
		request.setSwitch4(0);
		SocketStarterListener.getTerminalServer().sendPack(deviceId, request);
	}

}
