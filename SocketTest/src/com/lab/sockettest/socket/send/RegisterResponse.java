package com.lab.sockettest.socket.send;

import com.lab.sockettest.console.util.BytesUtil;

import shit.socket.pack.Send;

public class RegisterResponse extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1846524031296274844L;
	
	public RegisterResponse() {
		super();
		funcCode = new byte[] {01, 01};
	}

	private String time;
	
	/**
	 * 0失败1成功
	 */
	private int result;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	protected byte[] body() {
		byte[] bytes = new byte[15];
		bytes[0] = (byte)result;
		bytes = BytesUtil.addBytes(bytes, 1, BytesUtil.stringToBytes(time));
		return bytes;
	}

	@Override
	@Send
	public byte[] send() {
		return super.send();
	}

	

}
