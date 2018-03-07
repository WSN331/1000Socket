package com.lab.sockettest.socket.receive;

import java.io.Serializable;

/**
 * 基本接收包
 * @author GongTengPangYi
 *
 */
public abstract class BaseReceivePack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1174103219030808152L;

	/**
	 * 流水号， 第6-9位
	 */
	protected byte[] id;
	
	/**
	 * 包体长度， 第10-11位
	 */
	protected int length;

	public byte[] getId() {
		return id;
	}

	public void setId(byte[] id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public abstract void setBody(byte[] body);
}
