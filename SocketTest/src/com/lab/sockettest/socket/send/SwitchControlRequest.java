package com.lab.sockettest.socket.send;

import shit.socket.pack.Send;

public class SwitchControlRequest extends BaseSendPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8459956542854403870L;
	
	private Integer switch1;
	private Integer switch2;
	private Integer switch3;
	private Integer switch4;
	
	public Integer getSwitch1() {
		return switch1;
	}

	public void setSwitch1(Integer switch1) {
		this.switch1 = switch1;
	}

	public Integer getSwitch2() {
		return switch2;
	}

	public void setSwitch2(Integer switch2) {
		this.switch2 = switch2;
	}

	public Integer getSwitch3() {
		return switch3;
	}

	public void setSwitch3(Integer switch3) {
		this.switch3 = switch3;
	}

	public Integer getSwitch4() {
		return switch4;
	}

	public void setSwitch4(Integer switch4) {
		this.switch4 = switch4;
	}

	@Override
	protected byte[] body() {
		byte[] bytes = new byte[4];
		bytes[0] = (byte)switch1.intValue();
		bytes[1] = (byte)switch2.intValue();
		bytes[2] = (byte)switch3.intValue();
		bytes[3] = (byte)switch4.intValue();
		return bytes;
	}

	@Override
	@Send
	public byte[] send() {
		return super.send();
	}

}
