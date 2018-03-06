package com.lab.sockettest.receive;

import java.util.Date;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class RegisterPack extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4883924853871043008L;
	
	private String deviceId;
	
	private Integer deviceType;
	
	private String deviceVersion;
	
	private Date createTime;
	
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

	@Override
	public void setBody(byte[] body) {
		System.out.println("body.length = " + body.length);
		System.out.print("body:");
		for (byte b : body) {
			System.out.print(b);
			System.out.print(" ");
		}
	}

	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {

	}
	
}
