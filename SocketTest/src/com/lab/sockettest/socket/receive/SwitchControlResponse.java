package com.lab.sockettest.socket.receive;

import com.lab.sockettest.socket.send.SwitchStateRequest;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class SwitchControlResponse extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3139249574149389057L;
	
	private Integer result;

	@Override
	public void setBody(byte[] body) {
		result = (int) body[0];
	
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		if (result == 1) {
			socketClient.sendPack(new SwitchStateRequest());
		}
	}

}
