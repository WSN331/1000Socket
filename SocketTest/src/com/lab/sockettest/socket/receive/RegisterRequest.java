package com.lab.sockettest.socket.receive;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lab.sockettest.console.util.BytesUtil;
import com.lab.sockettest.socket.send.RegisterResponse;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class RegisterRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4883924853871043008L;
	
	private String deviceId;
	
	private Integer deviceType;
	
	private String deviceVersion;
	
	private String createTime;
	
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

	@Override
	public void setBody(byte[] body) {
		byte[] id = BytesUtil.subBytes(body, 0, 16);
		byte[] type = BytesUtil.subBytes(body, 16, 2);
		byte[] version = BytesUtil.subBytes(body, 18, 16);
		byte[] time = BytesUtil.subBytes(body, 34, 14);
		
		setDeviceId(BytesUtil.bytesToString(id));
		setDeviceType(BytesUtil.bytesToInt(type));
		setDeviceVersion(BytesUtil.bytesToString(version));
		setCreateTime(BytesUtil.bytesToString(time));
		
	}

	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		socketClient.register(deviceId);
		RegisterResponse response = new RegisterResponse();
		response.setId(id);
		response.setFuncCode(new byte[]{(byte)01, (byte)01});
		response.setResult(1);
		SimpleDateFormat format = new SimpleDateFormat("yyyymmddhhmmss");
		response.setTime(format.format(new Date()));
		socketClient.sendPack(response);
	}
	
}
