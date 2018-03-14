package com.lab.sockettest.socket.send;

import shit.socket.pack.Send;

public class SwitchStateRequest extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8459956542854403870L;
	

	public SwitchStateRequest() {
		super();
		funcCode = new byte[] {01, 06};
	}

	@Override
	protected byte[] body() {
		return new byte[] {};
	}

	@Override
	@Send
	public byte[] send() {
		return super.send();
	}

}
