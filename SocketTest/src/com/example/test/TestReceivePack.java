package com.example.test;

import java.util.Date;

import shit.socket.ShitSocketClient;
import shit.socket.pack.Receive;
import shit.socket.pack.ReceiveAction;

@Receive(objValue = "test")
public class TestReceivePack {
	private String name;
	private String password;
	//{"type":"test","name":"xxx","password":"yyy"}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "TestReceivePack [name=" + name + ", password=" + password + "]";
	}

	@ReceiveAction
	public void receive(ShitSocketClient<?> socketClient) {
		socketClient.register("111");
		System.out.println(this);
		TestSendPack sendPack = new TestSendPack();
		sendPack.setId("dsfs");
		sendPack.setTime(new Date());
		socketClient.sendPack(sendPack);
		socketClient.sendPack("111", sendPack);
		
	}
}
