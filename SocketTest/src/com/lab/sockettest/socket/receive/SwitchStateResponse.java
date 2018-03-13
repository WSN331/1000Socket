package com.lab.sockettest.socket.receive;

import com.lab.sockettest.console.util.BizUtil;
import com.lab.sockettest.model.bean.Device;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class SwitchStateResponse extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3139249574149389057L;
	
	private Integer switch1;
	private Integer switch2;
	private Integer switch3;
	private Integer switch4;
	
	
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

	public Integer getSwitch3() {
		return switch3;
	}

	public void setSwitch3(Integer switch3) {
		this.switch3 = switch3;
	}

	public Integer getSwitch4() {
		return switch4;
	}

	public void setSwitch4(Integer switch4) {
		this.switch4 = switch4;
	}

	@Override
	public void setBody(byte[] body) {
		switch1 = (int) body[0];
		switch2 = (int) body[1];
		switch3 = (int) body[2];
		switch4 = (int) body[3];
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		Device device = BizUtil.getDeviceBiz().findByDeviceId(socketClient.getKey());
		if (device != null) {
			device.setSwitch1(switch1);
			device.setSwitch2(switch2);
			BizUtil.getDeviceBiz().update(device);
		}
	}

}
