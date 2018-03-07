package com.example.test;


import shit.socket.ShitSocketClient;
import shit.socket.pack.Receive;
import shit.socket.pack.ReceiveAction;

@Receive(objValue = "test2")
public class TestReceiveBytesPack {

	
	private byte[] param1;
	
	public byte[] getParam1() {
		return param1;
	}

	public void setParam1(byte[] param1) {
		this.param1 = param1;
	}

	@ReceiveAction
	public void receive(ShitSocketClient<?> socketClient) {
		System.out.println(this);
		for (byte b : this.param1) {
			System.out.println(b);
		}
		System.out.println(this.param1.length);
		
	}
}
