package com.lab.sockettest.socket.send;

import com.lab.sockettest.console.util.BytesUtil;

import shit.socket.pack.Send;

public class ServerAccordUpdateResquest extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698977595014511293L;
	
	private Integer type;
	
	private String version;
	
	private Integer size;
	
	private Integer count;
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	protected byte[] body() {
		byte[] b = new byte[23];
		BytesUtil.addBytes(b, 0,  BytesUtil.intToBytes(type, 1));
		BytesUtil.addBytes(b, 1,  BytesUtil.stringToBytes(version));
		BytesUtil.addBytes(b, 17,  BytesUtil.intToBytes(size, 4));
		BytesUtil.addBytes(b, 21, BytesUtil.intToBytes(count, 2));
		return b;
	}

	@Send
	@Override
	public byte[] send() {
		return super.send();
	}
	
	

}
