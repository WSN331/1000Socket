package com.lab.sockettest.socket.send;

import com.lab.sockettest.console.util.BytesUtil;

import shit.socket.pack.Send;

public class SelectVersionResponse extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698977595014511293L;
	
	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	protected byte[] body() {
		byte[] b = new byte[16];
		BytesUtil.addBytes(b, 0, BytesUtil.stringToBytes(version));
		return b;
	}

	@Send
	@Override
	public byte[] send() {
		return super.send();
	}
	
	

}
