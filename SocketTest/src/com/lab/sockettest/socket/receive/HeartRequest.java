package com.lab.sockettest.socket.receive;

import java.util.Date;

import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Device;
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
		Device device = BizFactory.getDeviceBiz().findByDeviceId(socketClient.getKey());
		if (device != null) {
			device.setLastHeartTime(new Date());
			BizFactory.getDeviceBiz().update(device);
		}
		HeartResponse response = new HeartResponse();
		response.setId(id);
		response.setFuncCode(new byte[]{(byte)01, (byte)02});
		socketClient.sendPack(response);

	}

}
