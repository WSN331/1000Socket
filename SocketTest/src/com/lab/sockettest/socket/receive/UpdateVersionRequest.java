package com.lab.sockettest.socket.receive;

import com.lab.sockettest.console.util.BytesUtil;
import com.lab.sockettest.socket.send.UpdateVersionResponse;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class UpdateVersionRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;
	
	private Integer type;
	
	private String version;

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

	@Override
	public void setBody(byte[] body) {
		type = BytesUtil.bytesToInt(BytesUtil.subBytes(body, 0, 1));
		version = BytesUtil.bytesToString(BytesUtil.subBytes(body, 1, 17));
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		
		UpdateVersionResponse response = new UpdateVersionResponse();
		response.setId(id);
		response.setFuncCode(new byte[]{(byte)01, (byte)02});
		//TODO
		response.setResult(1);
		response.setSize(16);;
		response.setCount(4);
		socketClient.sendPack(response);

	}

}
