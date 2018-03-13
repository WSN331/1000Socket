package com.lab.sockettest.socket.receive;

import java.util.Date;

import com.lab.sockettest.console.util.BizUtil;
import com.lab.sockettest.model.bean.Device;
import com.lab.sockettest.socket.send.HeartResponse;

import shit.socket.core.StandardBytesSocketClient;
import shit.socket.pack.ReceiveAction;

public class DoUpdateRequest extends BaseReceivePack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5187135794013927003L;

	@Override
	public void setBody(byte[] body) {
		// 这个到底特么的是干嘛的woc
	}
	
	@ReceiveAction
	public void receiveAction(StandardBytesSocketClient socketClient) {
		

	}

}
