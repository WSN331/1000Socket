package com.lab.sockettest.socket.send;

import com.lab.sockettest.console.util.BytesUtil;

import shit.socket.pack.Send;

public class UpdateVersionResponse extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698977595014511293L;
	
	private Integer result;

	private Integer size;
	
	private Integer count;

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
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
		byte[] b = new byte[7];
		BytesUtil.addBytes(b, 0, BytesUtil.intToBytes(result, 1));
		BytesUtil.addBytes(b, 1, BytesUtil.intToBytes(size, 4));
		BytesUtil.addBytes(b, 5, BytesUtil.intToBytes(count, 2));
		return b;
	}

	@Send
	@Override
	public byte[] send() {
		return super.send();
	}
	
	

}
