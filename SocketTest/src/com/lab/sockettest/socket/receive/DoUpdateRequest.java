package com.lab.sockettest.socket.receive;

import com.lab.sockettest.console.common.UpdatePack;
import com.lab.sockettest.console.util.BytesUtil;
import com.lab.sockettest.model.BizFactory;
import com.lab.sockettest.model.bean.Version;
import com.lab.sockettest.socket.send.DoUpdateResponse;
import com.lab.sockettest.socket.send.UpdateVersionResponse;
import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class DoUpdateRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;

	private int packCount;

	private int packIndex;

	@Override
	public void setBody(byte[] body) {
		packCount = BytesUtil.bytesToInt(BytesUtil.subBytes(body, 0, 2));
		packIndex = BytesUtil.bytesToInt(BytesUtil.subBytes(body, 2, 2));
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		DoUpdateResponse response = new DoUpdateResponse();
		response.setId(id);
		Version version = BizFactory.getVersionBiz().findLastVersion();
		if (version != null) {
			UpdatePack updatePack = new UpdatePack(version);
			response.setPackIndex(packIndex);
			response.setPackCount(packCount);
			response.setCRC16(0000);
			response.setPack(updatePack.getOnePack(packIndex));
		} else {

		}
		socketClient.sendPack(response);
	}

}
