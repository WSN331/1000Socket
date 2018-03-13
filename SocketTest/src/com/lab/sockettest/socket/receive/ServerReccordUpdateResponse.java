package com.lab.sockettest.socket.receive;

import com.lab.sockettest.console.util.BytesUtil;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class ServerReccordUpdateResponse extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;
	
	private Integer result;
	
	
	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public void setBody(byte[] body) {
		result = BytesUtil.bytesToInt(BytesUtil.subBytes(body, 0, 1));
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		

	}

}
