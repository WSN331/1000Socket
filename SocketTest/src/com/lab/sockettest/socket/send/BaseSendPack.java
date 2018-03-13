package com.lab.sockettest.socket.send;

import java.io.Serializable;

import com.lab.sockettest.console.util.BytesUtil;

public abstract class BaseSendPack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8786810860313765010L;

	protected byte[] id;
	
	protected byte[] funcCode;	
	
	public byte[] getId() {
		return id;
	}

	public void setId(byte[] id) {
		this.id = id;
	}

	public byte[] getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(byte[] funcCode) {
		this.funcCode = funcCode;
	}

	public byte[] send() {
		byte[] body = body();
		byte[] result = new byte[body.length + 16];
		result[0] = (byte)72;
		result[1] = (byte)72;
		result[2] = (byte)74;
		result[3] = (byte)74;
		
		BytesUtil.addBytes(result, 4, funcCode);
		BytesUtil.addBytes(result, 6, id);
		BytesUtil.addBytes(result, 10, BytesUtil.intToBytes(body.length, 2));

		BytesUtil.addBytes(result, 12, body());
		
		result[result.length-1] = (byte)74;
		result[result.length-2] = (byte)74;
		result[result.length-3] = (byte)72;
		result[result.length-4] = (byte)72;
		
		printResult(result);
		
		return result;
	}
	
	private void printResult(byte[] bs) {
		System.out.print("send: ");
		for (byte b : bs) {
			System.out.print(b);
			System.out.print(" ");
		}
		System.out.println("");
	}
	
	/**
	 * 包体
	 * @return
	 */
	protected abstract byte[] body();
	

}
