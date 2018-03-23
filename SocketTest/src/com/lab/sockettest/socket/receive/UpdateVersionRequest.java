package com.lab.sockettest.socket.receive;

import com.lab.sockettest.console.util.BytesUtil;
import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Version;
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
		version = BytesUtil.bytesToString(BytesUtil.subBytes(body, 1, 16));
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		
		UpdateVersionResponse response = new UpdateVersionResponse();
		response.setId(id);
		//TODO
		Version version = BizFactory.getVersionBiz().findLastVersion();
		if (version != null) {			
			response.setResult(1);
			response.setSize(version.getSize());;
			response.setCount(version.getCount());
		} else {
			response.setResult(0);
		}
		socketClient.sendPack(response);

	}

}
