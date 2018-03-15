package com.lab.sockettest.websocket.response;

import com.lab.sockettest.model.bean.Device;

public class DeviceStateChangeResponse extends BaseWSResponse {

	public DeviceStateChangeResponse() {
		super("deviceStateChange");
	}

	private Device device;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
	
	
}
