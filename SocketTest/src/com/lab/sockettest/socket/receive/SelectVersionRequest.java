package com.lab.sockettest.socket.receive;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Version;
import com.lab.sockettest.socket.send.SelectVersionResponse;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class SelectVersionRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;

	@Override
	public void setBody(byte[] body) {
		
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		
		SelectVersionResponse response = new SelectVersionResponse();
		response.setId(id);
		//TODO
		Version version = BizFactory.getVersionBiz().findLastVersion();
		if (version != null) {	
			response.setVersion(version.getVersion());
		}
		socketClient.sendPack(response);

	}

}
