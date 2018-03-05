package com.lab.sockettest.receive;

import shit.socket.pack.ReceiveAction;

public class RegisterPack extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4883924853871043008L;

	@Override
	public void setBody(byte[] body) {
		for (byte b : body) {
			System.out.println(b);
		}
	}

	
	@ReceiveAction
	public void receiveAction() {
		
	}
	
}
