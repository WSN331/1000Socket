package com.lab.sockettest.socket.send;

import shit.socket.pack.Send;

public class HeartResponse extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3698977595014511293L;

	@Override
	protected byte[] body() {
		byte[] b = new byte[0];
		return b;
	}

	@Send
	@Override
	public byte[] send() {
		return super.send();
	}
	
	

}
