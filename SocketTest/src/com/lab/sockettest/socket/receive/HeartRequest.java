package com.lab.sockettest.socket.receive;

import com.lab.sockettest.socket.send.HeartResponse;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class HeartRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;

	@Override
	public void setBody(byte[] body) {
		
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		HeartResponse response = new HeartResponse();
		response.setId(id);
		response.setFuncCode(new byte[]{(byte)01, (byte)02});
		socketClient.sendPack(response);

	}

}
